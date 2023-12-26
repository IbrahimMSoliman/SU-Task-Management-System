package tms.charts;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.util.List;
import tms.charts.constants.ChartType;
import tms.charts.constants.HorizontalAlign;
import tms.charts.constants.LegendPosition;
import tms.charts.model.DataLabels;
import tms.charts.model.Fill;
import tms.charts.model.Legend;
import tms.charts.model.Series;
import tms.charts.model.Stroke;
import tms.charts.model.Title;
import tms.charts.model.Xaxis;

/**
 *
 * @author hima
 */
public class LineAreaChart
    {
    @JsonIgnore
    private String chart_id;

    private List<Series> series;
    private Chart chart;
    private Title title;
    private DataLabels dataLabels;
    private Stroke stroke;
    private Legend legend;
    private Fill fill;
    private Xaxis xaxis;

    public LineAreaChart(String chart_id, ChartType chartType, String chart_title, List<String> xaxisList, List<Series> series, boolean stacked, int width, int height)
        {
        this.chart_id = chart_id;
        this.series = series;
        //title
        title = new Title();
        title.setText(chart_title);
        //
        dataLabels = new DataLabels();
        dataLabels.setEnabled(false);
        //Chart
        chart = new Chart();
        chart.setType(chartType).setHeight(height).setStacked(stacked);
        chart.setWidth(width);
        //stroke
        stroke = new Stroke();
        stroke.setCurve("smooth");

        //fill
        fill = new Fill();
        fill.setType("gradient");
        fill.getGradient().setOpacityFrom(0.6).setOpacityTo(0.8);
        //legend
        legend = new Legend();
        legend.setPosition(LegendPosition.top).setHorizontalAlign(HorizontalAlign.center).setOffsetX(40);
        //Xaxis
        xaxis = new Xaxis();
        xaxis.setCategoriesAsArrayString(xaxisList);
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

    public List<Series> getSeries()
        {
        return series;
        }

    public void setSeries(List<Series> series)
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

    public Title getTitle()
        {
        return title;
        }

    public void setTitle(Title title)
        {
        this.title = title;
        }

    public DataLabels getDataLabels()
        {
        return dataLabels;
        }

    public void setDataLabels(DataLabels dataLabels)
        {
        this.dataLabels = dataLabels;
        }

    public Stroke getStroke()
        {
        return stroke;
        }

    public void setStroke(Stroke stroke)
        {
        this.stroke = stroke;
        }

    public Legend getLegend()
        {
        return legend;
        }

    public void setLegend(Legend legend)
        {
        this.legend = legend;
        }

    public Fill getFill()
        {
        return fill;
        }

    public void setFill(Fill fill)
        {
        this.fill = fill;
        }

    public Xaxis getXaxis()
        {
        return xaxis;
        }

    public void setXaxis(Xaxis xaxis)
        {
        this.xaxis = xaxis;
        }

    public String getChart_id()
        {
        return chart_id;
        }

    public void setChart_id(String chart_id)
        {
        this.chart_id = chart_id;
        }

    }
