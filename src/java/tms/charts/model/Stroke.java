package tms.charts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import tms.charts.model.common.BaseOption;
import tms.charts.model.common.Color;

public class Stroke extends BaseOption
    {
    private String curve;

    private Boolean show;

    public Object width;

    @JsonIgnore
    private List<Number> widthAsArrayNumber;

    @JsonIgnore
    private Number widthAsNumber;

    public Boolean getShow()
        {
        return this.show;
        }

    public Stroke setShow(Boolean show)
        {
        this.show = show;
        return this;
        }

    public List<Number> getWidthAsArrayNumber()
        {
        if(this.widthAsArrayNumber == null)
            {
            this.widthAsArrayNumber = new ArrayList<>();
            this.width = this.widthAsArrayNumber;
            }
        return this.widthAsArrayNumber;
        }

    public Stroke setWidth(List<Number> widthAsArrayNumber)
        {
        if(widthAsArrayNumber == null)
            widthAsArrayNumber = new ArrayList<>();
        this.widthAsArrayNumber = widthAsArrayNumber;
        this.width = widthAsArrayNumber;
        return this;
        }

    public Number getWidthAsNumber()
        {
        return this.widthAsNumber;
        }

    public Stroke setWidth(Number widthAsNumber)
        {
        if(widthAsNumber == null)
            widthAsNumber = Double.valueOf(Double.NaN);
        this.widthAsNumber = widthAsNumber;
        this.width = widthAsNumber;
        return this;
        }

    public String getCurve()
        {
        return curve;
        }

    public Stroke setCurve(String curve)
        {
        this.curve = curve;
        return this;
        }

    public Object getWidth()
        {
        return width;
        }

    public void setWidth(Object width)
        {
        this.width = width;
        }

    }
