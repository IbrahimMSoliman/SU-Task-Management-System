<%@ page import="java.util.*"%>
<%
        try{
           for(Enumeration e = request.getSession(true).getAttributeNames(); e.hasMoreElements(); ) 
                 {     
                 try{request.getSession(true).removeAttribute((String) e.nextElement());}catch(Exception exp){}
                 }
                
           response.sendRedirect(request.getServletContext().getContextPath()+"/users/login.xhtml"); 
           }catch(Exception exp){}
%>