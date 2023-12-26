package tms.beans.common.PDFExport;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFHeaderFooter extends PdfPageEventHelper
    {

    PdfTemplate total;
    PDFFonts pdffonts;
    private Phrase header;
    private String print_date;
    private String printed_by;
    private String page_orientation;
    private Image logo_image;

    public PDFHeaderFooter(PDFFonts pdffonts, Phrase header, String print_date, String printed_by, String page_orientation, Image logo_image)
        {
        super();
        this.pdffonts = pdffonts;
        this.header = header;
        this.print_date = print_date;
        this.printed_by = printed_by;
        this.page_orientation = page_orientation;
        this.logo_image = logo_image;
        }

    class TableHeaderBackground implements PdfPTableEvent
        {
        public void tableLayout(PdfPTable table, float[][] width, float[] height, int headerRows, int rowStart, PdfContentByte[] canvas)
            {
            PdfContentByte background = canvas[PdfPTable.BASECANVAS];
            background.saveState();
            background.setLineWidth(0.1f);
            background.setCMYKColorFill(8, 6, 6, 0);
            background.roundRectangle(width[0][0], height[height.length - 1] + 2, 580, height[0] - height[height.length - 1] - 4, 4);
            background.setLineWidth(0.5f);
            background.setColorStroke(BaseColor.DARK_GRAY);
            background.fillStroke();
            background.fill();
            background.restoreState();
            }
        }

    public void onOpenDocument(PdfWriter writer, Document document)
        {
        total = writer.getDirectContent().createTemplate(22, 18);
        }

    public void onEndPage(PdfWriter writer, Document document)
        {
        try
            {
            //UD Logo
            PdfPTable Logo_table = new PdfPTable(1);
            if(page_orientation.equalsIgnoreCase("Portrait"))
                Logo_table.setTotalWidth(592);
            else
                Logo_table.setTotalWidth(842);
            Logo_table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            Logo_table.getDefaultCell().setHorizontalAlignment(Rectangle.ALIGN_CENTER);
            //Logo_table.getDefaultCell().setMinimumHeight(70);
            Logo_table.addCell(logo_image);
            Logo_table.addCell(header);
            if(page_orientation.equalsIgnoreCase("Portrait"))
                Logo_table.writeSelectedRows(0, -1, 1, 815, writer.getDirectContent());//(xyxy)
            else
                Logo_table.writeSelectedRows(0, -1, 1, 570, writer.getDirectContent());//(xyxy)

            PdfPTable table_footer = new PdfPTable(3);
            table_footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);

            //footer
            if(page_orientation.equalsIgnoreCase("Portrait"))
                {
                table_footer.setTotalWidth(590);
                table_footer.setWidths(new int[]
                    {
                    300, 120, 10
                    });
                }
            else
                {
                table_footer.setTotalWidth(842);
                table_footer.setWidths(new int[]
                    {
                    710, 122, 15
                    });
                }
            table_footer.setHorizontalAlignment(Rectangle.ALIGN_LEFT);
            table_footer.setLockedWidth(true);
            table_footer.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table_footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table_footer.addCell(new Phrase("Printed by " + printed_by + " on " + print_date, pdffonts.normal_blue_7));
            table_footer.getDefaultCell().setFixedHeight(20);
            table_footer.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table_footer.addCell(new Phrase(String.format("Page %d of", (writer.getPageNumber())), pdffonts.normal_black_8));
            BaseFont bf = BaseFont.createFont(BaseFont.HELVETICA, BaseFont.WINANSI, BaseFont.NOT_EMBEDDED);
            total.setFontAndSize(bf, 11);
            PdfPCell cell = new PdfPCell(Image.getInstance(total));
            cell.setBorder(Rectangle.NO_BORDER);
            table_footer.addCell(cell);
            table_footer.writeSelectedRows(0, -1, 2, 20, writer.getDirectContent());//(xyxy)
            }
        catch(Exception de)
            {
            throw new ExceptionConverter(de);
            }
        }

    public void onCloseDocument(PdfWriter writer, Document document)
        {
        ColumnText.showTextAligned(total, Element.ALIGN_LEFT, new Phrase(String.valueOf(writer.getPageNumber() - 1)), 2, 2, 0);
        }
    }
