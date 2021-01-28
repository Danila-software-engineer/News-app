package orders.appup_kw.newsapp.use_case;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import orders.appup_kw.newsapp.App;
import orders.appup_kw.newsapp.model.NewsPOJO;
import orders.appup_kw.newsapp.model.PlacesPOJO;
import orders.appup_kw.newsapp.network.Api;

public class PlacesUseCase {


    @Inject
    Api api;

    public PlacesUseCase() {
        App.getAppComponent().inject(this);
    }

    public Observable<PlacesPOJO> loadData(){
        return api.getPlaces()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
