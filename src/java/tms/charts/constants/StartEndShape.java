package tms.charts.constants;

import tms.charts.model.common.ModelEnum;

public enum StartEndShape implements ModelEnum
    {
    ROUNDED("rounded");
    private final String enumValue;

    StartEndShape(String enumValue)
        {
        this.enumValue = enumValue;
        }

    public String toString()
        {
        return this.enumValue;
        }
    }
