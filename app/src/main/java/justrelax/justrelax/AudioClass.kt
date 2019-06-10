package justrelax.justrelax

public class AudioClass(id : Int, name : String, author : String, href : String)
{
    private var m_name : String = ""
    private var m_author : String = ""
    private var m_href : String = ""
    private var m_id : Int = 0

    init {
        m_name =  name
        m_author = author
        m_href = href
        m_id = id
    }
    public fun getId(): Int { return m_id}
    public fun getName(): String { return m_name}
    public fun getAuthor(): String { return m_author}
    public fun getHref(): String { return m_href}
}
