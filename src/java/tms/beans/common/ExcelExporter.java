package tms.beans.common;

import tms.db.connect;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import net.htmlparser.jericho.Source;
import net.htmlparser.jericho.TextExtractor;
import org.apache.poi.util.IOUtils;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.WorkbookUtil;

public class ExcelExporter
    {
    FacesContext context;
    int Columns_No = 0;
    int RowsIndex = 0;

    public ExcelExporter(FacesContext context)
        {
        this.context = context;
        }

    public void export_table(List<ExcelPdfRows> excelpdfRow, String file_name, String sheet_title, String[] TableHeader, String MainTitle)
        {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName(sheet_title));
        if(!MainTitle.trim().equals(""))
            RowsIndex = 1;

        //cell styles
        CellStyle cellstyle = workbook.createCellStyle();
        cellstyle.setBorderBottom(BorderStyle.THIN);
        cellstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellstyle.setBorderTop(BorderStyle.THIN);
        cellstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellstyle.setBorderLeft(BorderStyle.THIN);
        cellstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellstyle.setBorderRight(BorderStyle.THIN);
        cellstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellstyle.setWrapText(true);
        cellstyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //sub header style
        CellStyle sub_header_style = workbook.createCellStyle();
        sub_header_style.setBorderBottom(BorderStyle.THIN);
        sub_header_style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        sub_header_style.setBorderTop(BorderStyle.THIN);
        sub_header_style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        sub_header_style.setBorderLeft(BorderStyle.THIN);
        sub_header_style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        sub_header_style.setBorderRight(BorderStyle.THIN);
        sub_header_style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        sub_header_style.setFillForegroundColor(IndexedColors.GOLD.getIndex());
        sub_header_style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        ///Sub Header font
        Font sub_headerFont = workbook.createFont();
        sub_headerFont.setBold(true);
        sub_headerFont.setFontHeightInPoints((short) 9);
        sub_header_style.setFont(sub_headerFont);

        //main title styles
        CellStyle TitleStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        TitleStyle.setAlignment(HorizontalAlignment.CENTER);
        TitleStyle.setFont(headerFont);

        //create sheet header
        create_Excel_sheet_header(sheet, TableHeader);

        //strat putting data to cells
        for(ExcelPdfRows ERows : excelpdfRow)
            {
            //first check if title exist for subtables
            if(ERows.title != null && !ERows.title.trim().equals(""))
                {
                Row row = sheet.createRow(RowsIndex);
                Cell cell = row.createCell(0);
                cell.setCellValue(ERows.title);
                row.getCell(0).setCellStyle(sub_header_style);
                sheet.addMergedRegion(new CellRangeAddress(RowsIndex, RowsIndex, 0, Columns_No - 1));
                RowsIndex++;
                }
            //Start getting normal data/rows
            for(String[] data : ERows.list)
                {
                Row row = sheet.createRow(RowsIndex);
                int index = 0;
                for(String cell : data)
                    {
                    if(cell == null)
                        cell = "";

                    if(cell.toUpperCase().contains("<BR/>"))
                        row.createCell(index).setCellValue(cell.replaceAll("<br/>", "\n"));
                    else
                        {
                        Source source = new Source(cell);
                        TextExtractor textExtractor = source.getTextExtractor();
                        row.createCell(index).setCellValue(textExtractor.toString());
                        }
                    row.getCell(index).setCellStyle(cellstyle);
                    //sheet.autoSizeColumn(0);
                    index++;
                    if(index == Columns_No)
                        break;
                    }
                RowsIndex++;
                }
            }

        //Auto Columns Size
        for(int i = 0; i < Columns_No; i++)
            sheet.autoSizeColumn(i);

        //Sheet Title
        if(!MainTitle.trim().equals(""))
            {
            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue(MainTitle);
            row.getCell(0).setCellStyle(TitleStyle);
            sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, Columns_No - 1));
            sheet.autoSizeColumn(0);
            }

        try
            {
            ///now upload workbook to the context to be downloaded
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + "\"");
            ServletOutputStream ofile = response.getOutputStream();
            workbook.write(ofile);
            context.responseComplete();
            }
        catch(Exception ioexp)
            {
            }

        }

    public void export_table_merge(List<ExcelPdfRows> excelpdfRow, String file_name, String sheet_title, String[] TableHeader, String MainTitle, boolean ShowLogo, String logo_path, boolean autoSize, int merge_after, String[] merge_data)
        {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet(WorkbookUtil.createSafeSheetName(sheet_title));
        ShowLogo = true;
        if(ShowLogo)
            {
            RowsIndex = 4;
            if(!MainTitle.trim().equals(""))
                RowsIndex++;
            }

        else if(!MainTitle.trim().equals(""))
            RowsIndex = 1;

        //cell styles
        CellStyle cellstyle = workbook.createCellStyle();
        cellstyle.setBorderBottom(BorderStyle.THIN);
        cellstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        cellstyle.setBorderTop(BorderStyle.THIN);
        cellstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        cellstyle.setBorderLeft(BorderStyle.THIN);
        cellstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        cellstyle.setBorderRight(BorderStyle.THIN);
        cellstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
        cellstyle.setWrapText(true);
        cellstyle.setVerticalAlignment(VerticalAlignment.CENTER);

        //sub header style
        CellStyle sub_header_style = workbook.createCellStyle();
        sub_header_style.setBorderBottom(BorderStyle.THIN);
        sub_header_style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        sub_header_style.setBorderTop(BorderStyle.THIN);
        sub_header_style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        sub_header_style.setBorderLeft(BorderStyle.THIN);
        sub_header_style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        sub_header_style.setBorderRight(BorderStyle.THIN);
        sub_header_style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        sub_header_style.setFillForegroundColor(IndexedColors.GOLD.getIndex());
        sub_header_style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        ///Sub Header font
        Font sub_headerFont = workbook.createFont();
        sub_headerFont.setBold(true);
        sub_headerFont.setFontHeightInPoints((short) 9);
        sub_header_style.setFont(sub_headerFont);

        //main title styles
        CellStyle TitleStyle = workbook.createCellStyle();
        Font headerFont = workbook.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);
        TitleStyle.setAlignment(HorizontalAlignment.CENTER);
        TitleStyle.setFont(headerFont);

        //create sheet header
        create_Excel_sheet_header(sheet, TableHeader, merge_after, merge_data);

        //strat putting data to cells
        for(ExcelPdfRows ERows : excelpdfRow)
            {
            //first check if title exist for subtables
            if(ERows.title != null && !ERows.title.trim().equals(""))
                {
                Row row = sheet.createRow(RowsIndex);
                Cell cell = row.createCell(0);
                cell.setCellValue(ERows.title);
                row.getCell(0).setCellStyle(sub_header_style);
                sheet.addMergedRegion(new CellRangeAddress(RowsIndex, RowsIndex, 0, Columns_No - 1));
                RowsIndex++;
                }
            //Start getting normal data/rows
            for(String[] data : ERows.list)
                {
                Row row = sheet.createRow(RowsIndex);
                int index = 0;
                for(String cell : data)
                    {
                    if(cell == null)
                        cell = "";
                    if(cell.toUpperCase().contains("<BR/>"))
                        row.createCell(index).setCellValue(cell.replaceAll("<br/>", "\n"));
                    else
                        {
                        Source source = new Source(cell);
                        TextExtractor textExtractor = source.getTextExtractor();
                        row.createCell(index).setCellValue(textExtractor.toString());
                        }
                    row.getCell(index).setCellStyle(cellstyle);
                    //sheet.autoSizeColumn(0);
                    index++;
                    if(index == Columns_No)
                        break;
                    }
                RowsIndex++;
                }
            }

        //Auto Columns Size
        if(autoSize)
            for(int i = 0; i < Columns_No; i++)
                sheet.autoSizeColumn(i);

        //logo
        if(ShowLogo)
            try
            {
            InputStream is = new FileInputStream(logo_path);
            byte[] bytes = IOUtils.toByteArray(is);
            int pictureIdx = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_JPEG);
            is.close();
            CreationHelper helper = workbook.getCreationHelper();
            Drawing drawing = sheet.createDrawingPatriarch();
            ClientAnchor anchor = helper.createClientAnchor();
            //set top-left corner of the picture,
            //subsequent call of Picture#resize() will operate relative to it
            anchor.setCol1(0);
            anchor.setRow1(0);
            Picture pict = drawing.createPicture(anchor, pictureIdx);
            //pict.getPreferredSize();
            pict.resize();
            RowsIndex = 4;
            sheet.addMergedRegion(new CellRangeAddress(0, 3, 0, Columns_No - 1));
            }
        catch(Exception e)
            {
            }
        //Sheet Title
        if(!MainTitle.trim().equals(""))
            {
            Row row = sheet.createRow(RowsIndex);
            Cell cell = row.createCell(0);
            cell.setCellValue(MainTitle);
            row.getCell(0).setCellStyle(TitleStyle);
            sheet.addMergedRegion(new CellRangeAddress(RowsIndex, RowsIndex, 0, Columns_No - 1));
            sheet.autoSizeColumn(0);
            }

        try
            {
            ///now upload workbook to the context to be downloaded
            HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
            response.reset();
            response.setContentType("application/vnd.ms-excel");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name + "\"");
            ServletOutputStream ofile = response.getOutputStream();
            workbook.write(ofile);
            context.responseComplete();
            }
        catch(Exception ioexp)
            {
            }

        }

    private void create_Excel_sheet_header(Sheet sheet, String[] TableHeader, int merge_after, String[] merge_data)
        {
        //   sheet.setColumnWidth(200, 200);
        if(TableHeader != null)
            {
            CellStyle cellstyle = sheet.getWorkbook().createCellStyle();
            cellstyle.setBorderBottom(BorderStyle.THIN);
            cellstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            cellstyle.setBorderTop(BorderStyle.THIN);
            cellstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
            cellstyle.setBorderLeft(BorderStyle.THIN);
            cellstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            cellstyle.setBorderRight(BorderStyle.THIN);
            cellstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
            cellstyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellstyle.setAlignment(HorizontalAlignment.CENTER);

            Font headerFont = sheet.getWorkbook().createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 10);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            cellstyle.setFont(headerFont);

            cellstyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            if(TableHeader.length > 0)
                {
                Row row = sheet.createRow(RowsIndex);
                row.setHeight((short) 500);
                Columns_No = 0;
                int col_index = 0;

                for(int index = 0; index < TableHeader.length; index++)
                    if(TableHeader[index] != null)
                        {
                        Cell cell = row.createCell(col_index);
                        if(index >= merge_after)
                            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum(), col_index, col_index + merge_data.length - 1));
                        else
                            sheet.addMergedRegion(new CellRangeAddress(row.getRowNum(), row.getRowNum() + 1, col_index, col_index));
                        cell.setCellValue(TableHeader[index]);
                        //cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(cellstyle);
                        if(index >= merge_after)
                            {
                            col_index = col_index + merge_data.length;
                            Columns_No += merge_data.length;
                            }
                        else
                            {
                            col_index++;
                            Columns_No++;
                            }
                        }
                RowsIndex++;
                //create another row for merged head
                Row row2 = sheet.createRow(RowsIndex);
                row2.setHeight((short) 500);
                //                      Columns_No=0;
                col_index = merge_after;
                for(int index = 0; index < TableHeader.length; index++)
                    if(TableHeader[index] != null)
                        if(index >= merge_after)
                            for(String celltext : merge_data)
                                {
                                Cell cell = row2.createCell(col_index);
                                cell.setCellValue(celltext);
                                //cell.setCellType(Cell.CELL_TYPE_STRING);
                                cell.setCellStyle(cellstyle);
                                col_index++;
                                //Columns_No++;
                                }
                RowsIndex++;
                }
            }
        }

    private void create_Excel_sheet_header(Sheet sheet, String[] TableHeader)
        {
        if(TableHeader != null)
            {
            CellStyle cellstyle = sheet.getWorkbook().createCellStyle();
            cellstyle.setBorderBottom(BorderStyle.THIN);
            cellstyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
            cellstyle.setBorderTop(BorderStyle.THIN);
            cellstyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
            cellstyle.setBorderLeft(BorderStyle.THIN);
            cellstyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
            cellstyle.setBorderRight(BorderStyle.THIN);
            cellstyle.setRightBorderColor(IndexedColors.BLACK.getIndex());
            cellstyle.setVerticalAlignment(VerticalAlignment.CENTER);
            cellstyle.setAlignment(HorizontalAlignment.CENTER);

            Font headerFont = sheet.getWorkbook().createFont();
            headerFont.setBold(true);
            headerFont.setFontHeightInPoints((short) 10);
            headerFont.setColor(IndexedColors.WHITE.getIndex());
            cellstyle.setFont(headerFont);

            cellstyle.setFillForegroundColor(IndexedColors.LIGHT_BLUE.getIndex());
            cellstyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            if(TableHeader.length > 0)
                {
                Row row = sheet.createRow(RowsIndex);
                row.setHeight((short) 500);
                Columns_No = 0;

                for(int index = 0; index < TableHeader.length; index++)
                    if(TableHeader[index] != null)
                        {
                        Cell cell = row.createCell(index);
                        cell.setCellValue(TableHeader[index]);
                        //cell.setCellType(Cell.CELL_TYPE_STRING);
                        cell.setCellStyle(cellstyle);

                        Columns_No++;
                        }
                RowsIndex++;
                }
            }
        }

    }
