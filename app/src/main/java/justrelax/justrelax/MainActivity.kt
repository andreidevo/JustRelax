package justrelax.justrelax

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.Typeface
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
import android.text.Html
import android.text.Spannable
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_video.*
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType
import smartdevelop.ir.eram.showcaseviewlib.config.Gravity
import javax.xml.datatype.Duration


class MainActivity : AppCompatActivity() {

    private var mAdapter: RecyclerView.Adapter<*>? = null
    private var exampleList = ArrayList<VideoClass>()
    private var DataStr = ""

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



        "https://www.dropbox.com/s/pt2alqeubs5q1yi/data.json?dl=1"
            .httpGet()
            .responseString { request, response, result ->
                when (result) {
                    is Result.Failure -> {
                        val ex = result.getException()
                    }
                    is Result.Success -> {
                        val data = result.get()
                        DataStr = data
                    }
                }
            }





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




       // ShowcaseView.Builder(this).setTarget(ActionViewTarget(this, ActionViewTarget.Type.HOME)).setContentTitle("OPA").setContentText("HEA").hideOnTouchOutside().build()

        GuideView.Builder(this).setContentText("You can search videos for hashtags").setTargetView(search).setGravity(Gravity.center).setContentTypeFace(
            Typeface.DEFAULT).setGuideListener({
            GuideView.Builder(this)
                .setContentText("You can switch on dark theme")
                .setTargetView(mod)
                .setGravity(Gravity.center)
                .setContentTypeFace(Typeface.SERIF)
                .setGuideListener({
                    GuideView.Builder(this)
                        .setContentText("Hay, that is theme controller")
                        .setTargetView(guide)
                        .setGravity(Gravity.center)
                        .setContentTypeFace(Typeface.MONOSPACE)
                        .setTitleTypeFace(Typeface.DEFAULT_BOLD).setDismissType(DismissType.outside).build().show()
                })
                .setTitleTypeFace(Typeface.DEFAULT).setDismissType(DismissType.outside).build().show()
        })
.setTitleTypeFace(Typeface.DEFAULT).setDismissType(DismissType.outside).build().show()



        search.setOnClickListener {
            search.visibility = View.INVISIBLE
            mod.setImageDrawable(getResources().getDrawable(R.drawable.ic_cross_remove_sign))
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
        exampleList.add(VideoClass(0, "шар", "https://www.dropbox.com/s/gys5q7yc868ko59/52586642_324829134816014_3179773492790820864_n.mp4?dl=1","#ball","omg",190))
        exampleList.add(VideoClass(22, "13", "https://www.dropbox.com/s/imrtewj6oza023e/20486629_1796810533964174_6865957040330637312_n.mp4?dl=1","#top","omg",180))
        exampleList.add(VideoClass(22, "13", "https://www.dropbox.com/s/mn3b9h4pyaemtqc/21440162_128947414415069_8758301806833958912_n.mp4?dl=1","#ball","omg",200))
        exampleList.add(VideoClass(22, "13", "https://www.dropbox.com/s/3442nl4xoddb24x/24362181_514925875547912_2794886509730201600_n.mp4?dl=1","#ball","omg",220))
        exampleList.add(VideoClass(22, "13", "https://www.dropbox.com/s/gys5q7yc868ko59/52586642_324829134816014_3179773492790820864_n.mp4?dl=1","#ball","omg",210))
        exampleList.add(VideoClass(22, "13", "https://www.dropbox.com/s/gys5q7yc868ko59/52586642_324829134816014_3179773492790820864_n.mp4?dl=1","#ball","omg",220))
        exampleList.add(VideoClass(22, "13", "https://www.dropbox.com/s/gys5q7yc868ko59/52586642_324829134816014_3179773492790820864_n.mp4?dl=1","#ball","omg",200))
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
