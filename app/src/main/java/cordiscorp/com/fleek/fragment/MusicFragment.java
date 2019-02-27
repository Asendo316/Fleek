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
import android.widget.Toast;

import com.cooltechworks.views.shimmer.ShimmerRecyclerView;
import com.google.gson.Gson;

import cordiscorp.com.fleek.BuildConfig;
import cordiscorp.com.fleek.R;
import cordiscorp.com.fleek.connection.ConnectionManager;
import cordiscorp.com.fleek.connection.MusicClient;
import cordiscorp.com.fleek.connection.MusicService;
import cordiscorp.com.fleek.model.response.music_response.TopTracksResponse;
import cordiscorp.com.fleek.model.response.music_response.TopTracksTracks;
import io.paperdb.Paper;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MusicFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {


    public MusicFragment() {
        // Required empty public constructor
    }

    private SwipeRefreshLayout swipeRefreshLayout;
    private ShimmerRecyclerView topListenedTracks, topRatedMoviesRecycler, upComingMovieRecycler, nowPlayingMoviesRecycler;

    int top_to_padding = 190;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_music, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Paper.init(getContext());

        topListenedTracks = (ShimmerRecyclerView) view.findViewById(R.id.topListenedTracks);


        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        swipeRefreshLayout.setProgressViewOffset(false, 0, top_to_padding);

        topListenedTracks.showShimmerAdapter();

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateAllMusicViews(false);
    }

    private void getTopTracks() {
        if (ConnectionManager.isNetworkAvailable(getContext())) {
            try {
                MusicClient Client = new MusicClient();
                MusicService apiService =
                        Client.getClient().create(MusicService.class);
                Call<TopTracksResponse> call = apiService.getTopTracks("chart.gettoptracks", BuildConfig.LastFMApiToken, "json");
                call.enqueue(new Callback<TopTracksResponse>() {
                    @Override
                    public void onResponse(Call<TopTracksResponse> call, Response<TopTracksResponse> response) {
                        //Paper.book().write("latestMovieResponseCache", new Gson().toJson(response.body()));
                        TopTracksResponse movies = response.body();
                        //onSuccessfullLatestLoad(movies);
                        //swipeRefreshLayout.setRefreshing(false);
                        System.out.print(movies);
                    }

                    @Override
                    public void onFailure(Call<TopTracksResponse> call, Throwable t) {
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Toast.makeText(getActivity(), "Connection Error, Check connection settings and Try Again! ", Toast.LENGTH_SHORT).show();
            //swipeRefreshLayout.setRefreshing(false);
        }
    }

    private void updateAllMusicViews(boolean isRefreshed) {
        if (!isRefreshed) {
            String topTracksCache = Paper.book().read("topRatedTvShowsCache");
            /**
             String currentlyAiringTvShowsCache = Paper.book().read("currentlyAiringTvShowsCache");
             String popularTvShowsResponseCache = Paper.book().read("popularTvShowsResponseCache");
             String tvsShowsAiringTodayResponseCache = Paper.book().read("tvsShowsAiringTodayResponseCache");
             String latestTVShowsResponseCache = Paper.book().read("latestTVShowsResponseCache");
             **/


            if (topTracksCache != null && !topTracksCache.isEmpty() && !topTracksCache.equals("null")) {
                TopTracksResponse topTracksResponse = new Gson().fromJson(topTracksCache, TopTracksResponse.class);
                onSuccessTopTracksLoad(topTracksResponse.getTracks());
            } else {
                //loadTopRatedTVShows();
            }

            /**
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

             if (tvsShowsAiringTodayResponseCache != null && !tvsShowsAiringTodayResponseCache.isEmpty() && !tvsShowsAiringTodayResponseCache.equals("null")) {
             AiringTodayTVShowsResponse upcomingMovieResponse = new Gson().fromJson(tvsShowsAiringTodayResponseCache, AiringTodayTVShowsResponse.class);
             onSuccessfulLoadTvShowsAiringToday(upcomingMovieResponse.getAiringTodayTVShowsResults());
             } else {
             loadTvShowsAiringToday();
             }


             if (latestTVShowsResponseCache != null && !latestTVShowsResponseCache.isEmpty() && !latestTVShowsResponseCache.equals("null")) {
             LatestTVShowsResponse latestTVShowsResponse = new Gson().fromJson(latestTVShowsResponseCache, LatestTVShowsResponse.class);
             onSuccessfullLatestLoad(latestTVShowsResponse);
             } else {
             loadLatestTVShow();
             }

             **/
        } else {

            swipeRefreshLayout.setRefreshing(true);
            getTopTracks();
            /**
             loadCurrentlyAiringTvShows();
             loadPopularTVShows();
             loadTvShowsAiringToday();
             loadLatestTVShow();*/
        }
    }

    private void onSuccessTopTracksLoad(TopTracksTracks tracks) {
        if (tracks != null) {
            topListenedTracks.setItemAnimator(new DefaultItemAnimator());
            topListenedTracks.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
            //upComingMovieRecycler.setAdapter(new AiringTodayTVShowsAdapter(getContext(), movies));
        }
    }

    @Override
    public void onRefresh() {
        updateAllMusicViews(true);
    }
}
