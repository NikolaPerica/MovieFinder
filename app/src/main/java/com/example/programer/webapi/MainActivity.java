package com.example.programer.webapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity {
    private IMovies iMovies;
    private Callback<MoviesList> callback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupRestAdapter();
    }
    public void searchForMovie(View view){
        EditText etMovieTitle = (EditText) findViewById(R.id.etMovieTitle);
        String movieTitle = etMovieTitle.getText().toString();
        iMovies.getMovie(movieTitle, callback);
    }
    private void setupRestAdapter(){
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(IMovies.ENDPOINT_URL).build();
        iMovies = restAdapter.create(IMovies.class);
        callback = new Callback<MoviesList>() {
            @Override
            public void success(MoviesList moviesList, Response response) {
                StringBuilder report = new StringBuilder();
                if(moviesList.getMovies() != null){
                    Movie movie = moviesList.getMovies().get(0);
                    report.append(movie.getTitle());
                    report.append("\n");
                    report.append(movie.getYear());
                    report.append("\n");
                    report.append(movie.getDirector());
                    report.append("\n");
                    report.append(movie.getCast());
                } else {
                    report.append("No such movie");
                }
                Toast.makeText(MainActivity.this, report, Toast.LENGTH_LONG).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        };
    }
}
