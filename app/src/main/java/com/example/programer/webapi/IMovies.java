package com.example.programer.webapi;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

public interface IMovies {
    public static final String ENDPOINT_URL = "http://api.rest7.com";
    @GET("/v1/movie_info.php")
    void getMovie(@Query("title") String movieTitle, Callback<MoviesList> callback);

}
