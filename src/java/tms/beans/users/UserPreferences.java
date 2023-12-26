package tms.beans.users;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import tms.db.connect;
import java.sql.ResultSet;
import java.sql.SQLException;

@Named("userPreferences")
@SessionScoped
public class UserPreferences implements Serializable
    {
    private String menuMode = "layout-horizontal";
    private String darkMode = "light";
    private String layoutPrimaryColor = "amber";
    private String componentTheme = "blue";
    private String topbarTheme = "colored";
    private String menuTheme = "dim";
    private String profileMode = "popup";
    private String inputStyle = "outlined";
    private boolean groupedMenu = true;
    private boolean lightLogo = true;
    private final List<ComponentTheme> componentThemes = new ArrayList<>();
    private final List<LayoutPrimaryColor> layoutPrimaryColors = new ArrayList<>();

    private boolean show_breadcrumbs = true;
    private String user_id;
    private String user_type;
    private boolean pref_exist = false;

    @PostConstruct
    public void init()
        {
        componentThemes.add(new ComponentTheme("Blue", "blue", "#2c84d8"));
        componentThemes.add(new ComponentTheme("Wisteria", "wisteria", "#A864AE"));
        componentThemes.add(new ComponentTheme("Cyan", "cyan", "#25A4D4"));
        componentThemes.add(new ComponentTheme("Amber", "amber", "#DB8519"));
        componentThemes.add(new ComponentTheme("Pink", "pink", "#F5487F"));
        componentThemes.add(new ComponentTheme("Orange", "orange", "#CB623A"));
        componentThemes.add(new ComponentTheme("Victoria", "victoria", "#594791"));
        componentThemes.add(new ComponentTheme("Chateau Green", "chateau-green", "#3C9462"));
        componentThemes.add(new ComponentTheme("Paradiso", "paradiso", "#3B9195"));
        componentThemes.add(new ComponentTheme("Chambray", "chambray", "#3161BA"));
        componentThemes.add(new ComponentTheme("Tapestry", "tapestry", "#A2527F"));

        layoutPrimaryColors.add(new LayoutPrimaryColor("Blue", "blue", "#2c84d8"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Wisteria", "wisteria", "#A053A7"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Cyan", "cyan", "#25A4D4"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Amber", "amber", "#DB8519"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Pink", "pink", "#F5487F"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Orange", "orange", "#CB623A"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Victoria", "victoria", "#705BB1"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Chateau Green", "chateau-green", "#3C9462"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Paradiso", "paradiso", "#3B9195"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Chambray", "chambray", "#3161BA"));
        layoutPrimaryColors.add(new LayoutPrimaryColor("Tapestry", "tapestry", "#924470"));
        }

    public void update_user_preferences(connect conn, String user_type, String user_id)
        {
        this.user_id = user_id;
        this.user_type = user_type;
        load_user_preferences_from_db(conn);
        try
            {
            if(pref_exist == false)
                {
                conn.delete("delete from user_preferences where user_id=" + user_id);
                if(conn.insert("insert into user_preferences (USER_ID,USER_TYPE,theme,menuMode,layout,version,MENULAYOUT,darkMode,inputStyle,layoutPrimaryColor,topbarTheme,menuTheme,groupedMenu,profileMode,componentTheme,show_breadcrumbs) select " + user_id + ",'" + user_type.toUpperCase() + "',nvl(theme,'cyan'),nvl(layout,'layout'),'1',nvl(MENULAYOUT,'menu'),nvl(menuMode,'layout-static layout-static-active') ,nvl(darkMode,'light') ,nvl(inputStyle,'outlined') ,nvl(layoutPrimaryColor,'cyan') ,nvl(topbarTheme,'colored') ,nvl(menuTheme,'dim') ,nvl(groupedMenu,0) ,nvl(profileMode,'popup') ,nvl(componentTheme,'cyan') ,nvl(show_breadcrumbs,1) from user_preferences dd where dd.user_id=0") == 0)
                    {
                    conn.insert("Insert into USER_PREFERENCES (USER_ID,USER_TYPE,THEME,LAYOUT,MENUCLASS,PROFILEMODE,MENULAYOUT,VERSION,SHOW_BREADCRUMBS,MENUMODE,DARKMODE,LAYOUTPRIMARYCOLOR,COMPONENTTHEME,TOPBARTHEME,MENUTHEME,INPUTSTYLE,GROUPEDMENU) values (0,'ADMIN','THEME','LAYOUT','MENUCLASS','popup','MENULAYOUT','VERSION',1,'layout-horizontal','light','amber','blue','colored','dim','outlined',null)");
                    conn.insert("Insert into USER_PREFERENCES (USER_ID,USER_TYPE,THEME,LAYOUT,MENUCLASS,PROFILEMODE,MENULAYOUT,VERSION,SHOW_BREADCRUMBS,MENUMODE,DARKMODE,LAYOUTPRIMARYCOLOR,COMPONENTTHEME,TOPBARTHEME,MENUTHEME,INPUTSTYLE,GROUPEDMENU) values (" + user_id + ",'" + user_type.toUpperCase() + "','THEME','LAYOUT','MENUCLASS','popup','MENULAYOUT','VERSION',1,'layout-horizontal','light','amber','blue','colored','dim','outlined',null)");
                    }
                load_user_preferences_from_db(conn);
                }
            }
        catch(SQLException sqlEx)
            {
            System.out.println("Error (update_user_preferences): " + sqlEx.getMessage());
            }
        }

    private void load_user_preferences_from_db(connect conn)
        {
        pref_exist = false;
        try
            {
            ResultSet rs = conn.query("select nvl(menuMode,'layout-static layout-static-active') as menuMode,nvl(darkMode,'light') as darkMode,nvl(inputStyle,'outlined') as inputStyle,nvl(layoutPrimaryColor,'cyan') as layoutPrimaryColor,nvl(topbarTheme,'colored') as topbarTheme,nvl(menuTheme,'dim') as menuTheme,nvl(groupedMenu,0) as groupedMenu,nvl(profileMode,'popup') as profileMode,nvl(componentTheme,'cyan') as componentTheme ,nvl(show_breadcrumbs,1) as show_breadcrumbs from user_preferences where user_id=" + user_id);
            while(rs.next())
                {
                pref_exist = true;
                topbarTheme = rs.getString("topbarTheme");
                lightLogo = !this.topbarTheme.equals("light");
                menuTheme = rs.getString("menuTheme");
                componentTheme = rs.getString("componentTheme");
                show_breadcrumbs = rs.getInt("show_breadcrumbs") == 1;
                menuMode = rs.getString("menuMode");
                darkMode = rs.getString("darkMode");
                inputStyle = rs.getString("inputStyle");
                layoutPrimaryColor = rs.getString("layoutPrimaryColor");
                componentTheme = layoutPrimaryColor;
                groupedMenu = rs.getInt("groupedMenu") == 1;
                profileMode = rs.getString("profileMode");
                }
            rs.close();
            }
        catch(Exception ee)
            {
            System.out.println("Error (load_user_preferences_from_db): " + ee.getMessage());
            }

        }

    public void update_show_hide_breadcrumbs()
        {
        connect conn = new connect();
        try
            {
            conn.update("update user_preferences set show_breadcrumbs=" + (show_breadcrumbs ? "1" : "0") + " where upper(user_type)='" + user_type.toUpperCase() + "' and user_id=" + user_id);
            }
        catch(Exception ee)
            {
            }

        conn.close();

        }

    public String getDarkMode()
        {
        return darkMode;
        }

    public boolean isLightLogo()
        {
        return lightLogo;
        }

    public void setDarkMode(String darkMode)
        {
        this.darkMode = darkMode;
        //this.menuTheme = darkMode;
        //this.topbarTheme = darkMode;
        //this.lightLogo = !this.topbarTheme.equals("light");
        connect conn = new connect();
        try
            {
            conn.update("update USER_PREFERENCES set DARKMODE=? where user_id=? and upper(user_type)=?", new Object[]
                {
                darkMode, user_id, user_type.toUpperCase()
                });
            }
        catch(Exception ee)
            {
            System.out.println("Error(setDarkMode): " + ee.getMessage());
            }
        conn.close();

        }

    public String getLayout()
        {
        return "layout-" + this.layoutPrimaryColor + '-' + this.darkMode;
        }

    public String getTheme()
        {
        return this.componentTheme + '-' + this.darkMode;
        }

    public String getLayoutPrimaryColor()
        {
        return layoutPrimaryColor;
        }

    public void setLayoutPrimaryColor(String layoutPrimaryColor)
        {
        this.layoutPrimaryColor = layoutPrimaryColor;
        this.componentTheme = layoutPrimaryColor;

        connect conn = new connect();
        try
            {
            conn.update("update USER_PREFERENCES set layoutPrimaryColor=? where user_id=? and upper(user_type)=?", new Object[]
                {
                layoutPrimaryColor, user_id, user_type.toUpperCase()
                });
            }
        catch(Exception ee)
            {
            System.out.println("Error(setLayoutPrimaryColor): " + ee.getMessage());
            }
        conn.close();

        }

    public String getComponentTheme()
        {
        return componentTheme;
        }

    public void setComponentTheme(String componentTheme)
        {
        this.componentTheme = componentTheme;
        connect conn = new connect();
        try
            {
            conn.update("update USER_PREFERENCES set componentTheme=? where user_id=? and upper(user_type)=?", new Object[]
                {
                componentTheme, user_id, user_type.toUpperCase()
                });
            }
        catch(Exception ee)
            {
            System.out.println("Error(setComponentTheme): " + ee.getMessage());
            }
        conn.close();
        }

    public String getMenuTheme()
        {
        return menuTheme;
        }

    public void setMenuTheme(String menuTheme)
        {
        this.menuTheme = menuTheme;
        connect conn = new connect();
        try
            {
            conn.update("update USER_PREFERENCES set menuTheme=? where user_id=? and upper(user_type)=?", new Object[]
                {
                menuTheme, user_id, user_type.toUpperCase()
                });
            }
        catch(Exception ee)
            {
            System.out.println("Error(setMenuTheme): " + ee.getMessage());
            }
        conn.close();
        }

    public String getTopbarTheme()
        {
        return topbarTheme;
        }

    public void setTopbarTheme(String topbarTheme)
        {
        this.topbarTheme = topbarTheme;
        this.lightLogo = !this.topbarTheme.equals("light");
        connect conn = new connect();
        try
            {
            conn.update("update USER_PREFERENCES set topbarTheme=? where user_id=? and upper(user_type)=?", new Object[]
                {
                topbarTheme, user_id, user_type.toUpperCase()
                });
            }
        catch(Exception ee)
            {
            System.out.println("Error(setTopbarTheme): " + ee.getMessage());
            }
        conn.close();
        }

    public String getMenuMode()
        {
        return this.menuMode;
        }

    public void setMenuMode(String menuMode)
        {
        this.menuMode = menuMode;
        connect conn = new connect();
        if(menuMode.trim().equalsIgnoreCase("layout-horizontal"))
            profileMode = "popup";

        try
            {
            conn.update("update USER_PREFERENCES set menumode=?,profilemode=? where user_id=? and upper(user_type)=?", new Object[]
                {
                menuMode, profileMode, user_id, user_type.toUpperCase()
                });
            }
        catch(Exception ee)
            {
            System.out.println("Error(setMenuMode): " + ee.getMessage());
            }
        conn.close();
        }

    public boolean isGroupedMenu()
        {
        return this.groupedMenu;
        }

    public void setGroupedMenu(boolean value)
        {
        this.groupedMenu = value;
        connect conn = new connect();
        try
            {
            conn.update("update USER_PREFERENCES set groupedMenu=? where user_id=? and upper(user_type)=?", new Object[]
                {
                value ? "1" : "0", user_id, user_type.toUpperCase()
                });
            }
        catch(Exception ee)
            {
            System.out.println("Error(setGroupedMenu): " + ee.getMessage());
            }
        conn.close();
        }

    public String getProfileMode()
        {
        return this.profileMode;
        }

    public void setProfileMode(String profileMode)
        {
        this.profileMode = profileMode;
        connect conn = new connect();
        try
            {
            conn.update("update USER_PREFERENCES set profileMode=? where user_id=? and upper(user_type)=?", new Object[]
                {
                profileMode, user_id, user_type.toUpperCase()
                });
            }
        catch(Exception ee)
            {
            System.out.println("Error(setProfileMode): " + ee.getMessage());
            }
        conn.close();
        }

    public String getInputStyle()
        {
        return inputStyle;
        }

    public void setInputStyle(String inputStyle)
        {
        this.inputStyle = inputStyle;
        connect conn = new connect();
        try
            {
            conn.update("update USER_PREFERENCES set inputStyle=? where user_id=? and upper(user_type)=?", new Object[]
                {
                inputStyle, user_id, user_type.toUpperCase()
                });
            }
        catch(Exception ee)
            {
            System.out.println("Error(setInputStyle): " + ee.getMessage());
            }
        conn.close();
        }

    public String getInputStyleClass()
        {
        return this.inputStyle.equals("filled") ? "ui-input-filled" : "";
        }

    public List<ComponentTheme> getComponentThemes()
        {
        return componentThemes;
        }

    public List<LayoutPrimaryColor> getLayoutPrimaryColors()
        {
        return layoutPrimaryColors;
        }

    public boolean isShow_breadcrumbs()
        {
        return show_breadcrumbs;
        }

    public String getUser_id()
        {
        return user_id;
        }

    public String getUser_type()
        {
        return user_type;
        }

    public void setShow_breadcrumbs(boolean show_breadcrumbs)
        {
        this.show_breadcrumbs = show_breadcrumbs;
        }

    public class ComponentTheme
        {
        String name;
        String file;
        String color;

        public ComponentTheme(String name, String file, String color)
            {
            this.name = name;
            this.file = file;
            this.color = color;
            }

        public String getName()
            {
            return this.name;
            }

        public String getFile()
            {
            return this.file;
            }

        public String getColor()
            {
            return this.color;
            }
        }

    public class LayoutPrimaryColor
        {
        String name;
        String file;
        String color;

        public LayoutPrimaryColor(String name, String file, String color)
            {
            this.name = name;
            this.file = file;
            this.color = color;
            }

        public String getName()
            {
            return this.name;
            }

        public String getFile()
            {
            return this.file;
            }

        public String getColor()
            {
            return this.color;
            }
        }

    }
