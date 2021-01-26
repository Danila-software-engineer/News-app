package orders.appup_kw.newsapp.use_case;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import orders.appup_kw.newsapp.App;
import orders.appup_kw.newsapp.model.MoviesPOJO;
import orders.appup_kw.newsapp.network.Api;

public class MoviesUseCase {
    @Inject
    Api api;

    public MoviesUseCase() {
        App.getAppComponent().inject(this);
    }

    public Observable<MoviesPOJO> loadData(){
        return api.getMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
