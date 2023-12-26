package tms.charts.model;

import tms.charts.Chart;

/**
 *
 * @author hima
 */
public class Options
    {
    public Chart chart;
    public Legend legend;

    public Chart getChart()
        {
        if(chart == null)
            chart = new Chart();
        return chart;
        }

    public Options setChart(Chart chart)
        {
        this.chart = chart;
        return this;
        }

    public Legend getLegend()
        {
        if(legend == null)
            legend = new Legend();
        return legend;
        }

    public Options setLegend(Legend legend)
        {
        this.legend = legend;
        return this;
        }

    }
