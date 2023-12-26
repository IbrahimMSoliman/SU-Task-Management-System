package tms.charts.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import tms.charts.model.common.BaseOption;

public class Tooltip extends BaseOption
    {
    public static final Tooltip NULL = null;

    private Object y;

    @JsonIgnore
    private List<Y> yAsArrayObject;

    @JsonIgnore
    private Y yAsObject;

    public Y getYAsArrayObjectSingle()
        {
        /*  34 */ if(this.yAsArrayObject == null)
            {
            /*  35 */ this.yAsArrayObject = new ArrayList<>();
            /*  36 */ this.yAsArrayObject.add(new Y());
            /*  37 */ this.y = this.yAsArrayObject;
            }
        /*  39 */ return this.yAsArrayObject.get(0);
        }

    public List<Y> getYAsArrayObject()
        {
        /*  49 */ if(this.yAsArrayObject == null)
            {
            /*  50 */ this.yAsArrayObject = new ArrayList<>();
            /*  51 */ this.y = this.yAsArrayObject;
            }
        /*  53 */ return this.yAsArrayObject;
        }

    public Tooltip setY(List<Y> yAsArrayObject)
        {
        /*  65 */ if(yAsArrayObject == null)
            /*  66 */ yAsArrayObject = new ArrayList<>();
        /*  68 */ this.yAsArrayObject = yAsArrayObject;
        /*  69 */ this.y = yAsArrayObject;
        /*  70 */ return this;
        }

    public Y getY()
        {
        /*  80 */ if(this.yAsObject == null)
            {
            /*  81 */ this.yAsObject = new Y();
            /*  82 */ this.y = this.yAsObject;
            }
        /*  84 */ return this.yAsObject;
        }

    public Tooltip setY(Y yAsObject)
        {
        /*  96 */ if(yAsObject == null)
            {
            /*  97 */ yAsObject = new Y();
            /*  98 */ yAsObject.set_hcNulledOption(Boolean.valueOf(true));
            }
        /* 100 */ this.yAsObject = yAsObject;
        /* 101 */ this.y = yAsObject;
        /* 102 */ return this;
        }
    }


/* Location:              C:\Users\hima\Downloads\HyJavaApex-3-EVAL-C.jar!\com\hyjavaapex\model\apexcharts\Tooltip.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */
