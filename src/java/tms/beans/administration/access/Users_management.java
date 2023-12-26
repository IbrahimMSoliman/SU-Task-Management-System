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
import org.apache.commons.codec.binary.Base64;
import java.io.File;
import org.apache.commons.io.FileUtils;
import org.apache.commons.text.WordUtils;
import tms.TAGS.Image_editor_model;
import tms.beans.common.ExcelExporter;
import tms.beans.common.ExcelPdfRows;
import tms.beans.common.msgr;
import tms.db.connect;
import tms.beans.users.user_details;
import tms.common.Images_functions;
import tms.common.common_functions;

@Named("users_management")
@SessionScoped

public class Users_management implements Serializable
    {
    //Add/Edit Variables
    @Inject
    private user_details user_details;

    private String user_id;
    private String first_name;
    private String last_name;
    private String mid_name;
    private String display_name;
    private String username;
    private String password;

    private String department_id;
    private String email;
    private String mobile;
    private String process_type;
    private String photo_path;

    //Search Variables
    private String search_status;
    private String search_department;
    private List<SelectItem> search_departments_list = new ArrayList<>();
    private String search_name;
    private String search_access_group;
    private List<SelectItem> GroupsList = new ArrayList<>();
    private List<user_class> users_list = new ArrayList<>();
    private List<SelectItem> skills_list;
    private String user_skills[];
    //photo
    private Image_editor_model image_model;
    private String base64_image;

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
        skills_list = conn.SQLList("select skill_id,skill_title||'('||skill_short_name||')' from skills where skill_status=1 order by 2");
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

    private void reset()
        {
        first_name = "";
        last_name = "";
        mid_name = "";
        display_name = "";
        username = "";
        password = "";
        department_id = "";
        email = "";
        mobile = "";
        user_skills = null;
        }

    public void new_user()
        {
        connect conn = new connect();
        process_type = "ADD_NEW";
        reset();
        common_functions.getInstance().open_dialog("add_edit_user", 800, 500);
        conn.close();
        }

    private boolean validation(connect conn)
        {
        if(common_functions.getInstance().check_duplication(conn, "users", "display", display_name, "user_id", user_id) > 0)
            {
            msgr.error("There is another user with the same display name....");
            return false;
            }

        if(common_functions.getInstance().check_duplication(conn, "users", "username", username, "user_id", user_id) > 0)
            {
            msgr.error("There is another user with the same username....");
            return false;
            }
        return true;
        }

    public void save_user()
        {
        connect conn = new connect();

        if(validation(conn))
            {
            //add new user
            if(process_type.equalsIgnoreCase("ADD_NEW"))
                try
                {
                user_id = conn.get_max_id("users", "user_id");
                conn.insert("insert into users (USER_ID,DEPARTMENT_ID,USERNAME,PASSWORD,FIRST_NAME,MID_NAME,LAST_NAME,DISPLAY_NAME,EMAIL,MOBILE,USER_STATUS,PHOTO_PATH) values (?,?,?,?,?,?,?,?,?,?,?,?)",
                        new String[]
                            {
                            user_id, department_id, username, password, WordUtils.capitalizeFully(first_name.toLowerCase().trim()), WordUtils.capitalizeFully(mid_name.toLowerCase().trim()), WordUtils.capitalizeFully(last_name.toLowerCase().trim()), WordUtils.capitalizeFully(display_name.toLowerCase().trim()), email, mobile, "1", "TMS_Files/users_avatar/unknown.png"
                            });
                //add skills
                for(String sid : user_skills)
                    conn.insert("insert into user_skills (user_id,skill_id) values(?,?)", new Object[]
                        {
                        user_id, sid
                        }
                    );

                msgr.info("The user details have been added successfully.......");
                //Reset Data
                process_type = "ADD_NEW";
                reset();
                }
            catch(Exception exp)
                {
                user_id = "";
                msgr.error("Error: Unable to add new user :" + exp.toString());
                }
            //update users
            else
                try
                {
                conn.update("update users set DEPARTMENT_ID=?,USERNAME=?,PASSWORD=?,FIRST_NAME=?,MID_NAME=?,LAST_NAME=?,DISPLAY_NAME=?,EMAIL=?,MOBILE=? where USER_ID=?",
                        new String[]
                            {
                            department_id, username, password, WordUtils.capitalizeFully(first_name.toLowerCase().trim()), WordUtils.capitalizeFully(mid_name.toLowerCase().trim()), WordUtils.capitalizeFully(last_name.toLowerCase().trim()), WordUtils.capitalizeFully(display_name.toLowerCase().trim()), email, mobile, user_id
                            });
                //add skills
                conn.delete("delete from user_skills where user_id=" + user_id);
                for(String sid : user_skills)
                    conn.insert("insert into user_skills (user_id,skill_id) values(?,?)", new Object[]
                        {
                        user_id, sid
                        }
                    );
                msgr.info("The user details have been updated successfully.......");
                }
            catch(Exception exp)
                {
                msgr.error("Error: Unable to update the user :" + exp.toString());
                }

            Search();
            }
        conn.close();
        }

    public void save_user_photo()
        {
        connect conn = new connect();
        //copy file with new path
        try
            {
            String real_path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("") + "TMS_Files" + File.separator + "users_avatar" + File.separator + user_id + ".jpg";
            String relative_path = "TMS_Files/users_avatar/" + user_id + ".jpg";
            File file = new File(real_path);
            if(file.exists())
                file.delete();

            if(image_model.getImageBase64() != null)
                {

                byte image_bytes[] = Base64.decodeBase64(image_model.getImageBase64().substring(image_model.getImageBase64().indexOf(",") + 1));

                FileUtils.writeByteArrayToFile(file, image_bytes);
                conn.update("update users set photo_path=? where user_id=?", new Object[]
                    {
                    relative_path, user_id
                    });
                msgr.info("Course image has been updated....");
                }
            else
                msgr.error("Error: Unable to update course image....");

            }
        catch(Exception ee)
            {
            msgr.error("Error :" + ee.getMessage());
            }
        conn.close();
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

        public void open_user_photo()
            {
            user_id = this.u_id;
            display_name = this.u_display_name;
            photo_path = FacesContext.getCurrentInstance().getExternalContext().getRealPath(this.photo_path);

            base64_image = Images_functions.encodeFileToBase64Binary(photo_path);
            image_model = new Image_editor_model();
            image_model.setImageBase64(base64_image);

            common_functions.getInstance().open_dialog("update_user_photo", true, 800, 750, false, false, true, false);
            }

        public void edit()
            {
            connect conn = new connect();
            user_id = this.u_id;
            skills_list = conn.SQLList("select skill_id,skill_title||'('||skill_short_name||')' from skills ss where (ss.skill_status=1 or ss.skill_id in (select uu.skill_id from user_skills uu where uu.user_id=" + this.u_id + "))order by 2");
            process_type = "UPDATE_USER";
            reset();
            try
                {
                ResultSet rs = conn.query("select nvl(uu.mobile,' ') as mobile,nvl(uu.email ,' ') as email,uu.department_id as department_id,uu.first_name as first_name,uu.last_name as last_name,uu.mid_name as mid_name,uu.display_name as display_name,uu.username as username,uu.password as password ,nvl((select LISTAGG(ss.skill_id,',') within group (order by 1) from user_skills ss where ss.user_id=" + this.u_id + "),' ') as user_skill from users uu where user_id=" + user_id);
                while(rs.next())
                    {
                    first_name = rs.getString("first_name");
                    last_name = rs.getString("last_name");
                    mid_name = rs.getString("mid_name");
                    display_name = rs.getString("display_name");
                    username = rs.getString("username");
                    password = rs.getString("password");
                    department_id = rs.getString("department_id");
                    email = rs.getString("email").trim();
                    mobile = rs.getString("mobile").trim();
                    user_skills = rs.getString("user_skill").trim().split(",");
                    }
                rs.close();
                common_functions.getInstance().open_dialog("add_edit_user", 800, 500);
                }
            catch(SQLException sqlEx)
                {
                msgr.error("Error :" + sqlEx.getMessage());
                }
            conn.close();

            }

        public void delete_user()
            {
            connect conn = new connect();
            try
                {
                conn.setAutoCommit(false);
                conn.update("delete from user_access_group where user_id=" + this.u_id);
                conn.update("delete from user_skills where user_id=" + this.u_id);
                conn.update("delete from users where user_id=" + this.u_id);
                msgr.info("The user has been removed successfully........");
                conn.commit();
                Search();
                }
            catch(Exception exp)
                {
                conn.rollback();
                msgr.error("Sorry, Unable to remove users (Note: SIS Disabled all access groups for that user))");
                }
            conn.setAutoCommit(true);

            conn.close();

            }

        public void update_user_status()
            {
            connect conn = new connect();
            try
                {
                conn.update("update users set user_status=" + (status ? "1" : "0") + " where user_id=" + this.u_id);
                msgr.info("The user status has been updated successfully........");
                Search();
                }
            catch(SQLException sqlEx)
                {
                msgr.error("Unable to change users status........:" + sqlEx.toString());
                }
            conn.close();

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

    public String getFirst_name()
        {
        return first_name;
        }

    public void setFirst_name(String first_name)
        {
        this.first_name = first_name;
        }

    public String getLast_name()
        {
        return last_name;
        }

    public void setLast_name(String last_name)
        {
        this.last_name = last_name;
        }

    public String getMid_name()
        {
        return mid_name;
        }

    public void setMid_name(String mid_name)
        {
        this.mid_name = mid_name;
        }

    public String getDisplay_name()
        {
        return display_name;
        }

    public void setDisplay_name(String display_name)
        {
        this.display_name = display_name;
        }

    public String getUsername()
        {
        return username;
        }

    public void setUsername(String username)
        {
        this.username = username;
        }

    public String getPassword()
        {
        return password;
        }

    public void setPassword(String password)
        {
        this.password = password;
        }

    public String getDepartment_id()
        {
        return department_id;
        }

    public void setDepartment_id(String department_id)
        {
        this.department_id = department_id;
        }

    public String getEmail()
        {
        return email;
        }

    public void setEmail(String email)
        {
        this.email = email;
        }

    public String getMobile()
        {
        return mobile;
        }

    public void setMobile(String mobile)
        {
        this.mobile = mobile;
        }

    public String getProcess_type()
        {
        return process_type;
        }

    public void setProcess_type(String process_type)
        {
        this.process_type = process_type;
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

    public String getPhoto_path()
        {
        return photo_path;
        }

    public void setPhoto_path(String photo_path)
        {
        this.photo_path = photo_path;
        }

    public List<SelectItem> getSkills_list()
        {
        return skills_list;
        }

    public void setSkills_list(List<SelectItem> skills_list)
        {
        this.skills_list = skills_list;
        }

    public String[] getUser_skills()
        {
        return user_skills;
        }

    public void setUser_skills(String[] user_skills)
        {
        this.user_skills = user_skills;
        }

    public Image_editor_model getImage_model()
        {
        return image_model;
        }

    public void setImage_model(Image_editor_model image_model)
        {
        this.image_model = image_model;
        }

    public String getBase64_image()
        {
        return base64_image;
        }

    public void setBase64_image(String base64_image)
        {
        this.base64_image = base64_image;
        }

    }
