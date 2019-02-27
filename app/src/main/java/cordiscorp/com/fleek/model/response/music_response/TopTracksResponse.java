package cordiscorp.com.fleek.model.response.music_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ibkunle Adeoluwa on 2/27/2019.
 */
public class TopTracksResponse {
    @SerializedName("tracks")
    @Expose
    private TopTracksTracks tracks;

    public TopTracksTracks getTracks() {
        return tracks;
    }

    public void setTracks(TopTracksTracks tracks) {
        this.tracks = tracks;
    }

}
