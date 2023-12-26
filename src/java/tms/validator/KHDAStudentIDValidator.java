package tms.validator;

import java.sql.*;
import java.util.Collections;
import java.util.Map;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import org.primefaces.validate.ClientValidator;
import tms.db.connect;

@FacesValidator("sis.KHDAStudentIDValidator")
public class KHDAStudentIDValidator implements Validator, ClientValidator
    {

    private String app_id;
    private String academic_degree_type;

    @Override
    public Map<String, Object> getMetadata()
        {
        // TODO Implement this method
        return Collections.emptyMap();
        }

    @Override
    public String getValidatorId()
        {
        // TODO Implement this method
        return null;
        }

    @Override
    public void validate(FacesContext facesContext, UIComponent uIComponent, Object value) throws ValidatorException
        {
        if(value == null)
            return;
        else
            {
            String other_student_id = "";
            app_id = (String) uIComponent.getAttributes().get("app_id");
            academic_degree_type = (String) uIComponent.getAttributes().get("academic_degree_type");
            if(app_id == null)
                app_id = "";
            if(academic_degree_type == null)
                academic_degree_type = "";
            String sql = "select app.student_id from students app where ltrim(TRIM(BOTH ' ' FROM app.khda_student_id),'0')='" + value.toString().replaceFirst("^0+(?!$)", "") + "'";//  app.NATIONAL_ID='"+value.toString().replaceAll(" ", "").trim()+"'";
            if(!app_id.trim().equals(""))
                sql += " and app.app_id<>" + app_id + " and ((select dd.degree_type from curriculum curr,academic_degrees dd where curr.curriculum_id=app.curriculum_id and curr.degree_id=dd.degree_id)=(select dd.degree_type from curriculum curr,academic_degrees dd where curr.curriculum_id=(select app2.curriculum_id from students app2 where app2.app_id=" + app_id + ") and curr.degree_id=dd.degree_id))";
            if(!academic_degree_type.trim().equals(""))
                sql += " and ((select upper(dd.degree_type) from curriculum curr,academic_degrees dd where curr.curriculum_id=app.curriculum_id and curr.degree_id=dd.degree_id)='" + academic_degree_type.trim().toUpperCase() + "')";

            connect conn = new connect();
            try
                {
                ResultSet rs = conn.query(sql);
                while(rs.next())
                    {
                    other_student_id = rs.getString(1);
                    break;
                    }
                rs.close();
                }
            catch(Exception exp)
                {
                }
            if(!other_student_id.trim().equals(""))
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", "KHDA Student ID (" + value.toString() + " belongs to another student (" + other_student_id + "))"));
            conn.close();

            }
        }
    }
