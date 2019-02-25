package cordiscorp.com.fleek.connection;

import cordiscorp.com.fleek.model.response.LatestMovieResponse;
import cordiscorp.com.fleek.model.response.LatestTVShowsResponse;
import cordiscorp.com.fleek.model.response.MoviesResponse;
import cordiscorp.com.fleek.model.response.NowPlayingMovieResponse;
import cordiscorp.com.fleek.model.response.PopularMoviesResponse;
import cordiscorp.com.fleek.model.response.PopularTVShowsResponse;
import cordiscorp.com.fleek.model.response.TopRatedTVShowsResponse;
import cordiscorp.com.fleek.model.response.UpcomingMovieResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ibkunle Adeoluwa on 2/19/2019.
 */
public interface Service {

    /**
     * Movies URLS
     * @param apiKey
     * @return
     */
    @GET("movie/latest")
    Call<LatestMovieResponse>getLatestMovie(@Query("api_key") String apiKey);

    @GET("movie/top_rated")
    Call<MoviesResponse>getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/upcoming")
    Call<UpcomingMovieResponse>getUpcomingMovies(@Query("api_key") String apiKey);

    @GET("movie/now_playing")
    Call<NowPlayingMovieResponse>getNowPlaying(@Query("api_key") String apiKey);

    @GET("movie/popular")
    Call<PopularMoviesResponse>getPopularMovies(@Query("api_key") String apiKey);



    /**
     * TV SHows
     * @param apiKey
     * @return
     */

    @GET("tv/latest")
    Call<LatestTVShowsResponse>getLatestTVShows(@Query("api_key") String apiKey);

    @GET("tv/top_rated")
    Call<TopRatedTVShowsResponse>getTopRatedTVShows(@Query("api_key") String apiKey);

    @GET("tv/popular")
    Call<PopularTVShowsResponse>getPopularTVShows(@Query("api_key") String apiKey);



    @GET("tv/on_the_air")
    Call<MoviesResponse>getCurrentlyAiringTVShows(@Query("api_key") String apiKey);

    @GET("tv/airing_today")
    Call<MoviesResponse>getTVShowsAiringToday(@Query("api_key") String apiKey);
}
