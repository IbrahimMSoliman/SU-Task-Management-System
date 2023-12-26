package tms.beans.tasks;

/**
 *
 * @author hima
 */
import java.io.Serializable;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.model.SelectItem;
import jakarta.inject.Inject;
import java.util.Date;
import org.apache.commons.text.WordUtils;
import tms.beans.common.msgr;
import tms.common.common_functions;
import tms.db.connect;
import java.sql.ResultSet;
import tms.beans.users.user_details;

@Named
@SessionScoped
public class My_tasks implements Serializable
    {
    @Inject
    private user_details user_details;

    private List<task_class> tasks_list = new ArrayList<>();
    private String task_id;
    private String task_title;
    private String task_description;
    private String task_notes;
    private Date task_start_date;
    private Date task_end_date;
    private List<Date> task_duration;
    private Date task_deadline_date;
    private String priority_id;
    private List<SelectItem> priorities_list;
    private String department_id;
    private List<SelectItem> departments_list;
    private String skill_id[];
    private List<SelectItem> skills_list;
    private String process_type;
    private List<notes_class> notes_list;

    private boolean editable;

    @PostConstruct
    public void init()
        {
        Search();
        }

    public void reset()
        {
        task_id = "";
        task_title = "";
        task_description = "";
        task_notes = "";
        task_start_date = null;
        task_duration = null;
        task_end_date = null;
        task_deadline_date = null;
        priority_id = "";
        department_id = "";
        skill_id = null;
        skills_list = null;
        notes_list = new ArrayList<>();
        }

    public void update_task_duration()
        {
        task_start_date = null;
        task_end_date = null;
        if(task_duration != null && task_duration.size() > 1)
            {
            task_start_date = task_duration.get(0);
            task_end_date = task_duration.get(1);
            }
        }

    public void open_new_window()
        {
        reset();
        process_type = "new";
        editable = true;
        connect conn = new connect();
        priorities_list = conn.SQLList("select pp.priority_id,pp.priority_title from priorities pp where pp.priority_status=1 order by pp.priority_level");
        departments_list = conn.SQLList("select pp.department_id,pp.department_name from departments pp order by 2");
        skills_list = conn.SQLList("select pp.skill_id,pp.skill_title from skills pp where pp.skill_status=1 order by 2");

        conn.close();
        common_functions.getInstance().open_dialog("task_info", 800, 700);
        }

    public void close_dialog()
        {
        common_functions.getInstance().close_dialog();
        }

    public void Search()
        {
        connect conn = new connect();

        tasks_list.clear();
        try
            {
            ResultSet rs = conn.query("select  \n"
                    + "tt.task_id as t_id,barcode as barcode,tt.task_title as t_title,tt.task_description as t_description,\n"
                    + "nvl(tt.task_notes,' ') as t_notes,to_char(tt.task_start_date,'dd/mm/yyyy') as t_start_date,to_char(tt.task_end_date,'dd/mm/yyyy') as t_end_date,\n"
                    + "to_char(tt.SUBMIT_DEADLINE,'dd/mm/yyyy')   as t_deadline_date,(select pp.priority_title from priorities pp where pp.priority_id=tt.priority_id) as priority_title,\n"
                    + "(select pp.priority_color from priorities pp where pp.priority_id=tt.priority_id) as priority_color,(select dd.department_name from departments dd where dd.department_id=tt.department_id)  as t_dep_name,\n"
                    + "tt.task_status as t_status,uu.display_name as added_by,tt.adding_date as adding_date,uu.photo_path add_user_photo_path\n"
                    + " from task tt,users uu where uu.user_id=tt.added_by and tt.task_id in (select ss.task_id from task_assigned_users ss where ASSIGNED_USER_ID=" + user_details.getUser_id() + ") order by tt.adding_date desc");
            while(rs.next())
                {
                tasks_list.add(new task_class(conn, rs.getString("t_id"), rs.getString("barcode"), rs.getString("t_title"), rs.getString("t_description"), rs.getString("t_notes"), rs.getString("t_start_date"), rs.getString("t_end_date"), rs.getString("t_deadline_date"), rs.getString("priority_title"), rs.getString("priority_color"), rs.getString("t_dep_name"), rs.getString("t_status"), rs.getString("added_by"), rs.getDate("adding_date"), rs.getString("add_user_photo_path")));
                }
            rs.close();
            if(tasks_list.isEmpty())
                msgr.warn("No Data Found....");
            }
        catch(Exception exp)
            {
            msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
            }
        conn.close();
        }

    public void save()
        {

        connect conn = new connect();
        if(process_type.trim().equalsIgnoreCase("NEW"))
            try
            {
            task_id = conn.get_max_id("task", "task_id");
            double barcode = Double.parseDouble(task_id) * 13521485475f;
            conn.insert("insert into task (task_id,task_title,task_description,task_notes,added_by,adding_date,task_status,task_start_date,task_end_date,submit_deadline,priority_id,department_id,barcode) values(?,?,?,?,?,sysdate,?,?,?,?,?,?,?)", new Object[]
                {
                task_id, WordUtils.capitalizeFully(task_title.toLowerCase()), task_description, task_notes, user_details.getUser_id(), "New", task_start_date, task_end_date, task_deadline_date, priority_id, department_id, barcode
                });
            //add skills
            for(String sid : skill_id)
                conn.insert("insert into task_required_skills (task_id,skill_id) values(?,?)", new Object[]
                    {
                    task_id, sid
                    }
                );
            //add notes
            int index = 0;
            for(notes_class notecls : notes_list)
                conn.insert("insert into task_notes (task_id,note,NOTE_ORDER) values(?,?,?)", new Object[]
                    {
                    task_id, notecls.note, ++index
                    }
                );
            msgr.info("The task has been inserted successfully....");
            reset();
            process_type = "new";
            }
        catch(Exception exp)
            {
            msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
            }
        else
                try
            {
            conn.update("update task set task_title=?,task_description=?,task_notes=?,task_start_date=?,task_end_date=?,submit_deadline=?,priority_id=?,department_id=? where task_id=?", new Object[]
                {
                WordUtils.capitalizeFully(task_title.toLowerCase()), task_description, task_notes, task_start_date, task_end_date, task_deadline_date, priority_id, department_id, task_id
                });
            //add skills
            conn.delete("delete from task_required_skills where task_id=" + task_id);
            for(String sid : skill_id)
                conn.insert("insert into task_required_skills (task_id,skill_id) values(?,?)", new Object[]
                    {
                    task_id, sid
                    }
                );
            //add notes
            int index = 0;
            conn.delete("delete from task_notes where task_id=" + task_id);
            for(notes_class notecls : notes_list)
                conn.insert("insert into task_notes (task_id,note,NOTE_ORDER) values(?,?,?)", new Object[]
                    {
                    task_id, notecls.note, ++index
                    }
                );
            msgr.info("The task has been updated successfully....");
            }
        catch(Exception exp)
            {
            msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
            }
        Search();
        conn.close();
        }

    public void add_new_note()
        {
        notes_list.add(new notes_class(""));
        }

    public class notes_class
        {
        private String note;

        public notes_class(String note)
            {
            this.note = note;
            }

        public void delete()
            {
            notes_list.remove(this);
            }

        public String getNote()
            {
            return note;
            }

        public void setNote(String note)
            {
            this.note = note;
            }

        }

    public class user_class
        {
        private String display_name;
        private String photo_path;

        public user_class(String display_name, String photo_path)
            {
            this.display_name = display_name;
            this.photo_path = photo_path;
            }

        public String getDisplay_name()
            {
            return display_name;
            }

        public void setDisplay_name(String display_name)
            {
            this.display_name = display_name;
            }

        public String getPhoto_path()
            {
            return photo_path;
            }

        public void setPhoto_path(String photo_path)
            {
            this.photo_path = photo_path;
            }

        }

    public class task_class
        {
        private String t_id;
        private String barcode;
        private String t_title;
        private String t_description;
        private String t_notes;
        private String t_start_date;
        private String t_end_date;
        private String t_deadline_date;
        private String priority_title;
        private String priority_color;
        private String t_dep_name;
        private String t_status;
        private String added_by;
        private Date adding_date;
        private String add_user_photo_path;
        private String skills;
        private List<user_class> assigned_users_list = new ArrayList<>();
        private boolean deletable;

        public task_class(connect conn, String t_id, String barcode, String t_title, String t_description, String t_notes, String t_start_date, String t_end_date, String t_deadline_date, String priority_title, String priority_color, String t_dep_name, String t_status, String added_by, Date adding_date, String add_user_photo_path)
            {
            this.t_id = t_id;
            this.barcode = barcode;
            this.t_title = t_title;
            this.t_description = t_description;
            this.t_notes = t_notes;
            this.t_start_date = t_start_date;
            this.t_end_date = t_end_date;
            this.t_deadline_date = t_deadline_date;
            this.priority_title = priority_title;
            this.priority_color = priority_color;
            this.t_dep_name = t_dep_name;
            this.t_status = t_status;
            this.added_by = added_by;
            this.adding_date = adding_date;
            this.add_user_photo_path = add_user_photo_path;
            this.deletable = t_status.equalsIgnoreCase("NEW");
            //skills
            skills = "<div style=\"display:inline\">";
            try(ResultSet rs = conn.query("select ss.skill_short_name as skill_short_name,ss.skill_title as skill_title,ss.notes as notes ,ss.skill_color as skill_color from task_required_skills us,skills ss where ss.skill_id=us.skill_id and us.task_id=" + this.t_id + " order by ss.skill_title"))
                {
                while(rs.next())
                    {
                    skills += "<span title=\"" + rs.getString("notes") + "\" style=\"margin-top:3px;width:110px;background-color:" + rs.getString("skill_color") + "\" class=\"ui-tag ui-widget mr-2\"><span class=\"ui-tag-value\">" + rs.getString("skill_title") + "</span></span> ";
                    }
                }
            catch(Exception ee)
                {
                msgr.error("Error: " + ee.getMessage());
                }
            skills = skills + "</div>";

            //Assigned users
            try(ResultSet rs = conn.query("select uu.display_name,uu.photo_path from users uu,task_assigned_users tt where tt.task_id=" + this.t_id + " and tt.assigned_user_id=uu.user_id order by 1"))
                {
                while(rs.next())
                    {
                    assigned_users_list.add(new user_class(rs.getString("display_name"), rs.getString("photo_path")));
                    }
                }
            catch(Exception ee)
                {
                msgr.error("Error: " + ee.getMessage());
                }

            }

        public void edit()
            {
            process_type = "update";
            editable = false;
            reset();
            connect conn = new connect();
            priorities_list = conn.SQLList("select pp.priority_id,pp.priority_title from priorities pp where pp.priority_status=1 or upper(pp.priority_title)='" + priority_title.toUpperCase() + "' order by pp.priority_level");
            departments_list = conn.SQLList("select pp.department_id,pp.department_name from departments pp order by 2");
            skills_list = conn.SQLList("select pp.skill_id,pp.skill_title from skills pp where pp.skill_status=1 or pp.skill_id in (select ss.skill_id from task_required_skills ss where ss.task_id=" + this.t_id + ") order by 2");
            task_id = this.t_id;
            try(ResultSet rs = conn.query("select task_title as task_title,tt.task_description as task_description,nvl(tt.task_notes,' ') as task_notes,tt.task_start_date as task_start_date,tt.task_end_date as task_end_date,tt.submit_deadline as task_deadline_date,tt.priority_id as priority_id,tt.department_id as department_id,nvl((select LISTAGG(rr.skill_id,',') within group (order by 1) from task_required_skills rr where rr.task_id=tt.task_id),' ') as skillid from task tt where task_id=" + this.t_id))
                {
                while(rs.next())
                    {
                    task_title = rs.getString("task_title");
                    task_description = rs.getString("task_description");
                    task_notes = rs.getString("task_notes");
                    task_start_date = rs.getDate("task_start_date");
                    task_end_date = rs.getDate("task_end_date");
                    task_duration = new ArrayList<>();
                    if(task_start_date != null)
                        task_duration.add(task_start_date);
                    if(task_end_date != null)
                        task_duration.add(task_end_date);
                    task_deadline_date = rs.getDate("task_deadline_date");
                    priority_id = rs.getString("priority_id");
                    department_id = rs.getString("department_id");

                    skill_id = rs.getString("skillid").trim().split(",");
                    //private List<notes_class> notes_list;

                    //notes
                    notes_list = new ArrayList<>();
                    try(ResultSet rs_note = conn.query("select nn.note from task_notes nn where nn.task_id=" + this.t_id + " order by nn.note_order"))
                        {
                        while(rs_note.next())
                            {
                            notes_list.add(new notes_class(rs_note.getString("note")));
                            }
                        }
                    catch(Exception ee)
                        {
                        msgr.error("Error: " + ee.getMessage());
                        }

                    }
                }
            catch(Exception ee)
                {
                msgr.error("Error: " + ee.getMessage());
                }
            //load notes
            notes_list = new ArrayList<>();
            try(ResultSet rs = conn.query("select nn.note as note from task_notes nn where nn.task_id=" + task_id + " order by note_order"))
                {
                while(rs.next())
                    {
                    notes_list.add(new notes_class(rs.getString("note")));
                    }
                }
            catch(Exception ee)
                {
                msgr.error("Error: " + ee.getMessage());
                }

            conn.close();

            common_functions.getInstance().open_dialog("task_info", 800, 700);
            }

        public void delete()
            {
            connect conn = new connect();
            conn.setAutoCommit(false);
            try
                {
                conn.delete("delete from task_notes where task_id=" + this.t_id);
                conn.delete("delete from TASK_REQUIRED_SKILLS where task_id=" + this.t_id);
                conn.delete("delete from task where task_id=" + this.t_id);
                conn.commit();
                msgr.info("The Priority has been removed successfully.....");
                Search();
                }
            catch(Exception exp)
                {
                conn.rollback();
                msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
                }
            conn.close();
            }

        public List<user_class> getAssigned_users_list()
            {
            return assigned_users_list;
            }

        public void setAssigned_users_list(List<user_class> assigned_users_list)
            {
            this.assigned_users_list = assigned_users_list;
            }

        public String getSkills()
            {
            return skills;
            }

        public void setSkills(String skills)
            {
            this.skills = skills;
            }

        public String getAdded_by()
            {
            return added_by;
            }

        public void setAdded_by(String added_by)
            {
            this.added_by = added_by;
            }

        public Date getAdding_date()
            {
            return adding_date;
            }

        public void setAdding_date(Date adding_date)
            {
            this.adding_date = adding_date;
            }

        public String getAdd_user_photo_path()
            {
            return add_user_photo_path;
            }

        public void setAdd_user_photo_path(String add_user_photo_path)
            {
            this.add_user_photo_path = add_user_photo_path;
            }

        public String getBarcode()
            {
            return barcode;
            }

        public void setBarcode(String barcode)
            {
            this.barcode = barcode;
            }

        public String getT_title()
            {
            return t_title;
            }

        public void setT_title(String t_title)
            {
            this.t_title = t_title;
            }

        public String getT_id()
            {
            return t_id;
            }

        public void setT_id(String t_id)
            {
            this.t_id = t_id;
            }

        public String getT_description()
            {
            return t_description;
            }

        public void setT_description(String t_description)
            {
            this.t_description = t_description;
            }

        public String getT_notes()
            {
            return t_notes;
            }

        public void setT_notes(String t_notes)
            {
            this.t_notes = t_notes;
            }

        public String getT_start_date()
            {
            return t_start_date;
            }

        public void setT_start_date(String t_start_date)
            {
            this.t_start_date = t_start_date;
            }

        public String getT_end_date()
            {
            return t_end_date;
            }

        public void setT_end_date(String t_end_date)
            {
            this.t_end_date = t_end_date;
            }

        public String getT_deadline_date()
            {
            return t_deadline_date;
            }

        public void setT_deadline_date(String t_deadline_date)
            {
            this.t_deadline_date = t_deadline_date;
            }

        public String getPriority_title()
            {
            return priority_title;
            }

        public void setPriority_title(String priority_title)
            {
            this.priority_title = priority_title;
            }

        public String getPriority_color()
            {
            return priority_color;
            }

        public void setPriority_color(String priority_color)
            {
            this.priority_color = priority_color;
            }

        public String getT_dep_name()
            {
            return t_dep_name;
            }

        public void setT_dep_name(String t_dep_name)
            {
            this.t_dep_name = t_dep_name;
            }

        public String getT_status()
            {
            return t_status;
            }

        public void setT_status(String t_status)
            {
            this.t_status = t_status;
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

    public List<task_class> getTasks_list()
        {
        return tasks_list;
        }

    public void setTasks_list(List<task_class> tasks_list)
        {
        this.tasks_list = tasks_list;
        }

    public String getTask_id()
        {
        return task_id;
        }

    public void setTask_id(String task_id)
        {
        this.task_id = task_id;
        }

    public String getTask_description()
        {
        return task_description;
        }

    public void setTask_description(String task_description)
        {
        this.task_description = task_description;
        }

    public String getTask_notes()
        {
        return task_notes;
        }

    public void setTask_notes(String task_notes)
        {
        this.task_notes = task_notes;
        }

    public Date getTask_start_date()
        {
        return task_start_date;
        }

    public void setTask_start_date(Date task_start_date)
        {
        this.task_start_date = task_start_date;
        }

    public Date getTask_end_date()
        {
        return task_end_date;
        }

    public void setTask_end_date(Date task_end_date)
        {
        this.task_end_date = task_end_date;
        }

    public Date getTask_deadline_date()
        {
        return task_deadline_date;
        }

    public void setTask_deadline_date(Date task_deadline_date)
        {
        this.task_deadline_date = task_deadline_date;
        }

    public String getPriority_id()
        {
        return priority_id;
        }

    public void setPriority_id(String priority_id)
        {
        this.priority_id = priority_id;
        }

    public String getDepartment_id()
        {
        return department_id;
        }

    public void setDepartment_id(String department_id)
        {
        this.department_id = department_id;
        }

    public String getProcess_type()
        {
        return process_type;
        }

    public void setProcess_type(String process_type)
        {
        this.process_type = process_type;
        }

    public List<SelectItem> getPriorities_list()
        {
        return priorities_list;
        }

    public void setPriorities_list(List<SelectItem> priorities_list)
        {
        this.priorities_list = priorities_list;
        }

    public List<SelectItem> getDepartments_list()
        {
        return departments_list;
        }

    public void setDepartments_list(List<SelectItem> departments_list)
        {
        this.departments_list = departments_list;
        }

    public String getTask_title()
        {
        return task_title;
        }

    public void setTask_title(String task_title)
        {
        this.task_title = task_title;
        }

    public List<Date> getTask_duration()
        {
        return task_duration;
        }

    public void setTask_duration(List<Date> task_duration)
        {
        this.task_duration = task_duration;
        }

    public String[] getSkill_id()
        {
        return skill_id;
        }

    public void setSkill_id(String[] skill_id)
        {
        this.skill_id = skill_id;
        }

    public List<SelectItem> getSkills_list()
        {
        return skills_list;
        }

    public void setSkills_list(List<SelectItem> skills_list)
        {
        this.skills_list = skills_list;
        }

    public List<notes_class> getNotes_list()
        {
        return notes_list;
        }

    public void setNotes_list(List<notes_class> notes_list)
        {
        this.notes_list = notes_list;
        }

    public boolean isEditable()
        {
        return editable;
        }

    public void setEditable(boolean editable)
        {
        this.editable = editable;
        }

    public user_details getUser_details()
        {
        return user_details;
        }

    public void setUser_details(user_details user_details)
        {
        this.user_details = user_details;
        }

    }
