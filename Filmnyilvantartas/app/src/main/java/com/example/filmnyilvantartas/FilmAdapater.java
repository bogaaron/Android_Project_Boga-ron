package com.example.filmnyilvantartas;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.filmnyilvantartas.model.Film;

import java.util.List;
import java.util.Objects;

class FilmAdapater extends ArrayAdapter<Film> {
    private Context context;
    private List<Film> films;

    public FilmAdapater(@NonNull Context context, int resource, @NonNull List<Film> objects) {
        super(context, resource, objects);
        this.context = context;
        this.films = objects;
    }

    @Override
    public View getView(final int pos, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_film, parent, false);
        TextView txtFilmId = (TextView) rowView.findViewById(R.id.txtFilmId);
        TextView txtFilmname = (TextView) rowView.findViewById(R.id.txtFilmname);
        txtFilmId.setText(String.format("film_id: %d", films.get(pos).getId()));
        txtFilmname.setText(String.format("film_name: %s", films.get(pos).getName()));
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, FilmActivity.class);
                intent.putExtra("film_id", String.valueOf(films.get(pos).getId()));
                intent.putExtra("film_name", films.get(pos).getName());
                context.startActivity(intent);
            }
        });
        return rowView;
    }
}