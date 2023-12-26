package tms.beans.administration;

import java.io.Serializable;
import jakarta.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.model.SelectItem;
import tms.beans.common.msgr;
import tms.common.common_functions;
import tms.db.connect;

@Named("departments")
@SessionScoped
public class Departments implements Serializable
    {
    private static final long serialVersionUID = 1L;

    private List<departments_class> departments_list = new ArrayList<>();

    private String department_id = "";
    private String department_name = "";
    private String department_short_name = "";
    private String department_location = "";

    private String process_type;

    public Departments()
        {

        }

    @PostConstruct
    public void init()
        {
        connect conn = new connect();

        load_list(conn);
        conn.close();

        }

    public void open_new_window()
        {
        department_id = "";
        department_name = "";
        department_short_name = "";
        department_location = "";
        process_type = "new";
        common_functions.getInstance().open_dialog("add_edit_departments", 450, 320);
        }

    public void close_dialog()
        {
        common_functions.getInstance().close_dialog();
        }

    public void refresh_list()
        {

        connect conn = new connect();
        load_list(conn);
        conn.close();

        }

    public void load_list(connect conn)
        {
        departments_list.clear();
        try
            {
            ResultSet rs = conn.query("select dd.department_id as department_id,dd.department_name as department_name,dd.short_name as department_short_name,nvl(dd.location,' ') as department_location,(select count(*) from users uu where uu.department_id=dd.department_id) as users_count,(select count(*) from task tt where tt.department_id=dd.department_id) as tasks_count from departments dd order by dd.department_name");
            while(rs.next())
                {
                departments_list.add(new departments_class(rs.getString("department_id"), rs.getString("department_name"), rs.getString("department_short_name"), rs.getString("department_location"), rs.getInt("users_count"), rs.getInt("tasks_count")));
                }
            rs.close();
            if(departments_list.isEmpty())
                msgr.warn("No Data Found....");
            }
        catch(Exception exp)
            {
            msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
            }
        }

    public boolean validate(connect conn)
        {
        boolean valid = true;
        if(common_functions.getInstance().check_duplication(conn, "departments", "department_name", department_name, "department_id", department_id) > 0)
            {
            valid = false;
            msgr.error("There is another department with the same title....");
            }

        return valid;
        }

    public void save()
        {

        connect conn = new connect();
        if(validate(conn))
            if(process_type.trim().equalsIgnoreCase("NEW"))
                try
                {
                department_id = conn.get_max_id("departments", "department_id");
                conn.insert("insert into departments (DEPARTMENT_ID,DEPARTMENT_NAME,SHORT_NAME,LOCATION) values(?,?,?,?)", new String[]
                    {
                    department_id, department_name, department_short_name, department_location
                    });
                msgr.info("The department has been inserted successfully....");
                department_id = "";
                department_name = "";
                department_short_name = "";
                department_location = "";
                process_type = "new";
                }
            catch(Exception exp)
                {
                department_id = "";
                msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
                }
            else
                try
                {
                conn.update("update departments set DEPARTMENT_NAME=?,SHORT_NAME=?,LOCATION=? where DEPARTMENT_ID=?", new String[]
                    {
                    department_name, department_short_name, department_location, department_id
                    });
                msgr.info("The department has been updated successfully....");
                }
            catch(Exception exp)
                {
                msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
                }
        conn.close();
        }

    public class departments_class
        {
        private String dep_id;
        private String dep_name;
        private String dep_short_name;
        private String dep_location;
        private int users_count;
        private int tasks_count;
        private boolean deletable;

        public departments_class(String dep_id, String dep_name, String dep_short_name, String dep_location, int users_count, int tasks_count)
            {
            this.dep_id = dep_id;
            this.dep_name = dep_name;
            this.dep_short_name = dep_short_name;
            this.dep_location = dep_location;
            this.users_count = users_count;
            this.tasks_count = tasks_count;
            this.deletable = (users_count + tasks_count) == 0;
            }

        public void edit()
            {
            process_type = "update";
            connect conn = new connect();
            department_id = this.dep_id;
            try
                {
                ResultSet rs = conn.query("select dd.department_id as department_id,dd.department_name as department_name,dd.short_name as department_short_name,nvl(dd.location,' ') as department_location from departments dd where dd.department_id=" + department_id);
                while(rs.next())
                    {
                    department_name = rs.getString("department_name");
                    department_short_name = rs.getString("department_short_name");
                    department_location = rs.getString("department_location");
                    }
                rs.close();
                }
            catch(Exception exp)
                {
                System.out.println("Error :" + exp.toString());
                }

            conn.close();

            common_functions.getInstance().open_dialog("add_edit_departments", 450, 320);
            }

        public void delete()
            {

            if(this.deletable)
                {
                connect conn = new connect();
                try
                    {
                    conn.delete("delete from departments where department_id=" + this.dep_id);
                    msgr.info("The department room has been removed successfully.....");
                    load_list(conn);
                    }
                catch(Exception exp)
                    {
                    msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
                    }
                conn.close();

                }
            }

        public String getDep_id()
            {
            return dep_id;
            }

        public void setDep_id(String dep_id)
            {
            this.dep_id = dep_id;
            }

        public String getDep_name()
            {
            return dep_name;
            }

        public void setDep_name(String dep_name)
            {
            this.dep_name = dep_name;
            }

        public String getDep_short_name()
            {
            return dep_short_name;
            }

        public void setDep_short_name(String dep_short_name)
            {
            this.dep_short_name = dep_short_name;
            }

        public String getDep_location()
            {
            return dep_location;
            }

        public void setDep_location(String dep_location)
            {
            this.dep_location = dep_location;
            }

        public int getUsers_count()
            {
            return users_count;
            }

        public void setUsers_count(int users_count)
            {
            this.users_count = users_count;
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

    public void setDepartments_list(List<Departments.departments_class> departments_list)
        {
        this.departments_list = departments_list;
        }

    public List<Departments.departments_class> getDepartments_list()
        {
        return departments_list;
        }

    public void setDepartment_id(String department_id)
        {
        this.department_id = department_id;
        }

    public String getDepartment_id()
        {
        return department_id;
        }

    public void setDepartment_name(String department_name)
        {
        this.department_name = department_name;
        }

    public String getDepartment_name()
        {
        return department_name;
        }

    public void setDepartment_short_name(String department_short_name)
        {
        this.department_short_name = department_short_name;
        }

    public String getDepartment_short_name()
        {
        return department_short_name;
        }

    public void setDepartment_location(String department_location)
        {
        this.department_location = department_location;
        }

    public String getDepartment_location()
        {
        return department_location;
        }

    public void setProcess_type(String process_type)
        {
        this.process_type = process_type;
        }

    public String getProcess_type()
        {
        return process_type;
        }
    }
