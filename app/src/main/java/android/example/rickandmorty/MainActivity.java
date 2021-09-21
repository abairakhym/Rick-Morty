package android.example.rickandmorty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private String JSON_URL = "https://rickandmortyapi.com/api/character";

    private ArrayList<Person> personList;

    private RecyclerView recyclerView;

    private PersonAdapter.RecyclerViewClickListener listener;

    private String EXTRA_NAME = "name";
    private String EXTRA_STATUS = "status";
    private String EXTRA_SPECIES = "species";
    private String EXTRA_GENDER = "gender";
    private String EXTRA_LOCATION = "location";
    private String EXTRA_IMAGE = "image";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personList = new ArrayList<>();

        recyclerView = findViewById(R.id.mainRecyclerView);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setSelectedItemId(R.id.home);
        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener(){

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.home:
                        return true;
                    case R.id.settings:
                        startActivity(new Intent(getApplicationContext(), SettingsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });

        DownloadChatertorTask chatertorTask = new DownloadChatertorTask();
        chatertorTask.execute(JSON_URL);
        setOnClickListener();
    }

    private class DownloadChatertorTask extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            HttpURLConnection urlConnection = null;
            StringBuilder result = new StringBuilder();
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = urlConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                Log.i("URL",strings[0]);
                while (line != null){
                    result.append(line);
                    line = reader.readLine();
                }
                return result.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (urlConnection != null){
                    urlConnection.disconnect();
                }
            }

            return "";
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {

                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {

                    Person personModel = new Person();

                    JSONObject allCharecters = jsonArray.getJSONObject(i);

                    JSONObject location = allCharecters.getJSONObject("location");

                    personModel.setName(allCharecters.getString("name"));
                    personModel.setStatus(allCharecters.getString("status"));
                    personModel.setSpecies(allCharecters.getString("species"));
                    personModel.setGender(allCharecters.getString("gender"));
                    personModel.setImage(allCharecters.getString("image"));

                    personModel.setLocation(location.getString("name"));

                    personList.add(personModel);
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            PutDataIntoRecyclerView(personList);
        }
    }

    private void PutDataIntoRecyclerView(List<Person> personList){

        PersonAdapter adapter = new PersonAdapter(this, personList, listener);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    private void setOnClickListener() {
        listener = new PersonAdapter.RecyclerViewClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getApplicationContext(), AboutPerson.class);
                intent.putExtra(EXTRA_NAME, personList.get(position).getName());
                intent.putExtra(EXTRA_STATUS, personList.get(position).getStatus());
                intent.putExtra(EXTRA_SPECIES, personList.get(position).getSpecies());
                intent.putExtra(EXTRA_GENDER, personList.get(position).getGender());
                intent.putExtra(EXTRA_LOCATION, personList.get(position).getLocation());
                intent.putExtra(EXTRA_IMAGE, personList.get(position).getImage());
                startActivity(intent);
            }
        };
    }
}
