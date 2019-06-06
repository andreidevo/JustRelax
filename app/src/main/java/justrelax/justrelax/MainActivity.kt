package justrelax.justrelax

import android.app.PendingIntent.getActivity
import android.content.res.Resources
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.effect.Effect
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.TextWatcher
import android.view.View
import android.widget.*
import org.w3c.dom.Text
import java.util.ArrayList
import android.text.Editable
import android.util.Log


// question: Main screen
// question:
class MainActivity : AppCompatActivity() {

    private var mAdapter: RecyclerView.Adapter<*>? = null

    private var exampleList = ArrayList<VideoClass>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var mod : ImageView = findViewById(R.id.mod)
        var search : ImageView = findViewById(R.id.search)
        var texthead : TextView = findViewById(R.id.textHead)
        var CardSearch : CardView = findViewById(R.id.CardSearch)
        var EditText : EditText = findViewById(R.id.EditText)
        var recyclerView: RecyclerView = findViewById(R.id.recycle)
        //recyclerView.setNestedScrollingEnabled(false)
        var recyclerView1: RecyclerView = findViewById(R.id.recycle1)

        EditText.getBackground().setColorFilter(Color.parseColor("#f1efef"), PorterDuff.Mode.SRC_IN);
        var key: Boolean = false

        EditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                // TODO Auto-generated method stub
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // TODO Auto-generated method stub
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                    //METOD SEARCH
                   var List: ArrayList<VideoClass>  =  searcher(EditText.text.toString(), exampleList)
                   SetAdapterByList(recyclerView, List)
            }
        })

        search.setOnClickListener {
            search.visibility = View.INVISIBLE
            mod.setImageDrawable(getResources().getDrawable(R.drawable.ic_cancel_music))
            key = true
            texthead.visibility = View.INVISIBLE
            CardSearch.visibility = View.VISIBLE
            //exoPlayer
        }
        mod.setOnClickListener {
            if( key == true)
            {
                search.visibility = View.VISIBLE
                mod.setImageDrawable(getResources().getDrawable(R.drawable.moonblack))
                key = false
                texthead.visibility = View.VISIBLE
                CardSearch.visibility = View.INVISIBLE


            }
            // switch mod

        }//suggered grid layout manager grif layout

        //recyclerView1.setNestedScrollingEnabled(false)
        SetAdapter(recyclerView)
        SetAdapterClone(recyclerView1)
    }
    fun searcher( searchTag : String, exampleList: ArrayList<VideoClass> ) : ArrayList<VideoClass> {
        var searchList = ArrayList<VideoClass>()
        for (vid in exampleList) {

            for (tag in vid.tag.split("#"))

                if (tag == searchTag)
                    searchList.add(vid)
        }
        return searchList
    }
    private var mRecyclerView: RecyclerView? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    fun SetAdapterByList(recyclerView: RecyclerView, lister : ArrayList<VideoClass>) {




        mLayoutManager = LinearLayoutManager(this)

        mAdapter = VideoAdapterKot(lister, findViewById(R.id.con1))
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = mAdapter
    }
    fun SetAdapter(recyclerView: RecyclerView) {

        /* Set names of paragraphs */

        exampleList.add(VideoClass(0, "шар", "//https","#ball","omg",190))
        exampleList.add(VideoClass(22, "13", "//https","#top","omg",180))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",200))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",220))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",210))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",220))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",200))


        mLayoutManager = LinearLayoutManager(this)

        mAdapter = VideoAdapterKot(exampleList, findViewById(R.id.con1))
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = mAdapter
    }
    fun SetAdapterClone(recyclerView: RecyclerView) {

        /* Set names of paragraphs */
        exampleList.add(VideoClass(0, "шар", "//https","#ball","omg",200))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",210))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",210))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",200))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",210))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",230))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",210))


        mLayoutManager = LinearLayoutManager(this)

        mAdapter = VideoAdapterKot(exampleList, findViewById(R.id.con1))
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = mAdapter
    }
}
