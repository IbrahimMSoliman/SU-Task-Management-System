package tms.charts.model.common.themes;

import tms.charts.model.common.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;









public class Default
  implements Theme
{
  public List<Color> getColors() {
    return new ArrayList<>(Arrays.asList(new Color[] { new Color("#008FFB"), new Color("#00E396"), new Color("#FEB019"), new Color("#FF4560"), new Color("#775DD0") }));
  }
}


/* Location:              C:\Users\hima\Downloads\HyJavaApex-3-EVAL-C.jar!\com\hyjavaapex\model\common\themes\Default.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */