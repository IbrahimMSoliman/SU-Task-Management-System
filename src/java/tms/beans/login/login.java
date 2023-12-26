package tms.beans.login;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ResourceBundle;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.*;
import tms.beans.users.user_details;
import tms.db.connect;
import jakarta.servlet.ServletContext;
import tms.beans.common.msgr;
import tms.application.TMSApplication;
import tms.common.common_functions;

@Named("login")
@ViewScoped
public class login implements Serializable
    {
    @Inject
    private user_details user_details;

    private String username;
    private String password;
    ResourceBundle ar_resource = ResourceBundle.getBundle("tms/MsgsBundles/arabicmsgs");

    private boolean allow_login_again = true;
    private boolean is_valid_username_password;
    private String user_id = "";

    private boolean remember_me = false;
    private String university_name = "";
    private String logo_url = "";
    private String context_path;

    //login history
    private String returned_value;
    private boolean disable_login_button = false;

    public login()
        {

        }

    @PostConstruct
    public void init()
        {
        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        context_path = servletContext.getContextPath();
        try
            {
            university_name = TMSApplication.getEn_university_name();
            logo_url = TMSApplication.getApplication_logo_path();
            }
        catch(Exception ee)
            {
            }
        check_remember_me_option();
        }

    private void check_remember_me_option()
        {
        remember_me = false;
        FacesContext fc = FacesContext.getCurrentInstance();
        Cookie[] cookiesArr = ((HttpServletRequest) (fc.getExternalContext().getRequest())).getCookies();
        if(cookiesArr != null && cookiesArr.length > 0)
            for(int i = 0; i < cookiesArr.length; i++)
                {
                String cName = cookiesArr[i].getName();
                String cValue = cookiesArr[i].getValue();
                if(cName.equals("TMSUserId"))
                    username = cValue;
                else if(cName.equals("TMSPassword"))
                    password = cValue;
                else if(cName.equals("TMSRemembered"))
                    {
                    remember_me = cValue.equalsIgnoreCase("true");
                    if(remember_me == false)
                        {
                        username = "";
                        password = "";
                        break;
                        }
                    }

                }
        }

    private void save_remember_me_option()
        {
        FacesContext fc = FacesContext.getCurrentInstance();
        if(remember_me == true)
            {
            Cookie cUserId = new Cookie("TMSUserId", username);
            Cookie cPassword = new Cookie("TMSPassword", password);
            Cookie cVirtualCheck = new Cookie("TMSRemembered", "true");
            cUserId.setMaxAge(31557600);//one year per seconds 60*60*24*365.25
            cPassword.setMaxAge(31557600);
            cVirtualCheck.setMaxAge(31557600);
            ((HttpServletResponse) (fc.getExternalContext().getResponse())).addCookie(cUserId);
            ((HttpServletResponse) (fc.getExternalContext().getResponse())).addCookie(cPassword);
            ((HttpServletResponse) (fc.getExternalContext().getResponse())).addCookie(cVirtualCheck);
            }
        else
            {
            Cookie cVirtualCheck = new Cookie("TMSRemembered", "false");
            ((HttpServletResponse) (fc.getExternalContext().getResponse())).addCookie(cVirtualCheck);
            }
        }

    public String validateLogin()
        {
        if(disable_login_button == true)
            return "";
        disable_login_button = true;
        if(username == null)
            username = "";
        if(password == null)
            password = "";
        returned_value = "";
        //message="";
        if(username.trim().equals("") || password.trim().equals(""))
            //show error message
            msgr.error("Empty username and password are not allowed......<br/>" + ar_resource.getString("login.empty_username"));

        else
            {
            //connect to DB
            connect conn = new connect();
            //int counter=0;
            if(conn.validdb == false)
                {
                msgr.error("Unable to connect to system Database.<br/>" + ar_resource.getString("login.db_disconnected"));
                return "";
                }

            try
                {
                is_valid_username_password = false;
                check_username_password(conn);
                user_details.setLogin_counter(user_details.getLogin_counter() + 1);
                if(user_details.getLogin_counter() >= 3)
                    {
                    msgr.error("You have exceeded the number of allowed login attempts. Please close the web browser and try again later.<br/>\u0644\u0642\u062F \u062A\u062C\u0627\u0648\u0632\u062A \u0627\u0644\u062D\u062F \u0627\u0644\u0645\u0633\u0645\u0648\u062D \u0628\u0647 \u0644\u0645\u062D\u0627\u0648\u0644\u0627\u062A \u0627\u0644\u062F\u062E\u0648\u0644 , \u0627\u0644\u0631\u062C\u0627\u0621 \u0627\u063A\u0644\u0627\u0642 \u0627\u0644\u0645\u062A\u0635\u0641\u062D \u0648 \u0627\u0644\u0645\u062D\u0627\u0648\u0644\u0629 \u0645\u0631\u0629 \u0627\u062E\u0631\u0649 \u0644\u0627\u062D\u0642\u0627.\r");
                    allow_login_again = false;
                    }
                if(is_valid_username_password == false && user_details.getLogin_counter() < 3)
                    msgr.error("Invalid username and password......<br/>" + ar_resource.getString("login.invalid_username")); //add failed attepmt to database
                /////////////////////user\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
                else if(!user_id.trim().equals(""))
                    {
                    ResultSet rs = conn.query("select uu.user_id as user_id,uu.user_status as user_status from users uu where uu.user_id=" + user_id);
                    while(rs.next())
                        {
                        if(rs.getString("user_status").trim().equals("0")) //blocked user
                            msgr.error("Your account has been deactivated, for more details please contact the system administrator....");
                        else if(rs.getString("user_status").trim().equals("1")) //active account
                            {
                            user_details.prepare_user(conn, rs.getString("user_id"));
                            save_remember_me_option();
                            returned_value = "HomePage";
                            }
                        }

                    rs.getStatement().close();
                    rs.close();
                    }
                }
            catch(Exception exp)
                {
                msgr.error("Error: " + exp.getMessage());
                }
            conn.close();
            }

        if(returned_value.isEmpty())
            disable_login_button = false;
        return returned_value;
        }

    private void check_username_password(connect conn)
        {
        user_id = "";
        is_valid_username_password = false;
        try
            {
            ResultSet rs2 = conn.query("select user_id as user_id from users where username=? and password=?", new Object[]
                {
                username.trim(), password
                });
            while(rs2.next())
                {
                user_id = rs2.getString("user_id");
                is_valid_username_password = true;
                }
            rs2.close();
            }
        catch(Exception exp)
            {
            System.out.println("Error (" + this.getClass().toString() + ") :" + exp.toString());
            }
        }

    public String getRandom_bg_class()
        {
        return ("login-image" + common_functions.getInstance().getRandomNumberInRange(1, 5)).replaceAll(" ", "");
        }

    public void setUsername(String username)
        {
        this.username = username;
        }

    public String getUsername()
        {
        return username;
        }

    public void setPassword(String password)
        {
        this.password = password;
        }

    public String getPassword()
        {
        return password;
        }

    public void setUser_details(user_details user_details)
        {
        this.user_details = user_details;
        }

    public user_details getUser_details()
        {
        return user_details;
        }

    public void setAllow_login_again(boolean allow_login_again)
        {
        this.allow_login_again = allow_login_again;
        }

    public boolean isAllow_login_again()
        {
        return allow_login_again;
        }

    public void setAr_resource(ResourceBundle ar_resource)
        {
        this.ar_resource = ar_resource;
        }

    public ResourceBundle getAr_resource()
        {
        return ar_resource;
        }

    public boolean isIs_valid_username_password()
        {
        return is_valid_username_password;
        }

    public void setIs_valid_username_password(boolean is_valid_username_password)
        {
        this.is_valid_username_password = is_valid_username_password;
        }

    public String getUser_id()
        {
        return user_id;
        }

    public void setUser_id(String user_id)
        {
        this.user_id = user_id;
        }

    public boolean isRemember_me()
        {
        return remember_me;
        }

    public void setRemember_me(boolean remember_me)
        {
        this.remember_me = remember_me;
        }

    public String getUniversity_name()
        {
        return university_name;
        }

    public void setUniversity_name(String university_name)
        {
        this.university_name = university_name;
        }

    public String getLogo_url()
        {
        return logo_url;
        }

    public void setLogo_url(String logo_url)
        {
        this.logo_url = logo_url;
        }

    public String getContext_path()
        {
        return context_path;
        }

    public void setContext_path(String context_path)
        {
        this.context_path = context_path;
        }

    public String getReturned_value()
        {
        return returned_value;
        }

    public void setReturned_value(String returned_value)
        {
        this.returned_value = returned_value;
        }

    public boolean isDisable_login_button()
        {
        return disable_login_button;
        }

    public void setDisable_login_button(boolean disable_login_button)
        {
        this.disable_login_button = disable_login_button;
        }

    }
