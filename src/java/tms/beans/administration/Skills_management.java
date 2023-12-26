package tms.beans.administration;

import java.io.Serializable;
import jakarta.inject.Named;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import org.apache.commons.text.WordUtils;
import tms.beans.common.msgr;
import tms.common.common_functions;
import tms.db.connect;

@Named
@SessionScoped
public class Skills_management implements Serializable
    {
    private List<skill_class> skills_list = new ArrayList<>();
    private String skill_id;
    private String skill_title;
    private String skill_short_name;
    private String skill_color;
    private String notes;

    private String process_type;

    @PostConstruct
    public void init()
        {
        Search();
        }

    public void open_new_window()
        {
        skill_id = "";
        skill_title = "";
        skill_short_name = "";
        skill_color = "";
        notes = "";
        process_type = "new";
        common_functions.getInstance().open_dialog("add_edit_skill", 600, 500);
        }

    public void close_dialog()
        {
        common_functions.getInstance().close_dialog();
        }

    public void Search()
        {
        connect conn = new connect();

        skills_list.clear();
        try
            {
            ResultSet rs = conn.query("select ss.skill_id as s_id,ss.skill_title s_title,ss.skill_short_name as s_short_name,nvl(ss.notes,' ') as s_notes,ss.skill_color as s_color,ss.skill_status status,(select count(*) from user_skills uu where uu.skill_id=ss.skill_id) as users_count,(select count(*) from task_required_skills rr where rr.skill_id=ss.skill_id) as tasks_count from skills ss order by s_title");
            while(rs.next())
                {
                skills_list.add(new skill_class(rs.getString("s_id"), rs.getString("s_title"), rs.getString("s_short_name"), rs.getString("s_notes"), rs.getString("s_color"), rs.getInt("status") == 1, rs.getInt("users_count"), rs.getInt("tasks_count")));
                }
            rs.close();
            if(skills_list.isEmpty())
                msgr.warn("No Data Found....");
            }
        catch(Exception exp)
            {
            msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
            }
        conn.close();
        }

    public boolean validate(connect conn)
        {
        boolean valid = true;
        if(common_functions.getInstance().check_duplication(conn, "skills", "skill_title", skill_title, "skill_id", skill_id) > 0)
            {
            valid = false;
            msgr.error("There is another skill with the same title....");
            }
        if(common_functions.getInstance().check_duplication(conn, "skills", "skill_short_name", skill_short_name, "skill_id", skill_id) > 0)
            {
            valid = false;
            msgr.error("There is another skill with the same short name....");
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
                skill_id = conn.get_max_id("skills", "skill_id");
                conn.insert("insert into skills (SKILL_ID,SKILL_TITLE,SKILL_SHORT_NAME,NOTES,SKILL_COLOR,SKILL_STATUS) values(?,?,?,?,?,?)", new String[]
                    {
                    skill_id, WordUtils.capitalizeFully(skill_title.trim().toLowerCase()), skill_short_name.trim().toUpperCase(), notes, skill_color, "1"
                    });
                msgr.info("The skill has been inserted successfully....");
                skill_id = "";
                skill_title = "";
                skill_short_name = "";
                skill_color = "";
                notes = "";
                process_type = "new";
                }
            catch(Exception exp)
                {
                msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
                }
            else
                try
                {
                conn.update("update skills set SKILL_TITLE=?,SKILL_SHORT_NAME=?,NOTES=?,SKILL_COLOR=? where SKILL_ID=?", new String[]
                    {
                    WordUtils.capitalizeFully(skill_title.trim().toLowerCase()), skill_short_name.trim().toUpperCase(), notes, skill_color, skill_id
                    });
                msgr.info("The skill has been updated successfully....");
                }
            catch(Exception exp)
                {
                msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
                }
        Search();
        conn.close();
        }

    public class skill_class
        {
        private String s_id;
        private String s_title;
        private String s_short_name;
        private String s_notes;
        private String s_color;
        private boolean status;
        private int users_count;
        private int tasks_count;
        private boolean deletable;

        public skill_class(String s_id, String s_title, String s_short_name, String s_notes, String s_color, boolean status, int users_count, int tasks_count)
            {
            this.s_id = s_id;
            this.s_title = s_title;
            this.s_short_name = s_short_name;
            this.s_notes = s_notes;
            this.s_color = s_color;
            this.users_count = users_count;
            this.tasks_count = tasks_count;
            this.status = status;
            this.deletable = (users_count + tasks_count) == 0;
            }

        public void edit()
            {
            process_type = "update";
            skill_id = this.s_id;
            skill_title = this.s_title;
            skill_short_name = this.s_short_name;
            skill_color = this.s_color;
            notes = this.s_notes;
            common_functions.getInstance().open_dialog("add_edit_skill", 600, 500);
            }

        public void change_status()
            {
            connect conn = new connect();
            try
                {
                conn.update("update skills set skill_status=" + (this.status ? "1" : "0") + " where skill_id=" + this.s_id);
                msgr.info("Skill status has been updated......");
                Search();
                }
            catch(Exception ee)
                {
                msgr.error("Error: " + ee.getMessage());
                }

            conn.close();

            }

        public void delete()
            {
            connect conn = new connect();
            try
                {
                conn.delete("delete from skills where skill_id=" + this.s_id);
                msgr.info("The skill has been removed successfully.....");
                Search();
                }
            catch(Exception exp)
                {
                msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
                }
            conn.close();
            }

        public String getS_id()
            {
            return s_id;
            }

        public void setS_id(String s_id)
            {
            this.s_id = s_id;
            }

        public String getS_title()
            {
            return s_title;
            }

        public void setS_title(String s_title)
            {
            this.s_title = s_title;
            }

        public String getS_short_name()
            {
            return s_short_name;
            }

        public void setS_short_name(String s_short_name)
            {
            this.s_short_name = s_short_name;
            }

        public String getS_notes()
            {
            return s_notes;
            }

        public void setS_notes(String s_notes)
            {
            this.s_notes = s_notes;
            }

        public String getS_color()
            {
            return s_color;
            }

        public void setS_color(String s_color)
            {
            this.s_color = s_color;
            }

        public boolean isStatus()
            {
            return status;
            }

        public void setStatus(boolean status)
            {
            this.status = status;
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

    public List<skill_class> getSkills_list()
        {
        return skills_list;
        }

    public void setSkills_list(List<skill_class> skills_list)
        {
        this.skills_list = skills_list;
        }

    public String getSkill_id()
        {
        return skill_id;
        }

    public void setSkill_id(String skill_id)
        {
        this.skill_id = skill_id;
        }

    public String getSkill_title()
        {
        return skill_title;
        }

    public void setSkill_title(String skill_title)
        {
        this.skill_title = skill_title;
        }

    public String getSkill_short_name()
        {
        return skill_short_name;
        }

    public void setSkill_short_name(String skill_short_name)
        {
        this.skill_short_name = skill_short_name;
        }

    public String getSkill_color()
        {
        return skill_color;
        }

    public void setSkill_color(String skill_color)
        {
        this.skill_color = skill_color;
        }

    public String getNotes()
        {
        return notes;
        }

    public void setNotes(String notes)
        {
        this.notes = notes;
        }

    public String getProcess_type()
        {
        return process_type;
        }

    public void setProcess_type(String process_type)
        {
        this.process_type = process_type;
        }

    }
