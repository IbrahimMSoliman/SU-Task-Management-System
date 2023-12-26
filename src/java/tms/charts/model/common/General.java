package tms.charts.model.common;






public class General
  extends BaseOption
{
  private static final long serialVersionUID = 1L;
/* 12 */   public static final General NULL = null;



  
  private String jsValue;




  
  public General() {}




  
  public General(String jsValue) {
/* 30 */     this.jsValue = jsValue;
  }






  
  public String getRawValue() {
/* 40 */     return "" + this.jsValue;
  }









  
  public General setJsValue(String jsValue) {
/* 53 */     this.jsValue = jsValue;
/* 54 */     return this;
  }






  
  public String getJsValue() {
/* 64 */     return this.jsValue;
  }
}


/* Location:              C:\Users\hima\Downloads\HyJavaApex-3-EVAL-C.jar!\com\hyjavaapex\model\common\General.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */