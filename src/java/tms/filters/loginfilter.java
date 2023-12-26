package tms.filters;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.inject.Inject;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tms.beans.users.user_details;

public class loginfilter implements Filter
    {
    @Inject
    private user_details user_details;

    FilterConfig fc;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException
        {
        fc = filterConfig;
        }

    @Override
    public void doFilter(jakarta.servlet.ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException
        {
        System.setProperty("org.apache.el.parser.COERCE_TO_ZERO", "false");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String pageRequested = req.getRequestURI();
        String context_path = request.getServletContext().getContextPath();
        //Is AJAX request
        if(isAJAXRequest(req))
            if(user_details.isIsLoggedIn() == false && !pageRequested.toLowerCase().contains(context_path + "/admission/online_confirmation") && pageRequested.toLowerCase().indexOf("/login/") == -1 && pageRequested.toLowerCase().indexOf("login/smart_phone_login.xhtml") == -1 && pageRequested.toLowerCase().indexOf("/alumni/login.xhtml") == -1 && pageRequested.toLowerCase().indexOf("online_application") == -1 && pageRequested.toLowerCase().indexOf("/admission/online_admission") == -1)
                {
                StringBuilder sb = new StringBuilder();
                sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?><partial-response><redirect url=\"").append(context_path + "/login/login.xhtml").append("\"></redirect></partial-response>");
                resp.setHeader("Cache-Control", "no-cache");
                resp.setCharacterEncoding("UTF-8");
                resp.setContentType("text/xml");
                PrintWriter pw = resp.getWriter();
                pw.println(sb.toString());
                pw.flush();
                }
            else
                chain.doFilter(request, response);
        //not ajax
        else if(user_details.isIsLoggedIn())
            //check url header
            if(!pageRequested.toLowerCase().contains(context_path + "/admission/online_admission") && !pageRequested.trim().contains(context_path + "/dashboard.xhtml") && !pageRequested.trim().contains(context_path + "/students/students_courses_evaluation.xhtml") && !pageRequested.trim().contains(context_path + "/registration/booking_history_forms.jsp") && req.getHeader("referer") == null && !pageRequested.equalsIgnoreCase(context_path + "/login/login.xhtml") && !pageRequested.equalsIgnoreCase(context_path + "/login/smart_phone_login.xhtml") && !pageRequested.contains(context_path + "/errors/") && !pageRequested.contains("_forward.jsp") && !pageRequested.contains("_popup.jsp"))//if the user try to write the URL in addressbar
                resp.sendRedirect(context_path + "/dashboard.xhtml");
            else
                chain.doFilter(request, response);
        else //redirect user to login page
        if(pageRequested.contains("/admission/online_admission/") || pageRequested.toLowerCase().contains(context_path + "/students/alumni/login.xhtml") || pageRequested.toLowerCase().contains(context_path + "/admission/online_confirmation") || pageRequested.equalsIgnoreCase(context_path + "/alumni/index.jsp") || pageRequested.equalsIgnoreCase(context_path + "/login/alumni_login.xhtml") || pageRequested.equalsIgnoreCase(context_path + "/login/forget_password.xhtml") || pageRequested.equalsIgnoreCase(context_path + "/login/reset_password.xhtml") || pageRequested.toUpperCase().contains("JAKARTA.FACES.RESOURCE") || pageRequested.toLowerCase().contains(context_path + "/login/login.xhtml") || pageRequested.toLowerCase().contains(context_path + "/login/smart_phone_login.xhtml") || pageRequested.toLowerCase().contains(context_path + "/errors/"))
            chain.doFilter(request, response);
        else
            resp.sendRedirect(context_path + "/login/login.xhtml");
        }

    private boolean isAJAXRequest(HttpServletRequest request)
        {
        boolean check = false;
        String facesRequest = request.getHeader("Faces-Request");
        if(facesRequest != null && facesRequest.equals("partial/ajax"))
            check = true;
        return check;
        }

    @Override
    public void destroy()
        {

        }

    }
