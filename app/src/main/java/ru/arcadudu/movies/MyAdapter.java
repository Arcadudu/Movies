package ru.arcadudu.movies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Movie> movieList = new ArrayList<>();
    Context context;

    public MyAdapter(List<Movie> movieList, Context context) {
        this.movieList = movieList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Movie movie = movieList.get(position);
        holder.movieTitle.setText(movie.getTitle());
        holder.movieYear.setText(movie.getYear());
        holder.movieRating.setText(movie.getRating());
        holder.moviePlot.setText(movie.getPlot());

        boolean isExpanded = movie.isExpanded();
        holder.expandableConstraint.setVisibility(isExpanded ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout expandableConstraint;
        TextView movieTitle, movieYear, movieRating, moviePlot;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            movieTitle = itemView.findViewById(R.id.tv_title);
            movieYear = itemView.findViewById(R.id.tv_year);
            movieRating = itemView.findViewById(R.id.tv_rating);
            moviePlot = itemView.findViewById(R.id.tv_plot);
            expandableConstraint = itemView.findViewById(R.id.expandable_constraint);

            movieTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Movie movie = movieList.get(getAdapterPosition());
                    movie.setExpanded(!movie.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

        }
    }
}
