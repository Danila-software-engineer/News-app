package orders.appup_kw.newsapp.network;




import io.reactivex.Observable;
import orders.appup_kw.newsapp.model.MoviePOJO;
import orders.appup_kw.newsapp.model.MoviesPOJO;
import orders.appup_kw.newsapp.model.NewPOJO;
import orders.appup_kw.newsapp.model.NewsPOJO;
import orders.appup_kw.newsapp.model.PlacePOJO;
import orders.appup_kw.newsapp.model.PlacesPOJO;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("public-api/v1.2/news/?page=1&page_size=10&fields=id,publication_date,title,images")
    Observable<NewsPOJO> getNews();

    @GET("public-api/v1.2/news/{id}/?fields=id,title,body_text,images")
    Observable<NewPOJO> getNew(@Path("id") int id);



    @GET("public-api/v1.4/places/?page=1&page_size=10&fields=id,title,coords&location=msk")
    Observable<PlacesPOJO> getPlaces();

    @GET("public-api/v1.2/places/{id}/?fields=id,address,title,body_text,images")
    Observable<PlacePOJO> getPlace(@Path("id") int id);



    @GET("/public-api/v1.2/movies/?page_size=10&page=1&fields=id,title,images")
    Observable<MoviesPOJO> getMovies();

    @GET("/public-api/v1.4/movies/{id}/?fields=id,title,images,body_text,trailer")
    Observable<MoviePOJO> getMovie(@Path("id") int id);
}
