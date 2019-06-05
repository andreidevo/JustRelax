package justrelax.justrelax

import android.content.Intent
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
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList


class MainActivity : AppCompatActivity() {

    private var mAdapter: RecyclerView.Adapter<*>? = null

    fun actVideo(view : View){
        val inte = Intent(this, VideoActivity::class.java)
        inte.putExtra("Key", R.raw.smalltest)
        startActivity(inte)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        image1.setOnClickListener(::actVideo)
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
        var exampleList = ArrayList<VideoClass>()
        exampleList.add(VideoClass(0, "шар", "//https","#ball","omg"))
        exampleList.add(VideoClass(22, "13", "//https","#ball","omg"))
        exampleList.add(VideoClass(24, "13", "//https","#ball","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball","omg"))
        exampleList.add(VideoClass(25, "13", "//https","#ball","omg"))

        recyclerView.setHasFixedSize(true)
        mLayoutManager = LinearLayoutManager(this)

        mAdapter = VideoAdapter(exampleList)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.adapter = mAdapter
    }
}
