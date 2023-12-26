package tms.charts.model;

import tms.charts.model.common.BaseOption;

public class Title
        extends BaseOption
    {
    public static final Title NULL = null;

    private String text;

    public String getText()
        {
        return this.text;
        }

    public Title setText(String text)
        {
        if(text == null)
            text = "-null-field-";

        this.text = text;
        return this;
        }
    }
