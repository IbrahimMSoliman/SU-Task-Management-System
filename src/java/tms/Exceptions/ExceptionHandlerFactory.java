package tms.Exceptions;

import jakarta.faces.context.ExceptionHandler;

public class ExceptionHandlerFactory extends jakarta.faces.context.ExceptionHandlerFactory
    {
    private final jakarta.faces.context.ExceptionHandlerFactory parent;

    public ExceptionHandlerFactory(jakarta.faces.context.ExceptionHandlerFactory parent)
        {
        this.parent = parent;
        }

    @Override
    public ExceptionHandler getExceptionHandler()
        {
        ExceptionHandler result = parent.getExceptionHandler();
        result = new TMSExceptionHandler(result);
        return result;
        }
    }
