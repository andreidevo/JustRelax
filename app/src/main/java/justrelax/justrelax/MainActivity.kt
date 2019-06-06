package justrelax.justrelax

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.ArrayAdapter
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var exampleList = ArrayList<VideoClass>()
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
        var recyclerView: RecyclerView = findViewById(R.id.recycle)
        recyclerView.setNestedScrollingEnabled(false)
        var recyclerView1: RecyclerView = findViewById(R.id.recycle1)
        recyclerView1.setNestedScrollingEnabled(false)
        SetAdapter(recyclerView)
        SetAdapter(recyclerView1)
    }

    private var mRecyclerView: RecyclerView? = null
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    fun SetAdapter(recyclerView: RecyclerView) {

        /* Set names of paragraphs */

        exampleList.add(VideoClass(0, "шар", "//https","#ball1","omg"))
        exampleList.add(VideoClass(22, "13", "//https","#ball2","omg"))
        exampleList.add(VideoClass(24, "13", "//https","#ball3","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball4","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball5","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball6","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball7","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball8","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball9","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball10#fuck you","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball11","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball12","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball13","omg"))
        recyclerView.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)

        mAdapter = VideoAdapterKot(exampleList, findViewById(R.id.con1))
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = mAdapter
        search("fuck you")
    }

    fun search( searchTag : String ) : ArrayList<VideoClass> {
        var searchList = ArrayList<VideoClass>()
        for (vid in exampleList) {
            for (tag in vid.tag.split("#"))
                if (tag == searchTag){
                    searchList.add(vid)
                    Log.d("#", vid.tag)
                }
        }
        return searchList
    }
}