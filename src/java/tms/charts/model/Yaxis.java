package tms.charts.model;

import org.primefaces.model.charts.optionconfig.title.Title;
import tms.charts.model.common.BaseOption;

public class Yaxis extends BaseOption
    {
    public static final Yaxis NULL = null;

    private Title title;

    public Title getTitle()
        {
        if(this.title == null)
            this.title = new Title();
        return this.title;
        }

    public Yaxis setTitle(Title title)
        {
        if(title == null)
            title = new Title(); //title.set_hcNulledOption(Boolean.valueOf(true));
        this.title = title;
        return this;
        }
    }
