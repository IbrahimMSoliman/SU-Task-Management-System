package tms.beans.common;

import java.util.List;

public class ExcelPdfRows
    {
    public String title;
    public List<String[]> list;

    public ExcelPdfRows(String title, List<String[]> list)
        {
        super();
        this.title = title;
        this.list = list;
        }
    }
