package tms.db;

import jakarta.faces.context.FacesContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import oracle.ucp.UniversalConnectionPoolAdapter;
import oracle.ucp.admin.UniversalConnectionPoolManager;
import oracle.ucp.admin.UniversalConnectionPoolManagerImpl;
import oracle.ucp.jdbc.PoolDataSource;
import oracle.ucp.jdbc.PoolDataSourceFactory;
import java.io.File;
import java.io.FileInputStream;

public class SISDataSource
    {
    private static String POOL_NAME = "SIS_UCP";
    private static SISDataSource DSClass = null;
    private static PoolDataSource pds = null;
    private static String BDURL = "";
    private static String DBUSERNAME = "";
    private static String DBPASSWORD = "";
    private static int InitialPoolSize = 10;
    private static int MinPoolSize = 50;
    private static int MaxPoolSize = 1000;
    private static int MaxConnectionReuseTime = 3600;
    private static int MaxConnectionReuseCount = 1000;
    private static int InactiveConnectionTimeout = 60;
    private static int TimeoutCheckInterval = 3600;
    private static UniversalConnectionPoolManager PoolManager = null;
    public static boolean is_pool_started = true;

    private static final Logger logger = LogManager.getLogger(SISDataSource.class);

    public SISDataSource()
        {
        }

    public static PoolDataSource getDataSource() throws Exception
        {
        //  System.out.println(BDURL);
        if(DSClass == null)
            {
            DSClass = new SISDataSource();
            if(pds == null)
                if(BDURL.trim().equals("") || DBUSERNAME.trim().equals("") || DBPASSWORD.trim().equals(""))
                    getPropValues();
            if(!BDURL.trim().equals("") && !DBUSERNAME.trim().equals("") && !DBPASSWORD.trim().equals("") && pds == null)
                try
                {
                pds = PoolDataSourceFactory.getPoolDataSource();
                pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
                pds.setConnectionPoolName(POOL_NAME);
                pds.setURL(BDURL);
                pds.setUser(DBUSERNAME);
                pds.setPassword(DBPASSWORD);
                pds.setInitialPoolSize(InitialPoolSize);
                pds.setMinPoolSize(MinPoolSize);
                pds.setMaxPoolSize(MaxPoolSize);
                pds.setMaxConnectionReuseTime(MaxConnectionReuseTime);
                pds.setMaxConnectionReuseCount(MaxConnectionReuseCount);
                pds.setInactiveConnectionTimeout(InactiveConnectionTimeout);
                //System.out.println("MaxConnectionReuseTime="+MaxConnectionReuseTime);
                pds.setTimeoutCheckInterval(TimeoutCheckInterval);
                pds.setValidateConnectionOnBorrow(true);
                pds.setSQLForValidateConnection("Select 1 from Dual");
                try
                    {
                    PoolManager = UniversalConnectionPoolManagerImpl.getUniversalConnectionPoolManager();
                    PoolManager.createConnectionPool((UniversalConnectionPoolAdapter) pds);
                    }
                catch(Exception ee)
                    {
                    }
                is_pool_started = true;
                }
            catch(Exception exp)
                {
                exp.printStackTrace();
                //LogController.add_log(logger, "Fatal", "DataSource Error : "+exp.toString(), "", "SIS");
                }
            }
        return pds;
        }

    public static void getPropValues()
        {
        try
            {
            Properties prop = new Properties();
            // File conf_file = new File(SISDataSource.class.getProtectionDomain().getCodeSource().getLocation().getPath().substring(0, SISDataSource.class.getProtectionDomain().getCodeSource().getLocation().getPath().indexOf("WEB-INF")) + "WEB-INF" + File.separator + "conf" + File.separator + "config.properties");
            InputStream inputStream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/WEB-INF/conf/config.properties");

//System.out.println(conf_file.getAbsolutePath());
            //    InputStream inputStream = new FileInputStream(conf_file);
            prop.load(inputStream);
            if(inputStream != null)
                {
                BDURL = prop.getProperty("BDURL");
                DBUSERNAME = prop.getProperty("DBUSERNAME");
                DBPASSWORD = prop.getProperty("DBPASSWORD");
                try
                    {
                    InitialPoolSize = Integer.parseInt(prop.getProperty("InitialPoolSize"));
                    }
                catch(Exception exp)
                    {
                    InitialPoolSize = 10;
                    }
                try
                    {
                    MinPoolSize = Integer.parseInt(prop.getProperty("MinPoolSize"));
                    }
                catch(Exception exp)
                    {
                    MinPoolSize = 50;
                    }
                try
                    {
                    MaxPoolSize = Integer.parseInt(prop.getProperty("MaxPoolSize"));
                    }
                catch(Exception exp)
                    {
                    MaxPoolSize = 300;
                    }
                try
                    {
                    MaxConnectionReuseTime = Integer.parseInt(prop.getProperty("MaxConnectionReuseTime"));
                    }
                catch(Exception exp)
                    {
                    MaxConnectionReuseTime = 300;
                    }
                try
                    {
                    MaxConnectionReuseCount = Integer.parseInt(prop.getProperty("MaxConnectionReuseCount"));
                    }
                catch(Exception exp)
                    {
                    MaxConnectionReuseCount = 100;
                    }
                try
                    {
                    InactiveConnectionTimeout = Integer.parseInt(prop.getProperty("InactiveConnectionTimeout"));
                    }
                catch(Exception exp)
                    {
                    InactiveConnectionTimeout = 60;
                    }
                try
                    {
                    TimeoutCheckInterval = Integer.parseInt(prop.getProperty("TimeoutCheckInterval"));
                    }
                catch(Exception exp)
                    {
                    TimeoutCheckInterval = 300;
                    }
                }
            }
        catch(IOException ioe)
            {
            System.err.println("Error Unable to get config.properties file.");
            }
        }

    public static boolean ResetPool()
        {
        try
            {
            DestroyPool();
            DSClass = null;
            pds = null;
            return true;
            }
        catch(Exception exp)
            {
            System.out.println("Error(DataScource ResetPool):" + exp.toString());
            return false;
            }
        }

    public static boolean StartPool()
        {
        try
            {
            is_pool_started = true;
            PoolManager.startConnectionPool(POOL_NAME);
            return true;
            }
        catch(Exception exp)
            {
            System.out.println("Error(DataScource StartPool):" + exp.toString());
            return false;
            }
        }

    public static boolean StopPool()
        {
        try
            {
            is_pool_started = false;
            PoolManager.stopConnectionPool(POOL_NAME);
            return true;
            }
        catch(Exception exp)
            {
            System.out.println("Error(DataScource StopPool):" + exp.toString());
            return false;
            }
        }

    public static boolean DestroyPool()
        {
        try
            {
            PoolManager.destroyConnectionPool(POOL_NAME);
            DSClass = null;
            pds = null;
            return true;
            }
        catch(Exception exp)
            {
            System.out.println("Error(DataScource DestroyPool):" + exp.toString());
            return false;
            }
        }

    public static boolean PurgingPool()
        {
        try
            {
            PoolManager.purgeConnectionPool(POOL_NAME);
            return true;
            }
        catch(Exception exp)
            {
            System.out.println("Error(DataScource PurgingPool):" + exp.toString());
            return false;
            }
        }

    public static boolean RecyclingPool()
        {
        try
            {
            PoolManager.recycleConnectionPool(POOL_NAME);
            return true;
            }
        catch(Exception exp)
            {
            System.out.println("Error(DataScource RecyclingPool):" + exp.toString());
            return false;
            }
        }

    public static boolean RefreshPool()
        {
        try
            {
            PoolManager.refreshConnectionPool(POOL_NAME);
            return true;
            }
        catch(Exception exp)
            {
            System.out.println("Error(DataScource RefreshPool):" + exp.toString());
            return false;
            }
        }

    public static int AvailableConnection()
        {
        return pds.getStatistics().getAvailableConnectionsCount();
        }

    public static int BorrowedConnection()
        {
        return pds.getStatistics().getBorrowedConnectionsCount();
        }

    public static int ClosedConnection()
        {
        return pds.getStatistics().getConnectionsClosedCount();
        }

    public static int CreatedConnection()
        {
        return pds.getStatistics().getConnectionsCreatedCount();
        }

    public static int AbandonedConnection()
        {
        return pds.getStatistics().getAbandonedConnectionsCount();
        }

    public static int TotalConnection()
        {
        return pds.getStatistics().getTotalConnectionsCount();
        }
    }
