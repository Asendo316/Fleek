package cordiscorp.com.fleek.model.response.music_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ibkunle Adeoluwa on 2/27/2019.
 */
public class TopTracksGeoResponse {

    @SerializedName("tracks")
    @Expose
    private TopTracksGeoTracks tracks;

    public TopTracksGeoTracks getTracks() {
        return tracks;
    }

    public void setTracks(TopTracksGeoTracks tracks) {
        this.tracks = tracks;
    }

}