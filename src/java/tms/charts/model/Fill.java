package tms.charts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import tms.charts.model.common.BaseOption;

public class Fill extends BaseOption
    {
    public Object opacity;
    private String type;
    private Gradient gradient;

    @JsonIgnore
    private List<Number> opacityAsArrayNumber;

    @JsonIgnore
    private Number opacityAsNumber;

    public class Gradient
        {
        private double opacityFrom;
        private double opacityTo;

        public double getOpacityFrom()
            {
            return opacityFrom;
            }

        public Gradient setOpacityFrom(double opacityFrom)
            {
            this.opacityFrom = opacityFrom;
            return this;
            }

        public double getOpacityTo()
            {
            return opacityTo;
            }

        public Gradient setOpacityTo(double opacityTo)
            {
            this.opacityTo = opacityTo;
            return this;
            }

        }

    public List<Number> getOpacityAsArrayNumber()
        {
        if(this.opacityAsArrayNumber == null)
            {
            this.opacityAsArrayNumber = new ArrayList<>();
            this.opacity = this.opacityAsArrayNumber;
            }
        return this.opacityAsArrayNumber;
        }

    public Fill setOpacity(List<Number> opacityAsArrayNumber)
        {
        if(opacityAsArrayNumber == null)
            opacityAsArrayNumber = new ArrayList<>();
        this.opacityAsArrayNumber = opacityAsArrayNumber;
        this.opacity = opacityAsArrayNumber;
        return this;
        }

    public Number getOpacityAsNumber()
        {
        return this.opacityAsNumber;
        }

    public Fill setOpacity(Number opacityAsNumber)
        {
        if(opacityAsNumber == null)
            opacityAsNumber = Double.valueOf(Double.NaN);
        this.opacityAsNumber = opacityAsNumber;
        this.opacity = opacityAsNumber;
        return this;
        }

    public String getType()
        {
        return type;
        }

    public Fill setType(String type)
        {
        this.type = type;
        return this;
        }

    public Gradient getGradient()
        {
        if(gradient == null)
            gradient = new Gradient();
        return gradient;
        }

    public Fill setGradient(Gradient gradient)
        {
        this.gradient = gradient;
        return this;
        }

    }
