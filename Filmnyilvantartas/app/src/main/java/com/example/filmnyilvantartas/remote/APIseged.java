package com.example.filmnyilvantartas.remote;

public class APIseged {
    private APIseged(){

    }
    public static final String API_URL="http://192.168.0.103:80/";
    public static FilmInterface getFilmInterface(){
        return RetrofitKliens.getClient(API_URL).create(FilmInterface.class);
    }
}
