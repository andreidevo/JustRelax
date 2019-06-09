package justrelax.justrelax

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.effect.Effect
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.StaggeredGridLayoutManager
import android.text.TextWatcher
import android.view.View
import android.widget.*
import org.w3c.dom.Text
import java.util.ArrayList
import android.text.Editable
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import javax.xml.datatype.Duration


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

        recyclerView.overScrollMode = (View.OVER_SCROLL_NEVER)

        var x = isOnline(this)
        var key  = false
        EditText.getBackground().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_IN);
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
            mod.setImageDrawable(getResources().getDrawable(R.drawable.ic_sort_down))
            key = true
            texthead.visibility = View.INVISIBLE
            CardSearch.visibility = View.VISIBLE
            //exoPlayer
            var anim : Animation = AnimationUtils.loadAnimation(this,R.anim.bounce)
            CardSearch.animation = anim
            anim = AnimationUtils.loadAnimation(this,R.anim.rotate)
            mod.animation = anim
            anim = AnimationUtils.loadAnimation(this,R.anim.fadeout)
            search.animation = anim
            texthead.animation = anim

        }
        mod.setOnClickListener {
            if(key)
            {
                search.visibility = View.VISIBLE
                mod.setImageDrawable(getResources().getDrawable(R.drawable.ic_moon))
                key = false
                texthead.visibility = View.VISIBLE
                CardSearch.visibility = View.INVISIBLE
                var anim : Animation = AnimationUtils.loadAnimation(this,R.anim.righttoleft)
                CardSearch.animation = anim

                anim = AnimationUtils.loadAnimation(this,R.anim.fadein)
                search.animation = anim
                mod.animation = anim
                //
                texthead.animation = anim

            }



        }
        var animation:Animation =  AnimationUtils.loadAnimation(this, R.anim.anim1)

        SetAdapter(recyclerView)
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
    private var mLayoutManager: RecyclerView.LayoutManager? = null
    fun SetAdapterByList(recyclerView: RecyclerView, lister : ArrayList<VideoClass>) {
        var Grid = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = Grid
        mLayoutManager = LinearLayoutManager(this)
        mAdapter = VideoAdapterKot(lister, findViewById(R.id.con1))
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
        var Grid = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView.layoutManager = Grid
        mLayoutManager = LinearLayoutManager(this)
        mAdapter = VideoAdapterKot(exampleList, findViewById(R.id.con1))
        recyclerView.adapter = mAdapter
    }

    fun isOnline(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = cm.activeNetworkInfo
        return if (netInfo != null && netInfo.isConnectedOrConnecting) {
            true
        } else false
    }
}
