package justrelax.justrelax;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.FocusFinder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;


/* public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ExampleViewHolder> {
    private ArrayList<VideoClass> mExampleList;
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public android.support.v7.widget.CardView CardView;
        public ImageView image;
        public Context context;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            CardView = itemView.findViewById(R.id.cardview);
            image = itemView.findViewById(R.id.imageview);
            context = itemView.getContext();
        }
    }
    public VideoAdapter(ArrayList<VideoClass> exampleList) {
        mExampleList = exampleList;
    }

    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_video, parent, false);

        ExampleViewHolder evh = new ExampleViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder( final ExampleViewHolder holder, int position ) {
        final VideoClass currentItem = mExampleList.get(position);

        //holder.textView.setText(currentItem.getName());
        //holder.textView.setId(currentItem.getId());
        holder.CardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String mess = (String) v.getContentDescription();
                Intent intent = new Intent(holder.context, video.class);
                // intent.putExtra("id",currentItem.getId());
                //intent.putExtra("mess", mess);
                //commit
                holder.context.startActivity(intent);
            }
        });
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String mess = (String) v.getContentDescription();
                Intent intent = new Intent(holder.context, video.class);
                // intent.putExtra("id",currentItem.getId());
                //intent.putExtra("mess", mess);
                //commit
                //holder.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}


    var inflater: LayoutInflater  =  this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflater.inflate(R.layout.popupfragment, null)
        val popupWindow = PopupWindow(
        view, // Custom view to show in popup window
        LinearLayout.LayoutParams.WRAP_CONTENT, // Width of popup window
        LinearLayout.LayoutParams.WRAP_CONTENT // Window height
        )

        // Set an elevation for the popup window
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        popupWindow.elevation = 10.0F
        }


        // If API level 23 or higher then execute the code
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
        // Create a new slide animation for popup window enter transition
        val slideIn = Slide()
        slideIn.slideEdge = Gravity.TOP
        popupWindow.enterTransition = slideIn

        // Slide animation for popup window exit transition
        val slideOut = Slide()
        slideOut.slideEdge = Gravity.RIGHT
        popupWindow.exitTransition = slideOut

        }


        // Set a dismiss listener for popup window
        popupWindow.setOnDismissListener {
        Toast.makeText(applicationContext,"Popup closed",Toast.LENGTH_SHORT).show()
        }


        TransitionManager.beginDelayedTransition(root)
        popupWindow.showAtLocation(
        root, // Location to display popup window
        Gravity.CENTER, // Exact position of layout to display popup
        0, // X offset
        0 // Y offset
        )
*/
