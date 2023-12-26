package tms.converters;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

@FacesConverter("trim")
public class StringTrimmer implements Converter
    {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value)
        {
        return value != null ? value.trim().replaceAll("  ", " ").replaceAll("  ", " ") : null;
        }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value)
        {
        return (String) value;
        }

    }
