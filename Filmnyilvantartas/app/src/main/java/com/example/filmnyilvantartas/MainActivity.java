package com.example.filmnyilvantartas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.filmnyilvantartas.model.Film;
import com.example.filmnyilvantartas.remote.APIseged;
import com.example.filmnyilvantartas.remote.FilmInterface;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    Button btnAddFilm;
    Button btnGetFilmList;
    ListView listView;
    FilmInterface filmInterface;
    List<Film> list = new ArrayList<Film>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAddFilm = (Button) findViewById(R.id.btnAddFilm);
        btnGetFilmList = (Button) findViewById(R.id.btnGetFilmList);
        listView = (ListView) findViewById(R.id.listView);
        filmInterface = APIseged.getFilmInterface();

        btnGetFilmList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFilmList();
            }
        });
        btnAddFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FilmActivity.class);
                intent.putExtra("film_name", "");
                startActivity(intent);
            }
        });

    }

    public void getFilmList() {
        Call<Film> call = filmInterface.getFilm();
        call.enqueue(new Callback<Film>() {
            @Override
            public void onResponse(Call<Film> call, Response<Film> response) {
                if (response.body() != null) {
                    list = response.body().getData();
                }
                listView.setAdapter(new FilmAdapater(MainActivity.this, R.layout.list_film, list));
            }

            @Override
            public void onFailure(Call<Film> call, Throwable t) {
                Log.e("Error: ", t.getMessage());
            }
        });

    }
}