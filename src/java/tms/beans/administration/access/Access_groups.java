package tms.beans.administration.access;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import org.primefaces.model.TreeNode;
import tms.beans.common.ExcelExporter;
import tms.beans.common.ExcelPdfRows;
import tms.beans.common.msgr;
import tms.common.common_functions;
import tms.db.connect;

@Named("access_groups")
@SessionScoped
public class Access_groups implements Serializable
    {
    @Inject
    private Access_group_menu access_group_menu;

    @Inject
    private Access_group_preview access_group_preview;

    private List<Users_class> users_list = new ArrayList<>();

    private String search_group;

    private String process_type;
    private TreeNode root;
    private TreeNode[] selectedNodes;

    private List<DataClass> Table = new ArrayList<>();

    String sql = "";

    public Access_groups()
        {

        }

    @PostConstruct
    public void init()
        {
        Search();
        }

    @PreDestroy
    public void destroy()
        {

        }

    public void ValueChanged()
        {
        Search();
        }

    public void Search()
        {
        connect conn = new connect();
        Table.clear();
        try
            {
            String sql = "select gg.group_id as group_id,gg.group_name as group_name,gg.group_status as group_status,(select count(*) from access_group_items itm where itm.group_id=gg.group_id) as itm_count,(select count(*) from user_access_group uu where uu.group_id=gg.group_id) as user_count from access_groups gg ";
            if(search_group != null && !search_group.trim().equals(""))
                sql += " where (upper(gg.group_name) like '%" + search_group.toUpperCase() + "%' or gg.group_id in (select acc.group_id from user_access_group acc,users uu where acc.user_id=uu.user_id and upper(uu.first_name||' '||uu.last_name) like '%" + search_group.toUpperCase() + "%'  ) )";
            sql += " order by gg.group_name";
            ResultSet rs = conn.query(sql);
            while(rs.next())
                {
                Table.add(new DataClass(rs.getString("group_id"), rs.getString("group_name"), rs.getInt("group_status"), rs.getInt("user_count"), rs.getInt("itm_count"), rs.getInt("user_count") == 0));
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("Error (" + this.getClass().toString() + ") :" + exp.toString());
            }
        conn.close();

        }

    public void add_new()
        {
        process_type = "new";
        access_group_menu.start_menu("", "", process_type);

        common_functions.getInstance().open_dialog("add_edit_access_group", true, 850, 700, false, false, true, false);
        }

    public void export_to_excel()
        {
        //header=TableHeaderGrades
        String[] TableHeader = new String[]
            {
            "Access Group Name", "Group Status", "No. of Users", "No. of Items"
            };
        List<String[]> ExcelList = new ArrayList<>();
        for(DataClass data : Table)
            ExcelList.add(data.get_Array());
        ExcelExporter exporter = new ExcelExporter(FacesContext.getCurrentInstance());
        List<ExcelPdfRows> rowsList = new ArrayList<>();
        rowsList.add(new ExcelPdfRows("", ExcelList));
        exporter.export_table(rowsList, "Access_Groups.xls", "Access Groups", TableHeader, "List Of Access Groups");
        ExcelList = null;
        rowsList = null;
        }

    public class DataClass
        {
        private String group_id;
        private String group_name;
        private int status;
        private int users_no;
        private int items_no;
        private boolean status_boolean;
        private boolean deletable;

        public DataClass(String group_id, String group_name, int status, int users_no, int items_no, boolean deletable)
            {
            this.group_id = group_id;
            this.group_name = group_name;
            this.status = status;
            this.users_no = users_no;
            this.items_no = items_no;
            this.deletable = deletable;
            if(group_id.trim().equals("1")) //main admin group

                this.deletable = false;
            this.status_boolean = status == 1;
            }

        public void edit()
            {
            process_type = "update";
            access_group_menu.start_menu(this.group_id, this.group_name, process_type);
            common_functions.getInstance().open_dialog("add_edit_access_group", true, 850, 700, false, false, true, false);
            }

        public void delete_group()
            {
            connect conn = new connect();

            try
                {
                conn.setAutoCommit(false);
                conn.delete("delete from access_group_items where group_id=" + this.group_id);
                conn.delete("delete from access_groups where group_id=" + this.group_id);
                conn.commit();
                msgr.info("The access group has been removed successfully.......");
                Search();
                }
            catch(Exception exp)
                {
                conn.rollback();
                msgr.error("Unable to remove the access group : " + exp.toString());
                }

            conn.setAutoCommit(true);
            conn.close();

            }

        public void change_status()
            {
            connect conn = new connect();

            try
                {
                if(status == 1)
                    {
                    conn.update("update access_groups set group_status=0 where group_id=" + this.group_id);
                    msgr.info("The access group has been disabled successfully.......");
                    this.status = 0;
                    status_boolean = false;
                    }
                else
                    {
                    conn.update("update access_groups set group_status=1 where group_id=" + this.group_id);
                    msgr.info("The access group has been enabled successfully.......");
                    this.status = 1;
                    status_boolean = true;
                    }
                }
            catch(Exception exp)
                {
                msgr.error("Unable to enable the access group : " + exp.toString());
                }

            conn.close();

            }

        public void show_user_list(String gid)
            {
            this.group_id = gid;

            find_users_list();
            }

        public void find_users_list()
            {
            users_list.clear();

            connect conn = new connect();
            try
                {
                sql = "select gg.user_id as user_id,uu.first_name||' '||uu.last_name as user_name ,acc.group_name from  user_access_group gg,users uu,access_groups acc where uu.user_id=gg.user_id  and  acc.group_id=gg.group_id and gg.group_id=" + this.group_id;
                ResultSet rs = conn.query(sql);
                while(rs.next())
                    {
                    users_list.add(new Users_class(rs.getString("user_id"), rs.getString("user_name")));
                    }

                }
            catch(Exception exp)
                {
                System.out.println("Error (" + this.getClass().toString() + ") :" + exp.toString());
                }
            common_functions.getInstance().open_dialog("access_group_users_list", true, 500, 300, false, false, true, true);
            conn.close();
            }

        public String[] get_Array()
            {
            return new String[]
                {
                group_name, status == 1 ? "Enabled" : "Disabled", String.valueOf(users_no), String.valueOf(items_no)
                };
            }

        public void preview()
            {
            access_group_preview.start(this.group_id, "");
            }

        /*
        public void access_groups_nodes()
               {
               root =new DefaultTreeNode(new ItemsClass("", "Menu Header", "-1", 1), null);
               connect conn = new connect();

               //get main items
               try{
                  sql="select item_id as item_id,item_title as item_title,nvl(default_order,0) as default_order,0 as is_selected from menu_items mi where upper(item_parent)='MAIN' order by default_order ";
                  if(group_id!=null&&!group_id.trim().equals(""))
                      sql="select item_id as item_id,item_title as item_title,nvl(nvl((select min(ii.item_order) from access_group_items ii where ii.item_id=mi.item_id and ii.group_id="+group_id+"),mi.default_order),0) as default_order,(select count(*) from access_group_items ii where ii.item_id=mi.item_id and ii.group_id="+group_id+") as is_selected from menu_items mi where upper(item_parent)='MAIN' order by mi.default_order ";
                  ResultSet rs=conn.query(sql);
                  while(rs.next())
                      {
                      TreeNode Top = new DefaultTreeNode(new ItemsClass("MAIN", rs.getString("item_title"), rs.getString("item_id"), rs.getInt("default_order")), root);
                      if(rs.getInt("is_selected")>0)
                      Top.setSelected(true);
                      //call childs
                      access_groups_subnodes(Top,rs.getString("item_id"));
                          Top.setExpanded(true);
                      }
                  rs.close();
                  }catch(Exception exp){System.out.print("Error : "+exp.toString());}
               conn.close();

               }
            public void access_groups_subnodes(TreeNode parent,String parent_id)
                 {
                connect conn = new connect();
                 try{

                    sql="select item_id as item_id,item_title as item_title,nvl(default_order,0) as default_order,0 as is_selected from menu_items mi where upper(item_parent)='"+parent_id+"' order by default_order ";
                    if(group_id!=null&&!group_id.trim().equals(""))
                            sql="select item_id as item_id,item_title as item_title,nvl(nvl((select min(ii.item_order) from access_group_items ii where ii.item_id=mi.item_id and ii.group_id="+group_id+"),default_order),0) as default_order,(select count(*) from access_group_items ii where ii.item_id=mi.item_id and ii.group_id="+group_id+") as is_selected from menu_items mi where upper(item_parent)='"+parent_id+"' order by default_order ";
                        ResultSet rs=conn.query(sql);
                        while(rs.next())
                            {
                            TreeNode Top = new DefaultTreeNode(new ItemsClass(parent_id, rs.getString("item_title"), rs.getString("item_id"), rs.getInt("default_order")), parent);
                            if(rs.getInt("is_selected")>0)
                               Top.setSelected(true);
                            else if(parent.isSelected()) //not selected, set parent as parial selected
                                {
                               // parent.setSelected(false);
                                parent.setPartialSelected(true);
                                }
                            //call childs
                            access_groups_subnodes(Top,rs.getString("item_id"));
                            }
                        rs.close();
                        }catch(Exception exp){System.out.print("Error : "+exp.toString());}
                     conn.close();

                 }
         */
        public void setGroup_id(String group_id)
            {
            this.group_id = group_id;
            }

        public String getGroup_id()
            {
            return group_id;
            }

        public void setStatus_boolean(boolean status_boolean)
            {
            this.status_boolean = status_boolean;
            }

        public boolean isStatus_boolean()
            {
            return status_boolean;
            }

        public void setGroup_name(String group_name)
            {
            this.group_name = group_name;
            }

        public String getGroup_name()
            {
            return group_name;
            }

        public void setStatus(int status)
            {
            this.status = status;
            }

        public int getStatus()
            {
            return status;
            }

        public void setUsers_no(int users_no)
            {
            this.users_no = users_no;
            }

        public int getUsers_no()
            {
            return users_no;
            }

        public void setItems_no(int items_no)
            {
            this.items_no = items_no;
            }

        public int getItems_no()
            {
            return items_no;
            }

        public void setDeletable(boolean deletable)
            {
            this.deletable = deletable;
            }

        public boolean isDeletable()
            {
            return deletable;
            }

        }

    public class Users_class
        {
        private String user_code;
        private String user_name;

        public Users_class(String user_code, String user_name)
            {
            this.user_code = user_code;
            this.user_name = user_name;

            }

        public String getUser_code()
            {
            return user_code;
            }

        public void setUser_code(String user_code)
            {
            this.user_code = user_code;
            }

        public String getUser_name()
            {
            return user_name;
            }

        public void setUser_name(String user_name)
            {
            this.user_name = user_name;
            }

        }

    public void setSearch_group(String search_group)
        {
        this.search_group = search_group;
        }

    public String getSearch_group()
        {
        return search_group;
        }

    public void setTable(List<Access_groups.DataClass> Table)
        {
        this.Table = Table;
        }

    public List<Access_groups.DataClass> getTable()
        {
        return Table;
        }

    public void setRoot(TreeNode root)
        {
        this.root = root;
        }

    public TreeNode getRoot()
        {
        return root;
        }

    public void setSelectedNodes(TreeNode[] selectedNodes)
        {
        this.selectedNodes = selectedNodes;
        }

    public TreeNode[] getSelectedNodes()
        {
        return selectedNodes;
        }

    public void setSql(String sql)
        {
        this.sql = sql;
        }

    public String getSql()
        {
        return sql;
        }

    public void setAccess_group_menu(Access_group_menu access_group_menu)
        {
        this.access_group_menu = access_group_menu;
        }

    public Access_group_menu getAccess_group_menu()
        {
        return access_group_menu;
        }

    public void setAccess_group_preview(Access_group_preview access_group_preview)
        {
        this.access_group_preview = access_group_preview;
        }

    public Access_group_preview getAccess_group_preview()
        {
        return access_group_preview;
        }

    public void setProcess_type(String process_type)
        {
        this.process_type = process_type;
        }

    public String getProcess_type()
        {
        return process_type;
        }

    public List<Users_class> getUsers_list()
        {
        return users_list;
        }

    public void setUsers_list(List<Users_class> users_list)
        {
        this.users_list = users_list;
        }

    }
