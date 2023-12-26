package tms.TAGS;

import jakarta.faces.model.SelectItem;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author isoliman
 */
public class TimeRangePickerModel
    {
    private int interval;
    private String min_time;
    private String max_time;
    private String start_time;
    private String end_time;
    private List<SelectItem> start_time_list = new ArrayList<>();
    private List<SelectItem> end_time_list = new ArrayList<>();

    public TimeRangePickerModel(int interval, String min_time, String max_time)
        {
        this.interval = interval;
        this.min_time = min_time;
        this.max_time = max_time;
        //create list
        create_times_list();
        if(start_time_list == null || start_time_list.isEmpty())
            {
            this.min_time = "";
            this.max_time = "";
            create_times_list();
            }
        }

    public void clear()
        {
        start_time = "";
        end_time = "";
        }

    private void create_times_list()
        {
        boolean start_fill_list = false;
        start_time_list.clear();
        String[] ap =
            {
            "AM", "PM"
            }; // AM-PM
        String timeFormat;
        boolean breakloop = false;
        for(int h = 0; h < 24; h++)
            {
            for(int m = 0; m < 60;)
                {
                if(min_time.trim().equals("") || String.format("%02d:%02d %s", h, m, "AM").toUpperCase().trim().equalsIgnoreCase(min_time) || String.format("%02d:%02d %s", h, m, "PM").toUpperCase().trim().equalsIgnoreCase(min_time))
                    start_fill_list = true;
                if(start_fill_list)
                    {
                    if(h < 12)
                        timeFormat = String.format("%02d:%02d %s", h, m, "AM");
                    else if(h == 12)
                        timeFormat = String.format("%02d:%02d %s", 12, m, "PM");
                    else
                        timeFormat = String.format("%02d:%02d %s", h - 12, m, "PM");

                    if(breakloop == false)
                        start_time_list.add(new SelectItem(timeFormat, timeFormat));
                    if(!max_time.trim().equals("") && max_time.equalsIgnoreCase(timeFormat))
                        breakloop = true;

                    if(breakloop)
                        break;

                    }
                if(breakloop)
                    break;
                m = m + interval;
                }
            if(breakloop)
                break;
            }
        }

    public void on_change()
        {
        end_time_list.clear();
        if(start_time != null && !start_time.trim().equals(""))
            {
            int index = start_time_list.indexOf(start_time_list.stream().filter(i -> i.getLabel().equalsIgnoreCase(start_time)).findFirst().get());
            if(index >= 0 && index < start_time_list.size())
                {
                ++index;
                for(int i = index; i < start_time_list.size(); i++)
                    end_time_list.add(start_time_list.get(i));
                }
            }
        }

    public String getStart_time()
        {
        return start_time;
        }

    public void setStart_time(String start_time)
        {
        this.start_time = start_time.toUpperCase();
        on_change();
        }

    public String getEnd_time()
        {
        return end_time;
        }

    public void setEnd_time(String end_time)
        {
        this.end_time = end_time.toUpperCase();
        }

    public List<SelectItem> getStart_time_list()
        {
        return start_time_list;
        }

    public void setStart_time_list(List<SelectItem> start_time_list)
        {
        this.start_time_list = start_time_list;
        }

    public List<SelectItem> getEnd_time_list()
        {
        return end_time_list;
        }

    public void setEnd_time_list(List<SelectItem> end_time_list)
        {
        this.end_time_list = end_time_list;
        }

    public int getInterval()
        {
        return interval;
        }

    public void setInterval(int interval)
        {
        this.interval = interval;
        }

    public String getMin_time()
        {
        return min_time;
        }

    public void setMin_time(String min_time)
        {
        this.min_time = min_time;
        }

    public String getMax_time()
        {
        return max_time;
        }

    public void setMax_time(String max_time)
        {
        this.max_time = max_time;
        }

    }
