package com.estiam.mybestyoutube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<YoutubeVideo> mesYoutubeVideos;
    private MyYoutubeVideoAdapter monAdapteur;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        mesYoutubeVideos = new ArrayList<>();

        mesYoutubeVideos.add(new YoutubeVideo("Première vidéo", "Ceci est la vidéo numéro 1","monurl", "Divertissement",1));
        mesYoutubeVideos.add(new YoutubeVideo("Deuxiéme vidéo", "Ceci est la vidéo numéro 2","monurl2", "Bonne question",1));
        mesYoutubeVideos.add(new YoutubeVideo("Troisième vidéo", "Ceci est la vidéo numéro 3","monurl3", "Bonne question",1));

        monAdapteur = new MyYoutubeVideoAdapter(mesYoutubeVideos);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mRecyclerView.setAdapter(monAdapteur);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // cration du menu
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.addVideoYoutubeItem) {
            // crée un Intent
            Intent intent = new Intent(getApplicationContext(), AddYoutubeActivity.class);
            // démarre l'activity
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}