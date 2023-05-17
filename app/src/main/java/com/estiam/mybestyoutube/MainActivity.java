package com.estiam.mybestyoutube;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<YoutubeVideo> mesYoutubeVideos;
    private MyYoutubeVideoAdapter monAdapteur;
    private final String TAG = "YoutubeVideo";
    public static final String KEY_VIDEO = "video";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = (RecyclerView) findViewById(R.id.myRecyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }


    @Override
    protected void onStart() {
        //Récupération de la liste des youtubeVideos en BDD
        super.onStart();
        //récupération données de la BDD
        mesYoutubeVideos = YoutubeVideoDatabase.getDb(getApplicationContext()).youtubeVideoDAO().list();

        monAdapteur = new MyYoutubeVideoAdapter(mesYoutubeVideos);
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