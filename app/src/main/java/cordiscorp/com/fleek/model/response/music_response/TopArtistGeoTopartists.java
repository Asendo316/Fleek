package cordiscorp.com.fleek.model.response.music_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ibkunle Adeoluwa on 2/27/2019.
 */
public class TopArtistGeoTopartists {

    @SerializedName("artist")
    @Expose
    private List<TopArtistGeoArtist> artist = null;
    @SerializedName("@attr")
    @Expose
    private TopArtistGeoAttr attr;

    public List<TopArtistGeoArtist> getArtist() {
        return artist;
    }

    public void setArtist(List<TopArtistGeoArtist> artist) {
        this.artist = artist;
    }

    public TopArtistGeoAttr getAttr() {
        return attr;
    }

    public void setAttr(TopArtistGeoAttr attr) {
        this.attr = attr;
    }

}
