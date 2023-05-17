package com.estiam.mybestyoutube;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyYoutubeVideoAdapter extends RecyclerView.Adapter<MyYoutubeVideoAdapter.MyViewHolder> {

    List<YoutubeVideo> mesVideosYoutube;

    public MyYoutubeVideoAdapter(List<YoutubeVideo> mesVideosYoutube) {
        this.mesVideosYoutube = mesVideosYoutube;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.video_youtube_item,parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.display(mesVideosYoutube.get(position));
    }

    @Override
    public int getItemCount() {
        return mesVideosYoutube.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView nTitreTV;
        private TextView nDescriptionTV;


        MyViewHolder(View itemView){
            super(itemView);
            nTitreTV = (TextView) itemView.findViewById(R.id.titre);
            nDescriptionTV = (TextView) itemView.findViewById(R.id.description);

        }
        void display(YoutubeVideo youtubeVideo){
            nTitreTV.setText(youtubeVideo.getTitre());
            nDescriptionTV.setText(youtubeVideo.getDescription());
        }
    }

}
