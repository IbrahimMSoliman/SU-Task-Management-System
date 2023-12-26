package tms.TAGS;

/**
 *
 * @author hima
 */
public class Rating_result_model {
    
    private String header_text;
    private String results_format;
    private int max_starts_count;
    private double rating_average;
    private int points;
    private int target_students_cnt;
    private int target_user_cnt;
    private int participant_students_cnt;
    private int participant_user_cnt; 
    
    
    //rating options answers
    private int rating_count1;
    private double rating_percentage1;
    private int rating_count2;
    private double rating_percentage2;
    private int rating_count3;
    private double rating_percentage3;
    private int rating_count4;
    private double rating_percentage4;
    private int rating_count5;
    private double rating_percentage5;
    private int rating_count6;
    private double rating_percentage6;
    private int rating_count7;
    private double rating_percentage7;
    private int rating_count8;
    private double rating_percentage8;
    private int rating_count9;
    private double rating_percentage9;
    private int rating_count10;
    private double rating_percentage10; 
    private double total_percentage;
    
    
    private String rate_title1;
    private String rate_title2;
    private String rate_title3;
    private String rate_title4;
    private String rate_title5;
    private String rate_title6;
    private String rate_title7;
    private String rate_title8;
    private String rate_title9;
    private String rate_title10;

    public Rating_result_model(String header_text, String results_format, int max_starts_count, double rating_average, int points, int target_students_cnt, int target_user_cnt, int participant_students_cnt, int participant_user_cnt, int rating_count1, double rating_percentage1, int rating_count2, double rating_percentage2, int rating_count3, double rating_percentage3, int rating_count4, double rating_percentage4, int rating_count5, double rating_percentage5, int rating_count6, double rating_percentage6, int rating_count7, double rating_percentage7, int rating_count8, double rating_percentage8, int rating_count9, double rating_percentage9, int rating_count10, double rating_percentage10, double total_percentage, String rate_title1, String rate_title2, String rate_title3, String rate_title4, String rate_title5, String rate_title6, String rate_title7, String rate_title8, String rate_title9, String rate_title10) {
        this.header_text = header_text;
        this.results_format = results_format;
        this.max_starts_count = max_starts_count;
        this.rating_average = rating_average;
        this.points = points;
        this.target_students_cnt = target_students_cnt;
        this.target_user_cnt = target_user_cnt;
        this.participant_students_cnt = participant_students_cnt;
        this.participant_user_cnt = participant_user_cnt;
        this.rating_count1 = rating_count1;
        this.rating_percentage1 = rating_percentage1;
        this.rating_count2 = rating_count2;
        this.rating_percentage2 = rating_percentage2;
        this.rating_count3 = rating_count3;
        this.rating_percentage3 = rating_percentage3;
        this.rating_count4 = rating_count4;
        this.rating_percentage4 = rating_percentage4;
        this.rating_count5 = rating_count5;
        this.rating_percentage5 = rating_percentage5;
        this.rating_count6 = rating_count6;
        this.rating_percentage6 = rating_percentage6;
        this.rating_count7 = rating_count7;
        this.rating_percentage7 = rating_percentage7;
        this.rating_count8 = rating_count8;
        this.rating_percentage8 = rating_percentage8;
        this.rating_count9 = rating_count9;
        this.rating_percentage9 = rating_percentage9;
        this.rating_count10 = rating_count10;
        this.rating_percentage10 = rating_percentage10;
        this.total_percentage = total_percentage;
        this.rate_title1 = rate_title1;
        this.rate_title2 = rate_title2;
        this.rate_title3 = rate_title3;
        this.rate_title4 = rate_title4;
        this.rate_title5 = rate_title5;
        this.rate_title6 = rate_title6;
        this.rate_title7 = rate_title7;
        this.rate_title8 = rate_title8;
        this.rate_title9 = rate_title9;
        this.rate_title10 = rate_title10;
    }
    
    

    public String getRate_title1() {
        return rate_title1;
    }

    public void setRate_title1(String rate_title1) {
        this.rate_title1 = rate_title1;
    }

    public String getRate_title2() {
        return rate_title2;
    }

    public void setRate_title2(String rate_title2) {
        this.rate_title2 = rate_title2;
    }

    public String getRate_title3() {
        return rate_title3;
    }

    public void setRate_title3(String rate_title3) {
        this.rate_title3 = rate_title3;
    }

    public String getRate_title4() {
        return rate_title4;
    }

    public void setRate_title4(String rate_title4) {
        this.rate_title4 = rate_title4;
    }

    public String getRate_title5() {
        return rate_title5;
    }

    public void setRate_title5(String rate_title5) {
        this.rate_title5 = rate_title5;
    }

    public String getRate_title6() {
        return rate_title6;
    }

    public void setRate_title6(String rate_title6) {
        this.rate_title6 = rate_title6;
    }

    public String getRate_title7() {
        return rate_title7;
    }

    public void setRate_title7(String rate_title7) {
        this.rate_title7 = rate_title7;
    }

    public String getRate_title8() {
        return rate_title8;
    }

    public void setRate_title8(String rate_title8) {
        this.rate_title8 = rate_title8;
    }

    public String getRate_title9() {
        return rate_title9;
    }

    public void setRate_title9(String rate_title9) {
        this.rate_title9 = rate_title9;
    }

