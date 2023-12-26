package tms.beans.users;

import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.text.WordUtils;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
import tms.application.TMSApplication;
import tms.db.connect;
import java.io.File;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpServletRequest;
import tms.beans.common.msgr;
import tms.application.BreadCrumbs;
import tms.application.TMSApplicationBean;

@Named
@SessionScoped
public class user_details implements Serializable
    {
    @Inject
    private TMSApplicationBean tmsApplicationBean;
    @Inject
    private UserPreferences userPreferences;
    private int login_counter = 0;
    private String user_id = "";
    private String display_name;
    private String email;
    private String user_type = "Admin";
    private String department_id;
    private String department_name;
    private String photo_path;
    private MenuModel menu_model;
    private String[] BreadCrumbsIDArray = null;
    //Login
    private boolean isLoggedIn;

    @PostConstruct
    public void init()
        {
        }

    public void keepSessionAlive()
        {
        msgr.info("User " + this.display_name + " has refreshed the session.");
        }

    public void redirect_to_main_page()
        {
        logout();
        }

    public void reset()
        {
        user_id = "";
        display_name = "";
        email = "";
        user_type = "Admin";
        department_id = "";
        department_name = "";
        photo_path = "";
        menu_model = null;
        BreadCrumbsIDArray = null;
        isLoggedIn = false;
        }

    public void clear_header_menu()
        {
        menu_model = new DefaultMenuModel();
        }

    public void prepare_user(connect conn, String user_id)
        {
        login_counter = 0;
        reset();
        this.user_id = user_id;
        clear_header_menu();
        try
            {
            conn.update("update users set last_login_date=sysdate where user_id=" + user_id);
            }
        catch(SQLException ee)
            {
            }

        try(ResultSet rs = conn.query("select uu.email as email,nvl(uu.photo_path,' ') as photo_path,uu.display_name as display_name,dd.department_name as department_name,dd.department_id as department_id from users uu,departments dd where uu.department_id=dd.department_id and uu.user_id=" + user_id))
            {

            while(rs.next())
                {
                this.user_type = "Admin";
                this.display_name = WordUtils.capitalize(rs.getString("display_name").toLowerCase());
                this.email = rs.getString("email");
                this.department_id = rs.getString("department_id");
                this.department_name = rs.getString("department_name");
                this.photo_path = "/" + rs.getString("photo_path");
                //check user photo
                if(this.photo_path.trim().equals(""))
                    this.photo_path = "/SIS_Files/users_avatar/unkown.png";
                else
                        try
                    {
                    File file = new File(FacesContext.getCurrentInstance().getExternalContext().getRealPath(this.photo_path));
                    if(file.exists() == false)
                        this.photo_path = "/SIS_Files/users_avatar/unkown.png";
                    }
                catch(Exception ee)
                    {
                    System.out.println(ee.getMessage());
                    }
                userPreferences.update_user_preferences(conn, "ADMIN", user_id);
                }
            isLoggedIn = true;
            create_header_menu(conn);
            }
        catch(SQLException sqlEx)
            {
            msgr.error("Error: " + sqlEx.getMessage());
            }
        }

    public void create_header_menu(connect conn)
        {
        create_user_menu(conn);
        }

    public void create_user_menu(connect conn)
        {
        menu_model = new DefaultMenuModel();

        //add dashboard
        DefaultMenuItem item = DefaultMenuItem.builder().value(" ").url(tmsApplicationBean.getContextPath() + "/dashboard.xhtml").icon("dashboard").build();

        item.setUrl(tmsApplicationBean.getContextPath() + "/dashboard.xhtml");
        item.setIcon("dashboard");
        //menu_model.getElements().add(item);
        try
            {
            ResultSet rs = conn.query("select mnu.icon  as micon,mnu.item_id as itemid,mnu.item_url as itmurl,mnu.item_title as mnutitle from menu_items mnu where mnu.item_id in (select itm.item_id from access_groups grp,access_group_items itm,user_access_group usrgrp where usrgrp.user_id=" + user_id + " and usrgrp.group_id = grp.group_id and grp.group_status=1  and grp.group_id=itm.group_id ) and upper(mnu.item_parent)='MAIN' order by mnu.default_order");
            while(rs.next())
                {
                List<DefaultMenuItem> bread_items_list = new ArrayList<>();
                bread_items_list.add(item);
                DefaultSubMenu main_submenu = DefaultSubMenu.builder().label(rs.getString("mnutitle")).icon(rs.getString("micon")).build();
                //parent breadcrumbs
                DefaultMenuItem item_parent = DefaultMenuItem.builder().value(" " + rs.getString("mnutitle")).url("#").icon(rs.getString("micon")).build();
                bread_items_list.add(item_parent);

                get_submenu(conn, rs.getString("itemid"), main_submenu, bread_items_list);
                menu_model.getElements().add(main_submenu);
                }
            rs.close();
            }
        catch(Exception exp)
            {
            msgr.error("Error (" + this.getClass().toString() + ") :" + exp.toString());
            }
        }

    public void get_submenu(connect conn, String item_id, DefaultSubMenu submenu, List<DefaultMenuItem> bread_items_list)
        {
        try
            {
            ResultSet rs = conn.query("select distinct mnu.icon  as micon,mnu.item_id as itemid,mnu.item_url as itmurl,mnu.item_title as mnutitle ,upper(mnu.item_title) ,(select count(*) from access_groups ccgrp,access_group_items ccitm,menu_items ccmnu,user_access_group ccusrgrp where ccusrgrp.user_id=usrgrp.user_id and ccusrgrp.group_id = ccgrp.group_id and ccgrp.group_status=1  and ccgrp.group_id=ccitm.group_id and ccitm.item_id=ccmnu.item_id and upper(ccmnu.item_parent)=upper(mnu.item_id)) as has_child from access_groups grp,access_group_items itm,menu_items mnu,user_access_group usrgrp where usrgrp.user_id=" + user_id + " and usrgrp.group_id = grp.group_id and grp.group_status=1  and grp.group_id=itm.group_id and itm.item_id=mnu.item_id and mnu.item_parent='" + item_id + "' order by upper(mnu.item_title)");
            while(rs.next())
                {
                if(rs.getInt("has_child") > 0)
                    {
                    //breadcrumbs menu item
                    List<DefaultMenuItem> sub_bread_items_list = new ArrayList<>();
                    for(DefaultMenuItem mitem : bread_items_list)
                        sub_bread_items_list.add(mitem);

                    DefaultMenuItem citem = DefaultMenuItem.builder().value(rs.getString("mnutitle")).icon(rs.getString("micon")).url("#").build();
                    //citem.setDisabled(true);
                    sub_bread_items_list.add(citem);

                    //////////////////////////////////////////
                    DefaultSubMenu child_submenu = DefaultSubMenu.builder().label(rs.getString("mnutitle")).icon(rs.getString("micon")).build();
                    get_submenu(conn, rs.getString("itemid"), child_submenu, sub_bread_items_list);
                    submenu.getElements().add(child_submenu);
                    }
                else //normal page(leaf)
                    {
                    DefaultMenuItem item = DefaultMenuItem.builder().value(rs.getString("mnutitle")).url(tmsApplicationBean.getContextPath() + "/" + rs.getString("itmurl")).icon(rs.getString("micon")).build();
                    submenu.getElements().add(item);
                    }
                }
            rs.close();
            }
        catch(SQLException sqle)
            {
            }

        }

    public List<BreadCrumbs> get_breadcrumbs_model(String page_id)
        {
        return TMSApplication.getBreadcrumbs_models_list().get(page_id);
        }

    public void logout()
        {
        ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
        ec.invalidateSession();
        try
            {
            ec.redirect(ec.getRequestContextPath() + "/login/login.xhtml");
            }
        catch(IOException e)
            {
            }
        }

    //must remove
    public String get_submenu(connect conn, String item_id, String root_bread_crumbs)
        {
        String items = "<ul>";
        List<String> item_vectors = new ArrayList<>();
        try
            {
            ResultSet rs = conn.query("select mnu.item_id as itemid,mnu.item_url as itmurl,mnu.item_title as mnutitle from access_groups grp,access_group_items itm,menu_items mnu,user_access_group usrgrp where usrgrp.user_id=" + user_id + " and usrgrp.group_id = grp.group_id and grp.group_status=1  and grp.group_id=itm.group_id and itm.item_id=mnu.item_id and mnu.item_parent='" + item_id + "' order by upper(mnu.item_title)");
            while(rs.next())
                {
                if(!item_vectors.contains(rs.getString("itemid")))
                    {
                    item_vectors.add(rs.getString("itemid"));
                    BreadCrumbsIDArray[rs.getInt("itemid")] = root_bread_crumbs + " » <a href=\" " + tmsApplicationBean.getContextPath() + "/" + rs.getString("itmurl") + " \">" + rs.getString("mnutitle") + "</a>";
                    items += "<li><a href=\"" + tmsApplicationBean.getContextPath() + "/" + rs.getString("itmurl") + "\">" + rs.getString("mnutitle") + "</a>" + get_submenu(conn, rs.getString("itemid"), BreadCrumbsIDArray[rs.getInt("itemid")]) + "</li> \n";
                    }
                }
            rs.close();
            }
        catch(SQLException sqle)
            {
            }
        item_vectors.clear();
        items += "</ul>";
        if(items.trim().equalsIgnoreCase(("<ul></ul>")))
            return "";
        else
            return items;
        }

    public String getBreadCrumbsLink(int page_index)
        {
        try
            {
            return "<span><a href=\"" + tmsApplicationBean.getContextPath() + "/dashboard.xhtml\" >Home</a> " + BreadCrumbsIDArray[page_index] + "</span>";
            }
        catch(Exception exp)
            {
            }
        return "";
        }

    public void set_sidebar(HttpServletRequest req, String value)
        {
        req.getSession().setAttribute("sidebar_side", value);
        try
            {
            connect conn = new connect();
            if(user_type.equalsIgnoreCase("student"))
                conn.update("update students set sidebar_side='" + value + "' where app_id=" + user_id);
            else
                conn.update("update users set sidebar_side='" + value + "' where user_id=" + user_id);

            conn.close();
            }
        catch(Exception exp)
            {
            System.out.println("Sidebar side Error: " + exp.toString());
            }
        }

    public void set_sidebar_status(HttpServletRequest req, String value)
        {
        req.getSession().setAttribute("sidebar_status", value);
        try
            {
            connect conn = new connect();
            if(user_type.equalsIgnoreCase("student"))
                conn.update("update students set sidebar_status=" + value + " where app_id=" + user_id);
            else
                conn.update("update users set sidebar_status=" + value + " where user_id=" + user_id);
            conn.close();
            }
        catch(Exception exp)
            {
            System.out.println("Sidebar_status Error: " + exp.toString());
            }
        }

    public void set_theme(HttpServletRequest req, String value)
        {
        req.getSession().setAttribute("theme_change", value);
        try
            {

            connect conn = new connect();
            if(user_type.equalsIgnoreCase("student"))
                conn.update("update students set themes_name='" + value + "' where app_id=" + user_id);
            else
                conn.update("update users set themes_name='" + value + "' where user_id=" + user_id);
            conn.close();
            }
        catch(Exception exp)
            {
            }
        }

    public void set_screen_size(HttpServletRequest req, String value)
        {
        req.getSession().setAttribute("screen_size", value);
        try
            {
            connect conn = new connect();
            if(user_type.equalsIgnoreCase("student"))
                conn.update("update students set screen_size='" + value + "' where app_id=" + user_id);
            else
                conn.update("update users set screen_size='" + value + "' where user_id=" + user_id);
            conn.close();
            }
        catch(Exception exp)
            {
            }
        }

    public void close_dialog()
        {
        tms.common.common_functions.getInstance().close_dialog();

        }

    public TMSApplicationBean getTmsApplicationBean()
        {
        return tmsApplicationBean;
        }

    public void setTmsApplicationBean(TMSApplicationBean tmsApplicationBean)
        {
        this.tmsApplicationBean = tmsApplicationBean;
        }

    public UserPreferences getUserPreferences()
        {
        return userPreferences;
        }

    public void setUserPreferences(UserPreferences userPreferences)
        {
        this.userPreferences = userPreferences;
        }

    public int getLogin_counter()
        {
        return login_counter;
        }

    public void setLogin_counter(int login_counter)
        {
        this.login_counter = login_counter;
        }

    public String getUser_id()
        {
        return user_id;
        }

    public void setUser_id(String user_id)
        {
        this.user_id = user_id;
        }

    public String getDisplay_name()
        {
        return display_name;
        }

    public void setDisplay_name(String display_name)
        {
        this.display_name = display_name;
        }

    public String getEmail()
        {
        return email;
        }

    public void setEmail(String email)
        {
        this.email = email;
        }

    public String getUser_type()
        {
        return user_type;
        }

    public void setUser_type(String user_type)
        {
        this.user_type = user_type;
        }

    public String getDepartment_id()
        {
        return department_id;
        }

    public void setDepartment_id(String department_id)
        {
        this.department_id = department_id;
        }

    public String getDepartment_name()
        {
        return department_name;
        }

    public void setDepartment_name(String department_name)
        {
        this.department_name = department_name;
        }

    public String getPhoto_path()
        {
        return photo_path;
        }

    public void setPhoto_path(String photo_path)
        {
        this.photo_path = photo_path;
        }

    public MenuModel getMenu_model()
        {
        return menu_model;
        }

    public void setMenu_model(MenuModel menu_model)
        {
        this.menu_model = menu_model;
        }

    public String[] getBreadCrumbsIDArray()
        {
        return BreadCrumbsIDArray;
        }

    public void setBreadCrumbsIDArray(String[] BreadCrumbsIDArray)
        {
        this.BreadCrumbsIDArray = BreadCrumbsIDArray;
        }

    public boolean isIsLoggedIn()
        {
        return isLoggedIn;
        }

    public void setIsLoggedIn(boolean isLoggedIn)
        {
        this.isLoggedIn = isLoggedIn;
        }

    }
