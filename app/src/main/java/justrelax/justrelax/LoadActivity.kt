package justrelax.justrelax

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.content.Intent
import android.net.Uri
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory


class LoadActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load)




       // val scr : Uri = Uri.parse("https://www.dropbox.com/s/gys5q7yc868ko59/52586642_324829134816014_3179773492790820864_n.mp4?dl=1")
        //val Video  = findViewById(R.id.video_view) as com.google.android.exoplayer2.ui.PlayerView

//        var player = ExoPlayerFactory.newSimpleInstance(this)
//        //Video.setBackgroundColor(Color.TRANSPARENT);
//
//        Video.setPlayer(player)
//
//        player.playWhenReady = true
//        player.seekTo(0,1000000)
//        var uri = Uri.parse("https://www.dropbox.com/s/gys5q7yc868ko59/52586642_324829134816014_3179773492790820864_n.mp4?dl=1")
//        var mediaSource = buildMediaSource(uri) as MediaSource
//        player.prepare(mediaSource, true, false)
        val mySuperIntent = Intent(this@LoadActivity, MainActivity::class.java)
                 startActivity(mySuperIntent)

//        Handler().postDelayed({
//            //Do any action here. Now we are moving to next page
//
//            val mySuperIntent = Intent(this@LoadActivity, MainActivity::class.java)
//            startActivity(mySuperIntent)
//            finish()
//        }, 500)
    }
    private fun  buildMediaSource( uri : Uri) : MediaSource {
        return ExtractorMediaSource.Factory(
            DefaultHttpDataSourceFactory("exoplayer-codelab")
        ).createMediaSource(uri)
    }
}
