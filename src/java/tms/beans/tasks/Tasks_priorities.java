package tms.beans.tasks;

/**
 *
 * @author hima
 */
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
public class Tasks_priorities implements Serializable
    {
    private List<priority_class> priority_list = new ArrayList<>();
    private String priority_id;
    private String priority_level;
    private String priority_title;
    private String priority_short_name;
    private String priority_color;
    private String priority_description;
    private String priority_notes;

    private String process_type;

    @PostConstruct
    public void init()
        {
        Search();
        }

    public void open_new_window()
        {
        priority_id = "";
        priority_level = "";
        priority_title = "";
        priority_short_name = "";
        priority_color = "";
        priority_description = "";
        priority_notes = "";
        process_type = "new";
        common_functions.getInstance().open_dialog("add_edit_priorities", 800, 450);
        }

    public void close_dialog()
        {
        common_functions.getInstance().close_dialog();
        }

    public void Search()
        {
        connect conn = new connect();

        priority_list.clear();
        try
            {
            ResultSet rs = conn.query("select pp.priority_id as p_id,pp.priority_level as p_level,pp.priority_title as p_title,pp.priority_short_name as p_short_name,pp.priority_color as p_color,nvl(pp.priority_description,' ') as p_description,nvl(pp.priority_notes,' ') as p_notes,pp.priority_status as status,(select count(*) from task tt where tt.PRIORITY_ID=pp.PRIORITY_ID) as tasks_count from priorities pp order by pp.priority_level");
            while(rs.next())
                {
                priority_list.add(new priority_class(rs.getString("p_id"), rs.getString("p_level"), rs.getString("p_title"), rs.getString("p_short_name"), rs.getString("p_color"), rs.getString("p_description"), rs.getString("p_notes"), rs.getInt("tasks_count"), rs.getInt("status") == 1));
                }
            rs.close();
            if(priority_list.isEmpty())
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

        return valid;
        }

    public void save()
        {

        connect conn = new connect();
        if(validate(conn))
            if(process_type.trim().equalsIgnoreCase("NEW"))
                try
                {
                priority_id = conn.get_max_id("priorities", "priority_id");
                conn.insert("insert into priorities (priority_id,priority_level,priority_title,priority_short_name,priority_color,priority_description,priority_notes,priority_status) values(?,?,?,?,?,?,?,?)", new String[]
                    {
                    priority_id, priority_level, WordUtils.capitalizeFully(priority_title.trim().toLowerCase()), priority_short_name.trim().toUpperCase(), priority_color, priority_description, priority_notes, "1"
                    });
                msgr.info("The priority has been inserted successfully....");
                priority_id = "";
                priority_level = "";
                priority_title = "";
                priority_short_name = "";
                priority_color = "";
                priority_description = "";
                priority_notes = "";
                process_type = "new";
                }
            catch(Exception exp)
                {
                msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
                }
            else
                try
                {
                conn.update("update priorities set priority_level=?,priority_title=?,priority_short_name=?,priority_color=?,priority_description=?,priority_notes=? where priority_id=?", new String[]
                    {
                    priority_level, WordUtils.capitalizeFully(priority_title.trim().toLowerCase()), priority_short_name.trim().toUpperCase(), priority_color, priority_description, priority_notes, priority_id
                    });
                msgr.info("The priority has been updated successfully....");
                }
            catch(Exception exp)
                {
                msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
                }
        Search();
        conn.close();
        }

    public class priority_class
        {
        private String p_id;
        private String p_level;
        private String p_title;
        private String p_short_name;
        private String p_color;
        private String p_description;
        private String p_notes;
        private int tasks_count;
        private boolean status;
        private boolean deletable;

        public priority_class(String p_id, String p_level, String p_title, String p_short_name, String p_color, String p_description, String p_notes, int tasks_count, boolean status)
            {
            this.p_id = p_id;
            this.p_level = p_level;
            this.p_title = p_title;
            this.p_short_name = p_short_name;
            this.p_color = p_color;
            this.p_description = p_description;
            this.p_notes = p_notes;
            this.tasks_count = tasks_count;
            this.status = status;
            this.deletable = tasks_count == 0;
            }

        public void edit()
            {
            process_type = "update";
            priority_id = this.p_id;
            priority_level = this.p_level;
            priority_title = this.p_title;
            priority_short_name = this.p_short_name;
            priority_color = this.p_color;
            priority_description = this.p_description;
            priority_notes = this.p_notes;
            common_functions.getInstance().open_dialog("add_edit_priorities", 800, 450);
            }

        public void change_status()
            {
            connect conn = new connect();
            try
                {
                conn.update("update priorities set priority_status=" + (this.status ? "1" : "0") + " where priority_id=" + this.p_id);
                msgr.info("Priority status has been updated......");
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
                conn.delete("delete from priorities where priority_id=" + this.p_id);
                msgr.info("The Priority has been removed successfully.....");
                Search();
                }
            catch(Exception exp)
                {
                msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
                }
            conn.close();
            }

        public boolean isStatus()
            {
            return status;
            }

        public void setStatus(boolean status)
            {
            this.status = status;
            }

        public String getP_id()
            {
            return p_id;
            }

        public void setP_id(String p_id)
            {
            this.p_id = p_id;
            }

        public String getP_level()
            {
            return p_level;
            }

        public void setP_level(String p_level)
            {
            this.p_level = p_level;
            }

        public String getP_title()
            {
            return p_title;
            }

        public void setP_title(String p_title)
            {
            this.p_title = p_title;
            }

        public String getP_short_name()
            {
            return p_short_name;
            }

        public void setP_short_name(String p_short_name)
            {
            this.p_short_name = p_short_name;
            }

        public String getP_color()
            {
            return p_color;
            }

        public void setP_color(String p_color)
            {
            this.p_color = p_color;
            }

        public String getP_description()
            {
            return p_description;
            }

        public void setP_description(String p_description)
            {
            this.p_description = p_description;
            }

        public String getP_notes()
            {
            return p_notes;
            }

        public void setP_notes(String p_notes)
            {
            this.p_notes = p_notes;
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

    public List<priority_class> getPriority_list()
        {
        return priority_list;
        }

    public void setPriority_list(List<priority_class> priority_list)
        {
        this.priority_list = priority_list;
        }

    public String getPriority_id()
        {
        return priority_id;
        }

    public void setPriority_id(String priority_id)
        {
        this.priority_id = priority_id;
        }

    public String getPriority_level()
        {
        return priority_level;
        }

    public void setPriority_level(String priority_level)
        {
        this.priority_level = priority_level;
        }

    public String getPriority_title()
        {
        return priority_title;
        }

    public void setPriority_title(String priority_title)
        {
        this.priority_title = priority_title;
        }

    public String getPriority_short_name()
        {
        return priority_short_name;
        }

    public void setPriority_short_name(String priority_short_name)
        {
        this.priority_short_name = priority_short_name;
        }

    public String getPriority_color()
        {
        return priority_color;
        }

    public void setPriority_color(String priority_color)
        {
        this.priority_color = priority_color;
        }

    public String getPriority_description()
        {
        return priority_description;
        }

    public void setPriority_description(String priority_description)
        {
        this.priority_description = priority_description;
        }

    public String getPriority_notes()
        {
        return priority_notes;
        }

    public void setPriority_notes(String priority_notes)
        {
        this.priority_notes = priority_notes;
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
