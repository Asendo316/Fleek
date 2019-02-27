package cordiscorp.com.fleek.model.response.music_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ibkunle Adeoluwa on 2/27/2019.
 */
public class TopArtistTracks {

    @SerializedName("track")
    @Expose
    private List<TopArtistTrack> track = null;
    @SerializedName("@attr")
    @Expose
    private TopArtistAttr attr;

    public List<TopArtistTrack> getTrack() {
        return track;
    }

    public void setTrack(List<TopArtistTrack> track) {
        this.track = track;
    }

    public TopArtistAttr getAttr() {
        return attr;
    }

    public void setAttr(TopArtistAttr attr) {
        this.attr = attr;
    }

}