package justrelax.justrelax;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.content.ContextCompat;
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


public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ExampleViewHolder> {
    private ArrayList<VideoClass> mExampleList;
    public static class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public Context context;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
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

        holder.textView.setText(currentItem.getName());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //String mess = (String) v.getContentDescription();
                Intent intent = new Intent(holder.context, LoadActivity.class);
                // intent.putExtra("id",currentItem.getId());
                //intent.putExtra("mess", mess);
                holder.context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}

