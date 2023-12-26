package tms.beans.administration.access;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.model.SelectItem;
import jakarta.inject.Inject;
import java.sql.PreparedStatement;
import java.sql.Clob;
import tms.beans.common.ExcelExporter;
import tms.beans.common.ExcelPdfRows;
import tms.beans.common.msgr;
import tms.db.connect;
import tms.beans.users.user_details;
import tms.common.common_functions;

@Named
@SessionScoped

public class users_access_groups implements Serializable
    {
    //Add/Edit Variables
    @Inject
    private user_details user_details;

    private String user_id;
    private String display_name;
    //Search Variables
    private String search_status;
    private String search_department;
    private List<SelectItem> search_departments_list = new ArrayList<>();
    private String search_name;
    private String search_access_group;
    private List<SelectItem> GroupsList = new ArrayList<>();
    private List<user_class> users_list = new ArrayList<>();
    private List<SelectItem> search_access_group_list = new ArrayList<>();
    private String[] selected_access_groups;
    private List<SelectItem> access_groups_list = new ArrayList<>();
    private String user_id_signature;
    private String user_name_signature;
    private String user_signature;
    private List<access_groups> approved_access_groups_list = new ArrayList<>();

    @PostConstruct
    public void init()
        {
        connect conn = new connect();
        search_departments_list = conn.SQLList("select department_id,department_name from departments order by 2");
        GroupsList = conn.SQLList("select group_id,group_name||decode(group_status,1,'(Active)',0,'(Not Active)') from access_groups order by 2");
        conn.close();

        Search();
        }

    @PreDestroy
    public void destroy()
        {

        }

    public void Search()
        {
        connect conn = new connect();
        users_list.clear();
        search_departments_list = conn.SQLList("select department_id,department_name from departments order by 2");
        String sql = "select department_name as u_department_name,uu.user_id as u_id,uu.username as u_username,uu.password as u_password,uu.first_name as u_first_name,\n"
                + "uu.mid_name as u_mid_name,uu.last_name as u_last_name,uu.display_name as u_display_name,uu.email u_email,nvl(to_char(last_login_date,'dd/mm/yyyy hh:mi AM'),' ') as last_login,\n"
                + "uu.mobile  as u_mobile,uu.photo_path as photo_path,uu.user_status as status,(select count(*) from task_assigned_users tt where tt.assigned_user_id=uu.user_id) tasks_count,(select count(*) from task_assigned_users tt where tt.assigned_user_id=uu.user_id and completed_percentage=100) completed_tasks_count\n"
                + " from departments dd,users uu where uu.department_id=dd.department_id ";
        if(search_department != null && !search_department.trim().equals(""))
            sql += " and uu.department_id=" + search_department;
        if(search_name != null && !search_name.trim().equals(""))
            sql += " and ( upper(uu.first_name||' '||nvl(uu.mid_name,' ')||' '||uu.last_name) like '%" + search_name.toUpperCase().replaceAll("'", "''") + "%' or upper(uu.display_name) like '%" + search_name.toUpperCase().replaceAll("'", "''") + "%' )";
        if(search_status != null && !search_status.trim().equals(""))
            sql += " and uu.user_status=" + search_status;

        if(search_access_group != null && !search_access_group.trim().equals(""))
            sql += " and uu.user_id in (select gg.user_id from user_access_group gg where gg.group_id=" + search_access_group + ")";
        sql += " order by uu.display_name";

        try(ResultSet rs = conn.query(sql))
            {
            while(rs.next())
                {
                users_list.add(new user_class(conn, rs.getString("u_id"), rs.getString("u_username"), rs.getString("u_password"), rs.getString("u_first_name"), rs.getString("u_mid_name"), rs.getString("u_last_name"), rs.getString("u_display_name"), rs.getString("u_department_name"), rs.getString("u_email"), rs.getString("last_login"), rs.getString("u_mobile"), rs.getString("photo_path"), rs.getInt("status") == 1, rs.getInt("tasks_count"), rs.getInt("completed_tasks_count")));
                }

            }
        catch(SQLException sqlEx)
            {
            }

        conn.close();

        }

    public void export_to_excel()
        {
        //header=TableHeaderGrades
        String[] TableHeader = new String[]
            {
            "First Name",
            "Mid Name",
            "Last Name",
            "Display Name",
            "Department Name",
            "Email",
            "Mobile",
            "Last Login Time",
            "User Status",
            "Skills",
            "Tasks#",
            "Account Username"

            };

        List<String[]> ExcelList = new ArrayList<>();
        for(user_class data : users_list)
            ExcelList.add(data.getArray());
        ExcelExporter exporter = new ExcelExporter(FacesContext.getCurrentInstance());
        List<ExcelPdfRows> rowsList = new ArrayList<>();
        rowsList.add(new ExcelPdfRows("", ExcelList));
        exporter.export_table(rowsList, "Users.xls", "Users", TableHeader, "List of Users");

        }

    public void close_dialog()
        {
        common_functions.getInstance().close_dialog();
        }

    public void save_access_groups()
        {
        connect conn = new connect();
        try
            {
            for(String gid : selected_access_groups)
                conn.insert("insert into user_access_group (user_id,group_id) values(" + user_id + "," + gid + ")");
            msgr.info("The access groups have been updated...");
            load_user_access_groups(conn);
            access_groups_list = conn.SQLList("select group_id,group_name from access_groups where group_status=1 and group_id not in (select uu.group_id from user_access_group uu where uu.user_id=" + user_id + ") order by 2");
            }
        catch(Exception exp)
            {
            msgr.error("Sorry, Unable to update users access groups....:" + exp.toString());
            }

        conn.close();

        }

    public void save_user_signature()
        {
        connect conn = new connect();
        try
            {
            Clob myClob = conn.getConn().createClob();
            myClob.setString(1, user_signature);
            PreparedStatement pstmt = conn.getConn().prepareStatement("update users set user_signature=? where user_id=?");
            pstmt.setClob(1, myClob);
            pstmt.setString(2, user_id_signature);
            pstmt.executeUpdate();
            msgr.info("The user signature has been updated successfully........");
            Search();
            }
        catch(Exception exp)
            {
            msgr.error("Error (" + this.getClass().toString() + ") :" + exp.toString());
            }
        conn.close();
        }

    public void clear_and_save_user_signature()
        {
        user_signature = "";
        connect conn = new connect();

        try
            {
            conn.update("update users set user_signature=? where user_id=?", new String[]
                {
                user_signature, user_id_signature
                });
            msgr.info("The user signature has been cleared successfully........");
            Search();
            }
        catch(Exception exp)
            {
            msgr.error("Error (" + this.getClass().toString() + ") :" + exp.toString());
            }
        conn.close();

        }

    private void load_user_access_groups(connect conn)
        {
        approved_access_groups_list.clear();
        try(ResultSet rs = conn.query("select gg.group_id as g_id,group_name as g_title,decode(group_status,1,'Active',0,'Disabled') as g_status ,(select count(*) from user_access_group ouu where ouu.user_id<>uu.user_id and ouu.group_id=gg.group_id) as other_users,(select count(*) from access_group_items itm where itm.group_id=gg.group_id)  as items_count from access_groups gg,user_access_group uu where  gg.group_id = uu.group_id  and uu.user_id=" + user_id + " order by g_title"))
            {
            while(rs.next())
                {
                approved_access_groups_list.add(new access_groups(rs.getString("g_id"), rs.getString("g_title"), rs.getString("g_status"), rs.getInt("other_users"), rs.getInt("items_count")));
                }
            }
        catch(Exception ee)
            {
            msgr.error("Error: " + ee.getMessage());
            }

        }

    public class access_groups
        {
        private String g_id;
        private String g_title;
        private String g_status;
        private int other_users;
        private int items_count;

        public access_groups(String g_id, String g_title, String g_status, int other_users, int items_count)
            {
            this.g_id = g_id;
            this.g_title = g_title;
            this.g_status = g_status;
            this.other_users = other_users;
            this.items_count = items_count;
            }

        public void delete()
            {
            connect conn = new connect();
            try
                {
                conn.delete("delete from user_access_group where user_id=" + user_id + " and group_id=" + g_id);
                msgr.info("The access groups have been removed...");
                load_user_access_groups(conn);
                access_groups_list = conn.SQLList("select group_id,group_name from access_groups where group_status=1 and group_id not in (select uu.group_id from user_access_group uu where uu.user_id=" + user_id + ") order by 2");
                }
            catch(Exception exp)
                {
                msgr.error("Sorry, Unable to remove the access groups....:" + exp.toString());
                }

            conn.close();

            }

        public int getItems_count()
            {
            return items_count;
            }

        public void setItems_count(int items_count)
            {
            this.items_count = items_count;
            }

        public String getG_id()
            {
            return g_id;
            }

        public void setG_id(String g_id)
            {
            this.g_id = g_id;
            }

        public String getG_title()
            {
            return g_title;
            }

        public void setG_title(String g_title)
            {
            this.g_title = g_title;
            }

        public String getG_status()
            {
            return g_status;
            }

        public void setG_status(String g_status)
            {
            this.g_status = g_status;
            }

        public int getOther_users()
            {
            return other_users;
            }

        public void setOther_users(int other_users)
            {
            this.other_users = other_users;
            }

        }

    public class user_class
        {
        private String u_id;
        private String u_username;
        private String u_password;
        private String u_first_name;
        private String u_mid_name;
        private String u_last_name;
        private String u_display_name;
        private String u_department_name;
        private String u_email;
        private String last_login;
        private String u_mobile;
        private String photo_path;
        private boolean status;
        private String skills;
        private int tasks_count;
        private int completed_tasks_count;

        private boolean deletable;

        public user_class(connect conn, String u_id, String u_username, String u_password, String u_first_name, String u_mid_name, String u_last_name, String u_display_name, String u_department_name, String u_email, String last_login, String u_mobile, String photo_path, boolean status, int tasks_count, int completed_tasks_count)
            {
            this.u_id = u_id;
            this.u_username = u_username;
            this.u_password = u_password;
            this.u_first_name = u_first_name;
            this.u_mid_name = u_mid_name;
            this.u_last_name = u_last_name;
            this.u_display_name = u_display_name;
            this.u_department_name = u_department_name;
            this.u_email = u_email;
            this.last_login = last_login;
            this.u_mobile = u_mobile;
            this.photo_path = photo_path;
            this.status = status;

            this.tasks_count = tasks_count;
            deletable = tasks_count == 0;
            this.completed_tasks_count = completed_tasks_count;
            skills = "";
            try(ResultSet rs = conn.query("select ss.skill_short_name as skill_short_name,ss.skill_title as skill_title,ss.notes as notes ,ss.skill_color as skill_color from user_skills us,skills ss where ss.skill_id=us.skill_id and us.user_id=" + this.u_id + " order by ss.skill_title"))
                {
                while(rs.next())
                    {
                    skills += "<span title=\"" + rs.getString("notes") + "\" style=\"margin-top:3px;width:110px;background-color:" + rs.getString("skill_color") + "\" class=\"ui-tag ui-widget mr-2\"><span class=\"ui-tag-value\">" + rs.getString("skill_title") + "</span></span><br/>";
                    }
                }
            catch(Exception ee)
                {
                msgr.error("Error: " + ee.getMessage());
                }

            }

        public void open_groups_dialog()
            {
            connect conn = new connect();
            selected_access_groups = null;
            user_id = this.u_id;
            display_name = this.u_display_name;
            //update list of access groups
            access_groups_list = conn.SQLList("select group_id,group_name from access_groups where group_status=1 and group_id not in (select uu.group_id from user_access_group uu where uu.user_id=" + this.u_id + ") order by 2");
            load_user_access_groups(conn);
            conn.close();

            common_functions.getInstance().open_dialog("add_edit_user_groups.xhtml", 850, 600);

            }

        public String login_as_user()
            {
            connect conn = new connect();
            user_details.prepare_user(conn, this.u_id);
            conn.close();
            return "/dashboard";
            }

        public String[] getArray()
            {
            return new String[]
                {
                u_first_name,
                u_mid_name,
                u_last_name,
                u_display_name,
                u_department_name,
                u_email,
                u_mobile,
                last_login,
                status ? "Active" : "Disabled",
                skills,
                tasks_count + "",
                u_username
                };
            }

        public int getCompleted_tasks_count()
            {
            return completed_tasks_count;
            }

        public void setCompleted_tasks_count(int completed_tasks_count)
            {
            this.completed_tasks_count = completed_tasks_count;
            }

        public String getU_id()
            {
            return u_id;
            }

        public void setU_id(String u_id)
            {
            this.u_id = u_id;
            }

        public String getU_username()
            {
            return u_username;
            }

        public void setU_username(String u_username)
            {
            this.u_username = u_username;
            }

        public String getU_password()
            {
            return u_password;
            }

        public void setU_password(String u_password)
            {
            this.u_password = u_password;
            }

        public String getU_first_name()
            {
            return u_first_name;
            }

        public void setU_first_name(String u_first_name)
            {
            this.u_first_name = u_first_name;
            }

        public String getU_mid_name()
            {
            return u_mid_name;
            }

        public void setU_mid_name(String u_mid_name)
            {
            this.u_mid_name = u_mid_name;
            }

        public String getU_last_name()
            {
            return u_last_name;
            }

        public void setU_last_name(String u_last_name)
            {
            this.u_last_name = u_last_name;
            }

        public String getU_display_name()
            {
            return u_display_name;
            }

        public void setU_display_name(String u_display_name)
            {
            this.u_display_name = u_display_name;
            }

        public String getU_department_name()
            {
            return u_department_name;
            }

        public void setU_department_name(String u_department_name)
            {
            this.u_department_name = u_department_name;
            }

        public String getU_email()
            {
            return u_email;
            }

        public void setU_email(String u_email)
            {
            this.u_email = u_email;
            }

        public String getLast_login()
            {
            return last_login;
            }

        public void setLast_login(String last_login)
            {
            this.last_login = last_login;
            }

        public String getU_mobile()
            {
            return u_mobile;
            }

        public void setU_mobile(String u_mobile)
            {
            this.u_mobile = u_mobile;
            }

        public String getPhoto_path()
            {
            return photo_path;
            }

        public void setPhoto_path(String photo_path)
            {
            this.photo_path = photo_path;
            }

        public boolean isStatus()
            {
            return status;
            }

        public void setStatus(boolean status)
            {
            this.status = status;
            }

        public String getSkills()
            {
            return skills;
            }

        public void setSkills(String skills)
            {
            this.skills = skills;
            }

        public int getTasks_count()
            {
            return tasks_count;
            }

        public void setTasks_count(int tasks_count)
            {
            this.tasks_count = tasks_count;
            }

        public boolean isDeletable()
            {
            return deletable;
            }

        public void setDeletable(boolean deletable)
            {
            this.deletable = deletable;
            }

        }

    public user_details getUser_details()
        {
        return user_details;
        }

    public void setUser_details(user_details user_details)
        {
        this.user_details = user_details;
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

    public String getSearch_status()
        {
        return search_status;
        }

    public void setSearch_status(String search_status)
        {
        this.search_status = search_status;
        }

    public String getSearch_department()
        {
        return search_department;
        }

    public void setSearch_department(String search_department)
        {
        this.search_department = search_department;
        }

    public List<SelectItem> getSearch_departments_list()
        {
        return search_departments_list;
        }

    public void setSearch_departments_list(List<SelectItem> search_departments_list)
        {
        this.search_departments_list = search_departments_list;
        }

    public String getSearch_name()
        {
        return search_name;
        }

    public void setSearch_name(String search_name)
        {
        this.search_name = search_name;
        }

    public String getSearch_access_group()
        {
        return search_access_group;
        }

    public void setSearch_access_group(String search_access_group)
        {
        this.search_access_group = search_access_group;
        }

    public List<SelectItem> getGroupsList()
        {
        return GroupsList;
        }

    public void setGroupsList(List<SelectItem> GroupsList)
        {
        this.GroupsList = GroupsList;
        }

    public List<user_class> getUsers_list()
        {
        return users_list;
        }

    public void setUsers_list(List<user_class> users_list)
        {
        this.users_list = users_list;
        }

    public List<SelectItem> getSearch_access_group_list()
        {
        return search_access_group_list;
        }

    public void setSearch_access_group_list(List<SelectItem> search_access_group_list)
        {
        this.search_access_group_list = search_access_group_list;
        }

    public String[] getSelected_access_groups()
        {
        return selected_access_groups;
        }

    public void setSelected_access_groups(String[] selected_access_groups)
        {
        this.selected_access_groups = selected_access_groups;
        }

    public List<SelectItem> getAccess_groups_list()
        {
        return access_groups_list;
        }

    public void setAccess_groups_list(List<SelectItem> access_groups_list)
        {
        this.access_groups_list = access_groups_list;
        }

    public String getUser_id_signature()
        {
        return user_id_signature;
        }

    public void setUser_id_signature(String user_id_signature)
        {
        this.user_id_signature = user_id_signature;
        }

    public String getUser_name_signature()
        {
        return user_name_signature;
        }

    public void setUser_name_signature(String user_name_signature)
        {
        this.user_name_signature = user_name_signature;
        }

    public String getUser_signature()
        {
        return user_signature;
        }

    public void setUser_signature(String user_signature)
        {
        this.user_signature = user_signature;
        }

    public List<access_groups> getApproved_access_groups_list()
        {
        return approved_access_groups_list;
        }

    public void setApproved_access_groups_list(List<access_groups> approved_access_groups_list)
        {
        this.approved_access_groups_list = approved_access_groups_list;
        }

    }
