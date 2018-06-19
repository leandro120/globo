package br.com.globo.movies.movies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.globo.movies.R;
import br.com.globo.movies.movies.model.Movie;
import br.com.globo.movies.movies.presenter.MoviesListPresenter;

public class MoviesAdapter extends RecyclerView.Adapter{

    private final MoviesListPresenter mPresenter;
    List<Movie> movies;
    private final Context context;

    public MoviesAdapter(Context context, List<Movie> movies, MoviesListPresenter moviesListPresenter) {
        this.movies = movies;
        this.context = context;
        this.mPresenter = moviesListPresenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate( R.layout.movies, parent, false);

        MoviesViewHolder holder = new MoviesViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MoviesViewHolder holder = (MoviesViewHolder) viewHolder;
        Movie movie = movies.get(position);

        Picasso.get().load(movie.getImages().get(0).getLarge())
                .into(holder.image);
        holder.title.setText(movie.getTitle());
        holder.subtitle.setText(movie.getSubtitle());
        holder.time.setText(movie.getTime());
        holder.synopsis.setText(movie.getSynopsis());
        holder.favoriteBtn.setTag(position);
        if (mPresenter.checkIfFavorite(movie)){
            holder.favoriteBtn.setText(context.getString(R.string.remove_favorite));
        }else{
            holder.favoriteBtn.setText(context.getString(R.string.add_favorite));
        }
        holder.favoriteBtn.setOnClickListener(v -> {
            favoriteAlert(movie, v);
        });
    }

    private void favoriteAlert(Movie movie, View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (mPresenter.checkIfFavorite(movie)) {
            builder.setTitle(context.getString(R.string.remove_favorite));
            builder.setMessage(context.getString(R.string.remove_favorite_msg));
        }else{
            builder.setTitle(context.getString(R.string.add_favorite));
            builder.setMessage(context.getString(R.string.add_favorite_msg));
        }
        builder.setPositiveButton(context.getString(R.string.yes), (arg0, arg1) -> {
            mPresenter.movieFavorite(movies.get((int)(v.getTag())));
            this.notifyDataSetChanged();
        });
        builder.setNegativeButton(context.getString(R.string.no), (arg0, arg1) -> {});
        builder.create().show();
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    private class MoviesViewHolder extends  RecyclerView.ViewHolder{
        private final ImageView image;
        private final TextView title;
        private final TextView subtitle;
        private final TextView time;
        private final TextView synopsis;
        private final Button favoriteBtn;

        public MoviesViewHolder(View view) {
            super( view );
            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title);
            subtitle = view.findViewById(R.id.subtitle);
            time = view.findViewById(R.id.time);
            synopsis = view.findViewById(R.id.synopsis);
            favoriteBtn = view.findViewById(R.id.favorite);
        }
    }
}
