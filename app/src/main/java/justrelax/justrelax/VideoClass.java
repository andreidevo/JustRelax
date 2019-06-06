package justrelax.justrelax;

import android.view.View;

public class VideoClass {

    private String name;
    private String href;
    private String hashTag;
    private String description;
    private int id;
    private int height;
    public View itemView;
    VideoClass(int id, String name, String href, String hash, String desc, int height)
    {
        this.id = id;
        this.height = height;
        this.name = name;
        this.hashTag = hash;
        this.href = href;
        this.description = desc;

    }
    public String getName()
    {
        return  this.name;
    }
    public int getHeight(){return  this.height;}
    public String getHref()
    {
        return this.href;
    }
    public String getTag()
    {
        return this.hashTag;
    }
    public String getDescription()
    {
        return this.description;
    }
    public int getId()
    {
        return this.id;
    }

}
