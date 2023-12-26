package tms.charts.model;

/**
 *
 * @author hima
 */
public class Responsive
    {
    public int breakpoint;
    public Options options;

    public int getBreakpoint()
        {
        return breakpoint;
        }

    public void setBreakpoint(int breakpoint)
        {
        this.breakpoint = breakpoint;
        }

    public Options getOptions()
        {
        if(options == null)
            options = new Options();
        return options;
        }

    public Responsive setOptions(Options options)
        {
        this.options = options;
        return this;
        }

    }
