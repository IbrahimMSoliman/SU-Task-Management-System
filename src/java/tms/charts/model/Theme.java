package tms.charts.model;

/**
 *
 * @author hima
 */
public class Theme
    {
    private Monochrome monochrome;

    public class Monochrome
        {
        private boolean enabled;

        public boolean isEnabled()
            {
            return enabled;
            }

        public void setEnabled(boolean enabled)
            {
            this.enabled = enabled;
            }
        }

    public Monochrome getMonochrome()
        {
        if(monochrome == null)
            monochrome = new Monochrome();
        return monochrome;
        }

    public Theme setMonochrome(Monochrome monochrome)
        {
        this.monochrome = monochrome;
        return this;
        }

    }
