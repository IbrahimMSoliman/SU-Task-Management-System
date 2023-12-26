package tms.beans.common;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;

public class msgr
    {

    public static void info(String msg)
        {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info!: ", msg));
        }

    public static void warn(String msg)
        {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Warning!: ", msg));
        }

    public static void error(String msg)
        {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!: ", msg));
        }

    public static void fatal(String msg)
        {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fatal!: ", msg));
        }

    }
