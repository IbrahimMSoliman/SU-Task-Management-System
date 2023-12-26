package tms.charts.model;

import tms.charts.model.common.BaseOption;

public class DataLabels extends BaseOption
    {
    public static final DataLabels NULL = null;

    private Boolean enabled;

    public Boolean getEnabled()
        {
        return this.enabled;
        }

    public DataLabels setEnabled(Boolean enabled)
        {
        this.enabled = enabled;
        return this;
        }
    }
