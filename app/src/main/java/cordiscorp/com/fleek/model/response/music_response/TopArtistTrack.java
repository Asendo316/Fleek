package cordiscorp.com.fleek.model.response.music_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ibkunle Adeoluwa on 2/27/2019.
 */
public class TopArtistTrack {

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
    private TopArtistStreamable streamable;
    @SerializedName("artist")
    @Expose
    private TopArtistArtist artist;
    @SerializedName("image")
    @Expose
    private List<TopArtistImage> image = null;

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

    public TopArtistStreamable getStreamable() {
        return streamable;
    }

    public void setStreamable(TopArtistStreamable streamable) {
        this.streamable = streamable;
    }

    public TopArtistArtist getArtist() {
        return artist;
    }

    public void setArtist(TopArtistArtist artist) {
        this.artist = artist;
    }

    public List<TopArtistImage> getImage() {
        return image;
    }

    public void setImage(List<TopArtistImage> image) {
        this.image = image;
    }

}
