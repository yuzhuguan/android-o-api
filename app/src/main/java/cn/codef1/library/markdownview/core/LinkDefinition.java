package cn.codef1.library.markdownview.core;

/**
 * Created by CoderF1 on 2017/11/9.
 */

public class LinkDefinition {
    private String url;
    private String title;

    public LinkDefinition(String url, String title) {
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return url + " (" + title + ")";
    }
}