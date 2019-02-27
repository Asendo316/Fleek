package cordiscorp.com.fleek.model.response.music_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ibkunle Adeoluwa on 2/27/2019.
 */
public class TopTagsTag {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("reach")
    @Expose
    private String reach;
    @SerializedName("taggings")
    @Expose
    private String taggings;
    @SerializedName("streamable")
    @Expose
    private String streamable;
    @SerializedName("wiki")
    @Expose
    private TopTagsWiki wiki;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getReach() {
        return reach;
    }

    public void setReach(String reach) {
        this.reach = reach;
    }

    public String getTaggings() {
        return taggings;
    }

    public void setTaggings(String taggings) {
        this.taggings = taggings;
    }

    public String getStreamable() {
        return streamable;
    }

    public void setStreamable(String streamable) {
        this.streamable = streamable;
    }

    public TopTagsWiki getWiki() {
        return wiki;
    }

    public void setWiki(TopTagsWiki wiki) {
        this.wiki = wiki;
    }

}
