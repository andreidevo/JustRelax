package justrelax.justrelax;

public class VideoClass {

    private String name;
    private String href;
    private String hashTag;
    private String description;

    VideoClass(int id, String name, String href, String hash, String desc)
    {
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


}
