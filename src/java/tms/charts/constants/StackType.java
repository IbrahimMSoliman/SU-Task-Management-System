package tms.charts.constants;

import tms.charts.model.common.ModelEnum;

public enum StackType implements ModelEnum
    {
    NORMAL("normal"),
    PERCENT100("100%");

    private final String value;

    StackType(String value)
        {
        this.value = value;
        }

    public String toString()
        {
        return this.value;
        }
    }
