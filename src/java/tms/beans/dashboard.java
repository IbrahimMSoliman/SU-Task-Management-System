package tms.beans;

import java.io.Serializable;
import jakarta.annotation.PostConstruct;
import jakarta.inject.Named;
import jakarta.inject.Inject;
import jakarta.enterprise.context.SessionScoped;
import tms.beans.users.user_details;

@Named("dashboard")
@SessionScoped
public class dashboard implements Serializable
    {
    @Inject
    private user_details user_details;
    private String sql;

    public dashboard()
        {

        }

    @PostConstruct
    public void init()
        {

        }

    public void setUser_details(user_details user_details)
        {
        this.user_details = user_details;
        }

    public user_details getUser_details()
        {
        return user_details;
        }

    public void setSql(String sql)
        {
        this.sql = sql;
        }

    public String getSql()
        {
        return sql;
        }

    }
