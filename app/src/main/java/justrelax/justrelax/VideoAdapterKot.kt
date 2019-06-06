package justrelax.justrelax

import android.animation.Animator
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_video.*
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionManager
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.widget.*
import justrelax.justrelax.R.id.root

import java.util.ArrayList



//Download, music, animation

class VideoAdapterKot(private val mExampleList: ArrayList<VideoClass>, layout: ConstraintLayout) :
    RecyclerView.Adapter<VideoAdapterKot.ExampleViewHolder>() {
    var mLay: ConstraintLayout? = null
    init {
        mLay = layout
    }
    class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var CardView: android.support.v7.widget.CardView
        var image: ImageView
        var context: Context
        var itemViews: View


        init {
            CardView = itemView.findViewById(R.id.cardview)
            image = itemView.findViewById(R.id.imageview)
            context = itemView.context
            itemViews = itemView
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.fragment_video, parent, false)

        return ExampleViewHolder(v)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = mExampleList[position]

        //holder.textView.setText(currentItem.getName());
        //holder.textView.setId(currentItem.getId());
//        holder.CardView.setOnClickListener {
//            //String mess = (String) v.getContentDescription();
//           // val intent = Intent(holder.context, video::class.java)
//            // intent.putExtra("id",currentItem.getId());
//            //intent.putExtra("mess", mess);
//            //commit
//            //holder.context.startActivity(intent)
//
//        }
        //redact CardView and image
        var params : ViewGroup.LayoutParams = holder.CardView.layoutParams
        params.height = (holder.CardView.resources.displayMetrics.density * currentItem.height).toInt() // типа 20dp
        holder.CardView.layoutParams = params
        holder.image.setOnClickListener {
            //String mess = (String) v.getContentDescription();
            //  val intent = Intent(holder.context, video::class.java)
            // intent.putExtra("id",currentItem.getId());
            //intent.putExtra("mess", mess);
            //commit
            //holder.context.startActivity(intent);


        var inflater: LayoutInflater  = holder.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflater.inflate(R.layout.popupfragment, null)

        val popupWindow = PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
          //  popupWindow.animationStyle = R.animator.alpha - not work

        /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.elevation = 10.0F
        }
            //

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        val slideIn = Slide()
        slideIn.slideEdge = Gravity.TOP
        popupWindow.enterTransition = slideIn

        val slideOut = Slide()
        slideOut.slideEdge = Gravity.RIGHT
        popupWindow.exitTransition = slideOut

        }
           //
            */
        //popupWindow.setOnDismissListener {
        //Toast.makeText(holder.context,"Popup closed", Toast.LENGTH_SHORT).show()
        //}


        //Тень.
        var shadow: ConstraintLayout = view.findViewById(R.id.contento)
        var headphones: ImageView = view.findViewById(R.id.pic1)
        var download: ImageView = view.findViewById(R.id.pic2)
        var VideoDescription : TextView = view.findViewById(R.id.Description)
        VideoDescription.setText(currentItem.description)
        //headphones.setOnClickListener() // загрузка видео
        //download.setOnClickListener() // выбор музыки
        shadow.setBackgroundColor(Color.parseColor("#DD000000"))
        //листенер на тень.
        shadow.setOnClickListener {
            popupWindow.dismiss()
        }

       // val animation :Animation = AlphaAnimation(0.0f,1.0f)
       //   animation.duration = 100
       // animation.startOffset = 20
       // animation.repeatCount = Animation.INFINITE
       // animation.repeatMode = Animation.REVERSE
        /*Video PLayer */
        val VideoClasser = VideoClasser(view, R.raw.smalltest.toString(), holder.context)
        VideoClasser.CreateVideo()

        TransitionManager.beginDelayedTransition(mLay)
        popupWindow.showAtLocation(mLay, Gravity.CENTER, 0, 0)
        }
    }

    override fun getItemCount(): Int {
        return mExampleList.size
    }
}

class VideoClasser(view: View, scr : String, base: Context) : ContextWrapper(base){//mb error


    private var scres : String = ""
    init {
         scres = scr
    }
    fun getSrc() : String{
        val Src : String = scres
        return Src
    }
    val Video : VideoView = view.findViewById(R.id.video)
    val PauseButton: Button = view.findViewById(R.id.PauseButton)

    fun StopPlay(view : View) {
        if (Video.isPlaying) {
            Video.pause()
            PauseButton.visibility = View.VISIBLE
        }
        else {
            Video.start()
            PauseButton.visibility = View.INVISIBLE
        }
    }
     fun CreateVideo()  {
        val Src = scres
        Log.d("myVideo", Src)
        val scr : Uri = Uri.parse( "android.resource://" + getPackageName() + "/" + Src)
        Video.setOnClickListener(::StopPlay)
        PauseButton.setOnClickListener(::StopPlay)
        Video.setVideoURI(scr)
        Video.start()
        Video.isPlaying
    }
}
