package com.odujokod.ztp_newsfeed.model;

public class Posts {
    private String description;
    private String media_url;
    private String thumbnail_url;
    private String comment_count;
    private String like_count;
    private String post_shares_count;
    private User user_info;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMedia_url() {
        return media_url;
    }

    public void setMedia_url(String media_url) {
        this.media_url = media_url;
    }

    public String getThumbnail_url() {
        return thumbnail_url;
    }

    public void setThumbnail_url(String thumbnail_url) {
        this.thumbnail_url = thumbnail_url;
    }

    public String getComment_count() {
        return comment_count;
    }

    public void setComment_count(String comment_count) {
        this.comment_count = comment_count;
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }

    public String getPost_shares_count() {
        return post_shares_count;
    }

    public void setPost_shares_count(String post_shares_count) {
        this.post_shares_count = post_shares_count;
    }

    public User getUser_info() {
        return user_info;
    }

    public void setUser_info(User user_info) {
        this.user_info = user_info;
    }
}
