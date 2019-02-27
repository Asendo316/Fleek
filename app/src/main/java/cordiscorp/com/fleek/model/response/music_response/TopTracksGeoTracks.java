package cordiscorp.com.fleek.model.response.music_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ibkunle Adeoluwa on 2/27/2019.
 */
public class TopTracksGeoTracks {

    @SerializedName("track")
    @Expose
    private List<TopTracksGeoTrack> track = null;
    @SerializedName("@attr")
    @Expose
    private TopTracksGeoAttr_ attr;

    public List<TopTracksGeoTrack> getTrack() {
        return track;
    }

    public void setTrack(List<TopTracksGeoTrack> track) {
        this.track = track;
    }

    public TopTracksGeoAttr_ getAttr() {
        return attr;
    }

    public void setAttr(TopTracksGeoAttr_ attr) {
        this.attr = attr;
    }

}
