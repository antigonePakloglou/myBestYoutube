package com.estiam.mybestyoutube;

import android.content.Context;
import android.content.Intent;
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

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView nTitreTV;
        private TextView nDescriptionTV;
        private long idVideoYoutube;


        MyViewHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            nTitreTV = (TextView) itemView.findViewById(R.id.titre);
            nDescriptionTV = (TextView) itemView.findViewById(R.id.description);

        }

        @Override
        public void onClick(View view) {
            /** Or you can use For loop if you have long list of items. */
                Context context = view.getContext();
                Intent intent = new Intent(context, DetailActivity.class);
                // enregistre dans l'intent l'objet de la question en cours
                intent.putExtra("idVideo",Long.toString(idVideoYoutube));
                // démarre l'activity
               context.startActivity(intent);


        }
        void display(YoutubeVideo youtubeVideo){
            idVideoYoutube = youtubeVideo.getId();
            nTitreTV.setText(youtubeVideo.getTitre());
            nDescriptionTV.setText(youtubeVideo.getDescription());
        }
    }

}
