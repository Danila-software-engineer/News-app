package orders.appup_kw.newsapp.presenter.single_presenter;

import android.widget.Toast;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import orders.appup_kw.newsapp.App;
import orders.appup_kw.newsapp.contract.single_contract.MovieContract;
import orders.appup_kw.newsapp.model.MoviePOJO;
import orders.appup_kw.newsapp.network.Api;

public class MoviePresenter {
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    MovieContract movieContract;

    @Inject
    Api api;

    public MoviePresenter(MovieContract movieContract) {
        this.movieContract = movieContract;
        App.getAppComponent().inject(this);
    }

    public void loadData(int id){
        compositeDisposable.add(
                api.getMovie(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(it -> movieContract.setData(it), throwable -> {}));
    }



    public void destroy(){
        compositeDisposable.dispose();
    }
}
