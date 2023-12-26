package tms.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.faces.model.SelectItem;

public class connect
    {
    public boolean validdb = false;
    protected Connection conn = null;

    public connect()
        {
        try
            {
            if(SISDataSource.getDataSource() != null)
                if(SISDataSource.getDataSource() != null)
                    {
                    conn = SISDataSource.getDataSource().getConnection();
                    validdb = true;
                    }
            }
        catch(Exception exp)
            {
            }
        }

    public void close()
        {
        try
            {
            if(conn.getAutoCommit() == false)
                conn.setAutoCommit(true);
            try
                {
                conn.commit();
                }
            catch(Exception exp)
                {
                }
            conn.close();
            }
        catch(Exception exp)
            {
            }
        }

    public ResultSet query(String sql) throws SQLException
        {
        Statement stmt;
        ResultSet rs = null;
        if(conn != null)
    try
            {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            }finally
            {

            }
        return (rs);
        }

    public ResultSet query(String sql, Object[] params) throws SQLException
        {
        PreparedStatement pst = null;
        ResultSet rs;
        int numRows = 0;
        if(conn != null)
            pst = conn.prepareStatement(sql);
        for(numRows = 0; numRows < params.length; numRows++)
            if(params[numRows] instanceof Date)
                {
                Date date = (Date) params[numRows];
                pst.setDate(numRows + 1, new java.sql.Date(date.getTime()));
                }

            else if(params[numRows] instanceof Double)
                pst.setDouble(numRows + 1, (Double) params[numRows]);

            else if(params[numRows] instanceof Boolean)
                pst.setInt(numRows + 1, ((Boolean) params[numRows]) ? 1 : 0);

            else if(params[numRows] instanceof Integer)
                pst.setInt(numRows + 1, (Integer) params[numRows]);

            else if(params[numRows] instanceof Float)
                pst.setFloat(numRows + 1, (Float) params[numRows]);

            else if(params[numRows] instanceof Long)
                pst.setLong(numRows + 1, (Long) params[numRows]);
            else
                pst.setString(numRows + 1, (String) params[numRows]);
        numRows = -1;
        rs = pst.executeQuery();
        pst.clearParameters();

        return rs;
        }

    public ResultSet query_scrolled(String queryStatement) throws SQLException
        {
        Statement stmt = null;
        ResultSet rs = null;
        if(conn != null)
  try
            {
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
            rs = stmt.executeQuery(queryStatement);
            }
        catch(Exception ex)
            {
            return null;
            }finally
            {

            }
        return (rs);
        }

    public int insert(String sql) throws SQLException
        {
        return (update(sql));
        }

    public int delete(String sql) throws SQLException
        {
        return (update(sql));
        }

    public int update(String sql) throws SQLException
        {
        Statement stmt = null;
        int numRows = -1;
        if(conn != null)
    try
            {
            stmt = conn.createStatement();
            numRows = stmt.executeUpdate(sql);
            }finally
            {
            if(stmt != null)
                stmt.close();
            }
        return (numRows);
        }

    public int insert(String sql, String[] params) throws SQLException
        {
        return update(sql, params);
        }

    public int insert(String sql, Object[] params) throws SQLException
        {
        return update(sql, params);
        }

    public int update(String sql, String[] params) throws SQLException
        {
        PreparedStatement pst = null;
        int numRows = 0;
        if(conn != null)
            try
            {
            pst = conn.prepareStatement(sql);
            for(numRows = 0; numRows < params.length; numRows++)
                pst.setString(numRows + 1, params[numRows]);
            numRows = -1;
            numRows = pst.executeUpdate();
            pst.clearParameters();

            }finally
            {
            if(pst != null)
                pst.close();
            }
        return (numRows);
        }

    public void setAutoCommit(boolean flag)
        {
        try
            {
            conn.setAutoCommit(flag);
            }
        catch(Exception exp)
            {
            System.out.println("Error :" + exp.toString());
            }
        }

    public void commit()
        {
        try
            {
            if(conn.getAutoCommit() == false)
                conn.commit();
            }
        catch(Exception exp)
            {
            System.out.println("Error :" + exp.toString());
            }
        }

    public void rollback()
        {
        try
            {
            conn.rollback();
            }
        catch(Exception exp)
            {
            System.out.println("Error :" + exp.toString());
            }
        }

    public Connection getConn()
        {
        return conn;
        }

    public String get_max_id(String table_name, String ID_name)
        {
        String new_id = "";
        try
            {
            ResultSet rs = query("select nvl(max(" + ID_name + "),0)+1 from " + table_name);
            while(rs.next())
                {
                new_id = rs.getString(1);
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("Error :" + exp.toString());
            }
        return new_id;
        }

    public int update(String sql, Object[] params) throws SQLException
        {
        PreparedStatement pst = null;
        int numRows = 0;
        if(conn != null)
          try
            {
            pst = conn.prepareStatement(sql);
            for(numRows = 0; numRows < params.length; numRows++)
                if(params[numRows] instanceof Date)
                    {
                    Date date = (Date) params[numRows];
                    pst.setDate(numRows + 1, new java.sql.Date(date.getTime()));
                    }

                else if(params[numRows] instanceof Double)
                    pst.setDouble(numRows + 1, (Double) params[numRows]);

                else if(params[numRows] instanceof Boolean)
                    pst.setInt(numRows + 1, ((Boolean) params[numRows]) ? 1 : 0);

                else if(params[numRows] instanceof Integer)
                    pst.setInt(numRows + 1, (Integer) params[numRows]);

                else if(params[numRows] instanceof Float)
                    pst.setFloat(numRows + 1, (Float) params[numRows]);

                else if(params[numRows] instanceof Long)
                    pst.setLong(numRows + 1, (Long) params[numRows]);
                else
                    pst.setString(numRows + 1, (String) params[numRows]);
            numRows = -1;
            numRows = pst.executeUpdate();
            pst.clearParameters();
            }
        catch(SQLException ex)
            {
            try
                {
                if(pst != null)
                    pst.close();
                }
            catch(SQLException ex2)
                {
                }finally
                {
                throw (ex);
                }
            }finally
            {
            try
                {
                if(pst != null)
                    pst.close();
                }
            catch(SQLException ex)
                {
                }
            }
        return (numRows);
        }

    public List<SelectItem> SQLList(String sql)
        {
        List<SelectItem> list = new ArrayList<>();
        ResultSet rs = null;
        try
            {
            rs = query(sql);
            while(rs.next())

                {
                list.add(new SelectItem(rs.getString(1), rs.getString(2)));
                }
            }
        catch(SQLException sqlEx)
            {
            }finally
            {
            try
                {
                if(rs != null)
                    {
                    rs.getStatement().close();
                    rs.close();
                    }
                }
            catch(SQLException sqlEx)
                {
                }
            }
        return list;
        }

    }
