package com.estiam.mybestyoutube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView detailTitre;
    private TextView detailDescription;
    private TextView detailUrl;
    private TextView detailCategorie;
    private Button btnVoir;
    private YoutubeVideo youtubeVideo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //récupération des éléments
        getElementsViewId();

        //récupération de la vidéo en BDD
        Long videoId = Long.valueOf(getIntent().getStringExtra("idVideo"));
        youtubeVideo = YoutubeVideoDatabase.getDb(getApplicationContext()).youtubeVideoDAO().find(videoId);
        //Affectation des valeurs
        setElementsView(youtubeVideo);

        //redirection youtube
        btnVoir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webIntent = new Intent(Intent.ACTION_VIEW,
                        Uri.parse(youtubeVideo.getUrl()));
                try {
                    DetailActivity.this.startActivity(webIntent);
                } catch (ActivityNotFoundException ex) {
                }
            }
        });
    }

    private void getElementsViewId(){
       detailTitre = findViewById(R.id.detailTitre);
       detailDescription = findViewById(R.id.detailDescription);
       detailUrl = findViewById(R.id.detailUrl);
       detailCategorie = findViewById(R.id.detailCategorie);
       btnVoir= findViewById(R.id.btnVoir);
    }

    private void setElementsView(YoutubeVideo youtubeVideo){
        detailTitre.setText(youtubeVideo.getTitre());
        detailDescription.setText(youtubeVideo.getDescription());
        detailUrl.setText(youtubeVideo.getUrl());
        detailCategorie.setText(youtubeVideo.getCategorie());
    }
}