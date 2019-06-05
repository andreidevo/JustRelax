package justrelax.justrelax

import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {

    fun getSrc() : String{
        val Src : String = intent.getIntExtra("Key", 0).toString()
        return Src
    }

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        val Src = getSrc()
        Log.d("myVideo", Src)
        val scr : Uri = Uri.parse( "android.resource://" + getPackageName() + "/" + Src)
        Log.d("video", R.raw.videotest.toString())
        Video.setOnClickListener(::StopPlay)
        PauseButton.setOnClickListener(::StopPlay)
        Video.setVideoURI(scr)
        Video.start()
        Video.isPlaying
    }
}
