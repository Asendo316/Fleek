package cordiscorp.com.fleek.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ibkunle Adeoluwa on 2/20/2019.
 */
public class NowPlayingTVShowsResponse {
    @SerializedName("page")
    @Expose
    private Integer page;
    @SerializedName("results")
    @Expose
    private List<NowPlayingTVShowsResult> nowPlayingTVShowsResults;
    @SerializedName("total_results")
    @Expose
    private Integer totalResults;
    @SerializedName("total_pages")
    @Expose
    private Integer totalPages;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<NowPlayingTVShowsResult> getNowPlayingTVShowsResults() {
        return nowPlayingTVShowsResults;
    }

    public void setNowPlayingTVShowsResults(List<NowPlayingTVShowsResult> nowPlayingTVShowsResults) {
        this.nowPlayingTVShowsResults = nowPlayingTVShowsResults;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

}
