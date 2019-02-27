package cordiscorp.com.fleek.connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Ibkunle Adeoluwa on 2/18/2019.
 */
public class MusicClient {
    public static final String BASE_URL = "http://ws.audioscrobbler.com";
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
