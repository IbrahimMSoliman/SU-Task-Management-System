package tms.charts.model.common;















public class Triplet
  extends BaseOption
{
  private static final long serialVersionUID = 1L;
  private Number value1;
  private Number value2;
  private Number value3;
  
  public Triplet(Number value1, Number value2, Number value3) {
/* 26 */     this.value1 = value1;
/* 27 */     this.value2 = value2;
/* 28 */     this.value3 = value3;
  }






  
  public String getRawValue() {
/* 38 */     return "[" + this.value1 + ", " + this.value2 + ", " + this.value3 + "]";
  }
}


/* Location:              C:\Users\hima\Downloads\HyJavaApex-3-EVAL-C.jar!\com\hyjavaapex\model\common\Triplet.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */