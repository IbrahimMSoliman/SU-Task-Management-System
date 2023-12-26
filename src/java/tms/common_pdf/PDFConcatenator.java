package tms.common_pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfImportedPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 *
 * @author hima
 */
public class PDFConcatenator
    {
    public ByteArrayOutputStream concat(List<PDFfile> files_list)
        {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try
            {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, out);
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            for(PDFfile file : files_list)
                {
                PdfReader reader = new PdfReader(file.getBaos().toByteArray());
                document.setPageSize(file.getPageSize());
                document.setMargins(file.getMarginLeft(), file.getMarginRight(), file.getMarginTop(), file.getMarginBottom());
                for(int ii = 1; ii <= reader.getNumberOfPages(); ii++)
                    {
                    float pageHeight = reader.getPageSizeWithRotation(ii).getHeight();
                    document.newPage();
                    PdfImportedPage page = writer.getImportedPage(reader, ii);
                    if(file.getPageSize().getWidth() == PageSize.A4.rotate().getWidth())//Landscap
                        cb.addTemplate(page, 0, -1f, 1f, 0, 0, pageHeight);
                    else
                        cb.addTemplate(page, 0, 0);//Portrait
                    }
                reader.close();
                }
            document.close();
            writer.close();
            }
        catch(Exception exp)
            {
            System.out.println("Inner Document Error: " + exp.toString());
            }
        return out;
        }

    }
