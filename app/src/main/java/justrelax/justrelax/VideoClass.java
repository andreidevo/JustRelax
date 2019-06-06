package justrelax.justrelax;

import android.view.View;

public class VideoClass {

    private String name;
    private String href;
    private String hashTag;
    private String description;
    private int id;

    public View itemView;
    VideoClass(int id, String name, String href, String hash, String desc)
    {
        this.id = id;
        this.name = name;
        this.hashTag = hash;
        this.href = href;
        this.description = desc;

    }
    public String getName()
    {
        return  this.name;
    }
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
