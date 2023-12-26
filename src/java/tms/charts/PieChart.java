package tms.charts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.ArrayList;
import java.util.List;
import tms.charts.constants.ChartType;
import tms.charts.constants.LegendPosition;
import tms.charts.model.Fill;
import tms.charts.model.Responsive;
import tms.charts.model.Theme;
import tms.charts.model.Title;
import tms.charts.model.Toolbar;

/**
 *
 * @author hima
 */
public class PieChart
    {
    @JsonIgnore
    private String chart_id;

    private List<Number> series;
    private Chart chart;
    private List<String> labels;
    private List<Responsive> responsive;
    private Title title;
    private Fill fill;
    private Theme theme;

    public PieChart(String chart_id, ChartType chartType, String chart_title, List<String> labels, List<Number> series, int width, int height, boolean gradient, boolean monochrome)
        {
        this.chart_id = chart_id;
        this.labels = labels;
        this.series = series;
        //title
        title = new Title();
        title.setText(chart_title);
        //Chart
        chart = new Chart();
        chart.setWidth(width);
        chart.setType(chartType).setHeight(height).setWidth(width);
        chart.setToolbar(new Toolbar());
        chart.getToolbar().setShow(true);
        if(gradient)
            {
            fill = new Fill();
            fill.setType("gradient");
            fill.setType("gradient");
            fill.getGradient().setOpacityFrom(1).setOpacityTo(1);
            }
        if(monochrome)
            {
            theme = new Theme();
            theme.getMonochrome().setEnabled(true);
            }
        //responsive
        responsive = new ArrayList<>();
        Responsive res = new Responsive();

        res.getOptions().getChart().setWidth(200);
        res.getOptions().getLegend().setPosition(LegendPosition.bottom);
        res.setBreakpoint(480);
        responsive.add(res);

        }

    public String toScript()
        {
        String script = "";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);//.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        try
            {
            script = "var options = " + ow.writeValueAsString(this) + ";  var chart = new ApexCharts(document.querySelector(\"#" + chart_id + "\"), options); chart.render();";
            }
        catch(JsonProcessingException ex)
            {
            System.out.println("Error: " + ex.getMessage());
            }

        return script;
        }

    public String getChart_id()
        {
        return chart_id;
        }

    public void setChart_id(String chart_id)
        {
        this.chart_id = chart_id;
        }

    public List<Number> getSeries()
        {
        return series;
        }

    public void setSeries(List<Number> series)
        {
        this.series = series;
        }

    public Chart getChart()
        {
        return chart;
        }

    public void setChart(Chart chart)
        {
        this.chart = chart;
        }

    public List<String> getLabels()
        {
        return labels;
        }

    public void setLabels(List<String> labels)
        {
        this.labels = labels;
        }

    public List<Responsive> getResponsive()
        {
        return responsive;
        }

    public void setResponsive(List<Responsive> responsive)
        {
        this.responsive = responsive;
        }

    public Title getTitle()
        {
        return title;
        }

    public void setTitle(Title title)
        {
        this.title = title;
        }

    public Fill getFill()
        {
        return fill;
        }

    public void setFill(Fill fill)
        {
        this.fill = fill;
        }

    public Theme getTheme()
        {
        return theme;
        }

    public void setTheme(Theme theme)
        {
        this.theme = theme;
        }

    }
