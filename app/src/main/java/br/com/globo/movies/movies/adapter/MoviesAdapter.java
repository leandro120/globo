package br.com.globo.movies.movies.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.globo.movies.R;
import br.com.globo.movies.movies.model.Movie;

public class MoviesAdapter extends RecyclerView.Adapter{

    List<Movie> movies;
    private final Context context;

    public MoviesAdapter(Context context, List<Movie> movies) {
        this.movies = movies;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate( R.layout.movies, parent, false);

        ProductsViewHolder holder = new ProductsViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        ProductsViewHolder holder = (ProductsViewHolder) viewHolder;
        Movie movie = movies.get(position);

        Picasso.get().load(movie.getImages().get(0).getLarge())
                .into(holder.image);
        holder.title.setText(movie.getTitle());
        holder.subtitle.setText(movie.getSubtitle());
        holder.time.setText(movie.getTime());
        holder.synopsis.setText(movie.getSynopsis());
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    private class ProductsViewHolder extends  RecyclerView.ViewHolder{
        private final ImageView image;
        private final TextView title;
        private final TextView subtitle;
        private final TextView time;
        private final TextView synopsis;

        public ProductsViewHolder(View view) {
            super( view );
            image = view.findViewById(R.id.image);
            title = view.findViewById(R.id.title);
            subtitle = view.findViewById(R.id.subtitle);
            time = view.findViewById(R.id.time);
            synopsis = view.findViewById(R.id.synopsis);
        }
    }
}
