package tms.charts.constants;

import tms.charts.model.common.ModelEnum;

public enum ChartType implements ModelEnum
    {
    line("line"),
    area("area"),
    bar("bar"),
    radar("radar"),
    histogram("histogram"),
    pie("pie"),
    donut("donut"),
    radialbar("radialbar"),
    scatter("scatter"),
    bubble("bubble"),
    heatmap("heatmap"),
    candlestick("candlestick"),;
    private final String enumValue;

    ChartType(String enumValue)
        {
        this.enumValue = enumValue;
        }

    public String toString()
        {
        return this.enumValue;
        }
    }
