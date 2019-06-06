package justrelax.justrelax

import android.content.res.Resources
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.ArrayAdapter
import android.widget.ImageView
import java.util.ArrayList

// question: Main screen
// question:
class MainActivity : AppCompatActivity() {

    private var mAdapter: RecyclerView.Adapter<*>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
           // val bundle1 = Bundle()
            //bundle1.putString("1","fragment 1")
           // var manager = supportFragmentManager
            //var transic = manager.beginTransaction()
            //var newFragment = FragVideo()
           // transic.add(R.id.con1, newFragment).commit()

        }
        var mod : ImageView = findViewById(R.id.mod)
        var search : ImageView = findViewById(R.id.search)
        var key: Boolean = false
        search.setOnClickListener {
            search.visibility = View.INVISIBLE
            mod.setImageDrawable(getResources().getDrawable(R.drawable.ic_magnifying_glass))
            key = true

        }
        mod.setOnClickListener {
            if( key == true)
            {
                search.visibility = View.VISIBLE
                mod.setImageDrawable(getResources().getDrawable(R.drawable.moonblack))
                key = false
            }
            // switch mod

        }
        var recyclerView: RecyclerView = findViewById(R.id.recycle)
        recyclerView.setNestedScrollingEnabled(false)
        var recyclerView1: RecyclerView = findViewById(R.id.recycle1)
        recyclerView1.setNestedScrollingEnabled(false)
        SetAdapter(recyclerView)
        SetAdapterClone(recyclerView1)
    }

    private var mRecyclerView: RecyclerView? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    fun SetAdapter(recyclerView: RecyclerView) {

        /* Set names of paragraphs */
        var exampleList = ArrayList<VideoClass>()
        exampleList.add(VideoClass(0, "шар", "//https","#ball","omg",190))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",180))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",200))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",220))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",210))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",220))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",200))


        recyclerView.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)

        mAdapter = VideoAdapterKot(exampleList, findViewById(R.id.con1))
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = mAdapter
    }
    fun SetAdapterClone(recyclerView: RecyclerView) {

        /* Set names of paragraphs */
        var exampleList = ArrayList<VideoClass>()
        exampleList.add(VideoClass(0, "шар", "//https","#ball","omg",200))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",210))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",210))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",200))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",210))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",230))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg",210))


        recyclerView.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)

        mAdapter = VideoAdapterKot(exampleList, findViewById(R.id.con1))
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = mAdapter
    }
}
