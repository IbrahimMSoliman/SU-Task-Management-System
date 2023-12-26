package tms.beans.common;

import jakarta.faces.context.FacesContext;
import jakarta.faces.event.PhaseEvent;
import jakarta.faces.event.PhaseId;
import jakarta.faces.event.PhaseListener;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
public class CacheControlPhaseListener implements PhaseListener
    {
    public PhaseId getPhaseId()
        {
        return PhaseId.RENDER_RESPONSE;
        }

    public void afterPhase(PhaseEvent event)
        {
        }

    public void beforePhase(PhaseEvent event)
        {
        FacesContext facesContext = event.getFacesContext();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        // Stronger according to blog comment below that references HTTP spec
        response.addHeader("Cache-Control", "no-store");
        response.addHeader("Cache-Control", "must-revalidate");
        // some date in the past
        response.addHeader("Expires", "Mon, 8 Aug 2006 10:00:00 GMT");
        }
    }
