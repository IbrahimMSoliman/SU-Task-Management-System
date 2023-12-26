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

@FacesValidator("sis.hischoolscore")
public class HiSchoolScoreValidator implements Validator, ClientValidator
    {
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
        academic_degree_type = (String) uIComponent.getAttributes().get("academic_degree_type");
        if(academic_degree_type == null)
            academic_degree_type = "";
        if(academic_degree_type.equalsIgnoreCase(""))
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", "Unknow academic degree..."));

        if(value == null)
            if(academic_degree_type.equalsIgnoreCase("UNDERGRADUATE"))
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", "School Score: Empty value is not allowed..."));
            else
                throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", "CGPA: Empty value is not allowed..."));

        else
            {
            String str_value = value.toString().replaceAll("%", "");
            double dvalue = 0;
            try
                {
                dvalue = Double.parseDouble(str_value);
                }
            catch(Exception ee)
                {
                }
            if(dvalue == 0)
                if(academic_degree_type.equalsIgnoreCase("UNDERGRADUATE"))
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", "School Score: Value must be greater than zero"));
                else
                    throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Validation Error", "CGPA : Value must be greater than zero"));
            }
        }
    }
