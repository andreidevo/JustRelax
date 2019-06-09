package justrelax.justrelax

import android.animation.Animator
import android.animation.AnimatorInflater
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
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.RecyclerView
import android.transition.Fade
import android.transition.Slide
import android.transition.TransitionManager
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.animation.DecelerateInterpolator
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.*
import justrelax.justrelax.R.id.root

import java.util.ArrayList

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
        var params : ViewGroup.LayoutParams = holder.CardView.layoutParams

        /*Растяжение карточек на какую-то высоту*/
        params.height = (holder.CardView.resources.displayMetrics.density * currentItem.height).toInt() // типа 20dp
        holder.CardView.layoutParams = params
        /*Нажатие на карточку*/
        holder.image.setOnClickListener {

            /*Взятие контекста*/
            var inflater: LayoutInflater  = holder.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            /*Создание вью*/
            var view = inflater.inflate(R.layout.popupfragment, null)
            /*Определение попапа*/
            val popupWindow = PopupWindow(view, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
            /*действия при нажатии на тень - закрытие попапа*/
            var shadow: ConstraintLayout = view.findViewById(R.id.contento)
            shadow.setOnClickListener {
                popupWindow.dismiss()
            }
            /*нажатие на наушник - открытие нижнего меню - музыки*/
            var headphones: ImageView = view.findViewById(R.id.pic1)
            /*Нажатие на иконку загрузки*/
            //var download: ImageView = view.findViewById(R.id.pic2)
            /*Описание видео*/
            var VideoDescription : TextView = view.findViewById(R.id.Description)
            VideoDescription.setText(currentItem.description)

            /*нажатие на иконку нашуников*/
            headphones.setOnClickListener{
                /*определение лэйаута*/
                var some : android.support.design.widget.CoordinatorLayout = view.findViewById(R.id.someCoordianal)
                /*определение меню*/
                var llBottomSheet: android.support.v7.widget.LinearLayoutCompat?? =  view.findViewById(R.id.bottom2) as? android.support.v7.widget.LinearLayoutCompat
                /*меню выдвигается*/
                var bottom  = BottomSheetBehavior.from(llBottomSheet)
                bottom.setState(BottomSheetBehavior.STATE_EXPANDED)
        }

//        var  webView  = view.findViewById(R.id.myweb) as WebView
//            webView.setWebViewClient( WebViewClient());
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
//        webView.getSettings().setPluginState(WebSettings.PluginState.ON);
//        webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
//        webView.setWebChromeClient( WebChromeClient());
//        webView.loadUrl("https://www.dropbox.com/s/gys5q7yc868ko59/52586642_324829134816014_3179773492790820864_n.mp4?dl=1")
//        /*Video PLayer */
        val VideoClasser = VideoClasser(view, "asdasd", holder.context)
        VideoClasser.CreateVideo()
        TransitionManager.beginDelayedTransition(mLay)
        /*Создание попапа по центру лэйаута*/
        popupWindow.showAtLocation(mLay, Gravity.CENTER, 0, 0)

        }
    }

    override fun getItemCount(): Int {
        return mExampleList.size
    }
}

class VideoClasser(view: View, scr : String, base: Context) : ContextWrapper(base){//mb error
    /*пока что не нужный конструктор*/
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
     /*Основная часть*/
     fun CreateVideo()  {
        val Src = scres
        Log.d("myVideo", Src)
        val scr : Uri = Uri.parse("https://www.dropbox.com/s/gys5q7yc868ko59/52586642_324829134816014_3179773492790820864_n.mp4?dl=1")
        Video.setOnClickListener(::StopPlay)
        PauseButton.setOnClickListener(::StopPlay)
        Video.setVideoURI(scr)
        Video.start()
    }
}
