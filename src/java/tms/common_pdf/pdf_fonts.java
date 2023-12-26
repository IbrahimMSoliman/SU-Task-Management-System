package tms.common_pdf;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.CMYKColor;

import com.itextpdf.text.pdf.GrayColor;

import java.io.File;
import java.io.IOException;

import java.net.URLDecoder;

public class pdf_fonts 
     {
        Font header_bold_font;
        Font header_normal_font;
        Font semester_title_font;
        Font table_header_font;
        Font course_name_font;
        Font course_code_font;
        Font sem_ech_gpa_font;
        Font other_red_font;
        Font disciplinary_font;
        Font page_numbering_font;
        Font bad_grade_font;
        Font incomplete_grade_font;
        Font green_reg_status_font;
        Font red_reg_status_font;
        Font stamp_font;
        Font none_officail_font;
        
        
        //AR
        Font ar_header_bold_font;
        Font ar_header_normal_font;
        Font ar_semester_title_font;
        Font ar_table_header_font;
        Font ar_course_name_font;
        Font ar_course_code_font;
        Font ar_sem_ech_gpa_font;
        Font ar_other_red_font;
        Font ar_disciplinary_font;
        Font ar_page_numbering_font;
        Font ar_bad_grade_font;
        Font ar_incomplete_grade_font;
        Font ar_green_reg_status_font;
        Font ar_red_reg_status_font;
        Font ar_stamp_font;
        Font ar_none_officail_font;
        
        
        //Curriculum Fonts
        
        Font univeristy_header_font;
        Font college_header_font;
        Font degree_font;
        Font prereq_font;
        Font coreq_font;
        Font group_title_font;
        Font print_font;
        Font group_equivalent_font;
        
        
        Font ar_univeristy_header_font;
        Font ar_college_header_font;
        Font ar_degree_font;
        Font ar_prereq_font;
        Font ar_coreq_font;
        Font ar_group_title_font;
        Font ar_print_font;
        Font ar_group_equivalent_font;
        
        //white 
        Font en_white_bold_5;
        Font en_white_normal_5;
        Font en_white_bold_6;
        Font en_white_normal_6;
        Font en_white_bold_7;
        Font en_white_normal_7;
        Font en_white_bold_8;
        Font en_white_normal_8;
        Font en_white_bold_9;
        Font en_white_normal_9;
        Font en_white_bold_10;
        Font en_white_normal_10;
        Font en_white_bold_11;
        Font en_white_normal_11;
        Font en_white_bold_12;
        Font en_white_normal_12;
        Font en_white_bold_13;
        Font en_white_normal_13;
        Font en_white_bold_14;
        Font en_white_normal_14;
        //Black 
        Font en_black_bold_5;
        Font en_black_normal_5;
        Font en_black_bold_6;
        Font en_black_normal_6;
        Font en_black_bold_7;
        Font en_black_normal_7;
        Font en_black_bold_8;
        Font en_black_normal_8;
        Font en_black_bold_9;
        Font en_black_normal_9;
        Font en_black_bold_10;
        Font en_black_normal_10;
        Font en_black_bold_11;
        Font en_black_normal_11;
        Font en_black_bold_12;
        Font en_black_normal_12;
        Font en_black_bold_13;
        Font en_black_normal_13;
        Font en_black_bold_14;
        Font en_black_normal_14;
        //Red 
        Font en_red_bold_5;
        Font en_red_normal_5;
        Font en_red_bold_6;
        Font en_red_normal_6;
        Font en_red_bold_7;
        Font en_red_normal_7;
        Font en_red_bold_8;
        Font en_red_normal_8;
        Font en_red_bold_9;
        Font en_red_normal_9;
        Font en_red_bold_10;
        Font en_red_normal_10;
        Font en_red_bold_11;
        Font en_red_normal_11;
        Font en_red_bold_12;
        Font en_red_normal_12;
        Font en_red_bold_13;
        Font en_red_normal_13;
        Font en_red_bold_14;
        Font en_red_normal_14;
        //blue 
        Font en_blue_bold_5;
        Font en_blue_normal_5;
        Font en_blue_bold_6;
        Font en_blue_normal_6;
        Font en_blue_bold_7;
        Font en_blue_normal_7;
        Font en_blue_bold_8;
        Font en_blue_normal_8;
        Font en_blue_bold_9;
        Font en_blue_normal_9;
        Font en_blue_bold_10;
        Font en_blue_normal_10;
        Font en_blue_bold_11;
        Font en_blue_normal_11;
        Font en_blue_bold_12;
        Font en_blue_normal_12;
        Font en_blue_bold_13;
        Font en_blue_normal_13;
        Font en_blue_bold_14;
        Font en_blue_normal_14;
        
        //green 
        Font en_green_bold_5;
        Font en_green_normal_5;
        Font en_green_bold_6;
        Font en_green_normal_6;
        Font en_green_bold_7;
        Font en_green_normal_7;
        Font en_green_bold_8;
        Font en_green_normal_8;
        Font en_green_bold_9;
        Font en_green_normal_9;
        Font en_green_bold_10;
        Font en_green_normal_10;
        Font en_green_bold_11;
        Font en_green_normal_11;
        Font en_green_bold_12;
        Font en_green_normal_12;
        Font en_green_bold_13;
        Font en_green_normal_13;
        Font en_green_bold_14;
        Font en_green_normal_14;
    
        //Arabic
        
        //white 
        Font ar_white_bold_5;
        Font ar_white_normal_5;
        Font ar_white_bold_6;
        Font ar_white_normal_6;
        Font ar_white_bold_7;
        Font ar_white_normal_7;
        Font ar_white_bold_8;
        Font ar_white_normal_8;
        Font ar_white_bold_9;
        Font ar_white_normal_9;
        Font ar_white_bold_10;
        Font ar_white_normal_10;
        Font ar_white_bold_11;
        Font ar_white_normal_11;
        Font ar_white_bold_12;
        Font ar_white_normal_12;
        Font ar_white_bold_13;
        Font ar_white_normal_13;
        Font ar_white_bold_14;
        Font ar_white_normal_14;
        //Black 
        Font ar_black_bold_5;
        Font ar_black_normal_5;
        Font ar_black_bold_6;
        Font ar_black_normal_6;
        Font ar_black_bold_7;
        Font ar_black_normal_7;
        Font ar_black_bold_8;
        Font ar_black_normal_8;
        Font ar_black_bold_9;
        Font ar_black_normal_9;
        Font ar_black_bold_10;
        Font ar_black_normal_10;
        Font ar_black_bold_11;
        Font ar_black_normal_11;
        Font ar_black_bold_12;
        Font ar_black_normal_12;
        Font ar_black_bold_13;
        Font ar_black_normal_13;
        Font ar_black_bold_14;
        Font ar_black_normal_14;
        //Red 
        Font ar_red_bold_5;
        Font ar_red_normal_5;
        Font ar_red_bold_6;
        Font ar_red_normal_6;
        Font ar_red_bold_7;
        Font ar_red_normal_7;
        Font ar_red_bold_8;
        Font ar_red_normal_8;
        Font ar_red_bold_9;
        Font ar_red_normal_9;
        Font ar_red_bold_10;
        Font ar_red_normal_10;
        Font ar_red_bold_11;
        Font ar_red_normal_11;
        Font ar_red_bold_12;
        Font ar_red_normal_12;
        Font ar_red_bold_13;
        Font ar_red_normal_13;
        Font ar_red_bold_14;
        Font ar_red_normal_14;
        //blue 
        Font ar_blue_bold_5;
        Font ar_blue_normal_5;
        Font ar_blue_bold_6;
        Font ar_blue_normal_6;
        Font ar_blue_bold_7;
        Font ar_blue_normal_7;
        Font ar_blue_bold_8;
        Font ar_blue_normal_8;
        Font ar_blue_bold_9;
        Font ar_blue_normal_9;
        Font ar_blue_bold_10;
        Font ar_blue_normal_10;
        Font ar_blue_bold_11;
        Font ar_blue_normal_11;
        Font ar_blue_bold_12;
        Font ar_blue_normal_12;
        Font ar_blue_bold_13;
        Font ar_blue_normal_13;
        Font ar_blue_bold_14;
        Font ar_blue_normal_14;
        
        
        //green 
        Font ar_green_bold_5;
        Font ar_green_normal_5;
        Font ar_green_bold_6;
        Font ar_green_normal_6;
        Font ar_green_bold_7;
        Font ar_green_normal_7;
        Font ar_green_bold_8;
        Font ar_green_normal_8;
        Font ar_green_bold_9;
        Font ar_green_normal_9;
        Font ar_green_bold_10;
        Font ar_green_normal_10;
        Font ar_green_bold_11;
        Font ar_green_normal_11;
        Font ar_green_bold_12;
        Font ar_green_normal_12;
        Font ar_green_bold_13;
        Font ar_green_normal_13;
        Font ar_green_bold_14;
        Font ar_green_normal_14;
        
        Font watermark_font ;
        
    public pdf_fonts() 
            {
            try {
                File myClass = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getFile());
                String font_file_path=myClass.getAbsolutePath().substring(0,myClass.getAbsolutePath().indexOf("WEB-INF") )+"WEB-INF"+File.separator+"fonts"+File.separator+"english"+File.separator+"arial.ttf";
                font_file_path=font_file_path.replaceAll("%20", " ");
                font_file_path = URLDecoder.decode(font_file_path, "UTF-8");
                
                
                
                /*(1)*/
                header_bold_font=new Font(Font.getFamily("ARIAL"),9);
                header_bold_font.setStyle("bold");
                header_bold_font.setColor(0,0,153);/*#000099*/
                       
                /*(2)*/
                header_normal_font=new Font(Font.getFamily("ARIAL"),9);
                header_bold_font.setColor(BaseColor.BLACK);
                
                /*(3)*/
                semester_title_font=new Font(Font.getFamily("ARIAL"),9);
                semester_title_font.setStyle("bold");
                semester_title_font.setColor(BaseColor.BLACK);
                
                /*(4)*/
                table_header_font=new Font(Font.getFamily("ARIAL"),8);
                table_header_font.setStyle("bold");
                table_header_font.setColor(BaseColor.BLUE);
                
                /*(5)*/
                course_name_font=new Font(Font.getFamily("ARIAL"),7);
                course_name_font.setColor(BaseColor.BLACK);
                
                course_code_font=new Font(Font.getFamily("ARIAL"),7);
                course_code_font.setStyle("bold");
                course_code_font.setColor(BaseColor.BLACK);
              
                /*(6)*/
                sem_ech_gpa_font=new Font(Font.getFamily("ARIAL"),6);
                sem_ech_gpa_font.setStyle("bold");
                sem_ech_gpa_font.setColor(BaseColor.BLACK);
                
                green_reg_status_font=new Font(Font.getFamily("ARIAL"),6);
                green_reg_status_font.setStyle("bold");
                green_reg_status_font.setColor(BaseColor.BLACK);
                
                red_reg_status_font=new Font(Font.getFamily("ARIAL"),6);
                red_reg_status_font.setStyle("bold");
                red_reg_status_font.setColor(BaseColor.RED);
                
                
                /*(7)*/
                other_red_font=new Font(Font.getFamily("ARIAL"),8);
                other_red_font.setStyle("bold");
                other_red_font.setColor(BaseColor.RED);
                
                /*(8)*/
                disciplinary_font=new Font(Font.getFamily("ARIAL"),8);
                disciplinary_font.setStyle("bold");
                disciplinary_font.setColor(BaseColor.BLACK);
                
                /*(9)*/
                page_numbering_font=new Font(Font.getFamily("ARIAL"),8);
                page_numbering_font.setColor(BaseColor.BLACK);
            
            
                /*(10)*/
                bad_grade_font=new Font(Font.getFamily("ARIAL"),8);
                bad_grade_font.setColor(BaseColor.RED);
                  
                /*(11)*/
                incomplete_grade_font=new Font(Font.getFamily("ARIAL"),8);
                incomplete_grade_font.setColor(BaseColor.BLUE); 
              
                /*(12)*/
                stamp_font=new Font(Font.getFamily("Times"),35);
                stamp_font.setStyle("bold");
                stamp_font.setColor(new CMYKColor(2, 8, 9, 0));
                
                none_officail_font = new Font(FontFamily.HELVETICA, 52, Font.BOLD, new GrayColor(0.75f));
                
              //curriculum Fonts
              /*(1)*/
              univeristy_header_font=new Font(Font.getFamily("ARIAL"),12);
              univeristy_header_font.setStyle("bold");
              univeristy_header_font.setColor(BaseColor.BLACK);/*#000099*/
                     
              /*(2)*/
              college_header_font=new Font(Font.getFamily("ARIAL"),11);
              college_header_font.setColor(BaseColor.BLACK);
              college_header_font.setStyle("bold");
              /*(3)*/
              degree_font=new Font(Font.getFamily("ARIAL"),10);
              degree_font.setStyle("bold");
              degree_font.setColor(BaseColor.BLACK);  
            
              /*(4)*/
              prereq_font=new Font(Font.getFamily("ARIAL"),6);
              prereq_font.setColor(BaseColor.BLUE);
              
              /*(5)*/
              coreq_font=new Font(Font.getFamily("ARIAL"),6);
              coreq_font.setColor(BaseColor.RED);
             
              /*(6)*/    
              group_title_font=new Font(Font.getFamily("ARIAL"),8);
              group_title_font.setStyle("bold");
              group_title_font.setColor(BaseColor.WHITE);
              
              /*(7)*/
              group_equivalent_font=new Font(Font.getFamily("ARIAL"),8);
              group_equivalent_font.setStyle("bold");
              group_equivalent_font.setColor(BaseColor.BLUE);
            
              /*(8)*/
              print_font=new Font(Font.getFamily("ARIAL"),5);
              print_font.setColor(BaseColor.BLUE);
                
                
              //Ar Fonts  
              BaseFont arabic_font = BaseFont.createFont(font_file_path, BaseFont.IDENTITY_H, true);
              
              /*(1)*/
              ar_header_bold_font=new Font(arabic_font,9);
              ar_header_bold_font.setStyle("bold");
              ar_header_bold_font.setColor(0,0,153);/*#000099*/
                     
              /*(2)*/
              ar_header_normal_font=new Font(arabic_font,9);
              ar_header_bold_font.setColor(BaseColor.BLACK);
              
              /*(3)*/
              ar_semester_title_font=new Font(arabic_font,9);
              ar_semester_title_font.setStyle("bold");
              ar_semester_title_font.setColor(BaseColor.BLACK);
              
              /*(4)*/
              ar_table_header_font=new Font(arabic_font,8);
              ar_table_header_font.setStyle("bold");
              ar_table_header_font.setColor(BaseColor.BLUE);
              
              /*(5)*/
              ar_course_name_font=new Font(arabic_font,7);
              ar_course_name_font.setColor(BaseColor.BLACK);
              
              ar_course_code_font=new Font(arabic_font,7);
              ar_course_code_font.setStyle("bold");
              ar_course_code_font.setColor(BaseColor.BLACK);
              
              /*(6)*/
              ar_sem_ech_gpa_font=new Font(arabic_font,7);
              //ar_sem_ech_gpa_font.setStyle("bold");
              ar_sem_ech_gpa_font.setColor(BaseColor.BLACK);
              
              ar_green_reg_status_font=new Font(arabic_font,7);
              ar_green_reg_status_font.setStyle("bold");
              ar_green_reg_status_font.setColor(BaseColor.BLACK);
              
              ar_red_reg_status_font=new Font(arabic_font,7);
              ar_red_reg_status_font.setStyle("bold");
              ar_red_reg_status_font.setColor(BaseColor.RED);
              
              
              /*(7)*/
              ar_other_red_font=new Font(arabic_font,8);
              ar_other_red_font.setStyle("bold");
              ar_other_red_font.setColor(BaseColor.RED);
              
              /*(8)*/
              ar_disciplinary_font=new Font(arabic_font,8);
              ar_disciplinary_font.setStyle("bold");
              ar_disciplinary_font.setColor(BaseColor.BLACK);
              
              /*(9)*/
              ar_page_numbering_font=new Font(arabic_font,8);
              ar_page_numbering_font.setColor(BaseColor.BLACK);
              
              
              /*(10)*/
              ar_bad_grade_font=new Font(arabic_font,8);
              ar_bad_grade_font.setColor(BaseColor.RED);
                
              /*(11)*/
              ar_incomplete_grade_font=new Font(arabic_font,8);
              ar_incomplete_grade_font.setColor(BaseColor.BLUE); 
              
              /*(12)*/
              ar_stamp_font=new Font(arabic_font,35);
              ar_stamp_font.setStyle("bold");
              ar_stamp_font.setColor(new CMYKColor(2, 8, 9, 0));
                
              ar_none_officail_font = new Font(arabic_font, 52, Font.BOLD, new GrayColor(0.75f)); 
                
                
                
              //curriculum Fonts
              /*(1)*/
              ar_univeristy_header_font=new Font(arabic_font,12);
              ar_univeristy_header_font.setStyle("bold");
              ar_univeristy_header_font.setColor(BaseColor.BLACK);/*#000099*/
                     
              /*(2)*/
              ar_college_header_font=new Font(arabic_font,11);
              ar_college_header_font.setColor(BaseColor.BLACK);
              ar_college_header_font.setStyle("bold");
              /*(3)*/
              ar_degree_font=new Font(arabic_font,10);
              ar_degree_font.setStyle("bold");
              ar_degree_font.setColor(BaseColor.BLACK);  
              
              /*(4)*/
              ar_prereq_font=new Font(arabic_font,6);
              ar_prereq_font.setColor(BaseColor.BLUE);
              
              /*(5)*/
              ar_coreq_font=new Font(arabic_font,6);
              ar_coreq_font.setColor(BaseColor.RED);
              
              /*(6)*/    
              ar_group_title_font=new Font(arabic_font,8);
              ar_group_title_font.setStyle("bold");
              ar_group_title_font.setColor(BaseColor.WHITE);
              
              /*(7)*/
              ar_group_equivalent_font=new Font(arabic_font,8);
              ar_group_equivalent_font.setStyle("bold");
              ar_group_equivalent_font.setColor(BaseColor.BLUE);
              
              /*(8)*/
              ar_print_font=new Font(arabic_font,5);
              ar_print_font.setColor(BaseColor.BLUE);
                
                
              //white 
              en_white_bold_5=new Font(Font.getFamily("ARIAL"), 5, Font.BOLD,BaseColor.WHITE);
              en_white_normal_5=new Font(Font.getFamily("ARIAL"), 5, Font.NORMAL,BaseColor.WHITE);
              en_white_bold_6=new Font(Font.getFamily("ARIAL"), 6, Font.BOLD,BaseColor.WHITE);
              en_white_normal_6=new Font(Font.getFamily("ARIAL"), 6, Font.NORMAL,BaseColor.WHITE);
              en_white_bold_7=new Font(Font.getFamily("ARIAL"), 7, Font.BOLD,BaseColor.WHITE);
              en_white_normal_7=new Font(Font.getFamily("ARIAL"), 7, Font.NORMAL,BaseColor.WHITE);
              en_white_bold_8=new Font(Font.getFamily("ARIAL"), 8, Font.BOLD,BaseColor.WHITE);
              en_white_normal_8=new Font(Font.getFamily("ARIAL"), 8, Font.NORMAL,BaseColor.WHITE);
              en_white_bold_9=new Font(Font.getFamily("ARIAL"), 9, Font.BOLD,BaseColor.WHITE);
              en_white_normal_9=new Font(Font.getFamily("ARIAL"), 9, Font.NORMAL,BaseColor.WHITE);
              en_white_bold_10=new Font(Font.getFamily("ARIAL"), 10, Font.BOLD,BaseColor.WHITE);
              en_white_normal_10=new Font(Font.getFamily("ARIAL"), 10, Font.NORMAL,BaseColor.WHITE);
              en_white_bold_11=new Font(Font.getFamily("ARIAL"), 11, Font.BOLD,BaseColor.WHITE);
              en_white_normal_11=new Font(Font.getFamily("ARIAL"), 11, Font.NORMAL,BaseColor.WHITE);
              en_white_bold_12=new Font(Font.getFamily("ARIAL"), 12, Font.BOLD,BaseColor.WHITE);
              en_white_normal_12=new Font(Font.getFamily("ARIAL"), 12, Font.NORMAL,BaseColor.WHITE);
              en_white_bold_13=new Font(Font.getFamily("ARIAL"), 13, Font.BOLD,BaseColor.WHITE);
              en_white_normal_13=new Font(Font.getFamily("ARIAL"), 13, Font.NORMAL,BaseColor.WHITE);
              en_white_bold_14=new Font(Font.getFamily("ARIAL"), 14, Font.BOLD,BaseColor.WHITE);
              en_white_normal_14=new Font(Font.getFamily("ARIAL"), 14, Font.NORMAL,BaseColor.WHITE);
              //Black 
              en_black_bold_5=new Font(Font.getFamily("ARIAL"), 5, Font.BOLD,BaseColor.BLACK);
              en_black_normal_5=new Font(Font.getFamily("ARIAL"), 5, Font.NORMAL,BaseColor.BLACK);
              en_black_bold_6=new Font(Font.getFamily("ARIAL"), 6, Font.BOLD,BaseColor.BLACK);
              en_black_normal_6=new Font(Font.getFamily("ARIAL"), 6, Font.NORMAL,BaseColor.BLACK);
              en_black_bold_7=new Font(Font.getFamily("ARIAL"), 7, Font.BOLD,BaseColor.BLACK);
              en_black_normal_7=new Font(Font.getFamily("ARIAL"), 7, Font.NORMAL,BaseColor.BLACK);
              en_black_bold_8=new Font(Font.getFamily("ARIAL"), 8, Font.BOLD,BaseColor.BLACK);
              en_black_normal_8=new Font(Font.getFamily("ARIAL"), 8, Font.NORMAL,BaseColor.BLACK);
              en_black_bold_9=new Font(Font.getFamily("ARIAL"), 9, Font.BOLD,BaseColor.BLACK);
              en_black_normal_9=new Font(Font.getFamily("ARIAL"), 9, Font.NORMAL,BaseColor.BLACK);
              en_black_bold_10=new Font(Font.getFamily("ARIAL"), 10, Font.BOLD,BaseColor.BLACK);
              en_black_normal_10=new Font(Font.getFamily("ARIAL"), 10, Font.NORMAL,BaseColor.BLACK);
              en_black_bold_11=new Font(Font.getFamily("ARIAL"), 11, Font.BOLD,BaseColor.BLACK);
              en_black_normal_11=new Font(Font.getFamily("ARIAL"), 11, Font.NORMAL,BaseColor.BLACK);
              en_black_bold_12=new Font(Font.getFamily("ARIAL"), 12, Font.BOLD,BaseColor.BLACK);
              en_black_normal_12=new Font(Font.getFamily("ARIAL"), 12, Font.NORMAL,BaseColor.BLACK);
              en_black_bold_13=new Font(Font.getFamily("ARIAL"), 13, Font.BOLD,BaseColor.BLACK);
              en_black_normal_13=new Font(Font.getFamily("ARIAL"), 13, Font.NORMAL,BaseColor.BLACK);
              en_black_bold_14=new Font(Font.getFamily("ARIAL"), 14, Font.BOLD,BaseColor.BLACK);
              en_black_normal_14=new Font(Font.getFamily("ARIAL"), 14, Font.NORMAL,BaseColor.BLACK);
              //Red 
              en_red_bold_5=new Font(Font.getFamily("ARIAL"), 5, Font.BOLD,BaseColor.RED);
              en_red_normal_5=new Font(Font.getFamily("ARIAL"), 5, Font.NORMAL,BaseColor.RED);
              en_red_bold_6=new Font(Font.getFamily("ARIAL"), 6, Font.BOLD,BaseColor.RED);
              en_red_normal_6=new Font(Font.getFamily("ARIAL"), 6, Font.NORMAL,BaseColor.RED);
              en_red_bold_7=new Font(Font.getFamily("ARIAL"), 7, Font.BOLD,BaseColor.RED);
              en_red_normal_7=new Font(Font.getFamily("ARIAL"), 7, Font.NORMAL,BaseColor.RED);
              en_red_bold_8=new Font(Font.getFamily("ARIAL"), 8, Font.BOLD,BaseColor.RED);
              en_red_normal_8=new Font(Font.getFamily("ARIAL"), 8, Font.NORMAL,BaseColor.RED);
              en_red_bold_9=new Font(Font.getFamily("ARIAL"), 9, Font.BOLD,BaseColor.RED);
              en_red_normal_9=new Font(Font.getFamily("ARIAL"), 9, Font.NORMAL,BaseColor.RED);
              en_red_bold_10=new Font(Font.getFamily("ARIAL"), 10, Font.BOLD,BaseColor.RED);
              en_red_normal_10=new Font(Font.getFamily("ARIAL"), 10, Font.NORMAL,BaseColor.RED);
              en_red_bold_11=new Font(Font.getFamily("ARIAL"), 11, Font.BOLD,BaseColor.RED);
              en_red_normal_11=new Font(Font.getFamily("ARIAL"), 11, Font.NORMAL,BaseColor.RED);
              en_red_bold_12=new Font(Font.getFamily("ARIAL"), 12, Font.BOLD,BaseColor.RED);
              en_red_normal_12=new Font(Font.getFamily("ARIAL"), 12, Font.NORMAL,BaseColor.RED);
              en_red_bold_13=new Font(Font.getFamily("ARIAL"), 13, Font.BOLD,BaseColor.RED);
              en_red_normal_13=new Font(Font.getFamily("ARIAL"), 13, Font.NORMAL,BaseColor.RED);
              en_red_bold_14=new Font(Font.getFamily("ARIAL"), 14, Font.BOLD,BaseColor.RED);
              en_red_normal_14=new Font(Font.getFamily("ARIAL"), 14, Font.NORMAL,BaseColor.RED);
              //blue 
              en_blue_bold_5=new Font(Font.getFamily("ARIAL"), 5, Font.BOLD,BaseColor.BLUE);
              en_blue_normal_5=new Font(Font.getFamily("ARIAL"), 5, Font.NORMAL,BaseColor.BLUE);
              en_blue_bold_6=new Font(Font.getFamily("ARIAL"), 6, Font.BOLD,BaseColor.BLUE);
              en_blue_normal_6=new Font(Font.getFamily("ARIAL"), 6, Font.NORMAL,BaseColor.BLUE);
              en_blue_bold_7=new Font(Font.getFamily("ARIAL"), 7, Font.BOLD,BaseColor.BLUE);
              en_blue_normal_7=new Font(Font.getFamily("ARIAL"), 7, Font.NORMAL,BaseColor.BLUE);
              en_blue_bold_8=new Font(Font.getFamily("ARIAL"), 8, Font.BOLD,BaseColor.BLUE);
              en_blue_normal_8=new Font(Font.getFamily("ARIAL"), 8, Font.NORMAL,BaseColor.BLUE);
              en_blue_bold_9=new Font(Font.getFamily("ARIAL"), 9, Font.BOLD,BaseColor.BLUE);
              en_blue_normal_9=new Font(Font.getFamily("ARIAL"), 9, Font.NORMAL,BaseColor.BLUE);
              en_blue_bold_10=new Font(Font.getFamily("ARIAL"), 10, Font.BOLD,BaseColor.BLUE);
              en_blue_normal_10=new Font(Font.getFamily("ARIAL"), 10, Font.NORMAL,BaseColor.BLUE);
              en_blue_bold_11=new Font(Font.getFamily("ARIAL"), 11, Font.BOLD,BaseColor.BLUE);
              en_blue_normal_11=new Font(Font.getFamily("ARIAL"), 11, Font.NORMAL,BaseColor.BLUE);
              en_blue_bold_12=new Font(Font.getFamily("ARIAL"), 12, Font.BOLD,BaseColor.BLUE);
              en_blue_normal_12=new Font(Font.getFamily("ARIAL"), 12, Font.NORMAL,BaseColor.BLUE);
              en_blue_bold_13=new Font(Font.getFamily("ARIAL"), 13, Font.BOLD,BaseColor.BLUE);
              en_blue_normal_13=new Font(Font.getFamily("ARIAL"), 13, Font.NORMAL,BaseColor.BLUE);
              en_blue_bold_14=new Font(Font.getFamily("ARIAL"), 14, Font.BOLD,BaseColor.BLUE);
              en_blue_normal_14=new Font(Font.getFamily("ARIAL"), 14, Font.NORMAL,BaseColor.BLUE);
                
                BaseColor green_basecolor=  new BaseColor(3, 121, 6);
                  //green 
                  en_green_bold_5=new Font(Font.getFamily("ARIAL"), 5, Font.BOLD,green_basecolor);
                  en_green_normal_5=new Font(Font.getFamily("ARIAL"), 5, Font.NORMAL,green_basecolor);
                  en_green_bold_6=new Font(Font.getFamily("ARIAL"), 6, Font.BOLD,green_basecolor);
                  en_green_normal_6=new Font(Font.getFamily("ARIAL"), 6, Font.NORMAL,green_basecolor);
                  en_green_bold_7=new Font(Font.getFamily("ARIAL"), 7, Font.BOLD,green_basecolor);
                  en_green_normal_7=new Font(Font.getFamily("ARIAL"), 7, Font.NORMAL,green_basecolor);
                  en_green_bold_8=new Font(Font.getFamily("ARIAL"), 8, Font.BOLD,green_basecolor);
                  en_green_normal_8=new Font(Font.getFamily("ARIAL"), 8, Font.NORMAL,green_basecolor);
                  en_green_bold_9=new Font(Font.getFamily("ARIAL"), 9, Font.BOLD,green_basecolor);
                  en_green_normal_9=new Font(Font.getFamily("ARIAL"), 9, Font.NORMAL,green_basecolor);
                  en_green_bold_10=new Font(Font.getFamily("ARIAL"), 10, Font.BOLD,green_basecolor);
                  en_green_normal_10=new Font(Font.getFamily("ARIAL"), 10, Font.NORMAL,green_basecolor);
                  en_green_bold_11=new Font(Font.getFamily("ARIAL"), 11, Font.BOLD,green_basecolor);
                  en_green_normal_11=new Font(Font.getFamily("ARIAL"), 11, Font.NORMAL,green_basecolor);
                  en_green_bold_12=new Font(Font.getFamily("ARIAL"), 12, Font.BOLD,green_basecolor);
                  en_green_normal_12=new Font(Font.getFamily("ARIAL"), 12, Font.NORMAL,green_basecolor);
                  en_green_bold_13=new Font(Font.getFamily("ARIAL"), 13, Font.BOLD,green_basecolor);
                  en_green_normal_13=new Font(Font.getFamily("ARIAL"), 13, Font.NORMAL,green_basecolor);
                  en_green_bold_14=new Font(Font.getFamily("ARIAL"), 14, Font.BOLD,green_basecolor);
                  en_green_normal_14=new Font(Font.getFamily("ARIAL"), 14, Font.NORMAL,green_basecolor);
              //Arabic
              //white 
              ar_white_bold_5=new Font(arabic_font, 5, Font.BOLD,BaseColor.WHITE);
              ar_white_normal_5=new Font(arabic_font, 5, Font.NORMAL,BaseColor.WHITE);
              ar_white_bold_6=new Font(arabic_font, 6, Font.BOLD,BaseColor.WHITE);
              ar_white_normal_6=new Font(arabic_font, 6, Font.NORMAL,BaseColor.WHITE);
              ar_white_bold_7=new Font(arabic_font, 7, Font.BOLD,BaseColor.WHITE);
              ar_white_normal_7=new Font(arabic_font, 7, Font.NORMAL,BaseColor.WHITE);
              ar_white_bold_8=new Font(arabic_font, 8, Font.BOLD,BaseColor.WHITE);
              ar_white_normal_8=new Font(arabic_font, 8, Font.NORMAL,BaseColor.WHITE);
              ar_white_bold_9=new Font(arabic_font, 9, Font.BOLD,BaseColor.WHITE);
              ar_white_normal_9=new Font(arabic_font, 9, Font.NORMAL,BaseColor.WHITE);
              ar_white_bold_10=new Font(arabic_font, 10, Font.BOLD,BaseColor.WHITE);
              ar_white_normal_10=new Font(arabic_font, 10, Font.NORMAL,BaseColor.WHITE);
              ar_white_bold_11=new Font(arabic_font, 11, Font.BOLD,BaseColor.WHITE);
              ar_white_normal_11=new Font(arabic_font, 11, Font.NORMAL,BaseColor.WHITE);
              ar_white_bold_12=new Font(arabic_font, 12, Font.BOLD,BaseColor.WHITE);
              ar_white_normal_12=new Font(arabic_font, 12, Font.NORMAL,BaseColor.WHITE);
              ar_white_bold_13=new Font(arabic_font, 13, Font.BOLD,BaseColor.WHITE);
              ar_white_normal_13=new Font(arabic_font, 13, Font.NORMAL,BaseColor.WHITE);
              ar_white_bold_14=new Font(arabic_font, 14, Font.BOLD,BaseColor.WHITE);
              ar_white_normal_14=new Font(arabic_font, 14, Font.NORMAL,BaseColor.WHITE);
              //Black 
              ar_black_bold_5=new Font(arabic_font, 5, Font.BOLD,BaseColor.BLACK);
              ar_black_normal_5=new Font(arabic_font, 5, Font.NORMAL,BaseColor.BLACK);
              ar_black_bold_6=new Font(arabic_font, 6, Font.BOLD,BaseColor.BLACK);
              ar_black_normal_6=new Font(arabic_font, 6, Font.NORMAL,BaseColor.BLACK);
              ar_black_bold_7=new Font(arabic_font, 7, Font.BOLD,BaseColor.BLACK);
              ar_black_normal_7=new Font(arabic_font, 7, Font.NORMAL,BaseColor.BLACK);
              ar_black_bold_8=new Font(arabic_font, 8, Font.BOLD,BaseColor.BLACK);
              ar_black_normal_8=new Font(arabic_font, 8, Font.NORMAL,BaseColor.BLACK);
              ar_black_bold_9=new Font(arabic_font, 9, Font.BOLD,BaseColor.BLACK);
              ar_black_normal_9=new Font(arabic_font, 9, Font.NORMAL,BaseColor.BLACK);
              ar_black_bold_10=new Font(arabic_font, 10, Font.BOLD,BaseColor.BLACK);
              ar_black_normal_10=new Font(arabic_font, 10, Font.NORMAL,BaseColor.BLACK);
              ar_black_bold_11=new Font(arabic_font, 11, Font.BOLD,BaseColor.BLACK);
              ar_black_normal_11=new Font(arabic_font, 11, Font.NORMAL,BaseColor.BLACK);
              ar_black_bold_12=new Font(arabic_font, 12, Font.BOLD,BaseColor.BLACK);
              ar_black_normal_12=new Font(arabic_font, 12, Font.NORMAL,BaseColor.BLACK);
              ar_black_bold_13=new Font(arabic_font, 13, Font.BOLD,BaseColor.BLACK);
              ar_black_normal_13=new Font(arabic_font, 13, Font.NORMAL,BaseColor.BLACK);
              ar_black_bold_14=new Font(arabic_font, 14, Font.BOLD,BaseColor.BLACK);
              ar_black_normal_14=new Font(arabic_font, 14, Font.NORMAL,BaseColor.BLACK);
              //Red 
              ar_red_bold_5=new Font(arabic_font, 5, Font.BOLD,BaseColor.RED);
              ar_red_normal_5=new Font(arabic_font, 5, Font.NORMAL,BaseColor.RED);
              ar_red_bold_6=new Font(arabic_font, 6, Font.BOLD,BaseColor.RED);
              ar_red_normal_6=new Font(arabic_font, 6, Font.NORMAL,BaseColor.RED);
              ar_red_bold_7=new Font(arabic_font, 7, Font.BOLD,BaseColor.RED);
              ar_red_normal_7=new Font(arabic_font, 7, Font.NORMAL,BaseColor.RED);
              ar_red_bold_8=new Font(arabic_font, 8, Font.BOLD,BaseColor.RED);
              ar_red_normal_8=new Font(arabic_font, 8, Font.NORMAL,BaseColor.RED);
              ar_red_bold_9=new Font(arabic_font, 9, Font.BOLD,BaseColor.RED);
              ar_red_normal_9=new Font(arabic_font, 9, Font.NORMAL,BaseColor.RED);
              ar_red_bold_10=new Font(arabic_font, 10, Font.BOLD,BaseColor.RED);
              ar_red_normal_10=new Font(arabic_font, 10, Font.NORMAL,BaseColor.RED);
              ar_red_bold_11=new Font(arabic_font, 11, Font.BOLD,BaseColor.RED);
              ar_red_normal_11=new Font(arabic_font, 11, Font.NORMAL,BaseColor.RED);
              ar_red_bold_12=new Font(arabic_font, 12, Font.BOLD,BaseColor.RED);
              ar_red_normal_12=new Font(arabic_font, 12, Font.NORMAL,BaseColor.RED);
              ar_red_bold_13=new Font(arabic_font, 13, Font.BOLD,BaseColor.RED);
              ar_red_normal_13=new Font(arabic_font, 13, Font.NORMAL,BaseColor.RED);
              ar_red_bold_14=new Font(arabic_font, 14, Font.BOLD,BaseColor.RED);
              ar_red_normal_14=new Font(arabic_font, 14, Font.NORMAL,BaseColor.RED);
              //blue 
              ar_blue_bold_5=new Font(arabic_font, 5, Font.BOLD,BaseColor.BLUE);
              ar_blue_normal_5=new Font(arabic_font, 5, Font.NORMAL,BaseColor.BLUE);
              ar_blue_bold_6=new Font(arabic_font, 6, Font.BOLD,BaseColor.BLUE);
              ar_blue_normal_6=new Font(arabic_font, 6, Font.NORMAL,BaseColor.BLUE);
              ar_blue_bold_7=new Font(arabic_font, 7, Font.BOLD,BaseColor.BLUE);
              ar_blue_normal_7=new Font(arabic_font, 7, Font.NORMAL,BaseColor.BLUE);
              ar_blue_bold_8=new Font(arabic_font, 8, Font.BOLD,BaseColor.BLUE);
              ar_blue_normal_8=new Font(arabic_font, 8, Font.NORMAL,BaseColor.BLUE);
              ar_blue_bold_9=new Font(arabic_font, 9, Font.BOLD,BaseColor.BLUE);
              ar_blue_normal_9=new Font(arabic_font, 9, Font.NORMAL,BaseColor.BLUE);
              ar_blue_bold_10=new Font(arabic_font, 10, Font.BOLD,BaseColor.BLUE);
              ar_blue_normal_10=new Font(arabic_font, 10, Font.NORMAL,BaseColor.BLUE);
              ar_blue_bold_11=new Font(arabic_font, 11, Font.BOLD,BaseColor.BLUE);
              ar_blue_normal_11=new Font(arabic_font, 11, Font.NORMAL,BaseColor.BLUE);
              ar_blue_bold_12=new Font(arabic_font, 12, Font.BOLD,BaseColor.BLUE);
              ar_blue_normal_12=new Font(arabic_font, 12, Font.NORMAL,BaseColor.BLUE);
              ar_blue_bold_13=new Font(arabic_font, 13, Font.BOLD,BaseColor.BLUE);
              ar_blue_normal_13=new Font(arabic_font, 13, Font.NORMAL,BaseColor.BLUE);
              ar_blue_bold_14=new Font(arabic_font, 14, Font.BOLD,BaseColor.BLUE);
              ar_blue_normal_14=new Font(arabic_font, 14, Font.NORMAL,BaseColor.BLUE);
                
                  //blue 
                  ar_green_bold_5=new Font(arabic_font, 5, Font.BOLD,green_basecolor);
                  ar_green_normal_5=new Font(arabic_font, 5, Font.NORMAL,green_basecolor);
                  ar_green_bold_6=new Font(arabic_font, 6, Font.BOLD,green_basecolor);
                  ar_green_normal_6=new Font(arabic_font, 6, Font.NORMAL,green_basecolor);
                  ar_green_bold_7=new Font(arabic_font, 7, Font.BOLD,green_basecolor);
                  ar_green_normal_7=new Font(arabic_font, 7, Font.NORMAL,green_basecolor);
                  ar_green_bold_8=new Font(arabic_font, 8, Font.BOLD,green_basecolor);
                  ar_green_normal_8=new Font(arabic_font, 8, Font.NORMAL,green_basecolor);
                  ar_green_bold_9=new Font(arabic_font, 9, Font.BOLD,green_basecolor);
                  ar_green_normal_9=new Font(arabic_font, 9, Font.NORMAL,green_basecolor);
                  ar_green_bold_10=new Font(arabic_font, 10, Font.BOLD,green_basecolor);
                  ar_green_normal_10=new Font(arabic_font, 10, Font.NORMAL,green_basecolor);
                  ar_green_bold_11=new Font(arabic_font, 11, Font.BOLD,green_basecolor);
                  ar_green_normal_11=new Font(arabic_font, 11, Font.NORMAL,green_basecolor);
                  ar_green_bold_12=new Font(arabic_font, 12, Font.BOLD,green_basecolor);
                  ar_green_normal_12=new Font(arabic_font, 12, Font.NORMAL,green_basecolor);
                  ar_green_bold_13=new Font(arabic_font, 13, Font.BOLD,green_basecolor);
                  ar_green_normal_13=new Font(arabic_font, 13, Font.NORMAL,green_basecolor);
                  ar_green_bold_14=new Font(arabic_font, 14, Font.BOLD,green_basecolor);
                  ar_green_normal_14=new Font(arabic_font, 14, Font.NORMAL,green_basecolor);
                
              //watermark
              watermark_font = new Font(FontFamily.HELVETICA, 52, Font.BOLD, new GrayColor(0.85f));
                  
              
              
              } catch (IOException e) {System.out.println("Error(PDF Font Class):"+e.toString()); } catch (DocumentException e) {System.out.println("Error(PDF Font Class):"+e.toString());}
        
    }


    public void setHeader_bold_font(Font header_bold_font) {
        this.header_bold_font = header_bold_font;
    }

    public void setNone_officail_font(Font none_officail_font) {
        this.none_officail_font = none_officail_font;
    }

    public Font getNone_officail_font() {
        return none_officail_font;
    }

    public void setAr_none_officail_font(Font ar_none_officail_font) {
        this.ar_none_officail_font = ar_none_officail_font;
    }

    public Font getAr_none_officail_font() {
        return ar_none_officail_font;
    }

    public Font getHeader_bold_font() {
        return header_bold_font;
    }

    public void setHeader_normal_font(Font header_normal_font) {
        this.header_normal_font = header_normal_font;
    }

    public Font getHeader_normal_font() {
        return header_normal_font;
    }

    public void setSemester_title_font(Font semester_title_font) {
        this.semester_title_font = semester_title_font;
    }

    public Font getSemester_title_font() {
        return semester_title_font;
    }

    public void setTable_header_font(Font table_header_font) {
        this.table_header_font = table_header_font;
    }

    public Font getTable_header_font() {
        return table_header_font;
    }

    public void setCourse_name_font(Font course_name_font) {
        this.course_name_font = course_name_font;
    }

    public Font getCourse_name_font() {
        return course_name_font;
    }

    public void setCourse_code_font(Font course_code_font) {
        this.course_code_font = course_code_font;
    }

    public Font getCourse_code_font() {
        return course_code_font;
    }

    public void setSem_ech_gpa_font(Font sem_ech_gpa_font) {
        this.sem_ech_gpa_font = sem_ech_gpa_font;
    }

    public Font getSem_ech_gpa_font() {
        return sem_ech_gpa_font;
    }

    public void setOther_red_font(Font other_red_font) {
        this.other_red_font = other_red_font;
    }

    public Font getOther_red_font() {
        return other_red_font;
    }

    public void setDisciplinary_font(Font disciplinary_font) {
        this.disciplinary_font = disciplinary_font;
    }

    public Font getDisciplinary_font() {
        return disciplinary_font;
    }

    public void setPage_numbering_font(Font page_numbering_font) {
        this.page_numbering_font = page_numbering_font;
    }

    public Font getPage_numbering_font() {
        return page_numbering_font;
    }

    public void setBad_grade_font(Font bad_grade_font) {
        this.bad_grade_font = bad_grade_font;
    }

    public Font getBad_grade_font() {
        return bad_grade_font;
    }

    public void setIncomplete_grade_font(Font incomplete_grade_font) {
        this.incomplete_grade_font = incomplete_grade_font;
    }

    public Font getIncomplete_grade_font() {
        return incomplete_grade_font;
    }

    public void setGreen_reg_status_font(Font green_reg_status_font) {
        this.green_reg_status_font = green_reg_status_font;
    }

    public Font getGreen_reg_status_font() {
        return green_reg_status_font;
    }

    public void setRed_reg_status_font(Font red_reg_status_font) {
        this.red_reg_status_font = red_reg_status_font;
    }

    public Font getRed_reg_status_font() {
        return red_reg_status_font;
    }

    public void setStamp_font(Font stamp_font) {
        this.stamp_font = stamp_font;
    }

    public Font getStamp_font() {
        return stamp_font;
    }

    public void setAr_header_bold_font(Font ar_header_bold_font) {
        this.ar_header_bold_font = ar_header_bold_font;
    }

    public Font getAr_header_bold_font() {
        return ar_header_bold_font;
    }

    public void setAr_header_normal_font(Font ar_header_normal_font) {
        this.ar_header_normal_font = ar_header_normal_font;
    }

    public Font getAr_header_normal_font() {
        return ar_header_normal_font;
    }

    public void setAr_semester_title_font(Font ar_semester_title_font) {
        this.ar_semester_title_font = ar_semester_title_font;
    }

    public Font getAr_semester_title_font() {
        return ar_semester_title_font;
    }

    public void setAr_table_header_font(Font ar_table_header_font) {
        this.ar_table_header_font = ar_table_header_font;
    }

    public Font getAr_table_header_font() {
        return ar_table_header_font;
    }

    public void setAr_course_name_font(Font ar_course_name_font) {
        this.ar_course_name_font = ar_course_name_font;
    }

    public Font getAr_course_name_font() {
        return ar_course_name_font;
    }

    public void setAr_course_code_font(Font ar_course_code_font) {
        this.ar_course_code_font = ar_course_code_font;
    }

    public Font getAr_course_code_font() {
        return ar_course_code_font;
    }

    public void setAr_sem_ech_gpa_font(Font ar_sem_ech_gpa_font) {
        this.ar_sem_ech_gpa_font = ar_sem_ech_gpa_font;
    }

    public Font getAr_sem_ech_gpa_font() {
        return ar_sem_ech_gpa_font;
    }

    public void setAr_other_red_font(Font ar_other_red_font) {
        this.ar_other_red_font = ar_other_red_font;
    }

    public Font getAr_other_red_font() {
        return ar_other_red_font;
    }

    public void setAr_disciplinary_font(Font ar_disciplinary_font) {
        this.ar_disciplinary_font = ar_disciplinary_font;
    }

    public Font getAr_disciplinary_font() {
        return ar_disciplinary_font;
    }

    public void setAr_page_numbering_font(Font ar_page_numbering_font) {
        this.ar_page_numbering_font = ar_page_numbering_font;
    }

    public Font getAr_page_numbering_font() {
        return ar_page_numbering_font;
    }

    public void setAr_bad_grade_font(Font ar_bad_grade_font) {
        this.ar_bad_grade_font = ar_bad_grade_font;
    }

    public Font getAr_bad_grade_font() {
        return ar_bad_grade_font;
    }

    public void setAr_incomplete_grade_font(Font ar_incomplete_grade_font) {
        this.ar_incomplete_grade_font = ar_incomplete_grade_font;
    }

    public Font getAr_incomplete_grade_font() {
        return ar_incomplete_grade_font;
    }

    public void setAr_green_reg_status_font(Font ar_green_reg_status_font) {
        this.ar_green_reg_status_font = ar_green_reg_status_font;
    }

    public Font getAr_green_reg_status_font() {
        return ar_green_reg_status_font;
    }

    public void setAr_red_reg_status_font(Font ar_red_reg_status_font) {
        this.ar_red_reg_status_font = ar_red_reg_status_font;
    }

    public Font getAr_red_reg_status_font() {
        return ar_red_reg_status_font;
    }

    public void setAr_stamp_font(Font ar_stamp_font) {
        this.ar_stamp_font = ar_stamp_font;
    }

    public Font getAr_stamp_font() {
        return ar_stamp_font;
    }

    public void setUniveristy_header_font(Font univeristy_header_font) {
        this.univeristy_header_font = univeristy_header_font;
    }

    public Font getUniveristy_header_font() {
        return univeristy_header_font;
    }

    public void setCollege_header_font(Font college_header_font) {
        this.college_header_font = college_header_font;
    }

    public Font getCollege_header_font() {
        return college_header_font;
    }

    public void setDegree_font(Font degree_font) {
        this.degree_font = degree_font;
    }

    public Font getDegree_font() {
        return degree_font;
    }

    public void setPrereq_font(Font prereq_font) {
        this.prereq_font = prereq_font;
    }

    public Font getPrereq_font() {
        return prereq_font;
    }

    public void setCoreq_font(Font coreq_font) {
        this.coreq_font = coreq_font;
    }

    public Font getCoreq_font() {
        return coreq_font;
    }

    public void setGroup_title_font(Font group_title_font) {
        this.group_title_font = group_title_font;
    }

    public Font getGroup_title_font() {
        return group_title_font;
    }

    public void setPrint_font(Font print_font) {
        this.print_font = print_font;
    }

    public Font getPrint_font() {
        return print_font;
    }

    public void setGroup_equivalent_font(Font group_equivalent_font) {
        this.group_equivalent_font = group_equivalent_font;
    }

    public Font getGroup_equivalent_font() {
        return group_equivalent_font;
    }

    public void setAr_univeristy_header_font(Font ar_univeristy_header_font) {
        this.ar_univeristy_header_font = ar_univeristy_header_font;
    }

    public Font getAr_univeristy_header_font() {
        return ar_univeristy_header_font;
    }

    public void setAr_college_header_font(Font ar_college_header_font) {
        this.ar_college_header_font = ar_college_header_font;
    }

    public Font getAr_college_header_font() {
        return ar_college_header_font;
    }

    public void setAr_degree_font(Font ar_degree_font) {
        this.ar_degree_font = ar_degree_font;
    }

    public Font getAr_degree_font() {
        return ar_degree_font;
    }

    public void setAr_prereq_font(Font ar_prereq_font) {
        this.ar_prereq_font = ar_prereq_font;
    }

    public Font getAr_prereq_font() {
        return ar_prereq_font;
    }

    public void setAr_coreq_font(Font ar_coreq_font) {
        this.ar_coreq_font = ar_coreq_font;
    }

    public Font getAr_coreq_font() {
        return ar_coreq_font;
    }

    public void setAr_group_title_font(Font ar_group_title_font) {
        this.ar_group_title_font = ar_group_title_font;
    }

    public Font getAr_group_title_font() {
        return ar_group_title_font;
    }

    public void setAr_print_font(Font ar_print_font) {
        this.ar_print_font = ar_print_font;
    }

    public Font getAr_print_font() {
        return ar_print_font;
    }

    public void setAr_group_equivalent_font(Font ar_group_equivalent_font) {
        this.ar_group_equivalent_font = ar_group_equivalent_font;
    }

    public Font getAr_group_equivalent_font() {
        return ar_group_equivalent_font;
    }

    public Font getEn_white_bold_5() {
        return en_white_bold_5;
    }

    public Font getEn_white_normal_5() {
        return en_white_normal_5;
    }

    public Font getEn_white_bold_6() {
        return en_white_bold_6;
    }

    public Font getEn_white_normal_6() {
        return en_white_normal_6;
    }

    public Font getEn_white_bold_7() {
        return en_white_bold_7;
    }

    public Font getEn_white_normal_7() {
        return en_white_normal_7;
    }

    public Font getEn_white_bold_8() {
        return en_white_bold_8;
    }

    public Font getEn_white_normal_8() {
        return en_white_normal_8;
    }

    public Font getEn_white_bold_9() {
        return en_white_bold_9;
    }

    public Font getEn_white_normal_9() {
        return en_white_normal_9;
    }

    public Font getEn_white_bold_10() {
        return en_white_bold_10;
    }

    public Font getEn_white_normal_10() {
        return en_white_normal_10;
    }

    public Font getEn_white_bold_11() {
        return en_white_bold_11;
    }

    public Font getEn_white_normal_11() {
        return en_white_normal_11;
    }

    public Font getEn_white_bold_12() {
        return en_white_bold_12;
    }

    public Font getEn_white_normal_12() {
        return en_white_normal_12;
    }

    public Font getEn_white_bold_13() {
        return en_white_bold_13;
    }

    public Font getEn_white_normal_13() {
        return en_white_normal_13;
    }

    public Font getEn_white_bold_14() {
        return en_white_bold_14;
    }

    public Font getEn_white_normal_14() {
        return en_white_normal_14;
    }

    public Font getEn_black_bold_5() {
        return en_black_bold_5;
    }

    public Font getEn_black_normal_5() {
        return en_black_normal_5;
    }

    public Font getEn_black_bold_6() {
        return en_black_bold_6;
    }

    public Font getEn_black_normal_6() {
        return en_black_normal_6;
    }

    public Font getEn_black_bold_7() {
        return en_black_bold_7;
    }

    public Font getEn_black_normal_7() {
        return en_black_normal_7;
    }

    public Font getEn_black_bold_8() {
        return en_black_bold_8;
    }

    public Font getEn_black_normal_8() {
        return en_black_normal_8;
    }

    public Font getEn_black_bold_9() {
        return en_black_bold_9;
    }

    public Font getEn_black_normal_9() {
        return en_black_normal_9;
    }

    public Font getEn_black_bold_10() {
        return en_black_bold_10;
    }

    public Font getEn_black_normal_10() {
        return en_black_normal_10;
    }

    public Font getEn_black_bold_11() {
        return en_black_bold_11;
    }

    public Font getEn_black_normal_11() {
        return en_black_normal_11;
    }

    public Font getEn_black_bold_12() {
        return en_black_bold_12;
    }

    public Font getEn_black_normal_12() {
        return en_black_normal_12;
    }

    public Font getEn_black_bold_13() {
        return en_black_bold_13;
    }

    public Font getEn_black_normal_13() {
        return en_black_normal_13;
    }

    public Font getEn_black_bold_14() {
        return en_black_bold_14;
    }

    public Font getEn_black_normal_14() {
        return en_black_normal_14;
    }

    public Font getEn_red_bold_5() {
        return en_red_bold_5;
    }

    public Font getEn_red_normal_5() {
        return en_red_normal_5;
    }

    public Font getEn_red_bold_6() {
        return en_red_bold_6;
    }

    public Font getEn_red_normal_6() {
        return en_red_normal_6;
    }

    public Font getEn_red_bold_7() {
        return en_red_bold_7;
    }

    public Font getEn_red_normal_7() {
        return en_red_normal_7;
    }

    public Font getEn_red_bold_8() {
        return en_red_bold_8;
    }

    public Font getEn_red_normal_8() {
        return en_red_normal_8;
    }

    public Font getEn_red_bold_9() {
        return en_red_bold_9;
    }

    public Font getEn_red_normal_9() {
        return en_red_normal_9;
    }

    public Font getEn_red_bold_10() {
        return en_red_bold_10;
    }

    public Font getEn_red_normal_10() {
        return en_red_normal_10;
    }

    public Font getEn_red_bold_11() {
        return en_red_bold_11;
    }

    public Font getEn_red_normal_11() {
        return en_red_normal_11;
    }

    public Font getEn_red_bold_12() {
        return en_red_bold_12;
    }

    public Font getEn_red_normal_12() {
        return en_red_normal_12;
    }

    public Font getEn_red_bold_13() {
        return en_red_bold_13;
    }

    public Font getEn_red_normal_13() {
        return en_red_normal_13;
    }

    public Font getEn_red_bold_14() {
        return en_red_bold_14;
    }

    public Font getEn_red_normal_14() {
        return en_red_normal_14;
    }

    public Font getEn_blue_bold_5() {
        return en_blue_bold_5;
    }

    public Font getEn_blue_normal_5() {
        return en_blue_normal_5;
    }

    public Font getEn_blue_bold_6() {
        return en_blue_bold_6;
    }

    public Font getEn_blue_normal_6() {
        return en_blue_normal_6;
    }

    public Font getEn_blue_bold_7() {
        return en_blue_bold_7;
    }

    public Font getEn_blue_normal_7() {
        return en_blue_normal_7;
    }

    public Font getEn_blue_bold_8() {
        return en_blue_bold_8;
    }

    public Font getEn_blue_normal_8() {
        return en_blue_normal_8;
    }

    public Font getEn_blue_bold_9() {
        return en_blue_bold_9;
    }

    public Font getEn_blue_normal_9() {
        return en_blue_normal_9;
    }

    public Font getEn_blue_bold_10() {
        return en_blue_bold_10;
    }

    public Font getEn_blue_normal_10() {
        return en_blue_normal_10;
    }

    public Font getEn_blue_bold_11() {
        return en_blue_bold_11;
    }

    public Font getEn_blue_normal_11() {
        return en_blue_normal_11;
    }

    public Font getEn_blue_bold_12() {
        return en_blue_bold_12;
    }

    public Font getEn_blue_normal_12() {
        return en_blue_normal_12;
    }

    public Font getEn_blue_bold_13() {
        return en_blue_bold_13;
    }

    public Font getEn_blue_normal_13() {
        return en_blue_normal_13;
    }

    public Font getEn_blue_bold_14() {
        return en_blue_bold_14;
    }

    public Font getEn_blue_normal_14() {
        return en_blue_normal_14;
    }

    public Font getAr_white_bold_5() {
        return ar_white_bold_5;
    }

    public Font getAr_white_normal_5() {
        return ar_white_normal_5;
    }

    public Font getAr_white_bold_6() {
        return ar_white_bold_6;
    }

    public Font getAr_white_normal_6() {
        return ar_white_normal_6;
    }

    public Font getAr_white_bold_7() {
        return ar_white_bold_7;
    }

    public Font getAr_white_normal_7() {
        return ar_white_normal_7;
    }

    public Font getAr_white_bold_8() {
        return ar_white_bold_8;
    }

    public Font getAr_white_normal_8() {
        return ar_white_normal_8;
    }

    public Font getAr_white_bold_9() {
        return ar_white_bold_9;
    }

    public Font getAr_white_normal_9() {
        return ar_white_normal_9;
    }

    public Font getAr_white_bold_10() {
        return ar_white_bold_10;
    }

    public Font getAr_white_normal_10() {
        return ar_white_normal_10;
    }

    public Font getAr_white_bold_11() {
        return ar_white_bold_11;
    }

    public Font getAr_white_normal_11() {
        return ar_white_normal_11;
    }

    public Font getAr_white_bold_12() {
        return ar_white_bold_12;
    }

    public Font getAr_white_normal_12() {
        return ar_white_normal_12;
    }

    public Font getAr_white_bold_13() {
        return ar_white_bold_13;
    }

    public Font getAr_white_normal_13() {
        return ar_white_normal_13;
    }

    public Font getAr_white_bold_14() {
        return ar_white_bold_14;
    }

    public Font getAr_white_normal_14() {
        return ar_white_normal_14;
    }

    public Font getAr_black_bold_5() {
        return ar_black_bold_5;
    }

    public Font getAr_black_normal_5() {
        return ar_black_normal_5;
    }

    public Font getAr_black_bold_6() {
        return ar_black_bold_6;
    }

    public Font getAr_black_normal_6() {
        return ar_black_normal_6;
    }

    public Font getAr_black_bold_7() {
        return ar_black_bold_7;
    }

    public Font getAr_black_normal_7() {
        return ar_black_normal_7;
    }

    public Font getAr_black_bold_8() {
        return ar_black_bold_8;
    }

    public Font getAr_black_normal_8() {
        return ar_black_normal_8;
    }

    public Font getAr_black_bold_9() {
        return ar_black_bold_9;
    }

    public Font getAr_black_normal_9() {
        return ar_black_normal_9;
    }

    public Font getAr_black_bold_10() {
        return ar_black_bold_10;
    }

    public Font getAr_black_normal_10() {
        return ar_black_normal_10;
    }

    public Font getAr_black_bold_11() {
        return ar_black_bold_11;
    }

    public Font getAr_black_normal_11() {
        return ar_black_normal_11;
    }

    public Font getAr_black_bold_12() {
        return ar_black_bold_12;
    }

    public Font getAr_black_normal_12() {
        return ar_black_normal_12;
    }

    public Font getAr_black_bold_13() {
        return ar_black_bold_13;
    }

    public Font getAr_black_normal_13() {
        return ar_black_normal_13;
    }

    public Font getAr_black_bold_14() {
        return ar_black_bold_14;
    }

    public Font getAr_black_normal_14() {
        return ar_black_normal_14;
    }

    public Font getAr_red_bold_5() {
        return ar_red_bold_5;
    }

    public Font getAr_red_normal_5() {
        return ar_red_normal_5;
    }

    public Font getAr_red_bold_6() {
        return ar_red_bold_6;
    }

    public Font getAr_red_normal_6() {
        return ar_red_normal_6;
    }

    public Font getAr_red_bold_7() {
        return ar_red_bold_7;
    }

    public Font getAr_red_normal_7() {
        return ar_red_normal_7;
    }

    public Font getAr_red_bold_8() {
        return ar_red_bold_8;
    }

    public Font getAr_red_normal_8() {
        return ar_red_normal_8;
    }

    public Font getAr_red_bold_9() {
        return ar_red_bold_9;
    }

    public Font getAr_red_normal_9() {
        return ar_red_normal_9;
    }

    public Font getAr_red_bold_10() {
        return ar_red_bold_10;
    }

    public Font getAr_red_normal_10() {
        return ar_red_normal_10;
    }

    public Font getAr_red_bold_11() {
        return ar_red_bold_11;
    }

    public Font getAr_red_normal_11() {
        return ar_red_normal_11;
    }

    public Font getAr_red_bold_12() {
        return ar_red_bold_12;
    }

    public Font getAr_red_normal_12() {
        return ar_red_normal_12;
    }

    public Font getAr_red_bold_13() {
        return ar_red_bold_13;
    }

    public Font getAr_red_normal_13() {
        return ar_red_normal_13;
    }

    public Font getAr_red_bold_14() {
        return ar_red_bold_14;
    }

    public Font getAr_red_normal_14() {
        return ar_red_normal_14;
    }

    public Font getAr_blue_bold_5() {
        return ar_blue_bold_5;
    }

    public Font getAr_blue_normal_5() {
        return ar_blue_normal_5;
    }

    public Font getAr_blue_bold_6() {
        return ar_blue_bold_6;
    }

    public Font getAr_blue_normal_6() {
        return ar_blue_normal_6;
    }

    public Font getAr_blue_bold_7() {
        return ar_blue_bold_7;
    }

    public Font getAr_blue_normal_7() {
        return ar_blue_normal_7;
    }

    public Font getAr_blue_bold_8() {
        return ar_blue_bold_8;
    }

    public Font getAr_blue_normal_8() {
        return ar_blue_normal_8;
    }

    public Font getAr_blue_bold_9() {
        return ar_blue_bold_9;
    }

    public Font getAr_blue_normal_9() {
        return ar_blue_normal_9;
    }

    public Font getAr_blue_bold_10() {
        return ar_blue_bold_10;
    }

    public Font getAr_blue_normal_10() {
        return ar_blue_normal_10;
    }

    public Font getAr_blue_bold_11() {
        return ar_blue_bold_11;
    }

    public Font getAr_blue_normal_11() {
        return ar_blue_normal_11;
    }

    public Font getAr_blue_bold_12() {
        return ar_blue_bold_12;
    }

    public Font getAr_blue_normal_12() {
        return ar_blue_normal_12;
    }

    public Font getAr_blue_bold_13() {
        return ar_blue_bold_13;
    }

    public Font getAr_blue_normal_13() {
        return ar_blue_normal_13;
    }

    public Font getAr_blue_bold_14() {
        return ar_blue_bold_14;
    }

    public Font getAr_blue_normal_14() {
        return ar_blue_normal_14;
    }


    public Font getWatermark_font() {
        return watermark_font;
    }

    public void setEn_white_bold_5(Font en_white_bold_5) {
        this.en_white_bold_5 = en_white_bold_5;
    }

    public void setEn_white_normal_5(Font en_white_normal_5) {
        this.en_white_normal_5 = en_white_normal_5;
    }

    public void setEn_white_bold_6(Font en_white_bold_6) {
        this.en_white_bold_6 = en_white_bold_6;
    }

    public void setEn_white_normal_6(Font en_white_normal_6) {
        this.en_white_normal_6 = en_white_normal_6;
    }

    public void setEn_white_bold_7(Font en_white_bold_7) {
        this.en_white_bold_7 = en_white_bold_7;
    }

    public void setEn_white_normal_7(Font en_white_normal_7) {
        this.en_white_normal_7 = en_white_normal_7;
    }

    public void setEn_white_bold_8(Font en_white_bold_8) {
        this.en_white_bold_8 = en_white_bold_8;
    }

    public void setEn_white_normal_8(Font en_white_normal_8) {
        this.en_white_normal_8 = en_white_normal_8;
    }

    public void setEn_white_bold_9(Font en_white_bold_9) {
        this.en_white_bold_9 = en_white_bold_9;
    }

    public void setEn_white_normal_9(Font en_white_normal_9) {
        this.en_white_normal_9 = en_white_normal_9;
    }

    public void setEn_white_bold_10(Font en_white_bold_10) {
        this.en_white_bold_10 = en_white_bold_10;
    }

    public void setEn_white_normal_10(Font en_white_normal_10) {
        this.en_white_normal_10 = en_white_normal_10;
    }

    public void setEn_white_bold_11(Font en_white_bold_11) {
        this.en_white_bold_11 = en_white_bold_11;
    }

    public void setEn_white_normal_11(Font en_white_normal_11) {
        this.en_white_normal_11 = en_white_normal_11;
    }

    public void setEn_white_bold_12(Font en_white_bold_12) {
        this.en_white_bold_12 = en_white_bold_12;
    }

    public void setEn_white_normal_12(Font en_white_normal_12) {
        this.en_white_normal_12 = en_white_normal_12;
    }

    public void setEn_white_bold_13(Font en_white_bold_13) {
        this.en_white_bold_13 = en_white_bold_13;
    }

    public void setEn_white_normal_13(Font en_white_normal_13) {
        this.en_white_normal_13 = en_white_normal_13;
    }

    public void setEn_white_bold_14(Font en_white_bold_14) {
        this.en_white_bold_14 = en_white_bold_14;
    }

    public void setEn_white_normal_14(Font en_white_normal_14) {
        this.en_white_normal_14 = en_white_normal_14;
    }

    public void setEn_black_bold_5(Font en_black_bold_5) {
        this.en_black_bold_5 = en_black_bold_5;
    }

    public void setEn_black_normal_5(Font en_black_normal_5) {
        this.en_black_normal_5 = en_black_normal_5;
    }

    public void setEn_black_bold_6(Font en_black_bold_6) {
        this.en_black_bold_6 = en_black_bold_6;
    }

    public void setEn_black_normal_6(Font en_black_normal_6) {
        this.en_black_normal_6 = en_black_normal_6;
    }

    public void setEn_black_bold_7(Font en_black_bold_7) {
        this.en_black_bold_7 = en_black_bold_7;
    }

    public void setEn_black_normal_7(Font en_black_normal_7) {
        this.en_black_normal_7 = en_black_normal_7;
    }

    public void setEn_black_bold_8(Font en_black_bold_8) {
        this.en_black_bold_8 = en_black_bold_8;
    }

    public void setEn_black_normal_8(Font en_black_normal_8) {
        this.en_black_normal_8 = en_black_normal_8;
    }

    public void setEn_black_bold_9(Font en_black_bold_9) {
        this.en_black_bold_9 = en_black_bold_9;
    }

    public void setEn_black_normal_9(Font en_black_normal_9) {
        this.en_black_normal_9 = en_black_normal_9;
    }

    public void setEn_black_bold_10(Font en_black_bold_10) {
        this.en_black_bold_10 = en_black_bold_10;
    }

    public void setEn_black_normal_10(Font en_black_normal_10) {
        this.en_black_normal_10 = en_black_normal_10;
    }

    public void setEn_black_bold_11(Font en_black_bold_11) {
        this.en_black_bold_11 = en_black_bold_11;
    }

    public void setEn_black_normal_11(Font en_black_normal_11) {
        this.en_black_normal_11 = en_black_normal_11;
    }

    public void setEn_black_bold_12(Font en_black_bold_12) {
        this.en_black_bold_12 = en_black_bold_12;
    }

    public void setEn_black_normal_12(Font en_black_normal_12) {
        this.en_black_normal_12 = en_black_normal_12;
    }

    public void setEn_black_bold_13(Font en_black_bold_13) {
        this.en_black_bold_13 = en_black_bold_13;
    }

    public void setEn_black_normal_13(Font en_black_normal_13) {
        this.en_black_normal_13 = en_black_normal_13;
    }

    public void setEn_black_bold_14(Font en_black_bold_14) {
        this.en_black_bold_14 = en_black_bold_14;
    }

    public void setEn_black_normal_14(Font en_black_normal_14) {
        this.en_black_normal_14 = en_black_normal_14;
    }

    public void setEn_red_bold_5(Font en_red_bold_5) {
        this.en_red_bold_5 = en_red_bold_5;
    }

    public void setEn_red_normal_5(Font en_red_normal_5) {
        this.en_red_normal_5 = en_red_normal_5;
    }

    public void setEn_red_bold_6(Font en_red_bold_6) {
        this.en_red_bold_6 = en_red_bold_6;
    }

    public void setEn_red_normal_6(Font en_red_normal_6) {
        this.en_red_normal_6 = en_red_normal_6;
    }

    public void setEn_red_bold_7(Font en_red_bold_7) {
        this.en_red_bold_7 = en_red_bold_7;
    }

    public void setEn_red_normal_7(Font en_red_normal_7) {
        this.en_red_normal_7 = en_red_normal_7;
    }

    public void setEn_red_bold_8(Font en_red_bold_8) {
        this.en_red_bold_8 = en_red_bold_8;
    }

    public void setEn_red_normal_8(Font en_red_normal_8) {
        this.en_red_normal_8 = en_red_normal_8;
    }

    public void setEn_red_bold_9(Font en_red_bold_9) {
        this.en_red_bold_9 = en_red_bold_9;
    }

    public void setEn_red_normal_9(Font en_red_normal_9) {
        this.en_red_normal_9 = en_red_normal_9;
    }

    public void setEn_red_bold_10(Font en_red_bold_10) {
        this.en_red_bold_10 = en_red_bold_10;
    }

    public void setEn_red_normal_10(Font en_red_normal_10) {
        this.en_red_normal_10 = en_red_normal_10;
    }

    public void setEn_red_bold_11(Font en_red_bold_11) {
        this.en_red_bold_11 = en_red_bold_11;
    }

    public void setEn_red_normal_11(Font en_red_normal_11) {
        this.en_red_normal_11 = en_red_normal_11;
    }

    public void setEn_red_bold_12(Font en_red_bold_12) {
        this.en_red_bold_12 = en_red_bold_12;
    }

    public void setEn_red_normal_12(Font en_red_normal_12) {
        this.en_red_normal_12 = en_red_normal_12;
    }

    public void setEn_red_bold_13(Font en_red_bold_13) {
        this.en_red_bold_13 = en_red_bold_13;
    }

    public void setEn_red_normal_13(Font en_red_normal_13) {
        this.en_red_normal_13 = en_red_normal_13;
    }

    public void setEn_red_bold_14(Font en_red_bold_14) {
        this.en_red_bold_14 = en_red_bold_14;
    }

    public void setEn_red_normal_14(Font en_red_normal_14) {
        this.en_red_normal_14 = en_red_normal_14;
    }

    public void setEn_blue_bold_5(Font en_blue_bold_5) {
        this.en_blue_bold_5 = en_blue_bold_5;
    }

    public void setEn_blue_normal_5(Font en_blue_normal_5) {
        this.en_blue_normal_5 = en_blue_normal_5;
    }

    public void setEn_blue_bold_6(Font en_blue_bold_6) {
        this.en_blue_bold_6 = en_blue_bold_6;
    }

    public void setEn_blue_normal_6(Font en_blue_normal_6) {
        this.en_blue_normal_6 = en_blue_normal_6;
    }

    public void setEn_blue_bold_7(Font en_blue_bold_7) {
        this.en_blue_bold_7 = en_blue_bold_7;
    }

    public void setEn_blue_normal_7(Font en_blue_normal_7) {
        this.en_blue_normal_7 = en_blue_normal_7;
    }

    public void setEn_blue_bold_8(Font en_blue_bold_8) {
        this.en_blue_bold_8 = en_blue_bold_8;
    }

    public void setEn_blue_normal_8(Font en_blue_normal_8) {
        this.en_blue_normal_8 = en_blue_normal_8;
    }

    public void setEn_blue_bold_9(Font en_blue_bold_9) {
        this.en_blue_bold_9 = en_blue_bold_9;
    }

    public void setEn_blue_normal_9(Font en_blue_normal_9) {
        this.en_blue_normal_9 = en_blue_normal_9;
    }

    public void setEn_blue_bold_10(Font en_blue_bold_10) {
        this.en_blue_bold_10 = en_blue_bold_10;
    }

    public void setEn_blue_normal_10(Font en_blue_normal_10) {
        this.en_blue_normal_10 = en_blue_normal_10;
    }

    public void setEn_blue_bold_11(Font en_blue_bold_11) {
        this.en_blue_bold_11 = en_blue_bold_11;
    }

    public void setEn_blue_normal_11(Font en_blue_normal_11) {
        this.en_blue_normal_11 = en_blue_normal_11;
    }

    public void setEn_blue_bold_12(Font en_blue_bold_12) {
        this.en_blue_bold_12 = en_blue_bold_12;
    }

    public void setEn_blue_normal_12(Font en_blue_normal_12) {
        this.en_blue_normal_12 = en_blue_normal_12;
    }

    public void setEn_blue_bold_13(Font en_blue_bold_13) {
        this.en_blue_bold_13 = en_blue_bold_13;
    }

    public void setEn_blue_normal_13(Font en_blue_normal_13) {
        this.en_blue_normal_13 = en_blue_normal_13;
    }

    public void setEn_blue_bold_14(Font en_blue_bold_14) {
        this.en_blue_bold_14 = en_blue_bold_14;
    }

    public void setEn_blue_normal_14(Font en_blue_normal_14) {
        this.en_blue_normal_14 = en_blue_normal_14;
    }

    public void setEn_green_bold_5(Font en_green_bold_5) {
        this.en_green_bold_5 = en_green_bold_5;
    }

    public Font getEn_green_bold_5() {
        return en_green_bold_5;
    }

    public void setEn_green_normal_5(Font en_green_normal_5) {
        this.en_green_normal_5 = en_green_normal_5;
    }

    public Font getEn_green_normal_5() {
        return en_green_normal_5;
    }

    public void setEn_green_bold_6(Font en_green_bold_6) {
        this.en_green_bold_6 = en_green_bold_6;
    }

    public Font getEn_green_bold_6() {
        return en_green_bold_6;
    }

    public void setEn_green_normal_6(Font en_green_normal_6) {
        this.en_green_normal_6 = en_green_normal_6;
    }

    public Font getEn_green_normal_6() {
        return en_green_normal_6;
    }

    public void setEn_green_bold_7(Font en_green_bold_7) {
        this.en_green_bold_7 = en_green_bold_7;
    }

    public Font getEn_green_bold_7() {
        return en_green_bold_7;
    }

    public void setEn_green_normal_7(Font en_green_normal_7) {
        this.en_green_normal_7 = en_green_normal_7;
    }

    public Font getEn_green_normal_7() {
        return en_green_normal_7;
    }

    public void setEn_green_bold_8(Font en_green_bold_8) {
        this.en_green_bold_8 = en_green_bold_8;
    }

    public Font getEn_green_bold_8() {
        return en_green_bold_8;
    }

    public void setEn_green_normal_8(Font en_green_normal_8) {
        this.en_green_normal_8 = en_green_normal_8;
    }

    public Font getEn_green_normal_8() {
        return en_green_normal_8;
    }

    public void setEn_green_bold_9(Font en_green_bold_9) {
        this.en_green_bold_9 = en_green_bold_9;
    }

    public Font getEn_green_bold_9() {
        return en_green_bold_9;
    }

    public void setEn_green_normal_9(Font en_green_normal_9) {
        this.en_green_normal_9 = en_green_normal_9;
    }

    public Font getEn_green_normal_9() {
        return en_green_normal_9;
    }

    public void setEn_green_bold_10(Font en_green_bold_10) {
        this.en_green_bold_10 = en_green_bold_10;
    }

    public Font getEn_green_bold_10() {
        return en_green_bold_10;
    }

    public void setEn_green_normal_10(Font en_green_normal_10) {
        this.en_green_normal_10 = en_green_normal_10;
    }

    public Font getEn_green_normal_10() {
        return en_green_normal_10;
    }

    public void setEn_green_bold_11(Font en_green_bold_11) {
        this.en_green_bold_11 = en_green_bold_11;
    }

    public Font getEn_green_bold_11() {
        return en_green_bold_11;
    }

    public void setEn_green_normal_11(Font en_green_normal_11) {
        this.en_green_normal_11 = en_green_normal_11;
    }

    public Font getEn_green_normal_11() {
        return en_green_normal_11;
    }

    public void setEn_green_bold_12(Font en_green_bold_12) {
        this.en_green_bold_12 = en_green_bold_12;
    }

    public Font getEn_green_bold_12() {
        return en_green_bold_12;
    }

    public void setEn_green_normal_12(Font en_green_normal_12) {
        this.en_green_normal_12 = en_green_normal_12;
    }

    public Font getEn_green_normal_12() {
        return en_green_normal_12;
    }

    public void setEn_green_bold_13(Font en_green_bold_13) {
        this.en_green_bold_13 = en_green_bold_13;
    }

    public Font getEn_green_bold_13() {
        return en_green_bold_13;
    }

    public void setEn_green_normal_13(Font en_green_normal_13) {
        this.en_green_normal_13 = en_green_normal_13;
    }

    public Font getEn_green_normal_13() {
        return en_green_normal_13;
    }

    public void setEn_green_bold_14(Font en_green_bold_14) {
        this.en_green_bold_14 = en_green_bold_14;
    }

    public Font getEn_green_bold_14() {
        return en_green_bold_14;
    }

    public void setEn_green_normal_14(Font en_green_normal_14) {
        this.en_green_normal_14 = en_green_normal_14;
    }

    public Font getEn_green_normal_14() {
        return en_green_normal_14;
    }

    public void setAr_white_bold_5(Font ar_white_bold_5) {
        this.ar_white_bold_5 = ar_white_bold_5;
    }

    public void setAr_white_normal_5(Font ar_white_normal_5) {
        this.ar_white_normal_5 = ar_white_normal_5;
    }

    public void setAr_white_bold_6(Font ar_white_bold_6) {
        this.ar_white_bold_6 = ar_white_bold_6;
    }

    public void setAr_white_normal_6(Font ar_white_normal_6) {
        this.ar_white_normal_6 = ar_white_normal_6;
    }

    public void setAr_white_bold_7(Font ar_white_bold_7) {
        this.ar_white_bold_7 = ar_white_bold_7;
    }

    public void setAr_white_normal_7(Font ar_white_normal_7) {
        this.ar_white_normal_7 = ar_white_normal_7;
    }

    public void setAr_white_bold_8(Font ar_white_bold_8) {
        this.ar_white_bold_8 = ar_white_bold_8;
    }

    public void setAr_white_normal_8(Font ar_white_normal_8) {
        this.ar_white_normal_8 = ar_white_normal_8;
    }

    public void setAr_white_bold_9(Font ar_white_bold_9) {
        this.ar_white_bold_9 = ar_white_bold_9;
    }

    public void setAr_white_normal_9(Font ar_white_normal_9) {
        this.ar_white_normal_9 = ar_white_normal_9;
    }

    public void setAr_white_bold_10(Font ar_white_bold_10) {
        this.ar_white_bold_10 = ar_white_bold_10;
    }

    public void setAr_white_normal_10(Font ar_white_normal_10) {
        this.ar_white_normal_10 = ar_white_normal_10;
    }

    public void setAr_white_bold_11(Font ar_white_bold_11) {
        this.ar_white_bold_11 = ar_white_bold_11;
    }

    public void setAr_white_normal_11(Font ar_white_normal_11) {
        this.ar_white_normal_11 = ar_white_normal_11;
    }

    public void setAr_white_bold_12(Font ar_white_bold_12) {
        this.ar_white_bold_12 = ar_white_bold_12;
    }

    public void setAr_white_normal_12(Font ar_white_normal_12) {
        this.ar_white_normal_12 = ar_white_normal_12;
    }

    public void setAr_white_bold_13(Font ar_white_bold_13) {
        this.ar_white_bold_13 = ar_white_bold_13;
    }

    public void setAr_white_normal_13(Font ar_white_normal_13) {
        this.ar_white_normal_13 = ar_white_normal_13;
    }

    public void setAr_white_bold_14(Font ar_white_bold_14) {
        this.ar_white_bold_14 = ar_white_bold_14;
    }

    public void setAr_white_normal_14(Font ar_white_normal_14) {
        this.ar_white_normal_14 = ar_white_normal_14;
    }

    public void setAr_black_bold_5(Font ar_black_bold_5) {
        this.ar_black_bold_5 = ar_black_bold_5;
    }

    public void setAr_black_normal_5(Font ar_black_normal_5) {
        this.ar_black_normal_5 = ar_black_normal_5;
    }

    public void setAr_black_bold_6(Font ar_black_bold_6) {
        this.ar_black_bold_6 = ar_black_bold_6;
    }

    public void setAr_black_normal_6(Font ar_black_normal_6) {
        this.ar_black_normal_6 = ar_black_normal_6;
    }

    public void setAr_black_bold_7(Font ar_black_bold_7) {
        this.ar_black_bold_7 = ar_black_bold_7;
    }

    public void setAr_black_normal_7(Font ar_black_normal_7) {
        this.ar_black_normal_7 = ar_black_normal_7;
    }

    public void setAr_black_bold_8(Font ar_black_bold_8) {
        this.ar_black_bold_8 = ar_black_bold_8;
    }

    public void setAr_black_normal_8(Font ar_black_normal_8) {
        this.ar_black_normal_8 = ar_black_normal_8;
    }

    public void setAr_black_bold_9(Font ar_black_bold_9) {
        this.ar_black_bold_9 = ar_black_bold_9;
    }

    public void setAr_black_normal_9(Font ar_black_normal_9) {
        this.ar_black_normal_9 = ar_black_normal_9;
    }

    public void setAr_black_bold_10(Font ar_black_bold_10) {
        this.ar_black_bold_10 = ar_black_bold_10;
    }

    public void setAr_black_normal_10(Font ar_black_normal_10) {
        this.ar_black_normal_10 = ar_black_normal_10;
    }

    public void setAr_black_bold_11(Font ar_black_bold_11) {
        this.ar_black_bold_11 = ar_black_bold_11;
    }

    public void setAr_black_normal_11(Font ar_black_normal_11) {
        this.ar_black_normal_11 = ar_black_normal_11;
    }

    public void setAr_black_bold_12(Font ar_black_bold_12) {
        this.ar_black_bold_12 = ar_black_bold_12;
    }

    public void setAr_black_normal_12(Font ar_black_normal_12) {
        this.ar_black_normal_12 = ar_black_normal_12;
    }

    public void setAr_black_bold_13(Font ar_black_bold_13) {
        this.ar_black_bold_13 = ar_black_bold_13;
    }

    public void setAr_black_normal_13(Font ar_black_normal_13) {
        this.ar_black_normal_13 = ar_black_normal_13;
    }

    public void setAr_black_bold_14(Font ar_black_bold_14) {
        this.ar_black_bold_14 = ar_black_bold_14;
    }

    public void setAr_black_normal_14(Font ar_black_normal_14) {
        this.ar_black_normal_14 = ar_black_normal_14;
    }

    public void setAr_red_bold_5(Font ar_red_bold_5) {
        this.ar_red_bold_5 = ar_red_bold_5;
    }

    public void setAr_red_normal_5(Font ar_red_normal_5) {
        this.ar_red_normal_5 = ar_red_normal_5;
    }

    public void setAr_red_bold_6(Font ar_red_bold_6) {
        this.ar_red_bold_6 = ar_red_bold_6;
    }

    public void setAr_red_normal_6(Font ar_red_normal_6) {
        this.ar_red_normal_6 = ar_red_normal_6;
    }

    public void setAr_red_bold_7(Font ar_red_bold_7) {
        this.ar_red_bold_7 = ar_red_bold_7;
    }

    public void setAr_red_normal_7(Font ar_red_normal_7) {
        this.ar_red_normal_7 = ar_red_normal_7;
    }

    public void setAr_red_bold_8(Font ar_red_bold_8) {
        this.ar_red_bold_8 = ar_red_bold_8;
    }

    public void setAr_red_normal_8(Font ar_red_normal_8) {
        this.ar_red_normal_8 = ar_red_normal_8;
    }

    public void setAr_red_bold_9(Font ar_red_bold_9) {
        this.ar_red_bold_9 = ar_red_bold_9;
    }

    public void setAr_red_normal_9(Font ar_red_normal_9) {
        this.ar_red_normal_9 = ar_red_normal_9;
    }

    public void setAr_red_bold_10(Font ar_red_bold_10) {
        this.ar_red_bold_10 = ar_red_bold_10;
    }

    public void setAr_red_normal_10(Font ar_red_normal_10) {
        this.ar_red_normal_10 = ar_red_normal_10;
    }

    public void setAr_red_bold_11(Font ar_red_bold_11) {
        this.ar_red_bold_11 = ar_red_bold_11;
    }

    public void setAr_red_normal_11(Font ar_red_normal_11) {
        this.ar_red_normal_11 = ar_red_normal_11;
    }

    public void setAr_red_bold_12(Font ar_red_bold_12) {
        this.ar_red_bold_12 = ar_red_bold_12;
    }

    public void setAr_red_normal_12(Font ar_red_normal_12) {
        this.ar_red_normal_12 = ar_red_normal_12;
    }

    public void setAr_red_bold_13(Font ar_red_bold_13) {
        this.ar_red_bold_13 = ar_red_bold_13;
    }

    public void setAr_red_normal_13(Font ar_red_normal_13) {
        this.ar_red_normal_13 = ar_red_normal_13;
    }

    public void setAr_red_bold_14(Font ar_red_bold_14) {
        this.ar_red_bold_14 = ar_red_bold_14;
    }

    public void setAr_red_normal_14(Font ar_red_normal_14) {
        this.ar_red_normal_14 = ar_red_normal_14;
    }

    public void setAr_blue_bold_5(Font ar_blue_bold_5) {
        this.ar_blue_bold_5 = ar_blue_bold_5;
    }

    public void setAr_blue_normal_5(Font ar_blue_normal_5) {
        this.ar_blue_normal_5 = ar_blue_normal_5;
    }

    public void setAr_blue_bold_6(Font ar_blue_bold_6) {
        this.ar_blue_bold_6 = ar_blue_bold_6;
    }

    public void setAr_blue_normal_6(Font ar_blue_normal_6) {
        this.ar_blue_normal_6 = ar_blue_normal_6;
    }

    public void setAr_blue_bold_7(Font ar_blue_bold_7) {
        this.ar_blue_bold_7 = ar_blue_bold_7;
    }

    public void setAr_blue_normal_7(Font ar_blue_normal_7) {
        this.ar_blue_normal_7 = ar_blue_normal_7;
    }

    public void setAr_blue_bold_8(Font ar_blue_bold_8) {
        this.ar_blue_bold_8 = ar_blue_bold_8;
    }

    public void setAr_blue_normal_8(Font ar_blue_normal_8) {
        this.ar_blue_normal_8 = ar_blue_normal_8;
    }

    public void setAr_blue_bold_9(Font ar_blue_bold_9) {
        this.ar_blue_bold_9 = ar_blue_bold_9;
    }

    public void setAr_blue_normal_9(Font ar_blue_normal_9) {
        this.ar_blue_normal_9 = ar_blue_normal_9;
    }

    public void setAr_blue_bold_10(Font ar_blue_bold_10) {
        this.ar_blue_bold_10 = ar_blue_bold_10;
    }

    public void setAr_blue_normal_10(Font ar_blue_normal_10) {
        this.ar_blue_normal_10 = ar_blue_normal_10;
    }

    public void setAr_blue_bold_11(Font ar_blue_bold_11) {
        this.ar_blue_bold_11 = ar_blue_bold_11;
    }

    public void setAr_blue_normal_11(Font ar_blue_normal_11) {
        this.ar_blue_normal_11 = ar_blue_normal_11;
    }

    public void setAr_blue_bold_12(Font ar_blue_bold_12) {
        this.ar_blue_bold_12 = ar_blue_bold_12;
    }

    public void setAr_blue_normal_12(Font ar_blue_normal_12) {
        this.ar_blue_normal_12 = ar_blue_normal_12;
    }

    public void setAr_blue_bold_13(Font ar_blue_bold_13) {
        this.ar_blue_bold_13 = ar_blue_bold_13;
    }

    public void setAr_blue_normal_13(Font ar_blue_normal_13) {
        this.ar_blue_normal_13 = ar_blue_normal_13;
    }

    public void setAr_blue_bold_14(Font ar_blue_bold_14) {
        this.ar_blue_bold_14 = ar_blue_bold_14;
    }

    public void setAr_blue_normal_14(Font ar_blue_normal_14) {
        this.ar_blue_normal_14 = ar_blue_normal_14;
    }

    public void setAr_green_bold_5(Font ar_green_bold_5) {
        this.ar_green_bold_5 = ar_green_bold_5;
    }

    public Font getAr_green_bold_5() {
        return ar_green_bold_5;
    }

    public void setAr_green_normal_5(Font ar_green_normal_5) {
        this.ar_green_normal_5 = ar_green_normal_5;
    }

    public Font getAr_green_normal_5() {
        return ar_green_normal_5;
    }

    public void setAr_green_bold_6(Font ar_green_bold_6) {
        this.ar_green_bold_6 = ar_green_bold_6;
    }

    public Font getAr_green_bold_6() {
        return ar_green_bold_6;
    }

    public void setAr_green_normal_6(Font ar_green_normal_6) {
        this.ar_green_normal_6 = ar_green_normal_6;
    }

    public Font getAr_green_normal_6() {
        return ar_green_normal_6;
    }

    public void setAr_green_bold_7(Font ar_green_bold_7) {
        this.ar_green_bold_7 = ar_green_bold_7;
    }

    public Font getAr_green_bold_7() {
        return ar_green_bold_7;
    }

    public void setAr_green_normal_7(Font ar_green_normal_7) {
        this.ar_green_normal_7 = ar_green_normal_7;
    }

    public Font getAr_green_normal_7() {
        return ar_green_normal_7;
    }

    public void setAr_green_bold_8(Font ar_green_bold_8) {
        this.ar_green_bold_8 = ar_green_bold_8;
    }

    public Font getAr_green_bold_8() {
        return ar_green_bold_8;
    }

    public void setAr_green_normal_8(Font ar_green_normal_8) {
        this.ar_green_normal_8 = ar_green_normal_8;
    }

    public Font getAr_green_normal_8() {
        return ar_green_normal_8;
    }

    public void setAr_green_bold_9(Font ar_green_bold_9) {
        this.ar_green_bold_9 = ar_green_bold_9;
    }

    public Font getAr_green_bold_9() {
        return ar_green_bold_9;
    }

    public void setAr_green_normal_9(Font ar_green_normal_9) {
        this.ar_green_normal_9 = ar_green_normal_9;
    }

    public Font getAr_green_normal_9() {
        return ar_green_normal_9;
    }

    public void setAr_green_bold_10(Font ar_green_bold_10) {
        this.ar_green_bold_10 = ar_green_bold_10;
    }

    public Font getAr_green_bold_10() {
        return ar_green_bold_10;
    }

    public void setAr_green_normal_10(Font ar_green_normal_10) {
        this.ar_green_normal_10 = ar_green_normal_10;
    }

    public Font getAr_green_normal_10() {
        return ar_green_normal_10;
    }

    public void setAr_green_bold_11(Font ar_green_bold_11) {
        this.ar_green_bold_11 = ar_green_bold_11;
    }

    public Font getAr_green_bold_11() {
        return ar_green_bold_11;
    }

    public void setAr_green_normal_11(Font ar_green_normal_11) {
        this.ar_green_normal_11 = ar_green_normal_11;
    }

    public Font getAr_green_normal_11() {
        return ar_green_normal_11;
    }

    public void setAr_green_bold_12(Font ar_green_bold_12) {
        this.ar_green_bold_12 = ar_green_bold_12;
    }

    public Font getAr_green_bold_12() {
        return ar_green_bold_12;
    }

    public void setAr_green_normal_12(Font ar_green_normal_12) {
        this.ar_green_normal_12 = ar_green_normal_12;
    }

    public Font getAr_green_normal_12() {
        return ar_green_normal_12;
    }

    public void setAr_green_bold_13(Font ar_green_bold_13) {
        this.ar_green_bold_13 = ar_green_bold_13;
    }

    public Font getAr_green_bold_13() {
        return ar_green_bold_13;
    }

    public void setAr_green_normal_13(Font ar_green_normal_13) {
        this.ar_green_normal_13 = ar_green_normal_13;
    }

    public Font getAr_green_normal_13() {
        return ar_green_normal_13;
    }

    public void setAr_green_bold_14(Font ar_green_bold_14) {
        this.ar_green_bold_14 = ar_green_bold_14;
    }

    public Font getAr_green_bold_14() {
        return ar_green_bold_14;
    }

    public void setAr_green_normal_14(Font ar_green_normal_14) {
        this.ar_green_normal_14 = ar_green_normal_14;
    }

    public Font getAr_green_normal_14() {
        return ar_green_normal_14;
    }

    public void setWatermark_font(Font watermark_font) {
        this.watermark_font = watermark_font;
    }
}
