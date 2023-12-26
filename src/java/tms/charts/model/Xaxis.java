package tms.charts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import tms.charts.model.common.BaseOption;

public class Xaxis extends BaseOption
    {
    public static final Xaxis NULL = null;

    public Object categories;

    @JsonIgnore
    private List<Number> categoriesAsArrayNumber;

    @JsonIgnore
    private List<String> categoriesAsArrayString;

    public List<Number> getCategoriesAsArrayNumber()
        {
        if(this.categoriesAsArrayNumber == null)
            {
            this.categoriesAsArrayNumber = new ArrayList<>();
            this.categories = this.categoriesAsArrayNumber;
            }
        return this.categoriesAsArrayNumber;
        }

    public Xaxis setCategoriesAsArrayNumber(List<Number> categoriesAsArrayNumber)
        {
        if(categoriesAsArrayNumber == null)
            categoriesAsArrayNumber = new ArrayList<>();
        this.categoriesAsArrayNumber = categoriesAsArrayNumber;
        this.categories = categoriesAsArrayNumber;
        return this;
        }

    public List<String> getCategoriesAsArrayString()
        {
        if(this.categoriesAsArrayString == null)
            {
            this.categoriesAsArrayString = new ArrayList<>();
            this.categories = this.categoriesAsArrayString;
            }
        return this.categoriesAsArrayString;
        }

    public Xaxis setCategoriesAsArrayString(List<String> categoriesAsArrayString)
        {
        if(categoriesAsArrayString == null)
            categoriesAsArrayString = new ArrayList<>();
        this.categoriesAsArrayString = categoriesAsArrayString;
        this.categories = categoriesAsArrayString;
        return this;
        }
    }
