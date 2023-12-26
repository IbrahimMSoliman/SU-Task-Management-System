package tms.TAGS;

import java.util.ArrayList;
import java.util.List;
import tms.charts.BarChart;
import tms.charts.LineAreaChart;
import tms.charts.PieChart;
import tms.charts.constants.ChartType;
import tms.charts.constants.StackType;
import tms.charts.model.Series;

/**
 *
 * @author hima
 */
public class Charts_model
    {
    private List<chart_class> charts_list = new ArrayList<>();

    public void add_new_bar_chart(String chart_id, ChartType chartType, String chart_title, List<String> xaxisList, List<Series> serieses_list, String yTitle, int width, int height, boolean stacked, StackType stackType, int borderRadius, boolean show_options)
        {
        charts_list.add(new chart_class(chart_id, chartType, chart_title, xaxisList, serieses_list, yTitle, width, height, stacked, stackType, borderRadius, show_options));
        }

    public void add_new_pie_chart(String chart_id, ChartType chartType, String chart_title, List<String> labels, List<Number> numbers_list, int width, int height, boolean gradient, boolean monochrome)
        {
        charts_list.add(new chart_class(chart_id, chartType, chart_title, labels, numbers_list, width, height, gradient, monochrome));
        }

    public void add_new_line_chart(String chart_id, ChartType chartType, String chart_title, List<String> xaxisList, List<Series> serieses_list, boolean stacked, int width, int height, boolean show_options)
        {
        charts_list.add(new chart_class(chart_id, chartType, chart_title, xaxisList, serieses_list, stacked, width, height, show_options));
        }

    public class chart_class
        {
        private String chart_id;
        private int width;
        private int height;
        private int borderRadius;
        private String chart_title;
        private String script;
        private boolean stacked;
        private StackType stackType;
        private ChartType chartType;
        private List<String> xaxisList;
        private List<Series> serieses_list;
        private String yTitle;
        private boolean show_options = false;
        private boolean isAreaChart = false;
        //Start Pie Variables

        //for Bar Chart
        public chart_class(String chart_id, ChartType chartType, String chart_title, List<String> xaxisList, List<Series> serieses_list, String yTitle, int width, int height, boolean stacked, StackType stackType, int borderRadius, boolean show_options)
            {
            this.chart_id = chart_id;
            this.chart_title = chart_title;
            this.width = width;
            this.height = height;
            this.borderRadius = borderRadius;
            this.stacked = stacked;
            this.stackType = stackType;
            this.chartType = chartType;
            this.xaxisList = xaxisList;
            this.yTitle = yTitle;
            this.serieses_list = serieses_list;
            this.show_options = show_options;
            //Bar
            if(chartType.equals(ChartType.bar))
                {
                BarChart barChart = new BarChart(chart_id, chart_title, xaxisList, serieses_list, yTitle, width, height, stacked, stackType, borderRadius);
                script = barChart.toScript();
                }

            }

        //for Pie
        public chart_class(String chart_id, ChartType chartType, String chart_title, List<String> labels, List<Number> numbers_list, int width, int height, boolean gradient, boolean monochrome)
            {
            this.chart_id = chart_id;
            this.chart_title = chart_title;
            this.chartType = chartType;
            this.width = width;
            this.height = height;
            PieChart pieChart = new PieChart(chart_id, chartType, chart_title, labels, numbers_list, width, height, gradient, monochrome);
            script = pieChart.toScript();
            }

        //for Line Chart
        public chart_class(String chart_id, ChartType chartType, String chart_title, List<String> xaxisList, List<Series> serieses_list, boolean stacked, int width, int height, boolean show_options)
            {
            this.chart_id = chart_id;
            this.chart_title = chart_title;
            this.chartType = chartType;
            this.width = width;
            this.height = height;
            this.stacked = stacked;
            this.xaxisList = xaxisList;
            this.serieses_list = serieses_list;
            this.show_options = show_options;
            isAreaChart = chartType == ChartType.area;
            LineAreaChart lineChart = new LineAreaChart(chart_id, chartType, chart_title, xaxisList, serieses_list, stacked, width, height);
            script = lineChart.toScript();

            }

        public void reload_chart()
            {
            if(chartType.equals(ChartType.bar))
                {
                BarChart barChart = new BarChart(chart_id, chart_title, xaxisList, serieses_list, yTitle, width, height, stacked, stackType, borderRadius);
                script = barChart.toScript();
                }
            else if(chartType.equals(ChartType.line) || chartType.equals(ChartType.area))
                {
                if(isAreaChart)
                    chartType = ChartType.area;
                else
                    chartType = ChartType.line;
                LineAreaChart lineChart = new LineAreaChart(chart_id, chartType, chart_title, xaxisList, serieses_list, stacked, width, height);
                script = lineChart.toScript();
                }
            }

        public String getChart_title()
            {
            return chart_title;
            }

        public String getChart_id()
            {
            return chart_id;
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

        public int getHeight()
            {
            return height;
            }

        public void setHeight(int height)
            {
            this.height = height;
            }

        public int getBorderRadius()
            {
            return borderRadius;
            }

        public void setBorderRadius(int borderRadius)
            {
            this.borderRadius = borderRadius;
            }

        public ChartType getChartType()
            {
            return chartType;
            }

        public void setChartType(ChartType chartType)
            {
            this.chartType = chartType;
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

        public String getScript()
            {
            return script;
            }

        public void setChart_title(String chart_title)
            {
            this.chart_title = chart_title;
            }

        public int getWidth()
            {
            return width;
            }

        public void setWidth(int width)
            {
            this.width = width;
            }

        public void setChart_id(String chart_id)
            {
            this.chart_id = chart_id;
            }

        public void setScript(String script)
            {
            this.script = script;
            }

        public boolean isShow_options()
            {
            return show_options;
            }

        public void setShow_options(boolean show_options)
            {
            this.show_options = show_options;
            }

        public String getyTitle()
            {
            return yTitle;
            }

        public void setyTitle(String yTitle)
            {
            this.yTitle = yTitle;
            }

        public boolean isIsAreaChart()
            {
            return isAreaChart;
            }

        public void setIsAreaChart(boolean isAreaChart)
            {
            this.isAreaChart = isAreaChart;
            }

        }

    public List<chart_class> getCharts_list()
        {
        return charts_list;
        }

    public void setCharts_list(List<chart_class> charts_list)
        {
        this.charts_list = charts_list;
        }

    }
