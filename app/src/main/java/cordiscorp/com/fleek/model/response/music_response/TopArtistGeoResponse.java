package cordiscorp.com.fleek.model.response.music_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ibkunle Adeoluwa on 2/27/2019.
 */
public class TopArtistGeoResponse {
    @SerializedName("topartists")
    @Expose
    private TopArtistGeoTopartists topartists;

    public TopArtistGeoTopartists getTopartists() {
        return topartists;
    }

    public void setTopartists(TopArtistGeoTopartists topartists) {
        this.topartists = topartists;
    }

}
