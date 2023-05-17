package com.estiam.mybestyoutube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // cration du menu
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.updateVideo) {
            // crée un Intent
            Intent intent = new Intent(getApplicationContext(), UpdateYoutubeActivity.class);
            intent.putExtra("idVideoYoutube",Long.toString(youtubeVideo.getId()));
            // démarre l'activity
            startActivity(intent);
            return true;
        } else if (item.getItemId() == R.id.deleteVideo) {
            YoutubeVideoDatabase.getDb(getApplicationContext()).youtubeVideoDAO().delete(youtubeVideo);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}