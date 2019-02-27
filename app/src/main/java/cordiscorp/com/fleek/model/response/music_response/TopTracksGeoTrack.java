package cordiscorp.com.fleek.model.response.music_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


import java.util.List;

/**
 * Created by Ibkunle Adeoluwa on 2/27/2019.
 */
public class TopTracksGeoTrack {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("duration")
    @Expose
    private String duration;
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
    private TopTracksGeoStreamable streamable;
    @SerializedName("artist")
    @Expose
    private TopTracksGeoArtist artist;
    @SerializedName("image")
    @Expose
    private List<TopTracksGeoImage> image = null;
    @SerializedName("@attr")
    @Expose
    private TopTracksGeoAttr attr;

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

    public TopTracksGeoStreamable getStreamable() {
        return streamable;
    }

    public void setStreamable(TopTracksGeoStreamable streamable) {
        this.streamable = streamable;
    }

    public TopTracksGeoArtist getArtist() {
        return artist;
    }

    public void setArtist(TopTracksGeoArtist artist) {
        this.artist = artist;
    }

    public List<TopTracksGeoImage> getImage() {
        return image;
    }

    public void setImage(List<TopTracksGeoImage> image) {
        this.image = image;
    }

    public TopTracksGeoAttr getAttr() {
        return attr;
    }

    public void setAttr(TopTracksGeoAttr attr) {
        this.attr = attr;
    }

}