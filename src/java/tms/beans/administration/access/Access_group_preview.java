package tms.beans.administration.access;

import java.sql.ResultSet;
import java.sql.SQLException;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import tms.common.common_functions;
import tms.db.connect;
import java.io.Serializable;
import org.primefaces.model.menu.MenuModel;

@Named("access_group_preview")
@SessionScoped
public class Access_group_preview implements Serializable
    {
    private MenuModel menu_model;
    private String user_id;
    private String group_id;
    private String sql;

    public void start(String group_id, String user_id)
        {
        this.user_id = user_id;
        this.group_id = group_id;
        create_menu();
        common_functions.getInstance().open_dialog("access_group_preview.xhtml", true, 850, 700, true, true, true, true);
        }

    public void create_menu()
        {
        connect conn = new connect();
        menu_model = new DefaultMenuModel();
        ResultSet rs = null;
        try
            {
            if (group_id != null && !group_id.trim().equals(""))
                {
                sql = "select mnu.icon  as micon,mnu.item_id as itemid,mnu.item_url as itmurl,mnu.item_title as mnutitle from menu_items mnu where mnu.item_id in (select itm.item_id from access_group_items itm where itm.group_id=" + group_id + ") and upper(mnu.item_parent)='MAIN' order by mnu.default_order";
                }
            else
                {
                sql = "select mnu.icon  as micon,mnu.item_id as itemid,mnu.item_url as itmurl,mnu.item_title as mnutitle from menu_items mnu where mnu.item_id in (select itm.item_id from access_groups grp,access_group_items itm,user_access_group usrgrp where usrgrp.user_id=" + user_id + " and usrgrp.group_id = grp.group_id and grp.group_status=1  and grp.group_id=itm.group_id ) and upper(mnu.item_parent)='MAIN' order by mnu.default_order";
                }
            rs = conn.query(sql);
            while (rs.next())
                {
                DefaultSubMenu main_submenu = DefaultSubMenu.builder().label(rs.getString("mnutitle")).icon(rs.getString("micon")).build();
                get_submenu(conn, rs.getString("itemid"), main_submenu);
                menu_model.getElements().add(main_submenu);
                }
            }
        catch (SQLException sqlEx)
            {
            } finally
            {
            try
                {
                if (rs != null)
                    {
                    rs.getStatement().close();
                    rs.close();
                    }
                }
            catch (SQLException sqlEx)
                {
                }
            }
        conn.close();
        }

    public void get_submenu(connect conn, String item_id, DefaultSubMenu submenu)
        {
        ResultSet rs = null;
        try
            {
            if (group_id != null && !group_id.trim().equals(""))
                {
                sql = "select distinct mnu.icon  as micon,mnu.item_id as itemid,mnu.item_url as itmurl,mnu.item_title as mnutitle ,upper(mnu.item_title) ,(select count(*) from access_group_items gi,menu_items it where  gi.group_id = 1  and gi.item_id=it.item_id and upper(it.item_parent)=upper(mnu.item_id)) as has_child from access_groups grp,access_group_items itm,menu_items mnu where  grp.group_id=" + group_id + " and grp.group_id=itm.group_id and itm.item_id=mnu.item_id and mnu.item_parent='" + item_id + "' order by upper(mnu.item_title)";
                }
            else
                {
                sql = "select distinct mnu.icon  as micon,mnu.item_id as itemid,mnu.item_url as itmurl,mnu.item_title as mnutitle ,upper(mnu.item_title) ,(select count(*) from access_groups ccgrp,access_group_items ccitm,menu_items ccmnu,user_access_group ccusrgrp where ccusrgrp.user_id=usrgrp.user_id and ccusrgrp.group_id = ccgrp.group_id and ccgrp.group_status=1  and ccgrp.group_id=ccitm.group_id and ccitm.item_id=ccmnu.item_id and upper(ccmnu.item_parent)=upper(mnu.item_id)) as has_child from access_groups grp,access_group_items itm,menu_items mnu,user_access_group usrgrp where usrgrp.user_id=" + user_id + " and usrgrp.group_id = grp.group_id and grp.group_status=1  and grp.group_id=itm.group_id and itm.item_id=mnu.item_id and mnu.item_parent='" + item_id + "' order by upper(mnu.item_title)";
                }
            rs = conn.query(sql);
            while (rs.next())
                {
                if (rs.getInt("has_child") > 0)
                    {
                    DefaultSubMenu child_submenu = DefaultSubMenu.builder().label(rs.getString("mnutitle")).build();
                    get_submenu(conn, rs.getString("itemid"), child_submenu);
                    child_submenu.setIcon(rs.getString("micon"));
                    submenu.getElements().add(child_submenu);
                    }
                else //normal page(leaf)
                    {
                    DefaultMenuItem item = DefaultMenuItem.builder().value(rs.getString("mnutitle")).title(rs.getString("mnutitle")).url("#").icon(rs.getString("micon")).build();
                    submenu.getElements().add(item);
                    }
                }
            }
        catch (SQLException sqlEx)
            {
            sqlEx.printStackTrace();
            } finally
            {
            try
                {
                if (rs != null)
                    {
                    rs.getStatement().close();
                    rs.close();
                    }
                }
            catch (SQLException sqlEx)
                {
                }
            }

        }

    public void setMenu_model(MenuModel menu_model)
        {
        this.menu_model = menu_model;
        }

    public MenuModel getMenu_model()
        {
        return menu_model;
        }

    public void setUser_id(String user_id)
        {
        this.user_id = user_id;
        }

    public String getUser_id()
        {
        return user_id;
        }

    public void setGroup_id(String group_id)
        {
        this.group_id = group_id;
        }

    public String getGroup_id()
        {
        return group_id;
        }

    public void setSql(String sql)
        {
        this.sql = sql;
        }

    public String getSql()
        {
        return sql;
        }
    }
