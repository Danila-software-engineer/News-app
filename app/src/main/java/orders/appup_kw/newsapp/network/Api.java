package orders.appup_kw.newsapp.network;




import io.reactivex.Observable;
import orders.appup_kw.newsapp.model.NewPOJO;
import orders.appup_kw.newsapp.model.NewsPOJO;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {

    @GET("public-api/v1.2/news/?page=1&page_size=10&fields=id,publication_date,title,images")
    Observable<NewsPOJO> getNews();

    @GET("public-api/v1.2/news/{id}/?fields=id,title,body_text,images")
    Observable<NewPOJO> getNew(@Path("id") int id);

}
