package orders.appup_kw.newsapp.presenter;


import android.util.Log;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import orders.appup_kw.newsapp.App;
import orders.appup_kw.newsapp.contract.MoviesContract;
import orders.appup_kw.newsapp.model.MoviesPOJO;
import orders.appup_kw.newsapp.use_case.MoviesUseCase;

public class MoviesPresenter {

    MoviesContract moviesContract;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    MoviesUseCase moviesUseCase;

    public MoviesPresenter(MoviesContract moviesContract) {
        this.moviesContract = moviesContract;
        App.getAppComponent().inject(this);
    }



    public void chosenItem(PublishSubject<Integer> publishSubject){
        Disposable disposable =  publishSubject.subscribe(integer -> {
            moviesContract.openNewFragment(integer);
        });
        compositeDisposable.add(disposable);
    }

    private void setData(MoviesPOJO moviesPOJO){
        moviesContract.setData(moviesPOJO);
    }

    public void loadDataFromNetwork(){
        compositeDisposable.add(
                moviesUseCase.loadData()
                        .subscribe(this::setData, throwable -> {
                            Log.e("TAGG_Movies", "Ошибка");
                        })
        );
    }




    public void destroy(){
        compositeDisposable.dispose();
    }
}
