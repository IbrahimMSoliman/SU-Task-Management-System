package tms.charts;

import tms.charts.model.common.BaseOption;

/**
 *
 * @author hima
 */
public class PlotOptions extends BaseOption
    {
    private Bar bar;

    public Bar getBar()
        {
        if(this.bar == null)
            this.bar = new Bar();
        return bar;
        }
    }
