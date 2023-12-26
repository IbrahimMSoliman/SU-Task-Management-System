package tms.beans.administration.access;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import tms.common.common_functions;
import tms.db.connect;
import tms.beans.common.msgr;

@Named("access_group_menu")
@SessionScoped
public class Access_group_menu implements Serializable
    {
    private static final Logger logger = LogManager.getLogger(Access_group_menu.class);
    private String group_id;
    private String group_title;
    private String process_type;

    private List<main_menu_class> main_list = new ArrayList<>();
    private List<child_menu_class> temp_parent_child_list = new ArrayList<>();
    private List<child_menu_class> temp_leaf_child_list = new ArrayList<>();
    private String current_main_menu;

    private String current_item_id;
    private String current_item_icon;

    String sql;

    public Access_group_menu()
        {

        }

    public void start_menu(String group_id, String group_title, String process_type)
        {
        reset();
        this.group_id = group_id;
        this.group_title = group_title;
        this.process_type = process_type;
        main_list.clear();
        connect conn = new connect();
        try(ResultSet rs = conn.query("select icon as item_icon,item_id as item_id,item_title as item_title,nvl(default_order,0) as default_order,0 as is_selected from menu_items mi where upper(item_parent)='MAIN' order by default_order"))
            {
            while(rs.next())
                {
                main_list.add(new main_menu_class(conn, rs.getString("item_id"), rs.getString("item_title"), rs.getString("item_icon")));
                }
            }
        catch(SQLException sqlEx)
            {

            }
        //load first menu to be shown
        main_list.get(0).show_submenu_in_container();
        conn.close();

        }

    public void close_dialog()
        {
        common_functions.getInstance().close_dialog();
        }

    private void reset()
        {
        temp_parent_child_list.clear();
        temp_leaf_child_list.clear();
        current_main_menu = "";
        group_id = "";
        group_title = "";
        for(main_menu_class main : main_list)
            {
            for(child_menu_class main_leaf : main.getLeafs_childs_list())
                main_leaf.selected = false;
            for(child_menu_class main_parent : main.getParents_childs_list())
                {
                main_parent.selected = false;
                for(child_menu_class parent_leaf : main_parent.childs_list)
                    parent_leaf.selected = false;
                }
            }
        }

    public void save()
        {
        connect conn = new connect();

        if(validation(conn))
            if(process_type.trim().equalsIgnoreCase("new"))//add
                {
                conn.setAutoCommit(false);
                try
                    {
                    group_id = conn.get_max_id("ACCESS_GROUPS", "GROUP_ID");
                    conn.insert("insert into access_groups (group_id,group_name,group_status) values(?,?,?)", new String[]
                        {
                        group_id, group_title, "1"
                        });
                    update_menu_items(conn);
                    conn.setAutoCommit(true);
                    msgr.info("The access group has been inserted successfully....");
                    reset();
                    }
                catch(Exception exp)
                    {
                    exp.printStackTrace();
                    conn.rollback();
                    msgr.error("Unable to add new access group....");
                    group_id = "";
                    }
                }
            else //add
                {
                conn.setAutoCommit(false);
                try
                    {
                    conn.update("update access_groups set group_name=? where group_id=?", new String[]
                        {
                        group_title, group_id
                        });
                    update_menu_items(conn);
                    conn.commit();
                    conn.setAutoCommit(true);
                    msgr.info("The access group has been updated successfully....");
                    }
                catch(Exception exp)
                    {

                    conn.rollback();
                    msgr.error("Unable to update access group....");
                    }
                }
        conn.close();

        }

    private void update_menu_items(connect conn) throws SQLException
        {
        if(group_id != null && !group_id.trim().equals(""))
            conn.delete("delete from access_group_items where group_id=" + group_id);
        for(main_menu_class main : main_list)
            {
            for(child_menu_class main_leaf : main.getLeafs_childs_list())
                if(main_leaf.selected)
                    {
                    //add main and leaf
                    //main
                    conn.delete("delete from access_group_items where group_id=" + group_id + " and item_id=" + main.item_id);
                    conn.insert("insert into access_group_items (group_id,item_id,item_order) values(" + group_id + "," + main.item_id + ",0)");
                    //leaf
                    conn.delete("delete from access_group_items where group_id=" + group_id + " and item_id=" + main_leaf.item_id);
                    conn.insert("insert into access_group_items (group_id,item_id,item_order) values(" + group_id + "," + main_leaf.item_id + ",0)");
                    }

            for(child_menu_class main_parent : main.getParents_childs_list())
                for(child_menu_class parent_leaf : main_parent.childs_list)
                    //add main and parent and leaf
                    //main
                    if(parent_leaf.isSelected())
                        {
                        conn.delete("delete from access_group_items where group_id=" + group_id + " and item_id=" + main.item_id);
                        conn.insert("insert into access_group_items (group_id,item_id,item_order) values(" + group_id + "," + main.item_id + ",0)");
                        //parent
                        conn.delete("delete from access_group_items where group_id=" + group_id + " and item_id=" + main_parent.item_id);
                        conn.insert("insert into access_group_items (group_id,item_id,item_order) values(" + group_id + "," + main_parent.item_id + ",0)");

                        //leaf
                        conn.delete("delete from access_group_items where group_id=" + group_id + " and item_id=" + parent_leaf.item_id);
                        conn.insert("insert into access_group_items (group_id,item_id,item_order) values(" + group_id + "," + parent_leaf.item_id + ",0)");
                        }
            }
        }

    private boolean validation(connect conn)
        {
        if(common_functions.getInstance().check_duplication(conn, "access_groups", "group_name", group_title, "group_id", group_id) > 0)
            {
            msgr.error("Access group title duplication :There is an access group with the same title....");
            return false;
            }
        return true;
        }

    public class main_menu_class
        {
        private String item_id;
        private String item_title;
        private String item_icon;
        private List<child_menu_class> parents_childs_list = new ArrayList<>();
        private List<child_menu_class> leafs_childs_list = new ArrayList<>();

        public main_menu_class(connect conn, String item_id, String item_title, String item_icon)
            {
            this.item_id = item_id;
            this.item_title = item_title;
            this.item_icon = item_icon;

            ResultSet rs = null;
            try
                {
                sql = "select icon as item_icon,item_id as item_id,item_title as item_title,0 as is_selected ,(select count(*) from menu_items chi where chi.item_parent=to_char(mi.item_id)) as is_parent from menu_items mi where upper(item_parent)='" + this.item_id + "' order by upper(item_title) ";
                if(group_id != null && !group_id.trim().equals(""))
                    sql = "select icon as item_icon,item_id as item_id,item_title as item_title,(select count(*) from access_group_items ii where ii.item_id=mi.item_id and ii.group_id=" + group_id + ") as is_selected ,(select count(*) from menu_items chi where chi.item_parent=to_char(mi.item_id)) as is_parent from menu_items mi where upper(item_parent)='" + this.item_id + "' order by upper(item_title) ";

                rs = conn.query(sql);
                while(rs.next())
                    {
                    if(rs.getInt("is_parent") == 0)
                        leafs_childs_list.add(new child_menu_class(conn, rs.getString("item_id"), rs.getString("item_title"), rs.getString("item_icon"), rs.getInt("is_selected") > 0, "Leaf"));
                    else
                        parents_childs_list.add(new child_menu_class(conn, rs.getString("item_id"), rs.getString("item_title"), rs.getString("item_icon"), rs.getInt("is_selected") > 0, "Parent"));
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

            }

        public void show_submenu_in_container()
            {
            current_main_menu = this.item_title;
            temp_parent_child_list = this.parents_childs_list;
            temp_leaf_child_list = this.leafs_childs_list;
            current_item_id = this.item_id;
            current_item_icon = this.item_icon;
            }

        public void setItem_id(String item_id)
            {
            this.item_id = item_id;
            }

        public void setParents_childs_list(List<Access_group_menu.child_menu_class> parents_childs_list)
            {
            this.parents_childs_list = parents_childs_list;
            }

        public List<Access_group_menu.child_menu_class> getParents_childs_list()
            {
            return parents_childs_list;
            }

        public void setLeafs_childs_list(List<Access_group_menu.child_menu_class> leafs_childs_list)
            {
            this.leafs_childs_list = leafs_childs_list;
            }

        public List<Access_group_menu.child_menu_class> getLeafs_childs_list()
            {
            return leafs_childs_list;
            }

        public String getItem_id()
            {
            return item_id;
            }

        public void setItem_title(String item_title)
            {
            this.item_title = item_title;
            }

        public String getItem_title()
            {
            return item_title;
            }

        public void setItem_icon(String item_icon)
            {
            this.item_icon = item_icon;
            }

        public String getItem_icon()
            {
            return item_icon;
            }
        }

    public class child_menu_class
        {
        private String item_id;
        private String item_title;
        private String item_icon;
        private boolean selected;
        private String type;
        private List<child_menu_class> childs_list = new ArrayList<>();

        public child_menu_class(connect conn, String item_id, String item_title, String item_icon, boolean selected, String type)
            {
            this.item_id = item_id;
            this.item_title = item_title;
            this.item_icon = item_icon;
            this.selected = selected;
            this.type = type;

            if(type.equalsIgnoreCase("Parent"))
                {
                ResultSet rs = null;
                try
                    {
                    sql = "select icon as item_icon,item_id as item_id,item_title as item_title,0 as is_selected ,(select count(*) from menu_items chi where chi.item_parent=to_char(mi.item_id)) as is_parent from menu_items mi where upper(item_parent)='" + this.item_id + "' order by upper(item_title) ";
                    if(group_id != null && !group_id.trim().equals(""))
                        sql = "select icon as item_icon,item_id as item_id,item_title as item_title,(select count(*) from access_group_items ii where ii.item_id=mi.item_id and ii.group_id=" + group_id + ") as is_selected ,(select count(*) from menu_items chi where chi.item_parent=to_char(mi.item_id)) as is_parent from menu_items mi where upper(item_parent)='" + this.item_id + "' order by upper(item_title) ";

                    rs = conn.query(sql);
                    while(rs.next())
                        {
                        childs_list.add(new child_menu_class(conn, rs.getString("item_id"), rs.getString("item_title"), rs.getString("item_icon"), rs.getInt("is_selected") > 0, rs.getInt("is_parent") > 0 ? "Parent" : "Leaf"));
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
                }
            }

        public void setItem_id(String item_id)
            {
            this.item_id = item_id;
            }

        public String getItem_id()
            {
            return item_id;
            }

        public void setItem_title(String item_title)
            {
            this.item_title = item_title;
            }

        public String getItem_title()
            {
            return item_title;
            }

        public void setItem_icon(String item_icon)
            {
            this.item_icon = item_icon;
            }

        public String getItem_icon()
            {
            return item_icon;
            }

        public void setSelected(boolean selected)
            {
            this.selected = selected;
            }

        public boolean isSelected()
            {
            return selected;
            }

        public void setType(String type)
            {
            this.type = type;
            }

        public void setChilds_list(List<Access_group_menu.child_menu_class> childs_list)
            {
            this.childs_list = childs_list;
            }

        public List<Access_group_menu.child_menu_class> getChilds_list()
            {
            return childs_list;
            }

        public String getType()
            {
            return type;
            }
        }

    public void setGroup_id(String group_id)
        {
        this.group_id = group_id;
        }

    public String getGroup_id()
        {
        return group_id;
        }

    public void setMain_list(List<Access_group_menu.main_menu_class> main_list)
        {
        this.main_list = main_list;
        }

    public List<Access_group_menu.main_menu_class> getMain_list()
        {
        return main_list;
        }

    public void setSql(String sql)
        {
        this.sql = sql;
        }

    public String getSql()
        {
        return sql;
        }

    public void setGroup_title(String group_title)
        {
        this.group_title = group_title;
        }

    public String getGroup_title()
        {
        return group_title;
        }

    public void setTemp_parent_child_list(List<Access_group_menu.child_menu_class> temp_parent_child_list)
        {
        this.temp_parent_child_list = temp_parent_child_list;
        }

    public List<Access_group_menu.child_menu_class> getTemp_parent_child_list()
        {
        return temp_parent_child_list;
        }

    public void setTemp_leaf_child_list(List<Access_group_menu.child_menu_class> temp_leaf_child_list)
        {
        this.temp_leaf_child_list = temp_leaf_child_list;
        }

    public List<Access_group_menu.child_menu_class> getTemp_leaf_child_list()
        {
        return temp_leaf_child_list;
        }

    public void setCurrent_main_menu(String current_main_menu)
        {
        this.current_main_menu = current_main_menu;
        }

    public String getCurrent_main_menu()
        {
        return current_main_menu;
        }

    public void setProcess_type(String process_type)
        {
        this.process_type = process_type;
        }

    public String getProcess_type()
        {
        return process_type;
        }

    public void setCurrent_item_id(String current_item_id)
        {
        this.current_item_id = current_item_id;
        }

    public String getCurrent_item_id()
        {
        return current_item_id;
        }

    public void setCurrent_item_icon(String current_item_icon)
        {
        this.current_item_icon = current_item_icon;
        }

    public String getCurrent_item_icon()
        {
        return current_item_icon;
        }

    }
