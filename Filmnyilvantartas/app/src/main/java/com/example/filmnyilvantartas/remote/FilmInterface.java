package com.example.filmnyilvantartas.remote;

import com.example.filmnyilvantartas.model.Film;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface FilmInterface {
    @GET("api/films/")
    Call<Film> getFilm();
    @POST("api/films/")
    Call<Film>addFilm(@Body Film film);
    @PUT("api/films/{id}")
    Call<Film>updateFilm(@Path("id") int id, @Body Film film);
    @DELETE("api/films/{id}")
    Call<Film>deleteFilm(@Path("id") int id);
}
