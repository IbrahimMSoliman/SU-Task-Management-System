package tms.application;

/**
 *
 * @author hima
 */
public class BreadCrumbs
    {

    private String title;
    private String icon;
    private String link;

    public BreadCrumbs(String title, String icon, String link)
        {
        this.title = title;
        this.icon = icon;
        this.link = link;
        }

    public String getTitle()
        {
        return title;
        }

    public void setTitle(String title)
        {
        this.title = title;
        }

    public String getIcon()
        {
        return icon;
        }

    public void setIcon(String icon)
        {
        this.icon = icon;
        }

    public String getLink()
        {
        return link;
        }

    public void setLink(String link)
        {
        this.link = link;
        }

    }
