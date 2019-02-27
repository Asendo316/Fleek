package cordiscorp.com.fleek.model.response.music_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ibkunle Adeoluwa on 2/27/2019.
 */
public class TopTrackTracks {

    @SerializedName("track")
    @Expose
    private List<TopTrackTrack> track = null;
    @SerializedName("@attr")
    @Expose
    private TopTrackAttr attr;

    public List<TopTrackTrack> getTrack() {
        return track;
    }

    public void setTrack(List<TopTrackTrack> track) {
        this.track = track;
    }

    public TopTrackAttr getAttr() {
        return attr;
    }

    public void setAttr(TopTrackAttr attr) {
        this.attr = attr;
    }

}
