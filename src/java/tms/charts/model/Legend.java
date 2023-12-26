package tms.charts.model;

import tms.charts.constants.HorizontalAlign;
import tms.charts.constants.LegendPosition;

public class Legend
    {
    private boolean show = true;

    private LegendPosition position = LegendPosition.top;
    private HorizontalAlign horizontalAlign = HorizontalAlign.left;
    private int width;
    private int height;
    private int offsetX = 0;
    private int offsetY = 0;

    public boolean isShow()
        {
        return show;
        }

    public void setShow(boolean show)
        {
        this.show = show;
        }

    public LegendPosition getPosition()
        {
        return position;
        }

    public Legend setPosition(LegendPosition position)
        {
        this.position = position;
        return this;
        }

    public HorizontalAlign getHorizontalAlign()
        {
        return horizontalAlign;
        }

    public Legend setHorizontalAlign(HorizontalAlign horizontalAlign)
        {
        this.horizontalAlign = horizontalAlign;
        return this;
        }

    public int getWidth()
        {
        return width;
        }

    public Legend setWidth(int width)
        {
        this.width = width;
        return this;
        }

    public int getHeight()
        {
        return height;
        }

    public Legend setHeight(int height)
        {
        this.height = height;
        return this;
        }

    public int getOffsetX()
        {
        return offsetX;
        }

    public Legend setOffsetX(int offsetX)
        {
        this.offsetX = offsetX;
        return this;
        }

    public int getOffsetY()
        {
        return offsetY;
        }

    public Legend setOffsetY(int offsetY)
        {
        this.offsetY = offsetY;
        return this;
        }

    }
