package tms.charts.model.common;

import java.io.Serializable;

public class BaseOption implements Serializable
    {
    private static final long serialVersionUID = 677838818555464240L;
    private Boolean _hcNulledOption;

    public void set_hcNulledOption(Boolean _hcNulledOption)
        {
        this._hcNulledOption = _hcNulledOption;
        }
    }
