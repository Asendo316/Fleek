package cordiscorp.com.fleek.connection;

import cordiscorp.com.fleek.model.response.movies_response.AiringTodayTVShowsResponse;
import cordiscorp.com.fleek.model.response.movies_response.CurrentlyAiringTVShowsResponse;
import cordiscorp.com.fleek.model.response.movies_response.LatestMovieResponse;
import cordiscorp.com.fleek.model.response.movies_response.LatestTVShowsResponse;
import cordiscorp.com.fleek.model.response.movies_response.MoviesResponse;
import cordiscorp.com.fleek.model.response.movies_response.NowPlayingMovieResponse;
import cordiscorp.com.fleek.model.response.movies_response.PopularMoviesResponse;
import cordiscorp.com.fleek.model.response.movies_response.PopularTVShowsResponse;
import cordiscorp.com.fleek.model.response.movies_response.TopRatedTVShowsResponse;
import cordiscorp.com.fleek.model.response.movies_response.UpcomingMovieResponse;
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
    Call<CurrentlyAiringTVShowsResponse>getCurrentlyAiringTVShows(@Query("api_key") String apiKey);

    @GET("tv/airing_today")
    Call<AiringTodayTVShowsResponse>getTVShowsAiringToday(@Query("api_key") String apiKey);
}
