package com.estiam.mybestyoutube;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

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
}