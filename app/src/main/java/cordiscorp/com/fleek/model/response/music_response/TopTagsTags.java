package cordiscorp.com.fleek.model.response.music_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ibkunle Adeoluwa on 2/27/2019.
 */
public class TopTagsTags {

    @SerializedName("tag")
    @Expose
    private List<TopTagsTag> tag = null;
    @SerializedName("@attr")
    @Expose
    private TopTagsAttr attr;

    public List<TopTagsTag> getTag() {
        return tag;
    }

    public void setTag(List<TopTagsTag> tag) {
        this.tag = tag;
    }

    public TopTagsAttr getAttr() {
        return attr;
    }

    public void setAttr(TopTagsAttr attr) {
        this.attr = attr;
    }

}