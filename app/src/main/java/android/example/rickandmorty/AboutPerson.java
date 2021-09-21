package android.example.rickandmorty;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class AboutPerson extends AppCompatActivity {

    private TextView textViewName, textViewStatus, textViewSpecies, textViewGender , textViewLocation;
    private ImageView imageViewCharactor;
    private String name, status, species, gender, location, image;

    private String EXTRA_NAME = "name";
    private String EXTRA_STATUS = "status";
    private String EXTRA_SPECIES = "species";
    private String EXTRA_GENDER = "gender";
    private String EXTRA_LOCATION = "location";
    private String EXTRA_IMAGE = "image";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_person);

        textViewName = findViewById(R.id.textViewName);
        textViewStatus = findViewById(R.id.textViewStatus);
        textViewSpecies = findViewById(R.id.textViewSpecies);
        textViewGender = findViewById(R.id.textViewGender);
        textViewLocation = findViewById(R.id.textViewLocation);
        imageViewCharactor = findViewById(R.id.imageViewCharacter);

        Bundle extras = getIntent().getExtras();
        if (extras != null){
            name = extras.getString(EXTRA_NAME);
            status = extras.getString(EXTRA_STATUS);
            species = extras.getString(EXTRA_SPECIES);
            gender = extras.getString(EXTRA_GENDER);
            location = extras.getString(EXTRA_LOCATION);
            image = extras.getString(EXTRA_IMAGE);
        }
        textViewName.setText(name);
        textViewStatus.setText("Status: " + status);
        textViewSpecies.setText("Species: " + species);
        textViewLocation.setText("Location: " + location);
        textViewGender.setText("Gender: " + gender);

        Glide.with(this)
                .load(image)
                .into(imageViewCharactor);

    }
}
