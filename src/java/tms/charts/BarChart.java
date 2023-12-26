package tms.charts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.ArrayList;
import java.util.List;
import tms.charts.constants.ChartType;
import tms.charts.constants.HorizontalAlign;
import tms.charts.constants.LegendPosition;
import tms.charts.constants.StackType;
import tms.charts.model.DataLabels;
import tms.charts.model.Fill;
import tms.charts.model.Legend;
import tms.charts.model.Series;
import tms.charts.model.Stroke;
import tms.charts.model.Title;
import tms.charts.model.Toolbar;
import tms.charts.model.Xaxis;
import tms.charts.model.Yaxis;
import tms.charts.model.common.Color;

/**
 *
 * @author hima
 */
public class BarChart
    {
    private ArrayList<Series> series;
    private Chart chart;
    private Title title;
    private PlotOptions plotOptions;
    private DataLabels dataLabels;
    private Stroke stroke;
    private Xaxis xaxis;
    private List<Yaxis> yaxis;
    private Legend legend;
    private Fill fill;

    //Variables
    @JsonIgnore
    private String chart_id;
    @JsonIgnore
    private String chart_title;
    @JsonIgnore
    private List<String> xaxisList;
    @JsonIgnore
    private List<Series> serieses_list;
    @JsonIgnore
    private String yTitle;
    @JsonIgnore
    private int width;
    @JsonIgnore
    private int height;
    @JsonIgnore
    private boolean stacked;
    @JsonIgnore
    private StackType stackType;
    @JsonIgnore
    private int borderRadius;

    public BarChart(String chart_id, String chart_title, List<String> xaxisList, List<Series> serieses_list, String yTitle, int width, int height, boolean stacked, StackType stackType, int borderRadius)
        {
        this.chart_id = chart_id;
        this.chart_title = chart_title;
        this.xaxisList = xaxisList;
        this.serieses_list = serieses_list;
        this.yTitle = yTitle;
        this.width = width;
        this.height = height;
        this.stacked = stacked;
        this.stackType = stackType;
        this.borderRadius = borderRadius;

        //Strok
        stroke = new Stroke();
        stroke.setWidth(1);//.getColors().add(new Color("#fff"));

        //title
        title = new Title();
        title.setText(chart_title);
        //add Series
        series = new ArrayList<>();
        for(Series ser : serieses_list)
            series.add(ser);

        //chart
        chart = new Chart();
        chart.setWidth(width);
        chart.setType(ChartType.bar).setHeight(height).setStacked(stacked).setStackType(stackType);
        chart.setToolbar(new Toolbar());
        chart.getToolbar().setShow(true);

        //plotoptions
        plotOptions = new PlotOptions();
        plotOptions.getBar().setHorizontal(false).setBorderRadius(4);

        //Legend
        legend = new Legend();
        legend.setPosition(LegendPosition.top).setHorizontalAlign(HorizontalAlign.center).setOffsetX(40);

        //Xaxis
        xaxis = new Xaxis();
        xaxis.setCategoriesAsArrayString(xaxisList);

        //DataLabels
        dataLabels = new DataLabels();
        dataLabels.setEnabled(true);

        //YAXIS
        if(!yTitle.trim().equals(""))
            {
            yaxis = new ArrayList<>();
            Yaxis yaxis1 = new Yaxis();
            yaxis1.getTitle().setText(yTitle);
            yaxis.add(yaxis1);
            }
        //fill
        fill = new Fill();
        fill.setOpacity(1);
        }

    public String toScript()
        {
        String script = "";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(Include.NON_NULL);//.setVisibility(PropertyAccessor.FIELD, Visibility.ANY);
        ObjectWriter ow = objectMapper.writer().withDefaultPrettyPrinter();
        try
            {
            script = "var options = " + ow.writeValueAsString(this).replaceAll("PERCENT100", "100%") + ";  var chart = new ApexCharts(document.querySelector(\"#" + chart_id + "\"), options); chart.render();";
            }
        catch(JsonProcessingException ex)
            {
            System.out.println("Error: " + ex.getMessage());
            }

        return script;
        }

    public ArrayList<Series> getSeries()
        {
        if(series == null)
            series = new ArrayList<>();
        return series;
        }

    public BarChart setSeries(ArrayList<Series> series)
        {
        this.series = series;
        return this;
        }

    public Chart getChart()
        {
        if(chart == null)
            chart = new Chart();
        return chart;
        }

    public BarChart setChart(Chart chart)
        {
        this.chart = chart;
        return this;
        }

    public PlotOptions getPlotOptions()
        {
        if(plotOptions == null)
            plotOptions = new PlotOptions();
        return plotOptions;
        }

    public BarChart setPlotOptions(PlotOptions plotOptions)
        {
        this.plotOptions = plotOptions;
        return this;
        }

    public DataLabels getDataLabels()
        {
        if(dataLabels == null)
            dataLabels = new DataLabels();
        return dataLabels;
        }

    public BarChart setDataLabels(DataLabels dataLabels)
        {
        this.dataLabels = dataLabels;
        return this;
        }

    public Stroke getStroke()
        {
        if(stroke == null)
            stroke = new Stroke();
        return stroke;
        }

    public BarChart setStroke(Stroke stroke)
        {
        this.stroke = stroke;
        return this;
        }

    public Xaxis getXaxis()
        {
        if(xaxis == null)
            xaxis = new Xaxis();
        return xaxis;
        }

    public BarChart setXaxis(Xaxis xaxis)
        {
        this.xaxis = xaxis;
        return this;
        }

    public List<Yaxis> getYaxis()
        {
        if(yaxis == null)
            yaxis = new ArrayList<>();
        return yaxis;
        }

    public BarChart setYaxis(List<Yaxis> yaxis)
        {
        this.yaxis = yaxis;
        return this;
        }

    public Fill getFill()
        {
        if(fill == null)
            fill = new Fill();
        return fill;
        }

    public BarChart setFill(Fill fill)
        {
        this.fill = fill;
        return this;
        }

    public Legend getLegend()
        {
        if(legend == null)
            legend = new Legend();
        return legend;
        }

    public BarChart setLegend(Legend legend)
        {
        this.legend = legend;
        return this;
        }

    public Title getTitle()
        {
        if(title == null)
            title = new Title();
        return title;
        }

    public BarChart setTitle(Title title)
        {
        this.title = title;
        return this;
        }

    public String getChart_id()
        {
        return chart_id;
        }

    public void setChart_id(String chart_id)
        {
        this.chart_id = chart_id;
        }

    public String getChart_title()
        {
        return chart_title;
        }

    public void setChart_title(String chart_title)
        {
        this.chart_title = chart_title;
        }

    public List<String> getXaxisList()
        {
        return xaxisList;
        }

    public void setXaxisList(List<String> xaxisList)
        {
        this.xaxisList = xaxisList;
        }

    public List<Series> getSerieses_list()
        {
        return serieses_list;
        }

    public void setSerieses_list(List<Series> serieses_list)
        {
        this.serieses_list = serieses_list;
        }

    public String getyTitle()
        {
        return yTitle;
        }

    public void setyTitle(String yTitle)
        {
        this.yTitle = yTitle;
        }

    public int getWidth()
        {
        return width;
        }

    public void setWidth(int width)
        {
        this.width = width;
        }

    public int getHeight()
        {
        return height;
        }

    public void setHeight(int height)
        {
        this.height = height;
        }

    public boolean isStacked()
        {
        return stacked;
        }

    public void setStacked(boolean stacked)
        {
        this.stacked = stacked;
        }

    public StackType getStackType()
        {
        return stackType;
        }

    public void setStackType(StackType stackType)
        {
        this.stackType = stackType;
        }

    public int getBorderRadius()
        {
        return borderRadius;
        }

    public void setBorderRadius(int borderRadius)
        {
        this.borderRadius = borderRadius;
        }

    }
