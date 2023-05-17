package com.estiam.mybestyoutube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UpdateYoutubeActivity extends AppCompatActivity {
    private YoutubeVideo youtubeVideo;
    private EditText updateTitre;
    private EditText updateDescription;
    private EditText updateUrl;
    private Spinner updateSpinnerCategorie;
    private Button btnModifier;
    private Button btnCancel;

    String[] categories = new String[]{
            "Sport",
            "Music",
            "Comedy",
            "Animal"
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_youtube);

        //récupération des éléments
        getElementsViewId();

        //creation spinner
        createSpinner();

        //récupération de la vidéo en BDD
        Long videoId = Long.valueOf(getIntent().getStringExtra("idVideoYoutube"));
        youtubeVideo = YoutubeVideoDatabase.getDb(getApplicationContext()).youtubeVideoDAO().find(videoId);

        //Affectation des valeurs
        setElementsView(youtubeVideo);


        //bouton MODIFIER
        btnModifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //si champs non remplis
                if (updateTitre.getText().toString().isEmpty() || updateDescription.getText().toString().isEmpty() || updateUrl.getText().toString().isEmpty()) {
                    Toast toast = Toast.makeText( getApplicationContext(), "Veuillez renseigner tout les champs", Toast.LENGTH_SHORT);
                    toast.show();
                }
                else{
                    updateYoutubeVideoInDb(updateTitre, updateDescription, updateUrl, updateSpinnerCategorie);
                    // crée un Intent
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    // démarre l'activity
                    startActivity(intent);
                }
            }
        });

        //bouton ANNULER
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void getElementsViewId(){
        updateTitre = findViewById(R.id.updateTitre);
        updateDescription = findViewById(R.id.updateDescription);
        updateUrl = findViewById(R.id.updateUrl);
        updateSpinnerCategorie = findViewById(R.id.updateSpinnerCategorie);
        btnModifier = findViewById(R.id.btnModifier);
        btnCancel = findViewById(R.id.btnUpdateAnnuler);
    }

    private void createSpinner(){
        Spinner spinner = findViewById(R.id.updateSpinnerCategorie);

        // Convert array to a list
        List<String> categorieList = new ArrayList<>(Arrays.asList(categories));

        // Initializing an ArrayAdapter
        ArrayAdapter<String> spinnerArrayAdapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                categorieList
        );

        // Set the drop down view resource
        spinnerArrayAdapter.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line
        );

        // Finally, data bind the spinner object with adapter
        spinner.setAdapter(spinnerArrayAdapter);
    }

    private void setElementsView(YoutubeVideo youtubeVideo){
        updateTitre.setText(youtubeVideo.getTitre());
        updateDescription.setText(youtubeVideo.getDescription());
        updateUrl.setText(youtubeVideo.getUrl());
        updateSpinnerCategorie.setSelection(Arrays.asList(categories).indexOf(youtubeVideo.getCategorie()));
    }

    private void updateYoutubeVideoInDb(EditText titre, EditText desc, EditText url, Spinner categorie){
        youtubeVideo.setTitre(titre.getText().toString());
        youtubeVideo.setDescription(desc.getText().toString());
        youtubeVideo.setUrl(url.getText().toString());
        youtubeVideo.setCategorie(categorie.getSelectedItem().toString());
        YoutubeVideoDatabase.getDb(getApplicationContext()).youtubeVideoDAO().update(youtubeVideo);
    }
}