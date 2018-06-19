package br.com.globo.movies.movies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.globo.movies.R;
import br.com.globo.movies.movies.model.Movie;
import br.com.globo.movies.movies.presenter.MoviesFavoritesPresenter;

public class MoviesFavoritesAdapter extends RecyclerView.Adapter{
    private final MoviesFavoritesPresenter mPresenter;
    private final Context context;
    private final List<Movie> movies;

    public MoviesFavoritesAdapter(Context context, List<Movie> movies, MoviesFavoritesPresenter mPresenter) {
        this.movies = movies;
        this.context = context;
        this.mPresenter = mPresenter;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate( R.layout.movies_favorites, parent, false);

        MoviesFavoritesAdapter.MoviesFavoritesViewHolder holder = new MoviesFavoritesAdapter.MoviesFavoritesViewHolder(view);

        return holder;
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        MoviesFavoritesAdapter.MoviesFavoritesViewHolder holder = (MoviesFavoritesAdapter.MoviesFavoritesViewHolder) viewHolder;
        Movie movie = movies.get(position);

        Picasso.get().load(movie.getImages().get(0).getExtraLarge())
                .into(holder.image);
        holder.title.setText(movie.getTitle());
        holder.removeBtn.setTag(position);
        holder.removeBtn.setOnClickListener(v -> {
            favoriteAlert(movie, v);
        });
    }

    private void favoriteAlert(Movie movie, View v) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(context.getString(R.string.remove_favorite));
        builder.setMessage(context.getString(R.string.remove_favorite_msg));
        builder.setPositiveButton(context.getString(R.string.yes), (arg0, arg1) -> {
            mPresenter.removeFavorite(movies.get((int)(v.getTag())));
            this.notifyDataSetChanged();
        });
        builder.setNegativeButton(context.getString(R.string.no), (arg0, arg1) -> {});
        builder.create().show();
    }

    private class MoviesFavoritesViewHolder extends  RecyclerView.ViewHolder{
        private final ImageView image;
        private final TextView title;
        private final ImageButton removeBtn;

        public MoviesFavoritesViewHolder(View view) {
            super( view );
            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title);
            removeBtn = view.findViewById(R.id.remove);
        }
    }
}
