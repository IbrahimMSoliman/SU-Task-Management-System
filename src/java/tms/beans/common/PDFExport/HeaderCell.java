package tms.beans.common.PDFExport;

public class HeaderCell
    {
    private String cell_text;
    private int cell_colspan;
    private int cell_rowspan;

    public HeaderCell(String cell_text, int cell_colspan, int cell_rowspan)
        {
        super();
        this.cell_text = cell_text;
        this.cell_colspan = cell_colspan;
        this.cell_rowspan = cell_rowspan;
        }

    public void setCell_text(String cell_text)
        {
        this.cell_text = cell_text;
        }

    public String getCell_text()
        {
        return cell_text;
        }

    public void setCell_colspan(int cell_colspan)
        {
        this.cell_colspan = cell_colspan;
        }

    public int getCell_colspan()
        {
        return cell_colspan;
        }

    public void setCell_rowspan(int cell_rowspan)
        {
        this.cell_rowspan = cell_rowspan;
        }

    public int getCell_rowspan()
        {
        return cell_rowspan;
        }
    }
