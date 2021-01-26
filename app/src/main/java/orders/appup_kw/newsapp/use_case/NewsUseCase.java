package orders.appup_kw.newsapp.use_case;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import orders.appup_kw.newsapp.App;
import orders.appup_kw.newsapp.model.NewsPOJO;
import orders.appup_kw.newsapp.network.Api;

public class NewsUseCase {

    @Inject
    Api api;

    public NewsUseCase() {
        App.getAppComponent().inject(this);
    }

    public Observable<NewsPOJO> loadData(){
        return api.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
