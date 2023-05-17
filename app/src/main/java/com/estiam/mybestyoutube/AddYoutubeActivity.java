package com.estiam.mybestyoutube;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddYoutubeActivity extends AppCompatActivity {

    private Button btnAjouter;
    private Button btnAnnuler;
    private EditText editTitre;
    private EditText editDescription;
    private EditText editUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_youtube);

        //récupération des éléments
        getElementsViewId();

        //bouton AJOUTER
        btnAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //si champs non remplis
                if (editTitre.getText().toString().isEmpty() || editDescription.getText().toString().isEmpty() || editUrl.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText( getApplicationContext(), "Veuillez renseigner tout les champs", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    saveYoutubeVideoInDb(editTitre, editDescription, editUrl);
                    finish();
                }
            }
        });

        //bouton ANNULER
        btnAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getElementsViewId(){
        btnAjouter = findViewById(R.id.btnAjouter);
        btnAnnuler = findViewById(R.id.btnAnnuler);
        editTitre = findViewById(R.id.editTitre);
        editDescription = findViewById(R.id.editDescription);
        editUrl = findViewById(R.id.editUrl);
    }

    private void saveYoutubeVideoInDb(EditText titre, EditText desc, EditText url){
        YoutubeVideo youtubeVideo = new YoutubeVideo();
        youtubeVideo.setTitre(titre.getText().toString());
        youtubeVideo.setDescription(desc.getText().toString());
        youtubeVideo.setUrl(url.getText().toString());
        YoutubeVideoDatabase.getDb(getApplicationContext()).youtubeVideoDAO().add(youtubeVideo);
    }
}