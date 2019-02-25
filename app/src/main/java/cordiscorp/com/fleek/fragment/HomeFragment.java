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
import cordiscorp.com.fleek.adapter.NowPlayingMoviesAdapter;
import cordiscorp.com.fleek.adapter.PopularMoviesAdapter;
import cordiscorp.com.fleek.adapter.TopRatedMoviesAdapter;
import cordiscorp.com.fleek.adapter.UpComingMoviesAdapter;
import cordiscorp.com.fleek.connection.Client;
import cordiscorp.com.fleek.connection.ConnectionManager;
import cordiscorp.com.fleek.connection.Service;
import cordiscorp.com.fleek.model.response.LatestMovieResponse;
import cordiscorp.com.fleek.model.response.Movie;
import cordiscorp.com.fleek.model.response.MoviesResponse;
import cordiscorp.com.fleek.model.response.NowPlayingMovie;
import cordiscorp.com.fleek.model.response.NowPlayingMovieResponse;
import cordiscorp.com.fleek.model.response.PopularMovie;
import cordiscorp.com.fleek.model.response.PopularMoviesResponse;
import cordiscorp.com.fleek.model.response.UpcomingMovie;
import cordiscorp.com.fleek.model.response.UpcomingMovieResponse;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    public HomeFragment() {
        // Required empty public constructor
    }

    private ShimmerRecyclerView popularMoviesRecycler, topRatedMoviesRecycler, upComingMovieRecycler, nowPlayingMoviesRecycler;
    private TextView latestMovieTitle, latestMovieOverView, latestMoviePopularity, latestMovieReleaseStatus, latestMovieReleaseDate;
    private KenBurnsView latestMovieImage;
    private SwipeRefreshLayout swipeRefreshLayout;
    int top_to_padding = 190;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
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

    private void loadLatestMovie() {
        if (ConnectionManager.isNetworkAvailable(getContext())) {
            try {
                Client Client = new Client();
                Service apiService =
                        Client.getClient().create(Service.class);
                Call<LatestMovieResponse> call = apiService.getLatestMovie(BuildConfig.THE_MOVIE_DB_API_TOKEN);
                call.enqueue(new Callback<LatestMovieResponse>() {
                    @Override
                    public void onResponse(Call<LatestMovieResponse> call, Response<LatestMovieResponse> response) {
                        Paper.book().write("latestMovieResponseCache", new Gson().toJson(response.body()));
                        LatestMovieResponse movies = response.body();
                        onSuccessfullLatestLoad(movies);
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<LatestMovieResponse> call, Throwable t) {
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

    private void loadNowPlayingMovies() {
        if (ConnectionManager.isNetworkAvailable(getContext())) {
            try {
                Client Client = new Client();
                Service apiService =
                        Client.getClient().create(Service.class);
                Call<NowPlayingMovieResponse> call = apiService.getNowPlaying(BuildConfig.THE_MOVIE_DB_API_TOKEN);
                call.enqueue(new Callback<NowPlayingMovieResponse>() {
                    @Override
                    public void onResponse(Call<NowPlayingMovieResponse> call, Response<NowPlayingMovieResponse> response) {
                        Paper.book().write("nowPlayingMovieResponseCache", new Gson().toJson(response.body()));
                        List<NowPlayingMovie> movies = response.body().getResults();
                        onSuccessfullNowPlayingMoviesLoad(movies);
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<NowPlayingMovieResponse> call, Throwable t) {
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

    private void loadPopularMovies() {
        if (ConnectionManager.isNetworkAvailable(getContext())) {
            try {
                Client Client = new Client();
                Service apiService =
                        Client.getClient().create(Service.class);
                Call<PopularMoviesResponse> call = apiService.getPopularMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
                call.enqueue(new Callback<PopularMoviesResponse>() {
                    @Override
                    public void onResponse(Call<PopularMoviesResponse> call, Response<PopularMoviesResponse> response) {
                        Paper.book().write("popularMoviesResponseCache", new Gson().toJson(response.body()));
                        List<PopularMovie> movies = response.body().getResults();
                        onSuccessfullPopularMoviesLoad(movies);
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<PopularMoviesResponse> call, Throwable t) {
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
                        List<UpcomingMovie> movies = response.body().getResults();
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

    private void loadTopRatedMovies() {
        if (ConnectionManager.isNetworkAvailable(getContext())) {

            try {
                Client Client = new Client();
                Service apiService =
                        Client.getClient().create(Service.class);
                Call<MoviesResponse> call = apiService.getTopRatedMovies(BuildConfig.THE_MOVIE_DB_API_TOKEN);
                call.enqueue(new Callback<MoviesResponse>() {
                    @Override
                    public void onResponse(Call<MoviesResponse> call, Response<MoviesResponse> response) {
                        Paper.book().write("moviesResponseCache", new Gson().toJson(response.body()));
                        List<Movie> movies = response.body().getResults();
                        onSuccessTopRatedMoviesLoad(movies);
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onFailure(Call<MoviesResponse> call, Throwable t) {
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


    private void onSuccessTopRatedMoviesLoad(List<Movie> movies) {
        if (movies != null) {
            topRatedMoviesRecycler.setItemAnimator(new DefaultItemAnimator());
            topRatedMoviesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            topRatedMoviesRecycler.setAdapter(new TopRatedMoviesAdapter(getContext(), movies));
        }
    }

    private void onSuccessfullPopularMoviesLoad(List<PopularMovie> movies) {
        if (movies != null) {
            popularMoviesRecycler.setItemAnimator(new DefaultItemAnimator());
            popularMoviesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            popularMoviesRecycler.setAdapter(new PopularMoviesAdapter(getContext(), movies));
        }
    }

    private void onSuccessfullNowPlayingMoviesLoad(List<NowPlayingMovie> movies) {
        if (movies != null) {
            nowPlayingMoviesRecycler.setItemAnimator(new DefaultItemAnimator());
            nowPlayingMoviesRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            nowPlayingMoviesRecycler.setAdapter(new NowPlayingMoviesAdapter(getContext(), movies));
        }
    }

    private void onSuccessfulLoadUpcomingMovies(List<UpcomingMovie> movies) {
        if (movies != null) {
            upComingMovieRecycler.setItemAnimator(new DefaultItemAnimator());
            upComingMovieRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            upComingMovieRecycler.setAdapter(new UpComingMoviesAdapter(getContext(), movies));
        }
    }

    private void onSuccessfullLatestLoad(LatestMovieResponse movies) {
        if (movies != null) {
            try {
                latestMovieTitle.setText(movies.getTitle());
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
                latestMovieReleaseDate.setText(movies.getRelease_date());
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
                latestMovieOverView.setShadowLayer(0.01f, -2, 2, getResources().getColor(R.color.text_grey));
                latestMovieReleaseDate.setShadowLayer(0.01f, -2, 2, getResources().getColor(R.color.text_grey));
                latestMovieReleaseStatus.setShadowLayer(0.01f, -2, 2, getResources().getColor(R.color.text_grey));
                latestMoviePopularity.setShadowLayer(0.01f, -2, 2, getResources().getColor(R.color.text_grey));
                latestMovieTitle.setShadowLayer(0.01f, -2, 2, getResources().getColor(R.color.text_grey));

                Glide.with(this)
                        .load("https://image.tmdb.org/t/p/w500" + movies.getPoster_path())
                        .placeholder(R.drawable.ic_loading_img)
                        .into(latestMovieImage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    private void updateAllMovieViews(boolean isRefreshed) {
        if (!isRefreshed) {
            String moviesResponseCache = Paper.book().read("moviesResponseCache");
            String nowPlayingMovieResponseCache = Paper.book().read("nowPlayingMovieResponseCache");
            String popularMoviesResponseCache = Paper.book().read("popularMoviesResponseCache");
            String upcomingMovieResponseCache = Paper.book().read("upcomingMovieResponseCache");
            String latestMovieResponseCache = Paper.book().read("latestMovieResponseCache");

            if (moviesResponseCache != null && !moviesResponseCache.isEmpty() && !moviesResponseCache.equals("null")) {
                MoviesResponse moviesResponse = new Gson().fromJson(moviesResponseCache, MoviesResponse.class);
                onSuccessTopRatedMoviesLoad(moviesResponse.getResults());
            } else {
                loadTopRatedMovies();
            }

            if (nowPlayingMovieResponseCache != null && !nowPlayingMovieResponseCache.isEmpty() && !nowPlayingMovieResponseCache.equals("null")) {
                NowPlayingMovieResponse nowPlayingMovieResponse = new Gson().fromJson(nowPlayingMovieResponseCache, NowPlayingMovieResponse.class);
                onSuccessfullNowPlayingMoviesLoad(nowPlayingMovieResponse.getResults());
            } else {
                loadNowPlayingMovies();
            }

            if (popularMoviesResponseCache != null && !popularMoviesResponseCache.isEmpty() && !popularMoviesResponseCache.equals("null")) {
                PopularMoviesResponse popularMoviesResponse = new Gson().fromJson(popularMoviesResponseCache, PopularMoviesResponse.class);
                onSuccessfullPopularMoviesLoad(popularMoviesResponse.getResults());
            } else {
                loadPopularMovies();
            }

            if (upcomingMovieResponseCache != null && !upcomingMovieResponseCache.isEmpty() && !upcomingMovieResponseCache.equals("null")) {
                UpcomingMovieResponse upcomingMovieResponse = new Gson().fromJson(upcomingMovieResponseCache, UpcomingMovieResponse.class);
                onSuccessfulLoadUpcomingMovies(upcomingMovieResponse.getResults());
            } else {
                loadUpComingMovies();
            }

            if (latestMovieResponseCache != null && !latestMovieResponseCache.isEmpty() && !latestMovieResponseCache.equals("null")) {
                LatestMovieResponse latestMovieResponse = new Gson().fromJson(latestMovieResponseCache, LatestMovieResponse.class);
                onSuccessfullLatestLoad(latestMovieResponse);
            } else {
                loadLatestMovie();
            }
        } else {
            swipeRefreshLayout.setRefreshing(true);
            loadTopRatedMovies();
            loadNowPlayingMovies();
            loadPopularMovies();
            loadUpComingMovies();
            loadLatestMovie();
        }
    }
}

