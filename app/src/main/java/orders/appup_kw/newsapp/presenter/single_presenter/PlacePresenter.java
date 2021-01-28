package orders.appup_kw.newsapp.presenter.single_presenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import orders.appup_kw.newsapp.App;
import orders.appup_kw.newsapp.contract.single_contract.MovieContract;
import orders.appup_kw.newsapp.contract.single_contract.PlaceContract;
import orders.appup_kw.newsapp.network.Api;

public class PlacePresenter {
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    PlaceContract placeContract;

    @Inject
    Api api;

    public PlacePresenter(PlaceContract placeContract) {
        this.placeContract = placeContract;
        App.getAppComponent().inject(this);
    }

    public void loadData(int id){
        compositeDisposable.add(
                api.getPlace(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(it -> placeContract.setData(it), throwable -> {}));
    }



    public void destroy(){
        compositeDisposable.dispose();
    }
}
