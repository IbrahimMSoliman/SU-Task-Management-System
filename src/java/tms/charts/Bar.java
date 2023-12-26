package tms.charts;

import tms.charts.constants.StartEndShape;
import tms.charts.model.common.BaseOption;

/**
 *
 * @author hima
 */
public class Bar extends BaseOption
    {

    private String columnWidth;
    private StartEndShape endingShape;
    private Boolean horizontal;
    private int borderRadius;

    public String getColumnWidth()
        {
        return this.columnWidth;
        }

    public Bar setColumnWidth(String columnWidth)
        {
        if(columnWidth == null)
            columnWidth = "-null-field-";
        this.columnWidth = columnWidth;
        return this;
        }

    public StartEndShape getEndingShape()
        {
        return this.endingShape;
        }

    public Bar setEndingShape(StartEndShape endingShape)
        {
        this.endingShape = endingShape;
        return this;
        }

    public Boolean getHorizontal()
        {
        return this.horizontal;
        }

    public Bar setHorizontal(Boolean horizontal)
        {
        this.horizontal = horizontal;
        return this;
        }

    public int getBorderRadius()
        {
        return borderRadius;
        }

    public Bar setBorderRadius(int borderRadius)
        {
        this.borderRadius = borderRadius;
        return this;
        }

    }
