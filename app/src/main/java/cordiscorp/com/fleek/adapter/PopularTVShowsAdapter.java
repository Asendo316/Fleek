package cordiscorp.com.fleek.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cordiscorp.com.fleek.R;
import cordiscorp.com.fleek.model.response.PopularMovie;
import cordiscorp.com.fleek.model.response.PopularTVShowsResult;

/**
 * Created by Ibkunle Adeoluwa on 2/19/2019.
 */
public class PopularTVShowsAdapter extends RecyclerView.Adapter<PopularTVShowsAdapter.MyViewHolder> {
    private Context mContext;
    private List<PopularTVShowsResult> movieList;

    public PopularTVShowsAdapter(Context mContext, List<PopularTVShowsResult> movieList) {
        this.mContext = mContext;
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public PopularTVShowsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.movie_card_small, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularTVShowsAdapter.MyViewHolder viewHolder, int position) {
        PopularTVShowsResult movie = movieList.get(position);
        viewHolder.title.setText(movie.getName());
        String vote = Double.toString(movie.getVoteAverage());
        viewHolder.userrating.setText(vote);

        Glide.with(mContext)
                .load("https://image.tmdb.org/t/p/w500"+movie.getPosterPath())
                .placeholder(R.drawable.ic_loading_img)
                .into(viewHolder.thumbnail);
        System.out.print("image URL"+movie.getPosterPath());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, userrating;
        public ImageView thumbnail;

        public MyViewHolder(@NonNull View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            userrating = (TextView) view.findViewById(R.id.userrating);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);

            /*
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        Movie clickedDataItem = movieList.get(pos);
                        Intent intent = new Intent(mContext, DetailActivity.class);
                        intent.putExtra("original_title", movieList.get(pos).getOriginal_title());
                        intent.putExtra("poster_path", movieList.get(pos).getPosterPath());
                        intent.putExtra("overview", movieList.get(pos).getOverview());
                        intent.putExtra("vote_average", Double.toString(movieList.get(pos).getVoteAverage()));
                        intent.putExtra("release_date", movieList.get(pos).getReleaseDate());
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        Toast.makeText(view.getContext(), "You Clicked " + clickedDataItem.getOriginal_title(), Toast.LENGTH_SHORT).show();

                    }
                }
            }); */
        }
    }
}
