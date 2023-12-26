package tms.beans.quickView;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;
import tms.common.common_functions;
import tms.db.connect;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import tms.beans.common.msgr;
import tms.TAGS.Rating_result_model;

/**
 *
 * @author hima
 */
@Named
@SessionScoped
public class CourseQuickview implements Serializable
    {
    private String course_code;
    private String course_id;
    private String course_title;
    private String college_department;
    private String program_name;
    private String image_path;
    private double rating_value;
    private int int_rating_value;
    private String description;
    private String score_title1;
    private String score_title2;
    private String score_title3;

    private double max_score1;
    private double max_score2;
    private double max_score3;
    private double ch;
    private double nch;
    private String first_offering_semester;
    private int offered_sections_count;
    private int registered_std_count;
    private int pass_std_count;
    private int fails_std_count;
    private int fa_std_count;
    private double pass_percentage;
    private int instructors_count;
    private Rating_result_model rating_result_model;
    private double total_rating;
    private double total_rating_percentage;
    private List<rates_class> rates_list;
    private boolean show_rates_buttons;

    public void preview(String course_code)
        {
        this.course_code = course_code.trim().replaceAll(" ", "");
        connect conn = new connect();
        show_rates_buttons = true;
        try(ResultSet rs = conn.query("select cc.course_id as course_id, cc.course_code||' '||cc.course_number as course_code,cc.course_id as course_id,cc.course_name as course_title,clg.name as college_department,cc.course_image_path as image_path\n"
                + ",nvl((select count(*) from rating_sections rsec,sections sec where sec.section_id=rsec.section_id and sec.course_id=cc.course_id and rsec.rating_value=1),0) as rating_1\n"
                + ",nvl((select count(*) from rating_sections rsec,sections sec where sec.section_id=rsec.section_id and sec.course_id=cc.course_id and rsec.rating_value=2),0) as rating_2\n"
                + ",nvl((select count(*) from rating_sections rsec,sections sec where sec.section_id=rsec.section_id and sec.course_id=cc.course_id and rsec.rating_value=3),0) as rating_3\n"
                + ",nvl((select count(*) from rating_sections rsec,sections sec where sec.section_id=rsec.section_id and sec.course_id=cc.course_id and rsec.rating_value=4),0) as rating_4\n"
                + ",nvl((select count(*) from rating_sections rsec,sections sec where sec.section_id=rsec.section_id and sec.course_id=cc.course_id and rsec.rating_value=5),0) as rating_5"
                + ",round(nvl((select avg(rsec.rating_value) from rating_sections rsec,sections sec where sec.section_id=rsec.section_id and sec.course_id=cc.course_id),0),1) as rating_value\n"
                + ",nvl((select cd.description from course_description cd where cd.course_id=cc.course_id),' ') as descriptions,cc.credit_hours as ch,cc.none_credit_hours as nch"
                + ",(select count(*) from users uu where uu.status in(1,0) and uu.user_id in (select cii.inst_id from courses_instructors cii where cii.course_id=cc.course_id )) as instructors_count"
                + ",cc.score_title1,cc.score_title2,cc.score_title3,cc.default_classwork_score as max_score1,cc.default_midterm_score as max_score2,cc.default_final_exam_score as max_score3"
                + ",nvl((select sem.semester_name||' '||yy.academic_year_title as sem_name from academic_semester ac,academic_years yy,semesters sem where ac.academic_year_id=yy.academic_year_id and ac.semester_id=sem.semester_id and ac.semester_start_date=(select min(ff.semester_start_date) from academic_semester ff,sections sec where sec.academic_semester_id=ff.academic_semester_id and sec.status=1 and sec.course_id=cc.course_id)),' ') as first_offering_semester"
                + ",(select count(*) from sections sec where sec.course_id=cc.course_id and sec.status=1 and sec.section_no<>'0') as offered_sections_count\n"
                + ",(select count(*) from student_registered_courses reg, sections sec where reg.section_id=sec.section_id and sec.course_id=cc.course_id and reg.grade<>'W') as registered_std_count\n"
                + ",(select count(*) from student_registered_courses reg, sections sec where reg.section_id=sec.section_id and sec.course_id=cc.course_id and reg.grade not in ('W','FA','F','NP') ) as pass_std_count\n"
                + ",(select count(*) from student_registered_courses reg, sections sec where reg.section_id=sec.section_id and sec.course_id=cc.course_id and reg.grade in ('F','NP') ) as fails_std_count\n"
                + ",(select count(*) from student_registered_courses reg, sections sec where reg.section_id=sec.section_id and sec.course_id=cc.course_id and reg.grade ='FA') as fa_std_count"
                + ",nvl((select mm.major_name from majors mm where mm.major_id=cc.program_id),'General') as program_name"
                + " from courses cc,colleges_departments_view clg where clg.id=cc.course_college_department and upper(cc.course_code||cc.course_number)='" + this.course_code.toUpperCase() + "'"))
            {
            while(rs.next())
                {
                course_id = rs.getString("course_id");
                course_title = rs.getString("course_title");
                college_department = rs.getString("college_department");
                image_path = rs.getString("image_path");
                instructors_count = rs.getInt("instructors_count");
                ch = rs.getDouble("ch");
                nch = rs.getDouble("nch");
                first_offering_semester = rs.getString("first_offering_semester").trim();
                program_name = rs.getString("program_name");
                offered_sections_count = rs.getInt("offered_sections_count");
                registered_std_count = rs.getInt("registered_std_count");
                pass_std_count = rs.getInt("pass_std_count");
                fails_std_count = rs.getInt("fails_std_count");
                fa_std_count = rs.getInt("fa_std_count");
                pass_percentage = 0;
                if(registered_std_count > 0)
                    {
                    pass_percentage = (double) pass_std_count * 100 / (double) registered_std_count;
                    pass_percentage = Math.floor(pass_percentage * 10 + 0.5) / 10;
                    }
                score_title1 = rs.getString("score_title1");
                score_title2 = rs.getString("score_title2");
                score_title3 = rs.getString("score_title3");

                max_score1 = rs.getDouble("max_score1");
                max_score2 = rs.getDouble("max_score2");
                max_score3 = rs.getDouble("max_score3");

                rating_value = rs.getDouble("rating_value");
                int_rating_value = (int) Math.round(rating_value);
                description = rs.getString("descriptions").trim();
                total_rating = rs.getInt("rating_1") + rs.getInt("rating_2") + rs.getInt("rating_3") + rs.getInt("rating_4") + rs.getInt("rating_5");
                total_rating_percentage = 0;
                if(total_rating > 0)
                    {
                    total_rating_percentage = (rs.getInt("rating_1") + (rs.getInt("rating_2") * 2) + (rs.getInt("rating_3") * 3) + (rs.getInt("rating_4") * 4) + (rs.getInt("rating_5") * 5)) * 100 / (total_rating * 5);
                    total_rating_percentage = Math.floor(total_rating_percentage * 100 + .5) / 100;
                    }
                rating_result_model = new Rating_result_model("Course Rating", "value_percentage", 5, rating_value, 0/*points*/, 0/*expected_seats_count*/, 0, 0/*responsed_seats_count*/, 0,
                        rs.getInt("rating_1"), (Math.floor(((double) rs.getInt("rating_1") * 100 / (double) total_rating) * 100 + .5) / 100),
                        rs.getInt("rating_2"), (Math.floor(((double) rs.getInt("rating_2") * 100 / (double) total_rating) * 100 + .5) / 100),
                        rs.getInt("rating_3"), (Math.floor(((double) rs.getInt("rating_3") * 100 / (double) total_rating) * 100 + .5) / 100),
                        rs.getInt("rating_4"), (Math.floor(((double) rs.getInt("rating_4") * 100 / (double) total_rating) * 100 + .5) / 100),
                        rs.getInt("rating_5"), (Math.floor(((double) rs.getInt("rating_5") * 100 / (double) total_rating) * 100 + .5) / 100),
                        0, 0, 0, 0, 0, 0, 0, 0, 0, 0, total_rating_percentage, "rate_title1", "rate_title2", "rate_title3", "rate_title4", "rate_title5", "", "", "", "", "");

                }
            }
        catch(Exception ee)
            {
            msgr.error("Error: " + ee.getMessage());
            }

        conn.close();

        common_functions.getInstance().open_dialog("/quickview/courseQuickview", 700, 600);
        }

    public void load_rates()
        {
        connect conn = new connect();
        rates_list = new ArrayList<>();
        show_rates_buttons = false;
        try(ResultSet rs = conn.query("select sem.semester_name||' '||yy.academic_year_title as academic_semester_title,app.en_short_name as student_name,app.app_id as app_id,app.student_id as student_id,rr.rating_value as rating_value,nvl(rr.rating_notes,' ') as rating_notes,rr.rating_date as rating_date,app.photo_path as  photo_path from academic_semester ac,academic_years yy,semesters sem ,rating_sections rr,sections sec,students app where rr.app_id=app.app_id and rr.section_id=sec.section_id and sec.academic_semester_id=ac.academic_semester_id and ac.academic_year_id=yy.academic_year_id and ac.semester_id=sem.semester_id and sec.course_id=" + course_id + " order by rr.rating_date desc"))
            {
            while(rs.next())
                {
                rates_list.add(new rates_class(rs.getString("academic_semester_title"), rs.getString("student_name"), rs.getString("app_id"), rs.getString("student_id"), rs.getInt("rating_value"), rs.getString("rating_notes"), rs.getDate("rating_date"), rs.getString("photo_path")));
                }
            }
        catch(Exception ee)
            {
            msgr.error("Error: " + ee.getMessage());
            }
        conn.close();
        }

    public class rates_class
        {
        private String academic_semester_title;
        private String student_name;
        private String app_id;
        private String student_id;
        private int rating_value;
        private String rating_notes;
        private Date rating_date;
        private String student_image_path;

        public rates_class(String academic_semester_title, String student_name, String app_id, String student_id, int rating_value, String rating_notes, Date rating_date, String student_image_path)
            {
            this.academic_semester_title = academic_semester_title;
            this.student_name = student_name;
            this.app_id = app_id;
            this.student_id = student_id;
            this.rating_value = rating_value;
            this.rating_notes = rating_notes;
            this.rating_date = rating_date;
            this.student_image_path = student_image_path;
            }

        public String getStudent_image_path()
            {
            return student_image_path;
            }

        public void setStudent_image_path(String student_image_path)
            {
            this.student_image_path = student_image_path;
            }

        public Date getRating_date()
            {
            return rating_date;
            }

        public void setRating_date(Date rating_date)
            {
            this.rating_date = rating_date;
            }

        public String getAcademic_semester_title()
            {
            return academic_semester_title;
            }

        public void setAcademic_semester_title(String academic_semester_title)
            {
            this.academic_semester_title = academic_semester_title;
            }

        public String getStudent_name()
            {
            return student_name;
            }

        public void setStudent_name(String student_name)
            {
            this.student_name = student_name;
            }

        public String getApp_id()
            {
            return app_id;
            }

        public void setApp_id(String app_id)
            {
            this.app_id = app_id;
            }

        public String getStudent_id()
            {
            return student_id;
            }

        public void setStudent_id(String student_id)
            {
            this.student_id = student_id;
            }

        public int getRating_value()
            {
            return rating_value;
            }

        public void setRating_value(int rating_value)
            {
            this.rating_value = rating_value;
            }

        public String getRating_notes()
            {
            return rating_notes;
            }

        public void setRating_notes(String rating_notes)
            {
            this.rating_notes = rating_notes;
            }

        }

    public String getCourse_code()
        {
        return course_code;
        }

    public void setCourse_code(String course_code)
        {
        this.course_code = course_code;
        }

    public String getCourse_id()
        {
        return course_id;
        }

    public void setCourse_id(String course_id)
        {
        this.course_id = course_id;
        }

    public String getCourse_title()
        {
        return course_title;
        }

    public void setCourse_title(String course_title)
        {
        this.course_title = course_title;
        }

    public String getCollege_department()
        {
        return college_department;
        }

    public void setCollege_department(String college_department)
        {
        this.college_department = college_department;
        }

    public String getImage_path()
        {
        return image_path;
        }

    public void setImage_path(String image_path)
        {
        this.image_path = image_path;
        }

    public double getRating_value()
        {
        return rating_value;
        }

    public void setRating_value(double rating_value)
        {
        this.rating_value = rating_value;
        }

    public int getInt_rating_value()
        {
        return int_rating_value;
        }

    public void setInt_rating_value(int int_rating_value)
        {
        this.int_rating_value = int_rating_value;
        }

    public String getDescription()
        {
        return description;
        }

    public void setDescription(String description)
        {
        this.description = description;
        }

    public Rating_result_model getRating_result_model()
        {
        return rating_result_model;
        }

    public void setRating_result_model(Rating_result_model rating_result_model)
        {
        this.rating_result_model = rating_result_model;
        }

    public int getInstructors_count()
        {
        return instructors_count;
        }

    public void setInstructors_count(int instructors_count)
        {
        this.instructors_count = instructors_count;
        }

    public String getScore_title1()
        {
        return score_title1;
        }

    public void setScore_title1(String score_title1)
        {
        this.score_title1 = score_title1;
        }

    public String getScore_title2()
        {
        return score_title2;
        }

    public void setScore_title2(String score_title2)
        {
        this.score_title2 = score_title2;
        }

    public String getScore_title3()
        {
        return score_title3;
        }

    public void setScore_title3(String score_title3)
        {
        this.score_title3 = score_title3;
        }

    public double getMax_score1()
        {
        return max_score1;
        }

    public void setMax_score1(double max_score1)
        {
        this.max_score1 = max_score1;
        }

    public double getMax_score2()
        {
        return max_score2;
        }

    public void setMax_score2(double max_score2)
        {
        this.max_score2 = max_score2;
        }

    public double getMax_score3()
        {
        return max_score3;
        }

    public void setMax_score3(double max_score3)
        {
        this.max_score3 = max_score3;
        }

    public double getCh()
        {
        return ch;
        }

    public void setCh(double ch)
        {
        this.ch = ch;
        }

    public double getNch()
        {
        return nch;
        }

    public void setNch(double nch)
        {
        this.nch = nch;
        }

    public String getFirst_offering_semester()
        {
        return first_offering_semester;
        }

    public void setFirst_offering_semester(String first_offering_semester)
        {
        this.first_offering_semester = first_offering_semester;
        }

    public int getRegistered_std_count()
        {
        return registered_std_count;
        }

    public void setRegistered_std_count(int registered_std_count)
        {
        this.registered_std_count = registered_std_count;
        }

    public int getPass_std_count()
        {
        return pass_std_count;
        }

    public void setPass_std_count(int pass_std_count)
        {
        this.pass_std_count = pass_std_count;
        }

    public int getFails_std_count()
        {
        return fails_std_count;
        }

    public void setFails_std_count(int fails_std_count)
        {
        this.fails_std_count = fails_std_count;
        }

    public int getFa_std_count()
        {
        return fa_std_count;
        }

    public void setFa_std_count(int fa_std_count)
        {
        this.fa_std_count = fa_std_count;
        }

    public double getPass_percentage()
        {
        return pass_percentage;
        }

    public void setPass_percentage(double pass_percentage)
        {
        this.pass_percentage = pass_percentage;
        }

    public int getOffered_sections_count()
        {
        return offered_sections_count;
        }

    public void setOffered_sections_count(int offered_sections_count)
        {
        this.offered_sections_count = offered_sections_count;
        }

    public String getProgram_name()
        {
        return program_name;
        }

    public void setProgram_name(String program_name)
        {
        this.program_name = program_name;
        }

    public double getTotal_rating()
        {
        return total_rating;
        }

    public void setTotal_rating(double total_rating)
        {
        this.total_rating = total_rating;
        }

    public double getTotal_rating_percentage()
        {
        return total_rating_percentage;
        }

    public void setTotal_rating_percentage(double total_rating_percentage)
        {
        this.total_rating_percentage = total_rating_percentage;
        }

    public List<rates_class> getRates_list()
        {
        return rates_list;
        }

    public void setRates_list(List<rates_class> rates_list)
        {
        this.rates_list = rates_list;
        }

    public boolean isShow_rates_buttons()
        {
        return show_rates_buttons;
        }

    public void setShow_rates_buttons(boolean show_rates_buttons)
        {
        this.show_rates_buttons = show_rates_buttons;
        }

    }
