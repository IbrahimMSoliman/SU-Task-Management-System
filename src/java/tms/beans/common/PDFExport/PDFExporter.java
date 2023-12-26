package tms.beans.common.PDFExport;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletResponse;
import tms.application.TMSApplication;

public class PDFExporter
    {
    float[] COLUMNS =
        {
        0, 0, 0, 0
        };

    private FacesContext context;
    private String page_orientation;
    private String page_header1;
    private String page_header2;

    private List<PDFTable> tables_list = new ArrayList<>();
    int columns_no;
    private String small_notes_page_bottom;
    private int center_cell_after;
    private int index_of_total_column;
    private PDFFonts pdffonts = new PDFFonts();
    private String print_date;
    private String printed_by;
    private String file_name;

    public PDFExporter(FacesContext context, String page_orientation, String page_header1, String page_header2, int columns_no, int center_cell_after, int index_of_total_column, String small_notes_page_bottom, String print_date, String printed_by, String file_name)
        {
        super();
        this.context = context;
        this.page_orientation = page_orientation;
        this.page_header1 = page_header1;
        this.page_header2 = page_header2;

        this.small_notes_page_bottom = small_notes_page_bottom;
        this.columns_no = columns_no;
        this.center_cell_after = center_cell_after;
        this.index_of_total_column = index_of_total_column;
        this.print_date = print_date;
        this.printed_by = printed_by;
        this.file_name = file_name;
        }

    public void create_PDF()
        {
        COLUMNS = new float[]
            {
            1/*x1*/, 20, 593/*x2*/, 740/*y2*/ };
        if(page_orientation.equalsIgnoreCase("landscape"))
            COLUMNS = new float[]
                {
                1/*x*/, 20, 841, 480
                };

        //x1 +=more space in left side and -=less space in left side
        //x2 +=less space in righr side and -=more space in left side
        //y2 +=go up and -=go down
        try
            {
            String logo_path = "";//TMSApplication.getPdf_report_header_path();
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + ".pdf\"");
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            Document document;
            if(page_orientation.equalsIgnoreCase("landscape"))
                document = new Document(PageSize.A4.rotate(), 5, 5, 5, 5);
            else
                document = new Document(PageSize.A4, 5, 5, 5, 5);
            PdfWriter writer = PdfWriter.getInstance(document, baos);
            //System.out.println("PPPPP="+PageSize.A4.getTop());
            //Page header and footer
            Phrase page_head_phrase = new Phrase("");
            page_head_phrase.add(new Chunk(page_header1, pdffonts.bold_black_12));
            if(!page_header2.trim().equals(""))
                {
                page_head_phrase.add(Chunk.NEWLINE);
                page_head_phrase.add(new Chunk(page_header2, pdffonts.bold_black_10));
                }
            Image logo_image = Image.getInstance(logo_path);
            writer.setPageEvent(new PDFHeaderFooter(pdffonts, page_head_phrase, print_date, printed_by, page_orientation, logo_image));
            document.open();

            ColumnText PageColumns = new ColumnText(writer.getDirectContent());
            PageColumns.setAlignment(Element.ALIGN_LEFT);
            int status = ColumnText.START_COLUMN;
            PageColumns.setSimpleColumn(COLUMNS[0], COLUMNS[1], COLUMNS[2], COLUMNS[3]);
            float y;

            //loop over tables
            for(PDFTable tt : tables_list)
                {
                y = PageColumns.getYLine();
                PdfPTable DateTable = data_table(tt);
                PageColumns.addElement(DateTable);
                status = PageColumns.go(true);
                if(ColumnText.hasMoreText(status))
                    {
                    document.newPage();
                    y = COLUMNS[3];
                    }
                PageColumns.setYLine(y);
                PageColumns.setText(null);
                PageColumns.addElement(DateTable);
                status = PageColumns.go(false);
                }

            //add notes table
            if(!small_notes_page_bottom.trim().equals(""))
                {
                y = PageColumns.getYLine();
                PdfPTable NotesTable = new PdfPTable(1);
                NotesTable.setWidthPercentage(100);
                NotesTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
                NotesTable.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                NotesTable.addCell(new Phrase(small_notes_page_bottom, pdffonts.normal_black_7));
                PageColumns.addElement(NotesTable);
                status = PageColumns.go(true);
                if(ColumnText.hasMoreText(status))
                    {
                    document.newPage();
                    y = COLUMNS[3];
                    }
                PageColumns.setYLine(y);
                PageColumns.setText(null);
                PageColumns.addElement(NotesTable);
                status = PageColumns.go(false);
                }
            document.close();
            writer.close();
            response.getOutputStream().write(baos.toByteArray());
            response.getOutputStream().close();
            context.responseComplete();
            }
        catch(Exception ee)
            {
            System.out.println("Error " + this.getClass().toString() + ": " + ee.toString());
            }
        }

    private PdfPTable data_table(PDFTable pdfdata)
        {
        PdfPTable inner_table = new PdfPTable(columns_no);
        boolean is_total_row = false;
        try
            {
            // int index=0;
            inner_table.setWidths(pdfdata.getTable_width());
            inner_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            inner_table.getDefaultCell().setColspan(columns_no);
            if(!pdfdata.getTable_title().trim().equals(""))
                inner_table.addCell(new Phrase("\r" + pdfdata.getTable_title(), pdffonts.bold_black_11));

            inner_table.getDefaultCell().setColspan(1);
            inner_table.setWidthPercentage(100);
            inner_table.setSpacingAfter(1f);
            inner_table.setSpacingBefore(0);
            inner_table.getDefaultCell().setBorder(Rectangle.BOX);

            //table titles
            inner_table.getDefaultCell().setVerticalAlignment(PdfPCell.ALIGN_MIDDLE);
            inner_table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
            inner_table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);

            for(HeaderCell hc : pdfdata.getHeader_cells_list())
                {
                inner_table.getDefaultCell().setColspan(hc.getCell_colspan());
                inner_table.getDefaultCell().setRowspan(hc.getCell_rowspan());
                inner_table.addCell(new Phrase(hc.getCell_text(), pdffonts.bold_black_8));
                }
            //Table Data
            inner_table.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
            inner_table.getDefaultCell().setColspan(1);
            inner_table.getDefaultCell().setRowspan(1);
            inner_table.getDefaultCell().setNoWrap(false);
            for(String[] row_array : pdfdata.getRows_list())
                {
                is_total_row = false;
                if(row_array[0].equalsIgnoreCase("Total") || row_array[0].equalsIgnoreCase("Summary"))
                    is_total_row = true;
                for(int index = 0; index < row_array.length; index++)
                    if(index >= center_cell_after)
                        {
                        inner_table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_CENTER);
                        if(index == index_of_total_column)//last column for total
                            {
                            inner_table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
                            inner_table.addCell(new Phrase(row_array[index], pdffonts.bold_black_8));

                            }
                        else
                            {
                            inner_table.getDefaultCell().setBackgroundColor(is_total_row ? BaseColor.LIGHT_GRAY : row_array[index].indexOf("%") > 0 ? new BaseColor(207, 235, 250) : BaseColor.WHITE);
                            if(row_array[index].equals("0") || row_array[index].equals("0%"))
                                inner_table.addCell(new Phrase(row_array[index], pdffonts.normal_red_8));
                            else
                                inner_table.addCell(new Phrase(row_array[index], pdffonts.normal_blue_8));
                            }
                        }
                    else //first cells
                        {
                        inner_table.getDefaultCell().setHorizontalAlignment(PdfPCell.ALIGN_LEFT);
                        inner_table.getDefaultCell().setBackgroundColor(BaseColor.LIGHT_GRAY);
                        inner_table.addCell(new Phrase(row_array[index], pdffonts.bold_black_8));
                        }
                }
            inner_table.setComplete(true);

            }
        catch(Exception exp)
            {
            System.out.println("Error (" + this.getClass().toString() + ") :" + exp.toString());
            }
        return inner_table;
        }

    public void setSmall_notes_page_bottom(String small_notes_page_bottom)
        {
        this.small_notes_page_bottom = small_notes_page_bottom;
        }

    public String getSmall_notes_page_bottom()
        {
        return small_notes_page_bottom;
        }

    public void setCOLUMNS(float[] COLUMNS)
        {
        this.COLUMNS = COLUMNS;
        }

    public float[] getCOLUMNS()
        {
        return COLUMNS;
        }

    public void setPage_orientation(String page_orientation)
        {
        this.page_orientation = page_orientation;
        }

    public String getPage_orientation()
        {
        return page_orientation;
        }

    public void setContext(FacesContext context)
        {
        this.context = context;
        }

    public FacesContext getContext()
        {
        return context;
        }

    public void setPage_header1(String page_header1)
        {
        this.page_header1 = page_header1;
        }

    public String getPage_header1()
        {
        return page_header1;
        }

    public void setPage_header2(String page_header2)
        {
        this.page_header2 = page_header2;
        }

    public String getPage_header2()
        {
        return page_header2;
        }

    public void setTables_list(List<PDFTable> tables_list)
        {
        this.tables_list = tables_list;
        }

    public List<PDFTable> getTables_list()
        {
        return tables_list;
        }

    public void setColumns_no(int columns_no)
        {
        this.columns_no = columns_no;
        }

    public int getColumns_no()
        {
        return columns_no;
        }
    }
