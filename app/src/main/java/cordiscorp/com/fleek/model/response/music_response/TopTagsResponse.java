package cordiscorp.com.fleek.model.response.music_response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ibkunle Adeoluwa on 2/27/2019.
 */
public class TopTagsResponse {

    @SerializedName("tags")
    @Expose
    private TopTagsTags tags;

    public TopTagsTags getTags() {
        return tags;
    }

    public void setTags(TopTagsTags tags) {
        this.tags = tags;
    }

}
