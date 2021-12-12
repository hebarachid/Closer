package com.example.closer;

public class MovieModelClass {

    String id;
    String name;
    String overview;
    String release_date;
    String img;
    public MovieModelClass(String id, String name, String overview, String release_date, String img) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.release_date = release_date;
        this.img = img;
    }



    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getOverview() {
        return overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public MovieModelClass() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImg() {
        return img;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
