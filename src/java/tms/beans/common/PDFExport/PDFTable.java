package tms.beans.common.PDFExport;

import java.util.ArrayList;
import java.util.List;

public class PDFTable
    {
    private String table_title;
    private List<HeaderCell> header_cells_list = new ArrayList<>();
    private List<String[]> rows_list = new ArrayList<>();
    private float[] table_width;

    public PDFTable(String table_title, List<HeaderCell> header_cells_list, List<String[]> rows_list, float[] default_width, int columns, float total_page_with)
        {
        super();
        this.table_title = table_title;
        this.header_cells_list = header_cells_list;
        this.rows_list = rows_list;
        table_width = new float[columns];
        if(default_width == null)
            for(int index = 0; index < columns; index++)
                table_width[index] = total_page_with / columns;
        else
            {
            float assigned_width = 0;
            for(int index = 0; index < default_width.length; index++)
                {
                assigned_width += default_width[index];
                table_width[index] = default_width[index];
                }
            float new_width = (total_page_with - assigned_width) / (columns - default_width.length);
            for(int index = default_width.length; index < columns; index++)
                table_width[index] = new_width;

            }

        }

    public void setTable_width(float[] table_width)
        {
        this.table_width = table_width;
        }

    public float[] getTable_width()
        {
        return table_width;
        }

    public void setTable_title(String table_title)
        {
        this.table_title = table_title;
        }

    public String getTable_title()
        {
        return table_title;
        }

    public void setHeader_cells_list(List<HeaderCell> header_cells_list)
        {
        this.header_cells_list = header_cells_list;
        }

    public List<HeaderCell> getHeader_cells_list()
        {
        return header_cells_list;
        }

    public void setRows_list(List<String[]> rows_list)
        {
        this.rows_list = rows_list;
        }

    public List<String[]> getRows_list()
        {
        return rows_list;
        }
    }
