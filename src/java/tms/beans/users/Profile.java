package tms.beans.users;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.faces.context.FacesContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import tms.db.connect;
import jakarta.enterprise.context.SessionScoped;
import tms.beans.common.msgr;
import tms.application.TMSApplication;

@Named("user_profile")
@SessionScoped
public class Profile implements Serializable
    {
    @Inject

    private user_details user_details;

    private String name;
    private String user_type;
    private String title;
    private String work_mode;
    private String nationality;
    private String email;
    private String mobile;
    private String last_login_date;
    private String sponsor_name;
    private String ad_username;
    private String username;
    private String password;
    private String confirm_password;
    private String photo_path;

    private UploadedFile file;

    @PostConstruct
    public void init()
        {
        connect conn = new connect();
        if(user_details.getUser_type().equalsIgnoreCase("student"))
            try
            {
            ResultSet rs = conn.query("select nvl(app.photo_path,'/SIS_Files/users_avatar/unkown.png') as photo_path,nvl(app.mobile,' ') as mobile"
                    + ",app.en_first_name||' '||app.en_last_name as app_name"
                    + ",nat.country_name as nationality"
                    + ",app.study_mode as work_mode"
                    + ",sp.sponsor_name as sponsor_name"
                    + ",app.student_id as student_id"
                    + ",nvl(username,app.student_id) as username"
                    + ",to_char(app.last_login_date,'Day dd Mon yyyy hh:mi am') as last_login_date"
                    + " from students app,countries nat,sponsors sp where app.sponsor_id=sp.sponsor_id and app.nationality_id=nat.country_id and app.app_id=" + user_details.getUser_id());
            while(rs.next())
                {
                name = rs.getString("app_name");
                user_type = "Student";
                title = "";
                work_mode = rs.getString("work_mode");
                nationality = rs.getString("nationality");
                email = "";

                mobile = rs.getString("mobile");
                last_login_date = rs.getString("last_login_date");
                sponsor_name = rs.getString("sponsor_name");
                ad_username = rs.getString("student_id");
                username = rs.getString("student_id");
                password = "";
                confirm_password = "";
                photo_path = rs.getString("photo_path");
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("Error in (" + this.getClass().getName() + ") :" + exp.toString());
            }
        else
            try
            {
            ResultSet rs = conn.query("select decode(upper(uu.user_type),'ADMIN','Administrator','INST','Instructor',' ') as user_type,"
                    + "nvl(uu.photo_path,'/SIS_Files/users_avatar/unkown.png') as photo_path,nvl(uu.title,' ') as title,nvl(uu.email,' ') as email,"
                    + "uu.first_name||' '||uu.last_name as uname,nvl(uu.mobile,' ') as mobile,"
                    + "nat.country_name as nationality"
                    + ",uu.work_mode as work_mode"
                    + ",nvl((select sp.sponsor_name from sponsors sp where sp.sponsor_id=nvl(uu.sponsor_id,-1)),' ') as sponsor_name"
                    + ",nvl(uu.ad_username,' ') as ad_username"
                    + ",nvl(username,' ') as username"
                    + ",to_char(uu.last_login_date,'Day dd Mon yyyy hh:mi am') as last_login_date"
                    + " from users uu,countries nat where uu.nationality_id=nat.country_id and uu.user_id=" + user_details.getUser_id());
            while(rs.next())
                {
                name = rs.getString("uname");
                user_type = rs.getString("user_type");
                title = rs.getString("title");
                work_mode = rs.getString("work_mode");
                nationality = rs.getString("nationality");
                email = rs.getString("email");
                mobile = rs.getString("mobile");
                last_login_date = rs.getString("last_login_date");
                sponsor_name = rs.getString("sponsor_name");
                ad_username = rs.getString("ad_username");
                username = rs.getString("username").trim();
                password = "";
                confirm_password = "";
                photo_path = rs.getString("photo_path");
                }
            rs.close();
            }
        catch(Exception exp)
            {
            System.out.println("Error in (" + this.getClass().getName() + ") :" + exp.toString());
            }
        conn.close();

        }

    public void handleFilePhotoUpload(FileUploadEvent event)
        {

        String upload_folder = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/SIS_Files/users_avatar");
        try
            {
            String file_type = event.getFile().getFileName().substring(event.getFile().getFileName().lastIndexOf("."));
            String file_name = user_details.getUser_id() + file_type;

            File photo_file = new File(upload_folder + File.separator + file_name);
            if(photo_file.exists())
                photo_file.delete();

            OutputStream out = new FileOutputStream(photo_file);
            InputStream in = event.getFile().getInputStream();
            int read = 0;
            byte[] bytes = new byte[1024];
            while((read = in.read(bytes)) != -1)
                {
                out.write(bytes, 0, read);
                }
            in.close();
            out.flush();
            out.close();
            //now save the application icon to db
            photo_path = "/SIS_Files/users_avatar/" + file_name;
            connect conn = new connect();
            conn.update("update users set photo_path=? where user_id=?", new String[]
                {
                photo_path, user_details.getUser_id()
                });
            user_details.setPhoto_path(photo_path);
            conn.close();

            msgr.info("Your photo has been uploaded successfully......");

            }
        catch(Exception ee)
            {
            msgr.error("Unable to upload your photo.");
            }

        }

    public void save()
        {

        if(user_type.equalsIgnoreCase("student"))
            if(!password.equals(confirm_password))
                msgr.error("The Password does not match the confirm password.....");
            else if(password.trim().length() <= 5)
                msgr.error("The Password Should be 6 characters at least.....");
            else if(password.equals(username))
                msgr.error("Invalid Password ,username and password must be different....");
            else
                {
                connect conn = new connect();
                try
                    {
                    conn.update("update students set username=student_id ,password=? where app_id=?", new String[]
                        {
                        password, user_details.getUser_id()
                        });
                    msgr.info("Your password has been updated successfully.....");
                    }
                catch(Exception exp)
                    {
                    msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
                    }

                conn.close();

                }
        else //user
        if(username.replaceAll("0", "").replaceAll("1", "").replaceAll("2", "").replaceAll("3", "").replaceAll("4", "").replaceAll("5", "").replaceAll("6", "").replaceAll("7", "").replaceAll("8", "").replaceAll("9", "").trim().equals(""))
            msgr.error("Invalid username, the username must contains at least one character");
        else if(username.trim().length() <= 5)
            msgr.error("Invalid username, the username length must be 6 characters or more.....");
        else if(!password.equals(confirm_password))
            msgr.error("The Password does not match the confirm password.....");
        else if(password.trim().length() <= 5)
            msgr.error("The Password Should be 6 characters at least.....");
        else if(password.equalsIgnoreCase(username))
            msgr.error("Invalid Password ,username and password must be different....");
        else
            {
            connect conn = new connect();
            try
                {
                conn.update("update users  set username=? ,password=? where user_id=?", new String[]
                    {
                    username, password, user_details.getUser_id()
                    });
                msgr.info("Your account has been updated successfully.....");
                }
            catch(Exception exp)
                {
                msgr.error("Error in (" + this.getClass().getName() + ") :" + exp.toString());
                }

            conn.close();

            }
        password = "";
        confirm_password = "";
        }

    public void setUser_details(user_details user_details)
        {
        this.user_details = user_details;
        }

    public user_details getUser_details()
        {
        return user_details;
        }

    public void setName(String name)
        {
        this.name = name;
        }

    public String getName()
        {
        return name;
        }

    public void setUser_type(String user_type)
        {
        this.user_type = user_type;
        }

    public String getUser_type()
        {
        return user_type;
        }

    public void setTitle(String title)
        {
        this.title = title;
        }

    public String getTitle()
        {
        return title;
        }

    public void setWork_mode(String work_mode)
        {
        this.work_mode = work_mode;
        }

    public String getWork_mode()
        {
        return work_mode;
        }

    public void setNationality(String nationality)
        {
        this.nationality = nationality;
        }

    public String getNationality()
        {
        return nationality;
        }

    public void setEmail(String email)
        {
        this.email = email;
        }

    public String getEmail()
        {
        return email;
        }

    public void setMobile(String mobile)
        {
        this.mobile = mobile;
        }

    public String getMobile()
        {
        return mobile;
        }

    public void setLast_login_date(String last_login_date)
        {
        this.last_login_date = last_login_date;
        }

    public String getLast_login_date()
        {
        return last_login_date;
        }

    public void setSponsor_name(String sponsor_name)
        {
        this.sponsor_name = sponsor_name;
        }

    public String getSponsor_name()
        {
        return sponsor_name;
        }

    public void setAd_username(String ad_username)
        {
        this.ad_username = ad_username;
        }

    public String getAd_username()
        {
        return ad_username;
        }

    public void setUsername(String username)
        {
        this.username = username;
        }

    public String getUsername()
        {
        return username;
        }

    public void setPassword(String password)
        {
        this.password = password;
        }

    public String getPassword()
        {
        return password;
        }

    public void setConfirm_password(String confirm_password)
        {
        this.confirm_password = confirm_password;
        }

    public String getConfirm_password()
        {
        return confirm_password;
        }

    public void setPhoto_path(String photo_path)
        {
        this.photo_path = photo_path;
        }

    public String getPhoto_path()
        {
        return photo_path;
        }

    public void setFile(UploadedFile file)
        {
        this.file = file;
        }

    public UploadedFile getFile()
        {
        return file;
        }
    }
