package tms.charts.constants;

import tms.charts.model.common.ModelEnum;

public enum HorizontalAlign implements ModelEnum
    {
    left("left"),
    center("center"),
    right("right");

    private final String enumValue;

    HorizontalAlign(String enumValue)
        {
        this.enumValue = enumValue;
        }

    public String toString()
        {
        return this.enumValue;
        }
    }
