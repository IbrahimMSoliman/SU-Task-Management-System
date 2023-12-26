package tms.listeners;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import tms.application.TMSApplication;

/**
 *
 * @author isoliman
 */
@WebListener
public class TMSContextListener implements ServletContextListener
    {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent)
        {
        //call application class to load the system setting
        TMSApplication.getApplication_title();//just call any function inside the class to be triggered
        }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent)
        {
        System.out.println("Shutting down!");
        }

    }
