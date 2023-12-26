package tms.charts;

import tms.charts.constants.ChartType;
import tms.charts.constants.StackType;
import tms.charts.model.Toolbar;
import tms.charts.model.common.BaseOption;

/**
 *
 * @author hima
 */
public class Chart extends BaseOption
    {
    private ChartType type;
    private int width;
    private int height;
    private boolean Stacked;
    private StackType stackType;
    private Toolbar toolbar;

    public ChartType getType()
        {
        return this.type;
        }

    public Chart setType(ChartType type)
        {
        this.type = type;
        return this;
        }

    public int getHeight()
        {
        return height;
        }

    public Chart setHeight(int height)
        {
        this.height = height;
        return this;
        }

    public boolean isStacked()
        {
        return Stacked;
        }

    public Chart setStacked(boolean Stacked)
        {
        this.Stacked = Stacked;
        return this;
        }

    public StackType getStackType()
        {
        return stackType;
        }

    public Chart setStackType(StackType stackType)
        {
        this.stackType = stackType;
        return this;
        }

    public int getWidth()
        {
        return width;
        }

    public void setWidth(int width)
        {
        this.width = width;
        }

    public Toolbar getToolbar()
        {
        return toolbar;
        }

    public Chart setToolbar(Toolbar toolbar)
        {
        this.toolbar = toolbar;
        return this;
        }

    }
