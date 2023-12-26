package tms.charts.model;

import tms.charts.model.common.BaseOption;
import tms.charts.model.common.Function;

public class Y extends BaseOption
    {
    /* 17 */ public static final Y NULL = null;

    private Function formatter;

    public Function getFormatter()
        {
        /* 27 */ if(this.formatter == null)
            /* 28 */ this.formatter = new Function();
        /* 30 */ return this.formatter;
        }

    public Y setFormatter(Function formatter)
        {
        /* 42 */ if(formatter == null)
            {
            /* 43 */ formatter = new Function();
            /* 44 */ formatter.set_hcNulledOption(Boolean.valueOf(true));
            }
        /* 46 */ this.formatter = formatter;
        /* 47 */ return this;
        }
    }
