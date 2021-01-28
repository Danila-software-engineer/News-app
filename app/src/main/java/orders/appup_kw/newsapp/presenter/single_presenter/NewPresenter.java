package orders.appup_kw.newsapp.presenter.single_presenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import orders.appup_kw.newsapp.App;
import orders.appup_kw.newsapp.contract.single_contract.MovieContract;
import orders.appup_kw.newsapp.contract.single_contract.NewContract;
import orders.appup_kw.newsapp.network.Api;

public class NewPresenter {
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    NewContract newContract;

    @Inject
    Api api;

    public NewPresenter(NewContract newContract) {
        this.newContract = newContract;
        App.getAppComponent().inject(this);
    }

    public void loadData(int id){
        compositeDisposable.add(
                api.getNew(id)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(it -> newContract.setData(it), throwable -> {}));
    }



    public void destroy(){
        compositeDisposable.dispose();
    }
}
