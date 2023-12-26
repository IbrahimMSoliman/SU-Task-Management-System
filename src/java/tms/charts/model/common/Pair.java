package tms.charts.model.common;

public class Pair extends BaseOption
    {
    private static final long serialVersionUID = 1L;
    private Number value1;
    private Number value2;

    public Pair(Number value1, Number value2)
        {
        this.value1 = value1;
        this.value2 = value2;
        }

    public String getRawValue()
        {
        return "[" + this.value1 + ", " + this.value2 + "]";
        }
    }
