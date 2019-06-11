package justrelax.justrelax

import android.animation.Animator
import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import kotlinx.android.synthetic.main.activity_main.*
import android.animation.ObjectAnimator
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BlurMaskFilter
import android.graphics.Color
import android.graphics.Typeface
import android.media.MediaMetadataRetriever
import android.net.Uri
import android.provider.MediaStore
import kotlinx.android.synthetic.main.activity_video.*
import android.os.Build
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.BottomSheetBehavior
import android.support.design.widget.CoordinatorLayout
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.app.AppCompatActivity
import android.support.v7.view.menu.MenuView
import android.support.v7.widget.CardView
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutManager
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
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.google.android.exoplayer2.*
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.trackselection.TrackSelection
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import justrelax.justrelax.R.id.root
import kotlinx.android.synthetic.main.fragment_video.view.*
import kotlinx.android.synthetic.main.popupfragment.view.*
import smartdevelop.ir.eram.showcaseviewlib.GuideView
import smartdevelop.ir.eram.showcaseviewlib.config.DismissType

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

        /*загрузка видео -> взятие первого кадра*/

           var ret = MediaMetadataRetriever()
            ret.setDataSource(currentItem.href,HashMap<String, String>())
            var bitmap = ret.getFrameAtTime(0, MediaMetadataRetriever.OPTION_CLOSEST)
            holder.image.setImageBitmap(bitmap)





        /*Растяжение карточек на какую-то высоту*/
        params.height = (holder.CardView.resources.displayMetrics.density * currentItem.height).toInt() // типа 20dp
        holder.CardView.layoutParams = params
        /*Нажатие на карточку*/
        holder.image.setOnLongClickListener{

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
            var stopMusic: ImageView = view.findViewById(R.id.pic3)
            stopMusic.setOnClickListener {



            }
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

                /* создание музыки */
                val somerec = view.findViewById(R.id.somerecycle) as RecyclerView
                val audioClass = AudioClasser(view)
                audioClass.setAudio(somerec)


            }

            /*popup animation*/
            var anim = AnimationUtils.loadAnimation(holder.context,R.anim.popupanim)
            var contentmenu = view.findViewById(R.id.contento) as android.support.constraint.ConstraintLayout
            contentmenu.animation = anim
            val VideoClasser = VideoClasser(view, currentItem.href, holder.context)
            /*Video PLayer */
            VideoClasser.CreateVideo(holder.context)
            TransitionManager.beginDelayedTransition(mLay)
            /*Создание попапа по центру лэйаута*/
            popupWindow.showAtLocation(mLay, Gravity.CENTER, 0, 0)



            return@setOnLongClickListener true
        }
        holder.image.setOnClickListener {
            var inf : Intent = Intent(holder.context, VideoPlayer::class.java )
            val bundle = Bundle()
            startActivity(holder.context,inf,bundle)

        }



    }

    override fun getItemCount(): Int {
        return mExampleList.size
    }
}
class AudioClasser(views: View)
{

    private var view : View? = null
    init {
        view = views
    }
    public fun setAudio(recyclerView: RecyclerView){
        val audio  = ArrayList<AudioClass>()
        audio.add(AudioClass(0,"SomeAudio1","someAuthor1","https://www.dropbox.com/s/wbxpdbc0qvqb033/Kodak%20Black%20-%20Roll%20in%20Peace%20instrumental.mp3?dl=1"))
        audio.add(AudioClass(0,"SomeAudio2","someAuthor2","https://www.dropbox.com/s/wbxpdbc0qvqb033/Kodak%20Black%20-%20Roll%20in%20Peace%20instrumental.mp3?dl=1"))
        var manager  = LinearLayoutManager(view?.context)
        recyclerView.layoutManager = manager
        recyclerView.adapter = AudioAdapterKot(audio)
    }
}
class VideoClasser(view: View, scr : String, base: Context) : ContextWrapper(base){
    /*пока что не нужный конструктор*/
    private var scres : String = ""
    private var x = MediaController(base)

    init {
         scres = scr
    }
    fun getSrc() : String{
        val Src : String = scres
        return Src
    }
    val Video  = view.findViewById(R.id.video_view) as com.google.android.exoplayer2.ui.PlayerView
    val PauseButton: Button = view.findViewById(R.id.PauseButton)
    fun StopPlay(view : View) {
//        if (Video.isPlaying) {
//            Video.pause()
//            PauseButton.visibility = View.VISIBLE
//        }
//        else {
//            Video.start()
//            PauseButton.visibility = View.INVISIBLE
//        }
    }
     /*Основная часть*/
     fun CreateVideo(context : Context)  {
        val Src = scres
        val scr : Uri = Uri.parse(getSrc())

         var simple = ExoPlayerFactory.newSimpleInstance(context)
         var player = ExoPlayerFactory.newSimpleInstance(
         DefaultRenderersFactory(this),
          DefaultTrackSelector(),  DefaultLoadControl()
         )
    Video.setBackgroundColor(Color.TRANSPARENT);

     Video.setPlayer(player)

     player.playWhenReady = true
     player.seekTo(0,1000000)
     var uri = Uri.parse(getSrc())
     var mediaSource = buildMediaSource(uri) as MediaSource
     player.prepare(mediaSource, true, false)

//        Video.setOnClickListener(::StopPlay)
//
//
//        //x.setAnchorView(Video)
//        //x.setMediaPlayer(Video)
//        //Video.setMediaController(x)
//
//        PauseButton.setOnClickListener(::StopPlay)
//        Video.setVideoURI(scr)
//        Video.setOnCompletionListener { Video.start() }
//
//        Video.start()
        // Video.setZOrderOnTop(true)
    }
private fun  buildMediaSource( uri : Uri) : MediaSource {
  return ExtractorMediaSource.Factory(
       DefaultHttpDataSourceFactory("exoplayer-codelab")
  ).createMediaSource(uri)
}
}


