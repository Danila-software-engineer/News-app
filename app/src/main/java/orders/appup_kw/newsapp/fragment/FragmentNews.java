package orders.appup_kw.newsapp.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import orders.appup_kw.newsapp.R;

import orders.appup_kw.newsapp.activity.MainActivity;
import orders.appup_kw.newsapp.adaper.NewsAdapter;
import orders.appup_kw.newsapp.model.NewsPOJO;
import orders.appup_kw.newsapp.network.NetworkService;

public class FragmentNews extends BaseFragment{

    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;
    Unbinder unbinder;
    NewsAdapter newsAdapter;

    private GreenListener clickListener;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    public static FragmentNews newInstance() {
        FragmentNews fragment = new FragmentNews();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {


        if (context instanceof MainActivity){
            MainActivity mainActivity = (MainActivity) context;
            clickListener = mainActivity;
        }

        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        updateActivityTitle(getString(R.string.news));



        List<NewsPOJO.Result> newsStrings = new ArrayList<>();




        compositeDisposable.add(
                getNews()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(newsPOJO ->{
                            newsStrings.addAll(newsPOJO.getResults());
                            newsAdapter.notifyDataSetChanged();
                        })
                        .subscribe());




        newsAdapter = new NewsAdapter(getContext(), newsStrings);

        myRecyclerView.setAdapter(newsAdapter);

       Disposable disposable =  newsAdapter.getPublishSubject().subscribe(integer -> {
           if (clickListener != null){
               clickListener.oneGreenClickButton(integer);
           }
        });
        compositeDisposable.add(disposable);

        return view;
    }

    private Observable<NewsPOJO> getNews(){
        return NetworkService.getInstance()
                .getJSONApi()
                .getNews();
    }


    public interface GreenListener{
        void oneGreenClickButton(int id);
    }


    @Override
    public void onDestroy() {

        compositeDisposable.dispose();
        super.onDestroy();

    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
