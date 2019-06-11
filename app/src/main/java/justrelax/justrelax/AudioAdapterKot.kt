package justrelax.justrelax

import android.content.Context
import android.media.AudioManager
import android.media.MediaPlayer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.audioblock.view.*
import android.widget.Toast
import java.io.IOException
class AudioAdapterKot(private val mList: ArrayList<AudioClass>) :
    RecyclerView.Adapter<AudioAdapterKot.SomeView>() {

    private var mPlayer  = MediaPlayer()
    class SomeView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var playbtn: ImageView? = null
        var image: ImageView? = null
        var settings: ImageView? = null

        init {
            playbtn = itemView.findViewById(R.id.playButton)
            image = itemView.findViewById(R.id.image)
            settings = itemView.findViewById(R.id.settings)
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SomeView {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.audioblock, parent, false)
        return AudioAdapterKot.SomeView(v)
    }

    override fun onBindViewHolder(holder: SomeView, position: Int) {
        val currentItem = mList[position]
        var state = false

        holder.playbtn?.setOnClickListener {
            if(!state) {
                state = true
                try {
                    mPlayer.setDataSource(currentItem.getHref())
                    //mPlayer.setOnCompletionListener { stopPlay() }
                    mPlayer.prepare()

                } catch (e: IllegalArgumentException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                } catch (e: SecurityException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                } catch (e: IllegalStateException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                } catch (e: IOException) {
                    // TODO Auto-generated catch block
                    e.printStackTrace()
                }
                mPlayer.start()
            }
            else
            {
                stopPlay()
                state = false
            }
        }
    }

    private fun stopPlay() {
        mPlayer.pause()
    }
    override fun getItemCount(): Int {
        return mList.size
    }
}