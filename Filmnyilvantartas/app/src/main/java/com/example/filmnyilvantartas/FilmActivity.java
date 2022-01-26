package com.example.filmnyilvantartas;


import android.os.Bundle;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.filmnyilvantartas.model.Film;
import com.example.filmnyilvantartas.remote.APIseged;
import com.example.filmnyilvantartas.remote.FilmInterface;
import com.google.android.material.tabs.TabLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FilmActivity extends AppCompatActivity {

    FilmInterface filmInterface;
    Button btnBack;
    Button btnSave;
    Button btnDelete;
    EditText edtUId;
    EditText edtUName;
    TextView tFilmId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);
        edtUId = (EditText) findViewById(R.id.edtUId);
        edtUName = (EditText) findViewById(R.id.edtUName);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        tFilmId = (TextView) findViewById(R.id.tFilmId);
        filmInterface = APIseged.getFilmInterface();
        Bundle extras = getIntent().getExtras();
        final String filmId = extras.getString("film_id");
        String filmName = extras.getString("film_name");
        edtUId.setText(filmId);
        edtUName.setText(filmName);
        if (filmId != null && filmId.trim().length() > 0) {
            edtUId.setFocusable(false);
        } else {
            tFilmId.setVisibility(View.INVISIBLE);
            edtUId.setVisibility(View.INVISIBLE);
            btnDelete.setVisibility(View.INVISIBLE);
        }

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Film u = new Film();
                u.setName(edtUName.getText().toString());
                if (filmId != null && filmId.trim().length() > 0) {
                    updateFilm(Integer.parseInt(filmId), u);
                } else {
                    addFilm(u);
                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FilmActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteFilm(Integer.parseInt(filmId));
                Intent intent = new Intent(FilmActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });




    }

    public void addFilm(Film u) {
        Call<Film> call = filmInterface.addFilm(u);
        call.enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(FilmActivity.this, "Sikeresen létrehozva!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }
    public void updateFilm(int id, Film u) {
        Call<Film> call = filmInterface.updateFilm(id, u);
        call.enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(FilmActivity.this, "Sikeresen modositva!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }
    public void deleteFilm(int id) {
        Call<Film> call = filmInterface.deleteFilm(id);
        call.enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(FilmActivity.this, "Sikeresen torolve!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });
    }
}