package orders.appup_kw.newsapp.presenter;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import orders.appup_kw.newsapp.contract.NewsContract;
import orders.appup_kw.newsapp.model.NewsPOJO;
import orders.appup_kw.newsapp.network.NetworkService;

public class NewsPresenter {

    NewsContract newsContract;

    public NewsPresenter(NewsContract newsContract) {
        this.newsContract = newsContract;
    }

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public void chosenItem(PublishSubject<Integer> publishSubject){
        Disposable disposable =  publishSubject.subscribe(integer -> {
            newsContract.openNewFragment(integer);
        });
        compositeDisposable.add(disposable);
    }


    public void loadDataFromNetwork(){
        compositeDisposable.add(
                getNews()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(newsPOJO ->{
                            newsContract.setData(newsPOJO);
                        })
                        .subscribe());
    }

    private Observable<NewsPOJO> getNews(){
        return NetworkService.getInstance()
                .getJSONApi()
                .getNews();
    }


    public void destroy(){
        compositeDisposable.dispose();
    }
}
