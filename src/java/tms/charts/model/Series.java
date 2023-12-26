package tms.charts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import tms.charts.model.common.BaseOption;
import tms.charts.model.common.Pair;

public class Series extends BaseOption
    {
    public static final Series NULL = null;

    public Object data;

    @JsonIgnore
    private List<Number> dataAsArrayNumber;

    @JsonIgnore
    private List<Pair> dataAsArrayPairs;

    private String name;

    public List<Number> getDataAsArrayNumber()
        {
        if(this.dataAsArrayNumber == null)
            {
            this.dataAsArrayNumber = new ArrayList<>();
            this.data = this.dataAsArrayNumber;
            }
        return this.dataAsArrayNumber;
        }

    public Series setDataAsArrayNumber(List<Number> dataAsArrayNumber)
        {
        if(dataAsArrayNumber == null)
            dataAsArrayNumber = new ArrayList<>();
        this.dataAsArrayNumber = dataAsArrayNumber;
        this.data = dataAsArrayNumber;
        return this;
        }

    public List<Pair> getDataAsArrayPairs()
        {
        if(this.dataAsArrayPairs == null)
            {
            this.dataAsArrayPairs = new ArrayList<>();
            this.data = this.dataAsArrayPairs;
            }
        return this.dataAsArrayPairs;
        }

    public Series setDataAsArrayPairs(List<Pair> dataAsArrayPairs)
        {
        if(dataAsArrayPairs == null)
            dataAsArrayPairs = new ArrayList<>();
        this.dataAsArrayPairs = dataAsArrayPairs;
        this.data = dataAsArrayPairs;
        return this;
        }

    public String getName()
        {
        return this.name;
        }

    public Series setName(String name)
        {
        if(name == null)
            name = "-null-field-";
        this.name = name;
        return this;
        }
    }
