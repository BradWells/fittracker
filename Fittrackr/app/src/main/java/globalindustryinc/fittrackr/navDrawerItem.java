package globalindustryinc.fittrackr;

/**
 * Created by jccline on 10/5/2014.
 */
public class NavDrawerItem {
    private String mTitle;
    private int mIcon;

    public NavDrawerItem(){}

    public NavDrawerItem(String title, int icon){
        this.mTitle = title;
        this.mIcon = icon;
    }

    public String getTitle(){
        return this.mTitle;
    }

    public int getIcon(){
        return this.mIcon;
    }

    public void setTitle(String title){
        this.mTitle = title;
    }

    public void setIcon(int icon){
        this.mIcon = icon;
    }
}