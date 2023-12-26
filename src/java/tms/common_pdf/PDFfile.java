package tms.common_pdf;

import com.itextpdf.text.Rectangle;
import java.io.ByteArrayOutputStream;

/**
 *
 * @author hima
 */
public class PDFfile
    {
    private Rectangle pageSize;
    private int marginLeft;
    private int marginRight;
    private int marginTop;
    private int marginBottom;
    private ByteArrayOutputStream baos;

    public PDFfile(Rectangle pageSize, int marginLeft, int marginRight, int marginTop, int marginBottom, ByteArrayOutputStream baos)
        {
        this.pageSize = pageSize;
        this.marginLeft = marginLeft;
        this.marginRight = marginRight;
        this.marginTop = marginTop;
        this.marginBottom = marginBottom;
        this.baos = baos;
        }

    public Rectangle getPageSize()
        {
        return pageSize;
        }

    public void setPageSize(Rectangle pageSize)
        {
        this.pageSize = pageSize;
        }

    public int getMarginLeft()
        {
        return marginLeft;
        }

    public void setMarginLeft(int marginLeft)
        {
        this.marginLeft = marginLeft;
        }

    public int getMarginRight()
        {
        return marginRight;
        }

    public void setMarginRight(int marginRight)
        {
        this.marginRight = marginRight;
        }

    public int getMarginTop()
        {
        return marginTop;
        }

    public void setMarginTop(int marginTop)
        {
        this.marginTop = marginTop;
        }

    public int getMarginBottom()
        {
        return marginBottom;
        }

    public void setMarginBottom(int marginBottom)
        {
        this.marginBottom = marginBottom;
        }

    public ByteArrayOutputStream getBaos()
        {
        return baos;
        }

    public void setBaos(ByteArrayOutputStream baos)
        {
        this.baos = baos;
        }

    }
