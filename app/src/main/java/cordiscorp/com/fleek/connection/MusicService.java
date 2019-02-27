package cordiscorp.com.fleek.connection;

import cordiscorp.com.fleek.model.response.music_response.TopArtistResponse;
import cordiscorp.com.fleek.model.response.music_response.TopTagsResponse;
import cordiscorp.com.fleek.model.response.music_response.TopTracksResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ibkunle Adeoluwa on 2/27/2019.
 */
public interface MusicService {

    //http://ws.audioscrobbler.com/2.0/?method=chart.gettoptracks&api_key=5b610dead62582230a50f45f4a3a50d1&format=json
    @GET("/2.0")
    Call<TopTracksResponse> getTopTracks(@Query("method") String method, @Query("api_key") String apiKey, @Query("format") String format);

    //http://ws.audioscrobbler.com/2.0/?method=chart.gettopartists&api_key=5b610dead62582230a50f45f4a3a50d1&format=json
    @GET("/2.0")
    Call<TopArtistResponse> getTopArtist(@Query("method") String method, @Query("api_key") String apiKey, @Query("format") String format);

    //http://ws.audioscrobbler.com/2.0/?method=chart.gettoptags&api_key=5b610dead62582230a50f45f4a3a50d1&format=json
    @GET("/2.0")
    Call<TopTagsResponse> getTopTags(@Query("method") String method, @Query("api_key") String apiKey, @Query("format") String format);
}
