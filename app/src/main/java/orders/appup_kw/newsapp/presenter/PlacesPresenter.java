package orders.appup_kw.newsapp.presenter;

import android.util.Log;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import orders.appup_kw.newsapp.App;
import orders.appup_kw.newsapp.contract.NewsContract;
import orders.appup_kw.newsapp.contract.PlacesContract;
import orders.appup_kw.newsapp.model.NewsPOJO;
import orders.appup_kw.newsapp.model.PlacesPOJO;
import orders.appup_kw.newsapp.use_case.NewsUseCase;
import orders.appup_kw.newsapp.use_case.PlacesUseCase;

public class PlacesPresenter {

    PlacesContract placesContract;

    @Inject
    PlacesUseCase placesUseCase;

    public PlacesPresenter(PlacesContract placesContract) {
        this.placesContract = placesContract;
        App.getAppComponent().inject(this);
    }

    CompositeDisposable compositeDisposable = new CompositeDisposable();



    private void setData(PlacesPOJO placesPOJO){
        placesContract.setData(placesPOJO);
    }

    public void loadDataFromNetwork(){
        compositeDisposable.add(
                placesUseCase.loadData()
                        .subscribe(this::setData, throwable -> {
                            Log.e("TAGG_Place", "Ошибка");
                            throwable.printStackTrace();
                        })
        );
    }

    public void destroy(){
        compositeDisposable.dispose();
    }
}
