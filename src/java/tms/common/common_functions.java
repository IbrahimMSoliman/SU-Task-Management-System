package tms.common;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import tms.db.connect;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import jakarta.faces.model.SelectItem;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.primefaces.PrimeFaces;

public class common_functions
    {
    private static common_functions cmn_func = null;

    public common_functions()
        {

        }

    public static common_functions getInstance()
        {
        if(cmn_func == null)
            cmn_func = new common_functions();
        return cmn_func;
        }

    public static String printDate()
        {
        //
        DateFormat dformat = new SimpleDateFormat("E, dd MMM yyyy HH:mm");
        return dformat.format(new Date());
        }

    public static String check_password_strength(String password)
        {
        String STRONG_PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
        String MEDIUM_PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,20}$";
        String WEAK_PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z]).{8,20}$";
        if(password.trim().length() < 8)
            return "Weak";
        if(password.matches(STRONG_PASSWORD_PATTERN))
            return "Strong";
        else if(password.matches(MEDIUM_PASSWORD_PATTERN))
            return "Medium";

        else if(password.matches(WEAK_PASSWORD_PATTERN))
            return "Weak";
        else
            return "Weak";
        }

    public static boolean IsListContainsValue(List<SelectItem> list, String value)
        {
        boolean exist = false;
        for(SelectItem item : list)
            if(item.getValue().toString().equalsIgnoreCase(value))
                {
                exist = true;
                break;
                }
        return exist;
        }

    public String FindEmailsByMenuItemID(connect conn, String menu_item_id)
        {
        String emails = "";
        try(ResultSet rs = conn.query("select nvl(listagg(uu.email,',') within group (order by 1),' ') from users uu where uu.status in (1,0) and uu.user_id in (select ug.user_id from user_access_group ug,access_group_items ii,access_groups ag where ag.group_status=1 and ag.group_id=ii.group_id and ii.group_id=ug.group_id and ii.item_id=" + menu_item_id + ")"))
            {
            while(rs.next())
                {
                emails = rs.getString(1).trim();
                }
            }
        catch(Exception ee)
            {
            }

        return emails;
        }

    public String FindAppId(connect conn, String query_search)
        {
        String app_id = "";
        try(ResultSet rs = conn.query("select app.app_id from students app where student_id='" + query_search.trim().replaceAll(" ", "") + "'"))
            {
            while(rs.next())
                {
                app_id = rs.getString("app_id");
                }
            }
        catch(Exception ee)
            {
            return "";
            }
        if(app_id.trim().equals(""))
            try(ResultSet rs = conn.query("select app.app_id from students app where national_id='" + query_search.trim().replaceAll(" ", "") + "'"))
            {
            while(rs.next())
                {
                app_id = rs.getString("app_id");
                }
            }
        catch(Exception ee)
            {
            return "";
            }
        return app_id;
        }

    public boolean update_student_foundation_curriculum(connect conn, String app_id) throws SQLException
        {
        boolean update_success = false;
        ResultSet rs = conn.query("select dd.degree_type as degree_type, mm.college_id as college_id, nvl((select main_fc.foundation_curriculum_id from foundation_curriculum main_fc,academic_semester main_ac where main_fc.curriculum_id=app.curriculum_id and main_fc.starting_ac_semester_id=main_ac.academic_semester_id and main_ac.semester_start_date=(select max(ac.semester_start_date) from foundation_curriculum fc,academic_semester ac where fc.curriculum_id=app.curriculum_id and fc.starting_ac_semester_id=ac.academic_semester_id and ac.semester_start_date<=admac.semester_start_date)),0) as foundation_curriculum_id from students app,academic_semester admac,curriculum curr,academic_degrees dd,majors mm where app.curriculum_id=curr.curriculum_id and dd.degree_id=curr.degree_id and curr.major_id=mm.major_id and  admac.academic_semester_id=app.academic_semester_id and app.app_id=" + app_id);
        while(rs.next())
            {
            if(rs.getInt("college_id") == 0)//visitor /short course student
                {
                conn.update("update students app set app.foundation_curriculum_id=0 where app.app_id=" + app_id);
                update_success = true;
                }
            else if(rs.getInt("foundation_curriculum_id") > 0)//normal student(not visitor)
                {
                conn.update("update students app set app.foundation_curriculum_id=" + rs.getString("foundation_curriculum_id") + " where app.app_id=" + app_id);
                update_success = true;
                }
            if(update_success == false && rs.getString("degree_type").equalsIgnoreCase("Postgraduate")) //post graduate student but there is no foundation course
                {
                conn.update("update students app set app.foundation_curriculum_id=0 where app.app_id=" + app_id);
                update_success = true;
                }
            }
        rs.close();
        return update_success;
        }

    public String pending_grade_request_error_msgs(connect conn, String app_id, String section_id)
        {
        String error_msgs = "";

        try(ResultSet rs = conn.query("select 'Change Grade Request' as request_type, rr.request_status as request_status,to_char(rr.request_date,'dd/mm/yyyy hh:mi am') as request_date from gd_change_grade_requests rr where rr.app_id=" + app_id + " and rr.section_id=" + section_id + " and upper(rr.request_status)<>'CONFIRMED'"
                + " union "
                + " select 'Incomplete Grade Request' as request_type, rr.request_status as request_status,to_char(rr.request_date,'dd/mm/yyyy hh:mi am') as request_date from gd_incomplete_exam_request rr where rr.app_id=" + app_id + " and rr.section_id=" + section_id + " and upper(rr.request_status)<>'CONFIRMED'"
                + " union "
                + " select 'Review Final Exam Request' as request_type, rr.request_status as request_status,to_char(rr.request_date,'dd/mm/yyyy hh:mi am') as request_date from gd_review_final_exam_requests rr where rr.app_id=" + app_id + " and rr.section_id=" + section_id + " and upper(rr.request_status)<>'CONFIRMED'"
                + " union"
                + " select 'Grade Appeal Request' as request_type, rr.request_status as request_status,to_char(rr.request_date,'dd/mm/yyyy hh:mi am') as request_date from gd_grade_appeal_requests rr where rr.app_id=" + app_id + " and rr.section_id=" + section_id + " and upper(rr.request_status)<>'CONFIRMED'"
                + " order by 1"))
            {
            while(rs.next())
                {
                error_msgs += "There is pending " + rs.getString("request_type") + "  (" + rs.getString("request_status") + " status) in " + rs.getString("request_date") + "<br/>";
                }
            }
        catch(Exception ee)
            {
            return ee.getMessage();
            }

        return error_msgs;
        }

    public void update_missing_document_and_admission_block(connect conn, String app_id) throws Exception
        {
        //applied for the student who are joined in fall 2020/2021 and above
        try(ResultSet rs_app = conn.query("select app.app_id,(select count(*) from academic_semester ac where admac.semester_start_date>=ac.semester_start_date and  ac.academic_semester_id=31) is_new_joined from students app,academic_semester admac where app.app_id=" + app_id + " and app.academic_semester_id=admac.academic_semester_id "))
            {
            while(rs_app.next())
                {
                conn.setAutoCommit(false);
                if(rs_app.getInt("is_new_joined") > 0)  //new joined students in fall 2020/2021 and above
                    {
                    //delete all missing doc which are required
                    conn.delete("delete from missing_documents dd where dd.app_id=" + app_id + " and dd.document_id in (select mm.document_id from documents mm where nvl(mm.required,0)=1)");
                    //delete all admission block which related to missing document
                    conn.delete("delete from student_block bb where bb.app_id=" + app_id + " and upper(bb.block_type)='ADMISSION' and nvl(bb.missing_doc_id,-1) in(select dd.document_id from documents dd)");
                    //add required document to missing document if not exist in archived
                    conn.insert("insert into missing_documents (APP_ID,DOCUMENT_ID,SUBMIT_DATE) select app.app_id, dd.document_id as document_id,ac.semester_start_date+nvl(dd.submit_days,0) as submit_date from documents dd,students app,curriculum curr,majors mm,academic_semester ac where ac.academic_semester_id=app.academic_semester_id and app.app_id=" + app_id + " and app.curriculum_id=curr.curriculum_id and curr.major_id=mm.major_id and nvl(dd.required,0)=1 and dd.doc_category_id not in (select arc_doc.category_id from doc_documents arc_doc where arc_doc.app_id=app.app_id) and ( nvl(dd.required_sponsor_id,-1)=-1 or nvl(dd.required_sponsor_id,-1)=app.sponsor_id) and ( nvl(dd.required_college_id,-1)=-1 or nvl(dd.required_college_id,-1)=mm.college_id) and ( upper(nvl(dd.required_parent_work_status,'ALL'))='ALL' or upper(nvl(dd.required_parent_work_status,'ALL'))=UPPER(decode(APP.parent_work_status ,'Social','Social','Limited Income','Limited Income','Normal')))");
                    //add admission blocks for missing document(For not graduated students)
                    ResultSet rs = conn.query("select doc.document_title as document_title,doc.document_id as document_id from missing_documents mm,documents doc where (select count(*) from graduation gg where gg.app_id=mm.app_id)=0 and doc.document_id=mm.document_id and mm.app_id=" + app_id + " and mm.SUBMIT_DATE<sysdate");
                    while(rs.next())
                        {
                        conn.insert("insert into student_block (app_id,block_type,block_reason,USER_TYPE,USER_ID,PROCESS_DATE,MISSING_DOC_ID) values(?,?,?,?,?,sysdate,?)", new String[]
                            {
                            app_id, "ADMISSION", "Missing Document-" + rs.getString("document_title"), "System", "0", rs.getString("document_id")
                            });
                        }
                    rs.close();
                    }
                else //other student
                    {
                    //delete missing courses if it is submitted in document archive
                    conn.delete("delete from missing_documents miss where miss.app_id=" + app_id + " and miss.document_id in (select doc.document_id from documents doc,doc_documents arc_doc where nvl(doc.doc_category_id,-1)=arc_doc.category_id and arc_doc.app_id=miss.app_id)");
                    //delete admission block for any missing course submitted
                    conn.delete("delete from student_block bb where bb.app_id=" + app_id + " and upper(bb.block_type)='ADMISSION' and nvl(bb.missing_doc_id,-1) not in (select miss.document_id from missing_documents miss where miss.app_id=bb.app_id)");
                    }

                conn.commit();
                conn.setAutoCommit(true);
                break;
                }
            }
        catch(Exception ee)
            {
            }
        }

    public String semester_name(connect conn, String academic_semester_id)
        {
        String semester_name = "";
        try
            {
            ResultSet rs = conn.query("select sem.semester_name||' '||yy.academic_year_title as sem_name from academic_semester ac,semesters sem,academic_years yy where ac.academic_year_id=yy.academic_year_id and ac.semester_id=sem.semester_id and  ac.academic_semester_id=" + academic_semester_id);
            while(rs.next())
                {
                semester_name = rs.getString(1);
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("Error(" + this.getClass().getName() + "):" + exp.toString());
            }
        return semester_name;
        }

    //Must Remove
    ////////////////////////////Messages\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public String pagination(int max_index, int current_index, String url)
        {
        String page = "";
        if(max_index > 1)
            {
            page = "<div><ul class=\"pagination\">\n";
            //previous button
            if(current_index == 1)
                page += "<li class=\"previous-off\">&laquo;Previous</li>\n";
            else if(current_index > 1)
                page += "<li><a href=\"" + url + "&current_page=" + String.valueOf(current_index - 1) + "\">&laquo;Previous</a></li>\n";
            //pages
            for(int index = 1; index <= max_index; index++)
                if(index == current_index)
                    page += "<li class=\"active\">" + String.valueOf(index) + "</li>\n";
                else
                    page += "<li><a href=\" " + url + "&current_page=" + String.valueOf(index) + "\">" + String.valueOf(index) + "</a></li>\n";
            //next button
            if(current_index < max_index)
                page += "<li class=\"next\"><a href=\" " + url + "&current_page=" + String.valueOf(current_index + 1) + "\">Next &raquo;</a></li>\n";
            else
                page += "<li class=\"next-off\">Next &raquo;</li>\n";

            page += "</ul></div>";
            }
        return page;
        }

    public String list_of_course_college_dept(connect conn, String name, String value, String title, String width, boolean autosubmit, boolean enabled)
        {
        String list = "";
        if(value == null)
            value = "-1";
        list = "<select name=\"" + name + "\" id=\"" + name + "\" ";
        if(!width.trim().equals(""))
            list += " style=\"width:" + width + "px\" ";
        if(autosubmit)
            list += "  onchange=\"document.forms['form'].submit();\" ";
        if(!enabled)
            list += " disabled=\"disabled\"  ";
        list += " >";
        if(!title.equals(""))
            list += "<option style=\"-webkit-print-color-adjust: exact;background-color:#ccffff\" value=\"-1\">" + title + "</option>";
        if(conn.validdb)
               try
            {

            ResultSet rs = conn.query("select 'CLG_'||cc.college_id,cc.college_short_name,'a' from colleges cc union select 'DEP_'||dep.department_id,dep.department_short_name,'b' from departments dep order by 3,2");
            while(rs.next())
                {
                list += "<option value=\"" + rs.getString(1) + "\" ";
                if(value.toUpperCase().equals(rs.getString(1).toUpperCase()))
                    list += " selected=\"selected\" style=\"-webkit-print-color-adjust: exact;background-color:lightgreen;\" ";
                list += ">" + rs.getString(2) + "</option>";
                }
            rs.close();
            }
        catch(SQLException exp)
            {
            }
        list += "</select>";
        return list;
        }

    public String create_pop_up_window_button(String url, String window_id, String width, String height, String onclose, String title)
        {
        String link = "";
        link = "<script>$(document).ready(function(){$(\"#" + window_id + "\").colorbox({fixed:true,iframe:true,overlayClose: false, width:\"" + width + "\", height:\"" + height + "\",  onClosed:function(){" + onclose + "} });});</script>";
        link += "<a id=" + window_id + " class=\"btn ui-state-default ui-corner-all\" href=\"" + url + "\"><span class=\"ui-icon ui-icon-newwin\"></span>" + title + "</a>";
        return link;
        }

    public String create_sms_button(String app_id)
        {
        return "";
        }

    public String ok_message(String msg)
        {
        return "<div class=\"response-msg success ui-corner-all\"><span>Success message</span>" + msg + "</div>";
        }

    public String error_message(String msg)
        {
        return "<div class=\"response-msg error ui-corner-all\"><span>Error message</span>" + msg + "</div>";
        }

    public String info_message(String msg)
        {
        return "<div class=\"response-msg inf ui-corner-all\"><span>Information message</span>" + msg + "</div>";
        }

    public String note_message(String msg)
        {
        return "<div class=\"response-msg notice ui-corner-all\"><span>Notice message</span>" + msg + "</div>";
        }

    public String gray_box(String title, String msg)
        {
        return "<div class=\"other-box gray-box ui-corner-all\"><div class=\"cont ui-corner-all\"><h3>" + title + "</h3><p>" + msg + "</p></div></div>";
        }

    public String yellow_box(String title, String msg)
        {
        return "<div class=\"other-box yellow-box ui-corner-all\"><div class=\"cont ui-corner-all\"><h3>" + title + "</h3><p>" + msg + "</p></div></div>";
        }

    public String small_gray_box(String title, String msg)
        {
        return "<div class=\"other-box gray-box float-left width50 ui-corner-all\"><div class=\"cont ui-corner-all\"><h3>" + title + "</h3><p>" + msg + "</p></div></div>";
        }

    public String small_yellow_box(String title, String msg)
        {
        return "<div class=\"other-box yellow-box float-right width50 ui-corner-all\"><div class=\"cont ui-corner-all\"><h3>" + title + "</h3><p>" + msg + "</p></div></div>";
        }

    public String create_select_tag(connect conn, String name, String value, String title, String width, String sql, boolean autosubmit, boolean enabled)
        {
        String list = "";
        if(value == null)
            value = "-1";
        list = "<select name=\"" + name + "\" id=\"" + name + "\" ";
        if(!width.trim().equals(""))
            list += " style=\"width:" + width + "px\" ";
        if(autosubmit)
            list += "  onchange=\"document.forms['form'].submit();\" ";
        if(!enabled)
            list += " disabled=\"disabled\"  ";
        list += " >";
        if(!title.equals(""))
            list += "<option style=\"-webkit-print-color-adjust: exact;background-color:#ccffff\" value=\"-1\">" + title + "</option>";
        if(conn.validdb)
               try
            {

            ResultSet rs = conn.query(sql);
            while(rs.next())
                {
                list += "<option value=\"" + rs.getString(1) + "\" ";
                if(value.equals(rs.getString(1)))
                    list += " selected=\"selected\" style=\"-webkit-print-color-adjust: exact;background-color:lightgreen;\" ";
                list += ">" + rs.getString(2) + "</option>";
                }
            rs.close();
            }
        catch(SQLException exp)
            {
            }
        list += "</select>";
        return list;
        }

    public String create_select_tag(connect conn, String name, String value, String title, String width, String sql, boolean autosubmit, boolean enabled, String form_action)
        {
        String list = "";
        if(value == null)
            value = "-1";
        list = "<select name=\"" + name + "\" id=\"" + name + "\" ";
        if(!width.trim().equals(""))
            list += " style=\"width:" + width + "px\" ";
        if(autosubmit)
            list += "  onchange=\" " + form_action + "; document.forms['form'].submit();\" ";
        if(!enabled)
            list += " disabled=\"disabled\"  ";
        list += " >";
        if(!title.equals(""))
            list += "<option style=\"-webkit-print-color-adjust: exact;background-color:#ccffff\" value=\"-1\">" + title + "</option>";
        if(conn.validdb)
             try
            {

            ResultSet rs = conn.query(sql);
            while(rs.next())
                {
                list += "<option value=\"" + rs.getString(1) + "\" ";
                if(value.equals(rs.getString(1)))
                    list += " selected=\"selected\" style=\"-webkit-print-color-adjust: exact;background-color:lightgreen;\" ";
                list += ">" + rs.getString(2) + "</option>";
                }
            rs.close();
            }
        catch(SQLException exp)
            {
            }
        list += "</select>";
        return list;
        }

    public String create_select_NO_tag(String name, String value, String title, String width, int start, int end, boolean autosubmit, boolean enabled)
        {
        String list = "";
        if(value == null)
            value = "-1";
        list = "<select name=\"" + name + "\" id=\"" + name + "\" ";
        if(!width.trim().equals(""))
            list += " style=\"width:" + width + "px\" ";
        if(autosubmit)
            list += "  onchange=\"document.forms['form'].submit();\" ";
        if(!enabled)
            list += " disabled=\"disabled\"  ";
        list += " >";
        if(!title.equals(""))
            list += "<option style=\"-webkit-print-color-adjust: exact;background-color:#ccffff\" value=\"-1\">" + title + "</option>";
        for(int index = start; index <= end; index++)
            {
            list += "<option value=\"" + String.valueOf(index) + "\" ";
            if(value.equals(String.valueOf(index)))
                list += " selected=\"selected\" style=\"-webkit-print-color-adjust: exact;background-color:lightgreen;\" ";
            list += ">" + String.valueOf(index) + "</option>";
            }
        list += "</select>";
        return list;
        }

    public String create_select_fraction_NO_tag(String name, String value, String title, String width, float start, float end, boolean autosubmit, boolean enabled)
        {
        String list = "";
        if(value == null)
            value = "-1";
        list = "<select name=\"" + name + "\" id=\"" + name + "\" ";
        if(!width.trim().equals(""))
            list += " style=\"width:" + width + "px\" ";
        if(autosubmit)
            list += "  onchange=\"document.forms['form'].submit();\" ";
        if(!enabled)
            list += " disabled=\"disabled\"  ";
        list += " >";
        if(!title.equals(""))
            list += "<option style=\"-webkit-print-color-adjust: exact;background-color:#ccffff\" value=\"-1\">" + title + "</option>";

        for(float index = start; index <= end; index = index + 0.5f)
            {

            list += "<option value=\"" + String.valueOf(index) + "\" ";
            try
                {
                if(index == Float.parseFloat(value))
                    list += " selected=\"selected\" style=\"-webkit-print-color-adjust: exact;background-color:lightgreen;\" ";
                }
            catch(Exception exp)
                {
                }

            list += ">" + String.valueOf(index) + "</option>";

            }
        list += "</select>";
        return list;
        }

    public String create_select_static_tag(String name, String value, String title, String width, String[][] data, boolean autosubmit, boolean enabled)
        {
        String list = "";
        if(value == null)
            value = "-1";
        list = "<select name=\"" + name + "\" id=\"" + name + "\" ";
        if(!width.trim().equals(""))
            list += " style=\"width:" + width + "px\" ";
        if(autosubmit)
            list += "  onchange=\"document.forms['form'].submit();\" ";
        if(!enabled)
            list += " disabled=\"disabled\"  ";
        list += " >";
        if(!title.equals(""))
            list += "<option style=\"-webkit-print-color-adjust: exact;background-color:#ccffff\" value=\"-1\">" + title + "</option>";
        for(int index = 0; index < data.length; index++)
            {
            list += "<option value=\"" + data[index][0] + "\" ";
            if(value.equalsIgnoreCase(data[index][0]))
                list += " selected=\"selected\" style=\"-webkit-print-color-adjust: exact;background-color:lightgreen;\" ";
            list += ">" + data[index][1] + "</option>";
            }
        list += "</select>";
        return list;
        }

    public String create_select_static_tag(String name, String value, String title, String width, String[][] data, boolean autosubmit, boolean enabled, String form_action)
        {
        String list = "";
        if(value == null)
            value = "-1";
        list = "<select name=\"" + name + "\" id=\"" + name + "\" ";
        if(!width.trim().equals(""))
            list += " style=\"width:" + width + "px\" ";
        if(autosubmit)
            list += "  onchange=\" " + form_action + " ;document.forms['form'].submit();\" ";
        if(!enabled)
            list += " disabled=\"disabled\"  ";
        list += " >";
        if(!title.equals(""))
            list += "<option style=\"-webkit-print-color-adjust: exact;background-color:#ccffff\" value=\"-1\">" + title + "</option>";
        for(int index = 0; index < data.length; index++)
            {
            list += "<option value=\"" + data[index][0] + "\" ";
            if(value.equalsIgnoreCase(data[index][0]))
                list += " selected=\"selected\" style=\"-webkit-print-color-adjust: exact;background-color:lightgreen;\" ";
            list += ">" + data[index][1] + "</option>";
            }
        list += "</select>";
        return list;
        }
    ////////////////////////////End of Messages\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

//////////////////////////////DB Functions\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public int check_duplication(connect conn, String table_name, String check_field_name, String data, String id_field_name, String id)
        {
        int count = 0;
        String sql = "";
        try
            {
            if(id.trim().equals(""))
                sql = "select count(*) from " + table_name + " where upper(" + check_field_name + ")='" + data.toUpperCase().replaceAll("'", "''") + "'";
            else
                sql = "select count(*) from " + table_name + " where " + id_field_name + "<>" + id + " and upper(" + check_field_name + ")='" + data.toUpperCase().replaceAll("'", "''") + "'";
            //System.out.println(sql);

            ResultSet rs = conn.query(sql);
            while(rs.next())
                {
                count = rs.getInt(1);
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("Error (check_duplication sql=" + sql + ") :" + exp.toString());
            }
        return count;
        }

    ///////////////////////////////////////////////////
    public boolean isValidEmailAddress(String email)
        {
        Pattern pattern = Pattern.compile(".+@.+\\.[a-z]+");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
        }

///////////////////////////////Date Checks\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    public boolean ValidateDate(String date)
        {
        Pattern pattern;
        Matcher matcher;
        String DATE_PATTERN = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)";
        pattern = Pattern.compile(DATE_PATTERN);
        matcher = pattern.matcher(date);
        if(matcher.matches())
            {
            matcher.reset();
            if(matcher.find())
                {
                String day = matcher.group(1);
                String month = matcher.group(2);
                int year = Integer.parseInt(matcher.group(3));
                if(day.equals("31") && (month.equals("4") || month.equals("6") || month.equals("9") || month.equals("11") || month.equals("04") || month.equals("06") || month.equals("09")))
                    return false; // only 1,3,5,7,8,10,12 has 31 days
                else if(month.equals("2") || month.equals("02"))
                    //leap year
                    if(year % 4 == 0)
                        if(day.equals("30") || day.equals("31"))
                            return false;
                        else
                            return true;
                    else if(day.equals("29") || day.equals("30") || day.equals("31"))
                        return false;
                    else
                        return true;
                else
                    return true;

                }
            else
                return false;

            }
        else
            return false;

        }
/////

    public boolean IsFirstDateOldaerThanSecondDate(connect conn, String fisrt_date, String second_date)
        {
        boolean is_older = false;
        try
            {
            ResultSet rs = conn.query("select to_date('" + second_date + "','dd/mm/yyyy')-to_date('" + fisrt_date + "','dd/mm/yyyy') from dual");
            while(rs.next())
                {
                if(rs.getDouble(1) >= 0)
                    is_older = true;
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("Error :" + exp.toString());
            }
        return is_older;
        }

/////////////////////////////////currciculum fucntions
    public String get_course_prerequisites_titles(connect conn, String course_id, String curriculum_id)
        {
        String data = "";
        try
            {
            ResultSet rs = conn.query("select cc.course_code||' '||cc.course_number as ccode,cc.course_name as cname,pre.prereq_type as preq_type from courses_prereq pre,courses cc where pre.course_id=" + course_id + " and (pre.curriculum_id=" + curriculum_id + " or pre.curriculum_id=-1) and pre.prereq_course_id=cc.course_id and pre.prereq_course_id in (select gcc.course_id from curriculum_groups_courses gcc where gcc.curriculum_id=" + curriculum_id + ") order by cc.course_code,cc.course_number");
            while(rs.next())
                {
                if(rs.getInt("preq_type") == 1)
                    data += "<span style=\"color:green\" title=\"" + rs.getString("cname") + "\">" + rs.getString("ccode") + "</span><br/>";
                else
                    data += "<span style=\"color:red\" title=\"" + rs.getString("cname") + "\">" + rs.getString("ccode") + " (Co-Req)</span><br/>";
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("get_course_prerequisites_titles Error :" + exp.toString());
            }
        return data;
        }

    public Vector get_course_prerequisites_ids(connect conn, String course_id, String curriculum_id)
        {
        Vector data = new Vector();
        try
            {
            ResultSet rs = conn.query("select  pre.prereq_course_id from courses_prereq pre where pre.course_id=" + course_id + " and (pre.curriculum_id=" + curriculum_id + " or pre.curriculum_id=-1) and pre.prereq_course_id in (select gcc.course_id from curriculum_groups_courses gcc where gcc.curriculum_id=" + curriculum_id + ")");
            while(rs.next())
                {
                data.add(rs.getString(1));
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("get_course_prerequisites_ids Error :" + exp.toString());
            }
        return data;
        }

    public int get_course_ch_prerequisites(connect conn, String course_id, String curriculum_id)
        {
        int data = 0;
        try
            {
            ResultSet rs = conn.query("select nvl((select max(pre.ech) from ech_courses_prereq pre where pre.course_id=cc.course_id and pre.curriculum_id=-1),0) as pgeneral,nvl((select max(pre.ech) from ech_courses_prereq pre where pre.course_id=cc.course_id and pre.curriculum_id=" + curriculum_id + "),0) as pcur from courses cc where cc.course_id=" + course_id);
            while(rs.next())
                {
                if(rs.getInt("pcur") > 0)
                    data = rs.getInt("pcur");
                else
                    data = rs.getInt("pgeneral");
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("Error :" + exp.toString());
            }
        if(data == 0)
            data = -1;
        return data;
        }

    public String create_student_details_link(String app_id, String student_id)
        {
        return "<a class=\"student_details_class\" href=\"/sis/admission/student_details.jsp?app_id=" + app_id + "\">" + student_id + "</a>";
        }
//setting variables

    public String setting_BOOKING_HOURS(connect conn)
        {
        String data = "";
        try
            {
            ResultSet rs = conn.query("select max(BOOKING_HOURS) from settings");
            while(rs.next())
                {
                data = rs.getString(1);
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("Error :" + exp.toString());
            }
        if(data.trim().equals(""))
            data = "36";
        return data;
        }

    public String setting_AFTER_TWO_F_GRADE_C(connect conn)
        {
        String data = "";
        try
            {
            ResultSet rs = conn.query("select max(AFTER_TWO_F_GRADE_C) from settings");
            while(rs.next())
                {
                data = rs.getString(1);
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("Error :" + exp.toString());
            }
        if(data.trim().equals(""))
            data = "1";
        return data;
        }

    public String setting_NO_DICON_TO_CANCEL(connect conn)
        {
        String data = "";
        try
            {
            ResultSet rs = conn.query("select max(NO_DICON_TO_CANCEL) from settings");
            while(rs.next())
                {
                data = rs.getString(1);
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("Error :" + exp.toString());
            }
        if(data.trim().equals(""))
            data = "4";
        return data;
        }

    public String setting_NO_SEQUENT_DICON_TO_CANCEL(connect conn)
        {
        String data = "";
        try
            {
            ResultSet rs = conn.query("select max(NO_SEQUENT_DICON_TO_CANCEL) from settings");
            while(rs.next())
                {
                data = rs.getString(1);
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("Error :" + exp.toString());
            }
        if(data.trim().equals(""))
            data = "3";
        return data;
        }

    public String get_section_days(connect conn, String section_id)
        {
        String day1 = "";
        String day2 = "";
        try
            {
            ResultSet rs = conn.query("select decode(dd.day_id,1,'U',2,'M',3,'T',4,'W',5,'Th',6,'Fri',7,'S',' '),nvl(session_no,1) from section_days dd where dd.section_id=" + section_id + " order by dd.day_id");
            while(rs.next())
                {
                if(rs.getString(2).trim().equals("1"))
                    day1 += rs.getString(1) + " ";
                else
                    day2 += rs.getString(1) + " ";
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("Error :" + exp.toString());
            }
        if(day2.trim().equals(""))
            return day1;
        else if(day1.trim().equals(day2.trim()))
            return day1;
        else
            return day1 + "<br/>--------------<br/>" + day2;

        }
////////////////////////////////Excel sheet Functions\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    public void create_Excel_sheet_header(Sheet sheet, String[] header)
        {
        if(header != null)
            {
            CellStyle cellstyle = sheet.getWorkbook().createCellStyle();
            cellstyle.setBorderBottom(BorderStyle.THIN);
            cellstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            cellstyle.setBorderTop(BorderStyle.THIN);
            cellstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
            cellstyle.setBorderLeft(BorderStyle.THIN);
            cellstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            cellstyle.setBorderRight(BorderStyle.THIN);
            cellstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
            cellstyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellstyle.setAlignment(HorizontalAlignment.CENTER);

            Font headerFont = sheet.getWorkbook().createFont();
            headerFont.setBold(true); //setBoldweight(Font.BOLDWEIGHT_BOLD);
            headerFont.setFontHeightInPoints((short) 10);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            cellstyle.setFont(headerFont);

            cellstyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            if(header.length > 0)
                {
                Row row = sheet.createRow(0);
                row.setHeight((short) 500);
                for(int index = 0; index < header.length; index++)
                    {
                    Cell cell = row.createCell(index);
                    cell.setCellValue(header[index]);
                    //cell.setCellType(Cell.CELL_TYPE_STRING);
                    cell.setCellStyle(cellstyle);

                    }
                }
            }
        }

    public int student_academic_level(double ech)
        {
        if(ech >= 0 && ech <= 36)
            return 1;
        else if(ech > 36 && ech <= 72)
            return 2;
        else if(ech > 72 && ech <= 108)
            return 3;
        else if(ech > 108 && ech <= 144)
            return 4;
        else if(ech > 144)
            return 5;
        else
            return 0;
        }

    public boolean is_main_admin(connect conn, String user_id)
        {
        boolean is_admin = false;
        try
            {
            ResultSet rs = conn.query("select count(*) from user_access_group where group_id=1 and user_id=" + user_id);
            while(rs.next())
                {
                if(rs.getInt(1) > 0)
                    is_admin = true;
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("Error :" + exp.toString());
            }
        return is_admin;
        }

    public boolean valid_mobile(String mobile)
        {
        boolean valid = true;
        //not empty
        if(mobile.trim().equals(""))
            return false;
        //no chars
        if(mobile.replaceAll("0", "").replaceAll("1", "").replaceAll("2", "").replaceAll("3", "").replaceAll("4", "").replaceAll("5", "").replaceAll("6", "").replaceAll("7", "").replaceAll("8", "").replaceAll("9", "").trim().length() > 0)
            return false;
        // length =8
        if(mobile.trim().length() != 8)
            return false;
        if(!mobile.trim().startsWith("9"))
            return false;
        return valid;

        }

    public void open_dialog(String page, boolean modal, int width, int height, boolean resizable, boolean maximizable, boolean draggable, boolean closable)
        {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", modal);
        options.put("resizable", resizable);
        options.put("maximizable", maximizable);
        options.put("draggable", draggable);
        options.put("closable", false);
        options.put("width", width);
        options.put("height", height);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("responsive", true);
        options.put("headerElement", "headerElement");
        options.put("blockScroll", true);
        PrimeFaces.current().dialog().openDynamic(page, options, null);
        }

    public void open_closable_dialog(String page, boolean modal, int width, int height, boolean resizable, boolean maximizable, boolean draggable)
        {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", modal);
        options.put("resizable", resizable);
        options.put("maximizable", maximizable);
        options.put("draggable", draggable);
        options.put("closable", true);
        options.put("width", width);
        options.put("height", height);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("responsive", true);
        options.put("blockScroll", true);
        PrimeFaces.current().dialog().openDynamic(page, options, null);
        }

    public void open_dialog_full_width(String page, boolean modal, int height, boolean resizable, boolean maximizable, boolean draggable, boolean closable)
        {
        Map<String, Object> options = new HashMap<>();
        options.put("modal", modal);
        options.put("resizable", resizable);
        options.put("maximizable", maximizable);
        options.put("draggable", draggable);
        options.put("closable", false);
        options.put("width", "99%");
        options.put("height", height);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        options.put("responsive", true);
        options.put("headerElement", "headerElement");
        options.put("blockScroll", true);
        PrimeFaces.current().dialog().openDynamic(page, options, null);
        }

    public void open_help_editor_dialog(String page, boolean modal, String page_id, String page_sub_id, boolean closable)
        {
        Map<String, List<String>> params = new HashMap<>();
        params.put("page_id", Arrays.asList(page_id));
        params.put("page_sub_id", Arrays.asList(page_sub_id));

        Map<String, Object> options = new HashMap<>();
        options.put("modal", modal);
        options.put("resizable", false);
        options.put("maximizable", true);
        options.put("draggable", false);
        options.put("closable", closable);
        options.put("width", "100%");
        options.put("height", "100%");
        options.put("responsive", true);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");
        PrimeFaces.current().dialog().openDynamic(page, options, params);
        }

    public void open_dialog(String page, int width, int height)
        {
        open_dialog(page, true, width, height, false, false, false, false);
        }

    public void close_dialog()
        {
        PrimeFaces.current().dialog().closeDynamic(null);
        }

    public String check_Consecutive_teaching_per_section(connect conn, String section_id)
        {
        String conflicted_day = "";
        ResultSet rs = null;
        try
            {
            rs = conn.query("select nvl((select max(max_consecutive_teach_minutes) from settings),270) as max_consecutive_teach_minutes,nvl((select max(min_consecutive_break_minutes) from settings),270) as min_consecutive_break_minutes,sec.academic_semester_id as sem_id,inst1.user_id as inst_id1,inst2.user_id as inst_id2,sec.is_compound as is_compound,inst1.first_name||' '||inst1.last_name as inst_name1,inst2.first_name||' '||inst2.last_name as inst_name2 from sections sec,users inst1,users inst2 where inst1.is_tba_inst=0 and inst1.is_tba_inst=0 and sec.inst_id=inst1.user_id and nvl(sec.inst_id2,sec.inst_id)=inst2.user_id and sec.section_id=" + section_id);
            while(rs.next())
                {
                conflicted_day = check_Consecutive_teaching(conn, rs.getString("sem_id"), section_id, rs.getString("inst_id1"), rs.getDouble("max_consecutive_teach_minutes"), rs.getDouble("min_consecutive_break_minutes"));
                if(!conflicted_day.trim().equals(""))//there is issue

                    conflicted_day = "The instructor (" + rs.getString("inst_name1") + ") has " + rs.getDouble("max_consecutive_teach_minutes") + " Consecutive teaching minutes (or more) without break (" + rs.getDouble("min_consecutive_break_minutes") + " Minutes at least) at " + conflicted_day;
                else if(rs.getInt("is_compound") == 1 && !rs.getString("inst_id1").equals(rs.getString("inst_id2")))
                    {
                    conflicted_day = check_Consecutive_teaching(conn, rs.getString("sem_id"), section_id, rs.getString("inst_id2"), rs.getDouble("max_consecutive_teach_minutes"), rs.getDouble("min_consecutive_break_minutes"));
                    if(!conflicted_day.trim().equals(""))//there is issue

                        conflicted_day = "The instructor (" + rs.getString("inst_name2") + ") has " + rs.getDouble("max_consecutive_teach_minutes") + " Consecutive teaching minutes (or more) without break (" + rs.getDouble("min_consecutive_break_minutes") + " Minutes at least) at " + conflicted_day;
                    }
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

        return conflicted_day;
        }

    public String check_Consecutive_teaching(connect conn, String academic_semester_id, String section_id, String inst_id, double max_allowed_consecutive_minutes, double min_break_minutes)
        {
        String conflicted_day = "";
        ResultSet rs = null;
        String day_id = "0";
        double total_duration = 0;
        Date previous_end_date = null;
        try
            {
            rs = conn.query("with inst_schedule as("
                    + "select sec.section_no as section_no, dd.day_id,decode(dd.day_id,1,'Sunday',2,'Monday',3,'Tuesday',4,'Wednesday',5,'Thursday',6,'Friday',7,'Saturday',' ') as day_name,to_date(to_char(sec.start_time,'hh24:mi'),'hh24:mi') as start_time,to_date(to_char(sec.end_time,'hh24:mi'),'hh24:mi') as end_time ,((to_date(to_char(sec.end_time,'hh24:mi'),'hh24:mi')-to_date(to_char(sec.start_time,'hh24:mi'),'hh24:mi'))*24*60)  as duration_per_min from sections sec,section_days dd where sec.section_id=dd.section_id and dd.session_no=1 and sec.academic_semester_id=" + academic_semester_id + " and (sec.status=1 or sec.section_id=" + section_id + ") and sec.inst_id=" + inst_id + ""
                    + " union "
                    + " select sec.section_no as section_no,dd.day_id,decode(dd.day_id,1,'Sunday',2,'Monday',3,'Tuesday',4,'Wednesday',5,'Thursday',6,'Friday',7,'Saturday',' ') as day_name,to_date(to_char(sec.start_time2,'hh24:mi'),'hh24:mi') as start_time,to_date(to_char(sec.end_time2,'hh24:mi'),'hh24:mi') as end_time ,((to_date(to_char(sec.end_time2,'hh24:mi'),'hh24:mi')-to_date(to_char(sec.start_time2,'hh24:mi'),'hh24:mi'))*24*60)  as duration_per_min from sections sec,section_days dd where sec.is_compound=1 and sec.section_id=dd.section_id and dd.session_no=2 and sec.academic_semester_id=" + academic_semester_id + " and (sec.status=1 or sec.section_id=" + section_id + ") and sec.inst_id2=" + inst_id
                    + ")"
                    + " select distinct ii.day_id as day_id,ii.day_name as day_name,ii.start_time as start_time,ii.end_time as end_time ,ii.duration_per_min as duration_per_min from inst_schedule ii order by day_id,start_time");
            while(rs.next())
                {
                if(!rs.getString("day_id").equals(day_id)) //new day
                    {
                    day_id = rs.getString("day_id");
                    total_duration = 0;
                    previous_end_date = null;
                    }

                if(total_duration >= max_allowed_consecutive_minutes)
                    if(((rs.getDate("start_time").getTime() - previous_end_date.getTime()) / 60000) < min_break_minutes)
                        {
                        conflicted_day = rs.getString("day_name");
                        break;
                        }
                    else
                        total_duration = 0;
                total_duration += rs.getDouble("duration_per_min");
                total_duration = Math.floor(total_duration * 100 + .5) / 100;
                previous_end_date = rs.getDate("end_time");
                }
            }
        catch(SQLException sqlEx)
            {
            System.out.println("Error: " + sqlEx.getMessage());
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
        return conflicted_day;
        }

    public String check_Consecutive_teaching(connect conn, String academic_semester_id, String section_id, String inst_id, String lecture_days_id, String lecture_timings_id)
        {
        String conflicted_day = "";
        ResultSet rs = null;
        String day_id = "0";
        double total_duration = 0;
        Date previous_end_date = null;
        double max_allowed_consecutive_minutes;
        double min_break_minutes;
        if(section_id == null || section_id.trim().equals(""))//new section
            section_id = "-1";
        try
            {
            rs = conn.query("with inst_schedule as("
                    + "select sec.section_no as section_no, dd.day_id,decode(dd.day_id,1,'Sunday',2,'Monday',3,'Tuesday',4,'Wednesday',5,'Thursday',6,'Friday',7,'Saturday',' ') as day_name,to_date(to_char(sec.start_time,'hh24:mi'),'hh24:mi') as start_time,to_date(to_char(sec.end_time,'hh24:mi'),'hh24:mi') as end_time ,((to_date(to_char(sec.end_time,'hh24:mi'),'hh24:mi')-to_date(to_char(sec.start_time,'hh24:mi'),'hh24:mi'))*24*60)  as duration_per_min from sections sec,section_days dd where sec.section_id=dd.section_id and dd.session_no=1 and sec.academic_semester_id=" + academic_semester_id + " and sec.status=1 and sec.section_id<>" + section_id + " and sec.inst_id=" + inst_id + ""
                    + " union "
                    + " select sec.section_no as section_no,dd.day_id,decode(dd.day_id,1,'Sunday',2,'Monday',3,'Tuesday',4,'Wednesday',5,'Thursday',6,'Friday',7,'Saturday',' ') as day_name,to_date(to_char(sec.start_time2,'hh24:mi'),'hh24:mi') as start_time,to_date(to_char(sec.end_time2,'hh24:mi'),'hh24:mi') as end_time ,((to_date(to_char(sec.end_time2,'hh24:mi'),'hh24:mi')-to_date(to_char(sec.start_time2,'hh24:mi'),'hh24:mi'))*24*60)  as duration_per_min from sections sec,section_days dd where sec.is_compound=1 and sec.section_id=dd.section_id and dd.session_no=2 and sec.academic_semester_id=" + academic_semester_id + " and sec.status=1 and sec.section_id<>" + section_id + " and sec.inst_id2=" + inst_id
                    + " union "
                    + " select 'New' as section_no,dd.day_id,decode(dd.day_id,1,'Sunday',2,'Monday',3,'Tuesday',4,'Wednesday',5,'Thursday',6,'Friday',7,'Saturday',' ') as day_name,to_date(to_char(tt.lecture_start_time,'hh24:mi'),'hh24:mi') as start_time,to_date(to_char(tt.lecture_end_time,'hh24:mi'),'hh24:mi') as end_time ,((to_date(to_char(tt.lecture_end_time,'hh24:mi'),'hh24:mi')-to_date(to_char(tt.lecture_start_time,'hh24:mi'),'hh24:mi'))*24*60)  as duration_per_min from lecture_timing tt,lecture_days_item dd where dd.lecture_day_id=" + lecture_days_id + " and tt.timing_id=" + lecture_timings_id
                    + ")"
                    + " select distinct ii.day_id as day_id,ii.day_name as day_name,ii.start_time as start_time,ii.end_time as end_time ,ii.duration_per_min as duration_per_min ,nvl((select max(max_consecutive_teach_minutes) from settings),270) as max_consecutive_teach_minutes,nvl((select max(min_consecutive_break_minutes) from settings),270) as min_consecutive_break_minutes from inst_schedule ii order by day_id,start_time");
            while(rs.next())
                {
                max_allowed_consecutive_minutes = rs.getDouble("max_consecutive_teach_minutes");
                min_break_minutes = rs.getDouble("min_consecutive_break_minutes");

                if(!rs.getString("day_id").equals(day_id)) //new day
                    {
                    day_id = rs.getString("day_id");
                    total_duration = 0;
                    previous_end_date = null;
                    }

                if(total_duration >= max_allowed_consecutive_minutes)
                    if(((rs.getDate("start_time").getTime() - previous_end_date.getTime()) / 60000) < min_break_minutes)
                        {
                        conflicted_day = rs.getString("day_name");
                        conflicted_day = total_duration + " Consecutive teaching minutes without break (" + min_break_minutes + " Minutes at least) at " + conflicted_day + ", while the Max allowed Consecutive teaching minutes is " + max_allowed_consecutive_minutes + " minutes.";
                        break;
                        }
                    else
                        total_duration = 0;
                total_duration += rs.getDouble("duration_per_min");
                total_duration = Math.floor(total_duration * 100 + .5) / 100;
                previous_end_date = rs.getDate("end_time");
                }
            }
        catch(SQLException sqlEx)
            {
            System.out.println("Error: " + sqlEx.getMessage());
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
        return conflicted_day;
        }

    public String conditional_acceptance_status(connect conn, String app_id, String ignored_academic_semester_id)
        {
        String status = "";
        double required_gpa = 0;
        double required_studied_ch = 0;
        ResultSet rs_conditional = null;
        try
            {
            rs_conditional = conn.query("select required_gpa as required_gpa,after_studied_ch as studied_ch from student_conditional_acceptance where app_id=" + app_id);
            while(rs_conditional.next())
                {
                required_gpa = rs_conditional.getDouble("required_gpa");
                required_studied_ch = rs_conditional.getDouble("studied_ch");
                }
            }
        catch(SQLException sqlEx)
            {
            }finally
            {
            try
                {
                if(rs_conditional != null)
                    {
                    rs_conditional.getStatement().close();
                    rs_conditional.close();
                    }
                }
            catch(SQLException sqlEx)
                {
                }
            }
        if(required_studied_ch <= 0)
            status = "Not Conditional Acceptance";
        else
            {
            ResultSet rs = null;
            try
                {
                status = "Pending";
                rs = conn.query("SELECT nvl((SELECT SUM(cc.credit_hours) FROM student_registered_courses reg,sections sec,courses cc,academic_semester secac WHERE reg.app_id=" + app_id + " AND reg.section_id=sec.section_id AND sec.course_id=cc.course_id AND sec.academic_semester_id=secac.academic_semester_id AND UPPER(reg.grade)<>'W' AND secac.semester_start_date<=ac.semester_start_date),0) AS studied_ch, nvl((select tt.cgpa from transcript_semesters tt where tt.academic_semester_id=ac.academic_semester_id and tt.app_id=" + app_id + "),-1) as gpa,ac.academic_semester_id AS academic_semester_id,sem.semester_name||' '||yy.academic_year_title "
                        + " FROM academic_semester ac,semesters sem,academic_years yy WHERE ac.academic_year_id=yy.academic_year_id AND ac.semester_id=sem.semester_id AND ac.semester_start_date<sysdate AND ac.academic_semester_id IN (SELECT sec.academic_semester_id FROM student_registered_courses reg,sections sec WHERE reg.app_id=" + app_id + " AND reg.section_id=sec.section_id and UPPER(reg.grade)<>'W') order by ac.semester_start_date");
                while(rs.next())
                    {
                    if(rs.getString("academic_semester_id").equals(ignored_academic_semester_id))
                        break;
                    if(rs.getDouble("studied_ch") >= required_studied_ch)
                        {
                        if(rs.getDouble("gpa") < required_gpa)
                            status = "Not Pass";
                        else
                            status = "Pass";
                        break;
                        }
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
        return status;
        }

    public String create_new_student_id(connect conn, String academic_semester_id, String academic_degree_type, boolean is_short_admission)
        {
        if(is_short_admission)
            return "0000000";
        String new_std_id = "";
        String STUDENT_ID_FORMAT1 = "ABCD";
        String STUDENT_ID_FORMAT2 = "0";
        String STUDENT_ID_FORMAT3 = "000";
        int sub_str_index = -4;
        try
            {
            ResultSet rs_St_Format = conn.query("select nvl(student_id_format,'CD-0-0000') from STUDENT_ID_FORMAT where upper(degree_type)='" + academic_degree_type.toUpperCase() + "'");
            while(rs_St_Format.next())
                {
                String format[] = rs_St_Format.getString(1).trim().split("-");
                try
                    {
                    STUDENT_ID_FORMAT1 = format[0].trim();
                    STUDENT_ID_FORMAT2 = format[1].trim();
                    STUDENT_ID_FORMAT3 = format[2].trim();
                    }
                catch(Exception exp)
                    {
                    }
                }
            rs_St_Format.close();
            sub_str_index = STUDENT_ID_FORMAT3.trim().length() * -1;

            ResultSet rs = conn.query("select cal.first_year||cal.second_year as years,to_char(sem.semester_order,'" + STUDENT_ID_FORMAT2 + "') as sem, to_char(nvl((select max(to_number(substr(app.STUDENT_ID," + sub_str_index + ")))+1 from students app,CURRICULUM curr,ACADEMIC_DEGREES acd where app.curriculum_id = curr.curriculum_id and curr.DEGREE_ID=acd.DEGREE_ID and upper(acd.DEGREE_TYPE)='" + academic_degree_type.toUpperCase() + "' and app.academic_semester_id=" + academic_semester_id + "),1),'" + STUDENT_ID_FORMAT3 + "') as sub_serial from academic_semester cs,academic_years cal,semesters sem where  cs.academic_year_id=cal.academic_year_id and cs.semester_id=sem.semester_id and cs.academic_semester_id=" + academic_semester_id);
            while(rs.next())
                {
                String years = rs.getString("years").trim();
                for(int index = 0; index < STUDENT_ID_FORMAT1.trim().length(); index++)
                    {
                    if(STUDENT_ID_FORMAT1.toUpperCase().trim().charAt(index) == 'A')
                        new_std_id += String.valueOf(years.charAt(0));
                    if(STUDENT_ID_FORMAT1.toUpperCase().trim().charAt(index) == 'B')
                        new_std_id += String.valueOf(years.charAt(1));
                    if(STUDENT_ID_FORMAT1.toUpperCase().trim().charAt(index) == 'C')
                        new_std_id += String.valueOf(years.charAt(2));
                    if(STUDENT_ID_FORMAT1.toUpperCase().trim().charAt(index) == 'D')
                        new_std_id += String.valueOf(years.charAt(3));
                    if(STUDENT_ID_FORMAT1.toUpperCase().trim().charAt(index) == 'E')
                        new_std_id += String.valueOf(years.charAt(4));
                    if(STUDENT_ID_FORMAT1.toUpperCase().trim().charAt(index) == 'F')
                        new_std_id += String.valueOf(years.charAt(5));
                    if(STUDENT_ID_FORMAT1.toUpperCase().trim().charAt(index) == 'G')
                        new_std_id += String.valueOf(years.charAt(6));
                    if(STUDENT_ID_FORMAT1.toUpperCase().trim().charAt(index) == 'H')
                        new_std_id += String.valueOf(years.charAt(7));
                    }
                new_std_id += rs.getString("sem") + rs.getString("sub_serial");
                new_std_id = new_std_id.trim().replaceAll(" ", "");
                }
            rs.close();
            }
        catch(Exception exp)
            {
            }
        return new_std_id;
        }

    public int getRandomNumberInRange(int min, int max)
        {
        if(min >= max)
            return 0;
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
        }

    public PdfPCell create_pdf_cell(int colspan, int rowspan, String text, com.itextpdf.text.Font en_font, com.itextpdf.text.Font ar_font, BaseColor background, float FixedHeight, int halign, int border_style)
        {
        PdfPCell cell = null;

        if(textContainsArabic(text))
            {
            cell = new PdfPCell(new Phrase(text, ar_font));
            cell.setColspan(colspan);
            cell.setRowspan(rowspan);
            cell.setBorder(border_style);
            cell.setRunDirection(PdfWriter.RUN_DIRECTION_RTL);
            if(background != null)
                cell.setBackgroundColor(background);
            if(FixedHeight > 0)
                cell.setFixedHeight(FixedHeight);
            cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(halign);
            }
        else
            {
            cell = new PdfPCell(new Phrase(text, en_font));
            cell.setColspan(colspan);
            cell.setRowspan(rowspan);
            cell.setBorder(border_style);
            if(background != null)
                cell.setBackgroundColor(background);
            if(FixedHeight > 0)
                cell.setFixedHeight(FixedHeight);
            cell.setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(halign);
            }
        return cell;
        }

    public static boolean textContainsArabic(String text)
        {
        for(char charac : text.toCharArray())
            if(Character.UnicodeBlock.of(charac) == Character.UnicodeBlock.ARABIC)
                return true;
        return false;
        }

    public String college_dept_dean_email(connect conn, String course_id)
        {
        String email = "";
        ResultSet rs = null;
        try
            {
            rs = conn.query("SELECT distinct nvl((SELECT LISTAGG(uu.email,',') WITHIN GROUP (ORDER BY 1) FROM USERS uu WHERE uu.status IN (1,0) AND (UPPER(uu.college_dept_id)=UPPER(crs.course_college_department) or  upper(nvl(uu.ALTERNATIVE_DEP_COLLEGE_ID,uu.college_dept_id))=UPPER(crs.course_college_department)) AND uu.user_id IN (SELECT ucc.user_id FROM user_access_group ucc,access_groups gg WHERE ucc.GROUP_ID=gg.GROUP_ID AND UPPER(gg.group_name)='DEANS') ),' ') AS dean_email from courses crs where crs.course_id=" + course_id);
            while(rs.next())
                {
                email = rs.getString(1);
                }
            }
        catch(SQLException sqlEx)
            {
            System.err.println("Error: " + sqlEx.getMessage());
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

        return email;
        }
    }
