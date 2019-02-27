package cordiscorp.com.fleek.model.response.music_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ibkunle Adeoluwa on 2/27/2019.
 */

public class TopTrackTrack {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("playcount")
    @Expose
    private String playcount;
    @SerializedName("listeners")
    @Expose
    private String listeners;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("streamable")
    @Expose
    private TopTrackStreamable streamable;
    @SerializedName("artist")
    @Expose
    private TopTacksArtist artist;
    @SerializedName("image")
    @Expose
    private List<TopTrackImage> image = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPlaycount() {
        return playcount;
    }

    public void setPlaycount(String playcount) {
        this.playcount = playcount;
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TopTrackStreamable getStreamable() {
        return streamable;
    }

    public void setStreamable(TopTrackStreamable streamable) {
        this.streamable = streamable;
    }

    public TopTacksArtist getArtist() {
        return artist;
    }

    public void setArtist(TopTacksArtist artist) {
        this.artist = artist;
    }

    public List<TopTrackImage> getImage() {
        return image;
    }

    public void setImage(List<TopTrackImage> image) {
        this.image = image;
    }

}
