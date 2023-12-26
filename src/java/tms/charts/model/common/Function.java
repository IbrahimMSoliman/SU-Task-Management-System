package tms.charts.model.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Function extends BaseOption
    {
    private static final long serialVersionUID = 1L;
    public static final Function NULL = null;

    private String functionBody;
    private List<String> parameters = new ArrayList<>();

    private boolean emitAsString = false;

    private boolean convertFunction = false;

    public Function()
        {
        }

    public Function(String functionBody, String... parameters)
        {
        this.functionBody = functionBody;
        this.parameters = Arrays.asList(parameters);
        }

    public Function(String functionBody)
        {
        this.functionBody = functionBody;
        }

    public String getRawValue()
        {
        String functionDecl;
        if(getFunctionBody() == null)
            return null;

        if(this.parameters.isEmpty())
            functionDecl = "function()";
        else
            {

            boolean firstTime = true;
            functionDecl = "function(";

            for(String param : this.parameters)
                {
                if(!firstTime)
                    functionDecl = functionDecl + ",";

                functionDecl = functionDecl + param;
                firstTime = false;
                }

            functionDecl = functionDecl + ")";
            }

        return functionDecl + "{" + getFunctionBody() + "}";
        }

    public void addParameter(String parameter)
        {
        this.parameters.add(parameter);
        }

    public String getFunctionBody()
        {
        return this.functionBody;
        }

    public Function setFunctionBody(String functionBody)
        {
        this.functionBody = functionBody;
        return this;
        }

    public boolean isEmitAsString()
        {
        return this.emitAsString;
        }

    public boolean isConvertFunction()
        {
        return this.convertFunction;
        }

    public Function setEmitAsString(boolean emitAsString)
        {
        return setEmitAsString(emitAsString, true);
        }

    public Function setEmitAsString(boolean emitAsString, boolean convertFunction)
        {
        this.emitAsString = emitAsString;
        this.convertFunction = convertFunction;
        return this;
        }
    }
