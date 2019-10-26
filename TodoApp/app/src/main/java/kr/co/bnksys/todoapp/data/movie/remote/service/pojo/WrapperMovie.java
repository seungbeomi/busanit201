package kr.co.bnksys.todoapp.data.movie.remote.service.pojo;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WrapperMovie {

    private String status;
    private String status_message;
    private ResponseData data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public ResponseData getData() {
        return data;
    }

    public void setData(ResponseData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "WrapperMovie{" +
                "status='" + status + '\'' +
                ", status_message='" + status_message + '\'' +
                ", data=" + data +
                '}';
    }

    public class ResponseData {

        @SerializedName(value = "movie_count")
        private int movieCount;
        private int limit;
        private int page_number;
        private List<Movie> movies;

        public int getMovieCount() {
            return movieCount;
        }

        public void setMovieCount(int movieCount) {
            this.movieCount = movieCount;
        }

        public int getLimit() {
            return limit;
        }

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public int getPage_number() {
            return page_number;
        }

        public void setPage_number(int page_number) {
            this.page_number = page_number;
        }

        public List<Movie> getMovies() {
            return movies;
        }

        public void setMovies(List<Movie> movies) {
            this.movies = movies;
        }

        @Override
        public String toString() {
            return "ResponseData{" +
                    "movieCount=" + movieCount +
                    ", limit=" + limit +
                    ", page_number=" + page_number +
                    ", movies=" + movies +
                    '}';
        }
    }
}
