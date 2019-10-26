package kr.co.bnksys.todoapp.data.movie.remote.service.pojo;

import com.google.gson.annotations.SerializedName;

public class Movie {

    private Long id;
    private String url;
    private String imdbCode;
    private String title;
    private String title_english;
    private String title_long;
    private String slug;
    private Integer year;
    private Double rating;
    private Long runtime;
    @SerializedName(value = "small_cover_image")
    private String smallCoverImage;
    private String summary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImdbCode() {
        return imdbCode;
    }

    public void setImdbCode(String imdbCode) {
        this.imdbCode = imdbCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle_english() {
        return title_english;
    }

    public void setTitle_english(String title_english) {
        this.title_english = title_english;
    }

    public String getTitle_long() {
        return title_long;
    }

    public void setTitle_long(String title_long) {
        this.title_long = title_long;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Long getRuntime() {
        return runtime;
    }

    public void setRuntime(Long runtime) {
        this.runtime = runtime;
    }

    public String getSmallCoverImage() {
        return smallCoverImage;
    }

    public void setSmallCoverImage(String smallCoverImage) {
        this.smallCoverImage = smallCoverImage;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", imdbCode='" + imdbCode + '\'' +
                ", title='" + title + '\'' +
                ", title_english='" + title_english + '\'' +
                ", title_long='" + title_long + '\'' +
                ", slug='" + slug + '\'' +
                ", year=" + year +
                ", rating=" + rating +
                ", runtime=" + runtime +
                '}';
    }
}