    public String getRate_title10() {
        return rate_title10;
    }

    public void setRate_title10(String rate_title10) {
        this.rate_title10 = rate_title10;
    }
    
     

    
    
    
    public String getHeader_text() {
        return header_text;
    }

    public void setHeader_text(String header_text) {
        this.header_text = header_text;
    }

    public String getResults_format() {
        return results_format;
    }

    public void setResults_format(String results_format) {
        this.results_format = results_format;
    }

    public int getMax_starts_count() {
        return max_starts_count;
    }

    public void setMax_starts_count(int max_starts_count) {
        this.max_starts_count = max_starts_count;
    }

     
    
    
    

    public double getRating_average() {
        return rating_average;
    }

    public void setRating_average(double rating_average) {
        this.rating_average = rating_average;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getTarget_students_cnt() {
        return target_students_cnt;
    }

    public void setTarget_students_cnt(int target_students_cnt) {
        this.target_students_cnt = target_students_cnt;
    }

    public int getTarget_user_cnt() {
        return target_user_cnt;
    }

    public void setTarget_user_cnt(int target_user_cnt) {
        this.target_user_cnt = target_user_cnt;
    }

    public int getParticipant_students_cnt() {
        return participant_students_cnt;
    }

    public void setParticipant_students_cnt(int participant_students_cnt) {
        this.participant_students_cnt = participant_students_cnt;
    }

    public int getParticipant_user_cnt() {
        return participant_user_cnt;
    }

    public void setParticipant_user_cnt(int participant_user_cnt) {
        this.participant_user_cnt = participant_user_cnt;
    }

    public int getRating_count1() {
        return rating_count1;
    }

    public void setRating_count1(int rating_count1) {
        this.rating_count1 = rating_count1;
    }

    public double getRating_percentage1() {
        return rating_percentage1;
    }

    public void setRating_percentage1(double rating_percentage1) {
        this.rating_percentage1 = rating_percentage1;
    }

    public int getRating_count2() {
        return rating_count2;
    }

    public void setRating_count2(int rating_count2) {
        this.rating_count2 = rating_count2;
    }

    public double getRating_percentage2() {
        return rating_percentage2;
    }

    public void setRating_percentage2(double rating_percentage2) {
        this.rating_percentage2 = rating_percentage2;
    }

    public int getRating_count3() {
        return rating_count3;
    }

    public void setRating_count3(int rating_count3) {
        this.rating_count3 = rating_count3;
    }

    public double getRating_percentage3() {
        return rating_percentage3;
    }

    public void setRating_percentage3(double rating_percentage3) {
        this.rating_percentage3 = rating_percentage3;
    }

    public int getRating_count4() {
        return rating_count4;
    }

    public void setRating_count4(int rating_count4) {
        this.rating_count4 = rating_count4;
    }

    public double getRating_percentage4() {
        return rating_percentage4;
    }

    public void setRating_percentage4(double rating_percentage4) {
        this.rating_percentage4 = rating_percentage4;
    }

    public int getRating_count5() {
        return rating_count5;
    }

    public void setRating_count5(int rating_count5) {
        this.rating_count5 = rating_count5;
    }

    public double getRating_percentage5() {
        return rating_percentage5;
    }

    public void setRating_percentage5(double rating_percentage5) {
        this.rating_percentage5 = rating_percentage5;
    }

    public int getRating_count6() {
        return rating_count6;
    }

    public void setRating_count6(int rating_count6) {
        this.rating_count6 = rating_count6;
    }

    public double getRating_percentage6() {
        return rating_percentage6;
    }

    public void setRating_percentage6(double rating_percentage6) {
        this.rating_percentage6 = rating_percentage6;
    }

    public int getRating_count7() {
        return rating_count7;
    }

    public void setRating_count7(int rating_count7) {
        this.rating_count7 = rating_count7;
    }

    public double getRating_percentage7() {
        return rating_percentage7;
    }

    public void setRating_percentage7(double rating_percentage7) {
        this.rating_percentage7 = rating_percentage7;
    }

    public int getRating_count8() {
        return rating_count8;
    }

    public void setRating_count8(int rating_count8) {
        this.rating_count8 = rating_count8;
    }

    public double getRating_percentage8() {
        return rating_percentage8;
    }

    public void setRating_percentage8(double rating_percentage8) {
        this.rating_percentage8 = rating_percentage8;
    }

    public int getRating_count9() {
        return rating_count9;
    }

    public void setRating_count9(int rating_count9) {
        this.rating_count9 = rating_count9;
    }

    public double getRating_percentage9() {
        return rating_percentage9;
    }

    public void setRating_percentage9(double rating_percentage9) {
        this.rating_percentage9 = rating_percentage9;
    }

    public int getRating_count10() {
        return rating_count10;
    }

    public void setRating_count10(int rating_count10) {
        this.rating_count10 = rating_count10;
    }

    public double getRating_percentage10() {
        return rating_percentage10;
    }

    public void setRating_percentage10(double rating_percentage10) {
        this.rating_percentage10 = rating_percentage10;
    }

    public double getTotal_percentage() {
        return total_percentage;
    }

    public void setTotal_percentage(double total_percentage) {
        this.total_percentage = total_percentage;
    }
    
    
    
    
}
