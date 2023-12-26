package tms.Exceptions;

import java.util.Iterator;
import jakarta.faces.FacesException;
import jakarta.faces.context.ExceptionHandler;
import jakarta.faces.context.ExceptionHandlerWrapper;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ExceptionQueuedEvent;
import jakarta.faces.event.ExceptionQueuedEventContext;

public class TMSExceptionHandler extends ExceptionHandlerWrapper
    {
    private ExceptionHandler parent;

    public TMSExceptionHandler()
        {
        }

    public TMSExceptionHandler(ExceptionHandler parent)
        {
        this.parent = parent;
        }

    @Override
    public ExceptionHandler getWrapped()
        {
        return this.parent;
        }

    @Override
    public void handle() throws FacesException
        {
        final Iterator<ExceptionQueuedEvent> queue = getUnhandledExceptionQueuedEvents().iterator();
        while(queue.hasNext())
            {
            ExceptionQueuedEvent item = queue.next();
            ExceptionQueuedEventContext exceptionQueuedEventContext = (ExceptionQueuedEventContext) item.getSource();
            try
                {
                Throwable throwable = exceptionQueuedEventContext.getException();
                FacesContext context = FacesContext.getCurrentInstance();
                if(throwable.toString().toUpperCase().contains("VIEWEXPIREDEXCEPTION"))
                    {

                    }
                ////////////////////////////////Not ViewExpiredException///////////////////////////////////////////////////
                else //Not ViewExpiredException
                    {
                    try
                        {
                        //throwable.printStackTrace();
                        // System.err.println(throwable.getMessage());
                        //for(StackTraceElement element:throwable.getStackTrace())
                        //System.out.println(element.getClassName()+"("+element.getFileName()+":"+element.getMethodName()+":"+element.getLineNumber());
                        context.getExternalContext().getSessionMap().put("error_message", throwable.getMessage());
                        context.getExternalContext().getSessionMap().put("error_stack", throwable.getStackTrace());
                        context.getExternalContext().redirect(context.getExternalContext().getRequestContextPath() + "/errors/errors_page.xhtml");
                        throwable = null;

                        context.responseComplete();
                        }
                    catch(Exception e)
                        {/*System.out.println("Error(SSSExceptionHandler) :"+e.toString());*/
                        }
                    return;
                    }
                }finally
                {
                queue.remove();
                }

            }

        //getWrapped().handle();
        }

    }
