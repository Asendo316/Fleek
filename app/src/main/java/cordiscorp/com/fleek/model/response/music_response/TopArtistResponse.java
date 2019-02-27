package cordiscorp.com.fleek.model.response.music_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ibkunle Adeoluwa on 2/27/2019.
 */
public class TopArtistResponse {

    @SerializedName("tracks")
    @Expose
    private TopArtistTracks tracks;

    public TopArtistTracks getTracks() {
        return tracks;
    }

    public void setTracks(TopArtistTracks tracks) {
        this.tracks = tracks;
    }
}

