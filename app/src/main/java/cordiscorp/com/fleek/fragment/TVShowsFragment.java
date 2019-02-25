package cordiscorp.com.fleek.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.flaviofaria.kenburnsview.KenBurnsView;
import com.google.gson.Gson;

import java.util.List;

import cordiscorp.com.fleek.BuildConfig;
import cordiscorp.com.fleek.R;
import cordiscorp.com.fleek.adapter.CurrentlyAiringTVShowsAdapter;
import cordiscorp.com.fleek.adapter.PopularTVShowsAdapter;
import cordiscorp.com.fleek.adapter.TopRatedTVShowsAdapter;
import cordiscorp.com.fleek.connection.Client;
import cordiscorp.com.fleek.connection.ConnectionManager;
import cordiscorp.com.fleek.connection.Service;
import cordiscorp.com.fleek.model.response.CurrentlyAiringTVShowsResponse;
import cordiscorp.com.fleek.model.response.CurrentlyAiringTVShowsResult;
import cordiscorp.com.fleek.model.response.LatestTVShowsResponse;
import cordiscorp.com.fleek.model.response.PopularTVShowsResponse;
import cordiscorp.com.fleek.model.response.PopularTVShowsResult;
import cordiscorp.com.fleek.model.response.TopRatedTVShowsResponse;
import cordiscorp.com.fleek.model.response.TopRatedTVShowsResult;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TVShowsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    public TVShowsFragment() {
        // Required empty public constructor
    }

    private ShimmerRecyclerView popularMoviesRecycler, topRatedMoviesRecycler, upComingMovieRecycler, nowPlayingMoviesRecycler;
    private TextView latestMovieTitle, latestMovieOverView, latestMoviePopularity, latestMovieReleaseStatus,
            latestMovieReleaseDate, latestMovieGenre, latestMovieSeasons, latestMovieNetworks;
    private KenBurnsView latestMovieImage;
    private SwipeRefreshLayout swipeRefreshLayout;
    int top_to_padding = 190;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tvshows, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Paper.init(getContext());

        popularMoviesRecycler = (ShimmerRecyclerView) view.findViewById(R.id.popularMoviesRecycler);
        topRatedMoviesRecycler = (ShimmerRecyclerView) view.findViewById(R.id.topRatedMoviesRecycler);
        upComingMovieRecycler = (ShimmerRecyclerView) view.findViewById(R.id.upComingMovieRecycler);
        nowPlayingMoviesRecycler = (ShimmerRecyclerView) view.findViewById(R.id.nowPlayingMoviesRecycler);

        latestMovieTitle = (TextView) view.findViewById(R.id.latestMovieTitle);
        latestMovieOverView = (TextView) view.findViewById(R.id.latestMovieOverView);
        latestMoviePopularity = (TextView) view.findViewById(R.id.latestMoviePopularity);
        latestMovieReleaseStatus = (TextView) view.findViewById(R.id.latestMovieReleaseStatus);
        latestMovieReleaseDate = (TextView) view.findViewById(R.id.latestMovieReleaseDate);
        latestMovieGenre = (TextView) view.findViewById(R.id.latestMovieGenre);
        latestMovieSeasons = (TextView) view.findViewById(R.id.latestMovieSeasons);
        latestMovieNetworks = (TextView) view.findViewById(R.id.latestMovieNetworks);


        latestMovieImage = (KenBurnsView) view.findViewById(R.id.latestMovieImage);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setProgressViewOffset(false, 0, top_to_padding);

        nowPlayingMoviesRecycler.showShimmerAdapter();
        topRatedMoviesRecycler.showShimmerAdapter();
        upComingMovieRecycler.showShimmerAdapter();
        nowPlayingMoviesRecycler.showShimmerAdapter();

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onRefresh() {
        updateAllMovieViews(true);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateAllMovieViews(false);
    }

    private void loadLatestTVShow() {
        if (ConnectionManager.isNetworkAvailable(getContext())) {
            try {
                Client Client = new Client();
                Service apiService =
                        Client.getClient().create(Service.class);
                Call<LatestTVShowsResponse> call = apiService.getLatestTVShows(BuildConfig.THE_MOVIE_DB_API_TOKEN);
                call.enqueue(new Callback<LatestTVShowsResponse>() {
                    @Override
                    public void onResponse(Call<LatestTVShowsResponse> call, Response<LatestTVShowsResponse> response) {
                        Paper.book().write("latestMovieResponseCache", new Gson().toJson(response.body()));
                        LatestTVShowsResponse movies = response.body();
                        onSuccessfullLatestLoad(movies);
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<LatestTVShowsResponse> call, Throwable t) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "Connection Error, Check connection settings and Try Again! ", Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        }
    }


    private void loadCurrentlyAiringTvShows() {
        if (ConnectionManager.isNetworkAvailable(getContext())) {
            try {
                Client Client = new Client();
                Service apiService =
                        Client.getClient().create(Service.class);
                Call<CurrentlyAiringTVShowsResponse> call = apiService.getCurrentlyAiringTVShows(BuildConfig.THE_MOVIE_DB_API_TOKEN);
                call.enqueue(new Callback<CurrentlyAiringTVShowsResponse>() {
                    @Override
                    public void onResponse(Call<CurrentlyAiringTVShowsResponse> call, Response<CurrentlyAiringTVShowsResponse> response) {
                        Paper.book().write("currentlyAiringTvShowsCache", new Gson().toJson(response.body()));
                        List<CurrentlyAiringTVShowsResult> movies = response.body().getCurrentlyAiringTVShowsResults();
                        onSuccessfullCurentlyAiringTvShowsResponse(movies);
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<CurrentlyAiringTVShowsResponse> call, Throwable t) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "Connection Error, Check connection settings and Try Again! ", Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        }
    }


    private void loadPopularTVShows() {
        if (ConnectionManager.isNetworkAvailable(getContext())) {
            try {
                Client Client = new Client();
                Service apiService =
                        Client.getClient().create(Service.class);
                Call<PopularTVShowsResponse> call = apiService.getPopularTVShows(BuildConfig.THE_MOVIE_DB_API_TOKEN);
                call.enqueue(new Callback<PopularTVShowsResponse>() {
                    @Override
                    public void onResponse(Call<PopularTVShowsResponse> call, Response<PopularTVShowsResponse> response) {
                        Paper.book().write("popularTvShowsResponseCache", new Gson().toJson(response.body()));
                        List<PopularTVShowsResult> movies = response.body().getPopularTVShowsResults();
                        onSuccessfullPopularTVShowsLoad(movies);
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<PopularTVShowsResponse> call, Throwable t) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "Connection Error, Check connection settings and Try Again! ", Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    /*
        private void loadUpComingMovies() {
            if (ConnectionManager.isNetworkAvailable(getContext())) {
                try {
                    Client Client = new Client();
                    Service apiService =
                            Client.getClient().create(Service.class);
                    Call<UpcomingMovieResponse> call = apiService.getUpcomingMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
                    call.enqueue(new Callback<UpcomingMovieResponse>() {
                        @Override
                        public void onResponse(Call<UpcomingMovieResponse> call, Response<UpcomingMovieResponse> response) {
                            Paper.book().write("upcomingMovieResponseCache", new Gson().toJson(response.body()));
                            List<UpcomingMovie> movies = response.body().getPopularTVShowsResults();
                            onSuccessfulLoadUpcomingMovies(movies);
                            swipeRefreshLayout.setRefreshing(false);
                        }

                        @Override
                        public void onFailure(Call<UpcomingMovieResponse> call, Throwable t) {
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getActivity(), "Connection Error, Check connection settings and Try Again! ", Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        }
    */
    private void loadTopRatedTVShows() {
        if (ConnectionManager.isNetworkAvailable(getContext())) {

            try {
                Client Client = new Client();
                Service apiService =
                        Client.getClient().create(Service.class);
                Call<TopRatedTVShowsResponse> call = apiService.getTopRatedTVShows(BuildConfig.THE_MOVIE_DB_API_TOKEN);
                call.enqueue(new Callback<TopRatedTVShowsResponse>() {
                    @Override
                    public void onResponse(Call<TopRatedTVShowsResponse> call, Response<TopRatedTVShowsResponse> response) {
                        Paper.book().write("topRatedTvShowsCache", new Gson().toJson(response.body()));
                        List<TopRatedTVShowsResult> movies = response.body().getTopRatedTVShowsResults();
                        onSuccessTopRatedTvShowsLoad(movies);
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<TopRatedTVShowsResponse> call, Throwable t) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "Connection Error, Check connection settings and Try Again! ", Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        }
    }


    private void onSuccessTopRatedTvShowsLoad(List<TopRatedTVShowsResult> movies) {
        if (movies != null) {
            topRatedMoviesRecycler.setItemAnimator(new DefaultItemAnimator());
            topRatedMoviesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            topRatedMoviesRecycler.setAdapter(new TopRatedTVShowsAdapter(getContext(), movies));
        }
    }

    private void onSuccessfullPopularTVShowsLoad(List<PopularTVShowsResult> movies) {
        if (movies != null) {
            popularMoviesRecycler.setItemAnimator(new DefaultItemAnimator());
            popularMoviesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            popularMoviesRecycler.setAdapter(new PopularTVShowsAdapter(getContext(), movies));
        }
    }

    private void onSuccessfullCurentlyAiringTvShowsResponse(List<CurrentlyAiringTVShowsResult> movies) {
        if (movies != null) {
            nowPlayingMoviesRecycler.setItemAnimator(new DefaultItemAnimator());
            nowPlayingMoviesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            nowPlayingMoviesRecycler.setAdapter(new CurrentlyAiringTVShowsAdapter(getContext(), movies));
        }
    }

    /*

    private void onSuccessfulLoadUpcomingMovies(List<UpcomingMovie> movies) {
        if (movies != null) {
            upComingMovieRecycler.setItemAnimator(new DefaultItemAnimator());
            upComingMovieRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            upComingMovieRecycler.setAdapter(new UpComingMoviesAdapter(getContext(), movies));
        }
    }

    */

    private void onSuccessfullLatestLoad(LatestTVShowsResponse movies) {
        if (movies != null) {
            try {
                latestMovieTitle.setText(movies.getName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                latestMoviePopularity.setText(movies.getPopularity());
            } catch (Exception e) {
                e.printStackTrace();
                latestMoviePopularity.setText("0");
            }
            try {
                latestMovieReleaseStatus.setText(movies.getStatus());
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                latestMovieReleaseDate.setText(movies.getFirstAirDate());
            } catch (Exception e) {
                e.printStackTrace();
                latestMovieReleaseDate.setText("unknown");
            }

            try {
                latestMovieOverView.setText(movies.getOverview());
            } catch (Exception e) {
                e.printStackTrace();
                latestMovieOverView.setText("unknown");
            }
            try {
                latestMovieGenre.setText(movies.getType());
            } catch (Exception e) {
                e.printStackTrace();
                latestMovieGenre.setText("unknown");
            }
            try {
                latestMovieSeasons.setText(movies.getSeasons().get(0).getSeasonNumber().toString());
            } catch (Exception e) {
                e.printStackTrace();
                latestMovieSeasons.setText("unknown");
            }
            try {
                latestMovieNetworks.setText(movies.getNetworks().get(0).getName());
            } catch (Exception e) {
                e.printStackTrace();
                latestMovieNetworks.setText("unknown");
            }
            try {
                latestMovieOverView.setShadowLayer(0.01f, -2, 2, getResources().getColor(R.color.text_grey));
                latestMovieReleaseDate.setShadowLayer(0.01f, -2, 2, getResources().getColor(R.color.text_grey));
                latestMovieReleaseStatus.setShadowLayer(0.01f, -2, 2, getResources().getColor(R.color.text_grey));
                latestMoviePopularity.setShadowLayer(0.01f, -2, 2, getResources().getColor(R.color.text_grey));
                latestMovieTitle.setShadowLayer(0.01f, -2, 2, getResources().getColor(R.color.text_grey));
                latestMovieGenre.setShadowLayer(0.01f, -2, 2, getResources().getColor(R.color.text_grey));
                latestMovieSeasons.setShadowLayer(0.01f, -2, 2, getResources().getColor(R.color.text_grey));
                latestMovieNetworks.setShadowLayer(0.01f, -2, 2, getResources().getColor(R.color.text_grey));

                Glide.with(this)
                        .load("https://image.tmdb.org/t/p/w500" + movies.getPosterPath())
                        .placeholder(R.drawable.ic_loading_img)
                        .into(latestMovieImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void updateAllMovieViews(boolean isRefreshed) {
        if (!isRefreshed) {
            String topRatedTvShowsCache = Paper.book().read("topRatedTvShowsCache");
            String currentlyAiringTvShowsCache = Paper.book().read("currentlyAiringTvShowsCache");
            String popularTvShowsResponseCache = Paper.book().read("popularTvShowsResponseCache");
            String upcomingMovieResponseCache = Paper.book().read("upcomingMovieResponseCache");
            String latestTVShowsResponseCache = Paper.book().read("latestTVShowsResponseCache");


            if (topRatedTvShowsCache != null && !topRatedTvShowsCache.isEmpty() && !topRatedTvShowsCache.equals("null")) {
                TopRatedTVShowsResponse moviesResponse = new Gson().fromJson(topRatedTvShowsCache, TopRatedTVShowsResponse.class);
                onSuccessTopRatedTvShowsLoad(moviesResponse.getTopRatedTVShowsResults());
            } else {
                loadTopRatedTVShows();
            }

            if (currentlyAiringTvShowsCache != null && !currentlyAiringTvShowsCache.isEmpty() && !currentlyAiringTvShowsCache.equals("null")) {
                CurrentlyAiringTVShowsResponse nowPlayingMovieResponse = new Gson().fromJson(currentlyAiringTvShowsCache, CurrentlyAiringTVShowsResponse.class);
                onSuccessfullCurentlyAiringTvShowsResponse(nowPlayingMovieResponse.getCurrentlyAiringTVShowsResults());
            } else {
                loadCurrentlyAiringTvShows();
            }

            if (popularTvShowsResponseCache != null && !popularTvShowsResponseCache.isEmpty() && !popularTvShowsResponseCache.equals("null")) {
                PopularTVShowsResponse popularMoviesResponse = new Gson().fromJson(popularTvShowsResponseCache, PopularTVShowsResponse.class);
                onSuccessfullPopularTVShowsLoad(popularMoviesResponse.getPopularTVShowsResults());
            } else {
                loadPopularTVShows();
            }
/*
            if (upcomingMovieResponseCache != null && !upcomingMovieResponseCache.isEmpty() && !upcomingMovieResponseCache.equals("null")) {
                UpcomingMovieResponse upcomingMovieResponse = new Gson().fromJson(upcomingMovieResponseCache, UpcomingMovieResponse.class);
                onSuccessfulLoadUpcomingMovies(upcomingMovieResponse.getPopularTVShowsResults());
            } else {
                loadUpComingMovies();
            }
            */

            if (latestTVShowsResponseCache != null && !latestTVShowsResponseCache.isEmpty() && !latestTVShowsResponseCache.equals("null")) {
                LatestTVShowsResponse latestTVShowsResponse = new Gson().fromJson(latestTVShowsResponseCache, LatestTVShowsResponse.class);
                onSuccessfullLatestLoad(latestTVShowsResponse);
            } else {
                loadLatestTVShow();
            }
        } else {
            swipeRefreshLayout.setRefreshing(true);
            loadTopRatedTVShows();
            loadCurrentlyAiringTvShows();
            loadPopularTVShows();
            //loadUpComingMovies();
            loadLatestTVShow();
        }
    }
}
