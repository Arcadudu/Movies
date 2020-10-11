package ru.arcadudu.movies;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyDialog.MyDialogListener {

    RecyclerView recycler;
    MyAdapter adapter;
    private List<Movie> movies = new ArrayList<>();
    FloatingActionButton fabAddFilm;


    String[] movieTitles = {"Побег из Шоушенка", "Зеленая миля", "Форрест Гамп", "Список Шиндлера", "1+1", "Начало"};
    String[] movieYears = {"1994", "1991", "1994", "1993", "2011", "2010"};
    String[] movieRatings = {"9.1", "9.1", "8.9", "8.8", "8.8", "8.7"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler = findViewById(R.id.recycler);
        movies.add(new Movie(movieTitles[0], "Рейтинг: " + movieRatings[0] + " / 10.0", "Год: " + movieYears[0], getResources().getString(R.string.plot1)));
        movies.add(new Movie(movieTitles[1], "Рейтинг: " + movieRatings[1] + " / 10.0", "Год: " + movieYears[1], getResources().getString(R.string.plot2)));
        movies.add(new Movie(movieTitles[2], "Рейтинг: " + movieRatings[2] + " / 10.0", "Год: " + movieYears[2], getResources().getString(R.string.plot3)));
        movies.add(new Movie(movieTitles[3], "Рейтинг: " + movieRatings[3] + " / 10.0", "Год: " + movieYears[3], getResources().getString(R.string.plot4)));
        movies.add(new Movie(movieTitles[4], "Рейтинг: " + movieRatings[4] + " / 10.0", "Год: " + movieYears[4], getResources().getString(R.string.plot5)));
        movies.add(new Movie(movieTitles[5], "Рейтинг: " + movieRatings[5] + " / 10.0", "Год: " + movieYears[5], getResources().getString(R.string.plot6)));

        adapter = new MyAdapter(movies, this);

        recycler.setHasFixedSize(true);
        recycler.setLayoutManager(new LinearLayoutManager(this));
        recycler.setAdapter(adapter);

        fabAddFilm = findViewById(R.id.fab_add_film);
        fabAddFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
                fabAddFilm.hide();
            }
        });



    }

    private void openDialog() {
        MyDialog dialog = new MyDialog();
        dialog.show(getSupportFragmentManager(), "MyDialog");
    }

    @Override
    public void applyText(String title, String year, String rating, String plot) {
        Movie movie = new Movie(title, "Рейтинг: " +rating+ " / 10.0", "Год: "+year, plot);
        movies.add(movie);
        adapter.notifyDataSetChanged();
        fabAddFilm.show();
    }
}