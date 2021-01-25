package orders.appup_kw.newsapp.model;




public class Movie {

    private String title;
    private String ageRestriction;
    private String country;

    public Movie(String title, String ageRestriction, String country) {
        this.title = title;
        this.ageRestriction = ageRestriction;
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAgeRestriction() {
        return ageRestriction;
    }

    public void setAgeRestriction(String ageRestriction) {
        this.ageRestriction = ageRestriction;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
