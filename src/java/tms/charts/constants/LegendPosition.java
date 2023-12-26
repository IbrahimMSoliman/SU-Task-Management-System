package tms.charts.constants;

import tms.charts.model.common.ModelEnum;

/**
 *
 * @author hima
 */
public enum LegendPosition implements ModelEnum
    {

    top("top"),
    right("right"),
    bottom("bottom"),
    left("left");

    private final String enumValue;

    LegendPosition(String enumValue)
        {
        this.enumValue = enumValue;
        }

    public String toString()
        {
        return this.enumValue;
        }
    }
