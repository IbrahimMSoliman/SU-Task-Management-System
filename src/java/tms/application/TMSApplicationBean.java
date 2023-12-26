package tms.application;

import java.io.Serializable;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import org.primefaces.PrimeFaces;

/**
 *
 * @author hima
 */
@Named("tmsApplicationBean")
@ApplicationScoped
public class TMSApplicationBean implements Serializable
    {
    private String contextPath;

    public TMSApplicationBean()
        {
        }

    @PostConstruct
    public void init()
        {
        contextPath = FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath();
        }

    public void close_dialog()
        {
        PrimeFaces.current().dialog().closeDynamic(null);
        }

    public String getContextPath()
        {
        return contextPath;
        }

    public void setContextPath(String contextPath)
        {
        this.contextPath = contextPath;
        }

    public String getApplication_favicon_path()
        {
        return TMSApplication.getApplication_favicon_path();
        }

    public String getApplication_logo_path()
        {
        return TMSApplication.getApplication_logo_path();
        }

    public String getEn_university_name()
        {
        return TMSApplication.getEn_university_name();
        }

    public String getAr_university_name()
        {
        return TMSApplication.getAr_university_name();
        }

    public String getApplication_title()
        {
        return TMSApplication.getApplication_title();
        }

    public String getUniversity_website()
        {
        return TMSApplication.getUniversity_website();
        }

    public String getFacebook_url()
        {
        return TMSApplication.getFacebook_url();
        }

    public String getYoutube_url()
        {
        return TMSApplication.getYoutube_url();
        }

    public String getTwitter_url()
        {
        return TMSApplication.getTwitter_url();
        }

    public String getInstagram_url()
        {
        return TMSApplication.getInstagram_url();
        }

    public String getCopyrigh_year()
        {
        return TMSApplication.getCopyrigh_year();
        }

    public String getDashboard_main_image_path()
        {
        return TMSApplication.getDashboard_main_image_path();
        }

    }
