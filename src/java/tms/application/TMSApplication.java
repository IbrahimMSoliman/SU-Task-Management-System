package tms.application;

import java.io.File;
import java.sql.ResultSet;
import tms.db.connect;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TMSApplication implements Serializable
    {
    private static final Map<String, List<BreadCrumbs>> breadcrumbs_models_list = new HashMap<>();
    private static TMSApplication tmsapplication = null;

    //logo for headers
    private static String application_favicon_path = "/TMS_Files/sys_images/icons/original_favicon.png";
    private static String application_logo_path = "/TMS_Files/sys_images/app/logo.png";
    private static String dashboard_main_image_path = "/TMS_Files/sys_images/dashboard/main.png";

    //university details
    private static String en_university_name = "Sohar University";
    private static String ar_university_name = "\u062c\u0627\u0645\u0639\u0629 \u0635\u062d\u0627\u0631";
    private static String application_title = "Tasks Management System";
    private static String university_website = "https://su.edu.om";

    private static String facebook_url = "https://www.facebook.com/soharuniversityoman";
    private static String youtube_url = "https://www.youtube.com/channel/UCMsP1JKpM7-Lq4j3Fnf7X1g?view_as=subscriber";
    private static String twitter_url = "https://twitter.com/Soharuniv";
    private static String instagram_url = "https://www.instagram.com/soharuniversity_oman/";
    private static String copyrigh_year = "2023";

    private static String context_path = "";

    private static String upload_folder_path = "E:\\TMSuploads";

    //static class contructor
    static
        {
        load_breadcrumbs_lists();
        }

    public TMSApplication()
        {

        }

    private static void load_breadcrumbs_lists()
        {
        File current_class = new File(TMSApplication.class.getProtectionDomain().getCodeSource().getLocation().getFile());
        context_path = current_class.getAbsolutePath().substring(0, current_class.getAbsolutePath().indexOf("WEB-INF"));
        context_path = context_path.replaceAll("%20", " ");
        connect conn = new connect();
        try(ResultSet rs = conn.query("SELECT item_id as item_id,item_url as item_url,item_parent,item_title,CONNECT_BY_ROOT item_id AS menu_item_id,LTRIM(SYS_CONNECT_BY_PATH(item_title, 'xxx'), 'xxx') AS path,LTRIM(SYS_CONNECT_BY_PATH(icon, 'iii'), 'iii') AS icons, CONNECT_BY_ISLEAF AS leaf,CONNECT_BY_ISCYCLE AS cycle FROM   menu_items START WITH item_parent ='MAIN' CONNECT BY NOCYCLE item_parent = PRIOR to_char(item_id) ORDER SIBLINGS BY item_id"))
            {
            while(rs.next())
                {
                if(rs.getString("leaf").equals("1"))  //leaf item
                    {
                    String[] path = rs.getString("path").split("xxx");
                    String[] icons = rs.getString("icons").split("iii");
                    if(path != null && path.length > 0)
                        {
                        List list = new ArrayList<BreadCrumbs>();
                        //Dashboard
                        list.add(new BreadCrumbs("Dashboard", "pi pi-home", "dashboard.xhtml"));
                        for(int index = 0; index < path.length; index++)
                            list.add(new BreadCrumbs(path[index], icons[index], (index + 1) == path.length ? rs.getString("item_url") : "#"));
                        breadcrumbs_models_list.put(rs.getString("item_id"), list);
                        }
                    }
                }
            }
        catch(Exception ee)
            {
            }

        conn.close();
        }

    public static Map<String, List<BreadCrumbs>> getBreadcrumbs_models_list()
        {
        return breadcrumbs_models_list;
        }

    public static void setDashboard_main_image_path(String dashboard_main_image_path)
        {
        TMSApplication.dashboard_main_image_path = dashboard_main_image_path;
        }

    public static String getDashboard_main_image_path()
        {
        return dashboard_main_image_path;
        }

    public static TMSApplication getTmsapplication()
        {
        return tmsapplication;
        }

    public static void setTmsapplication(TMSApplication tmsapplication)
        {
        TMSApplication.tmsapplication = tmsapplication;
        }

    public static String getApplication_favicon_path()
        {
        return application_favicon_path;
        }

    public static void setApplication_favicon_path(String application_favicon_path)
        {
        TMSApplication.application_favicon_path = application_favicon_path;
        }

    public static String getApplication_logo_path()
        {
        return application_logo_path;
        }

    public static void setApplication_logo_path(String application_logo_path)
        {
        TMSApplication.application_logo_path = application_logo_path;
        }

    public static String getEn_university_name()
        {
        return en_university_name;
        }

    public static void setEn_university_name(String en_university_name)
        {
        TMSApplication.en_university_name = en_university_name;
        }

    public static String getAr_university_name()
        {
        return ar_university_name;
        }

    public static void setAr_university_name(String ar_university_name)
        {
        TMSApplication.ar_university_name = ar_university_name;
        }

    public static String getApplication_title()
        {
        return application_title;
        }

    public static void setApplication_title(String application_title)
        {
        TMSApplication.application_title = application_title;
        }

    public static String getUniversity_website()
        {
        return university_website;
        }

    public static void setUniversity_website(String university_website)
        {
        TMSApplication.university_website = university_website;
        }

    public static String getFacebook_url()
        {
        return facebook_url;
        }

    public static void setFacebook_url(String facebook_url)
        {
        TMSApplication.facebook_url = facebook_url;
        }

    public static String getYoutube_url()
        {
        return youtube_url;
        }

    public static void setYoutube_url(String youtube_url)
        {
        TMSApplication.youtube_url = youtube_url;
        }

    public static String getTwitter_url()
        {
        return twitter_url;
        }

    public static void setTwitter_url(String twitter_url)
        {
        TMSApplication.twitter_url = twitter_url;
        }

    public static String getInstagram_url()
        {
        return instagram_url;
        }

    public static void setInstagram_url(String instagram_url)
        {
        TMSApplication.instagram_url = instagram_url;
        }

    public static String getCopyrigh_year()
        {
        return copyrigh_year;
        }

    public static void setCopyrigh_year(String copyrigh_year)
        {
        TMSApplication.copyrigh_year = copyrigh_year;
        }

    public static String getContext_path()
        {
        return context_path;
        }

    public static void setContext_path(String context_path)
        {
        TMSApplication.context_path = context_path;
        }

    public static String getUpload_folder_path()
        {
        return upload_folder_path;
        }

    public static void setUpload_folder_path(String upload_folder_path)
        {
        TMSApplication.upload_folder_path = upload_folder_path;
        }

    }
