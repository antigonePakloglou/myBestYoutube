package com.estiam.mybestyoutube;

import androidx.appcompat.app.AppCompatActivity;

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

public class AddYoutubeActivity extends AppCompatActivity {

    private Button btnAjouter;
    private Button btnAnnuler;
    private EditText editTitre;
    private EditText editDescription;
    private EditText editUrl;
    private Spinner spinnerCategorie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_youtube);

        //récupération des éléments
        getElementsViewId();

        //creation spinner
        createSpinner();

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
                    saveYoutubeVideoInDb(editTitre, editDescription, editUrl, spinnerCategorie);
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
        spinnerCategorie = findViewById(R.id.spinnerCategorie);
    }

    private void saveYoutubeVideoInDb(EditText titre, EditText desc, EditText url, Spinner categorie){
        YoutubeVideo youtubeVideo = new YoutubeVideo();
        youtubeVideo.setTitre(titre.getText().toString());
        youtubeVideo.setDescription(desc.getText().toString());
        youtubeVideo.setUrl(url.getText().toString());
        youtubeVideo.setCategorie(categorie.getSelectedItem().toString());
        YoutubeVideoDatabase.getDb(getApplicationContext()).youtubeVideoDAO().add(youtubeVideo);
    }

    private void createSpinner(){
        Spinner spinner = findViewById(R.id.spinnerCategorie);

        // Initializing a String Array
        String[] categories = new String[]{
                "Sport",
                "Music",
                "Comedy",
                "Animal"
        };

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
}