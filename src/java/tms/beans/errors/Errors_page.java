package tms.beans.errors;

import java.io.Serializable;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import tms.beans.users.user_details;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Named("errors_page")
@ViewScoped
public class Errors_page implements Serializable
    {
    @Inject

    private user_details user_details;

    private String error_messages;
    private String error_stack;
    StackTraceElement[] traceElement;
    Logger logger = LogManager.getLogger(this.getClass());

    @PostConstruct
    public void init()
        {
        error_messages = "";
        error_stack = "";
        //get messages from flash
        try
            {
            FacesContext context = FacesContext.getCurrentInstance();
            error_stack = "";
            if(context.getExternalContext().getSessionMap().get("error_message") != null)
                error_messages = context.getExternalContext().getSessionMap().get("error_message").toString();
            if(context.getExternalContext().getSessionMap().get("error_stack") != null)
                traceElement = (StackTraceElement[]) context.getExternalContext().getSessionMap().get("error_stack");
            if(traceElement != null && traceElement.length > 0)
                {
                error_stack = "<ul style=\"list-style: square inside !important;padding-left:20px;font-size:12px;\">";
                for(StackTraceElement element : traceElement)
                    error_stack += "<li> at " + element.getClassName() + "(" + element.getFileName() + ":" + element.getMethodName() + ":" + element.getLineNumber() + ").</li>";
                error_stack += "</ul>";
                }

            }
        catch(Exception ee)
            {
            ee.printStackTrace();
            }

        if(error_stack == null)
            error_stack = "";

        }

    public void setUser_details(user_details user_details)
        {
        this.user_details = user_details;
        }

    public user_details getUser_details()
        {
        return user_details;
        }

    public void setError_messages(String error_messages)
        {
        this.error_messages = error_messages;
        }

    public String getError_messages()
        {
        return error_messages;
        }

    public void setError_stack(String error_stack)
        {
        this.error_stack = error_stack;
        }

    public String getError_stack()
        {
        return error_stack;
        }

    public void setLogger(Logger logger)
        {
        this.logger = logger;
        }

    public Logger getLogger()
        {
        return logger;
        }

    public void setTraceElement(StackTraceElement[] traceElement)
        {
        this.traceElement = traceElement;
        }

    public StackTraceElement[] getTraceElement()
        {
        return traceElement;
        }
    }
