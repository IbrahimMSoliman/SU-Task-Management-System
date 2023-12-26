package tms.charts.model.common;


















public class ColorStop
  extends BaseOption
{
  private static final long serialVersionUID = 1L;
  private Number offset;
  private Color color;
  private Number opacity;
  
  public ColorStop() {}
  
  public ColorStop(Number offset, Color color, Number opacity) {
/* 31 */     this.offset = offset;
/* 32 */     this.color = color;
/* 33 */     this.opacity = opacity;
  }





  
  public Number getOffset() {
    return this.offset;
  }







  
  public ColorStop setOffset(Number offset) {
/* 53 */     this.offset = offset;
/* 54 */     return this;
  }





  
  public Color getColor() {
/* 63 */     return this.color;
  }







  
  public ColorStop setColor(Color color) {
/* 74 */     this.color = color;
/* 75 */     return this;
  }





  
  public Number getOpacity() {
/* 84 */     return this.opacity;
  }







  
  public ColorStop setOpacity(Number opacity) {
/* 95 */     this.opacity = opacity;
/* 96 */     return this;
  }
}


/* Location:              C:\Users\hima\Downloads\HyJavaApex-3-EVAL-C.jar!\com\hyjavaapex\model\common\ColorStop.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */