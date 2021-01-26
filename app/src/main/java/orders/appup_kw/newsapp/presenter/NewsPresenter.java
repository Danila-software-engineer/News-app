package orders.appup_kw.newsapp.presenter;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import orders.appup_kw.newsapp.App;
import orders.appup_kw.newsapp.contract.NewsContract;
import orders.appup_kw.newsapp.model.NewsPOJO;
import orders.appup_kw.newsapp.use_case.NewsUseCase;

public class NewsPresenter {

    NewsContract newsContract;

    @Inject
    NewsUseCase newsUseCase;

    public NewsPresenter(NewsContract newsContract) {
        this.newsContract = newsContract;
        App.getAppComponent().inject(this);
    }

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void chosenItem(PublishSubject<Integer> publishSubject){
        Disposable disposable =  publishSubject.subscribe(integer -> {
            newsContract.openNewFragment(integer);
        });
        compositeDisposable.add(disposable);
    }

    private void setData(NewsPOJO newsPOJO){
        newsContract.setData(newsPOJO);
    }

    public void loadDataFromNetwork(){
        compositeDisposable.add(
                newsUseCase.loadData()
                        .doOnNext(this::setData)
                        .subscribe()
        );
    }

    public void destroy(){
        compositeDisposable.dispose();
    }
}
