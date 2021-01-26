package orders.appup_kw.newsapp.fragment.multipleInformation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import orders.appup_kw.newsapp.R;

import orders.appup_kw.newsapp.activity.MainActivity;
import orders.appup_kw.newsapp.adaper.NewsAdapter;
import orders.appup_kw.newsapp.contract.NewsContract;
import orders.appup_kw.newsapp.fragment.BaseFragment;
import orders.appup_kw.newsapp.model.NewsPOJO;
import orders.appup_kw.newsapp.presenter.NewsPresenter;

public class FragmentNews extends BaseFragment implements NewsContract {

    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;
    Unbinder unbinder;
    NewsAdapter newsAdapter;
    List<NewsPOJO.Result> newsStrings = new ArrayList<>();
    private Listener clickListener;

    NewsPresenter newsPresenter;


    public static FragmentNews newInstance() {
        return new FragmentNews();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        if (context instanceof MainActivity){
            clickListener = (MainActivity) context;
        }
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        unbinder = ButterKnife.bind(this, view);
        updateActivityTitle(getString(R.string.news));


        newsAdapter = new NewsAdapter(getContext(), newsStrings);
        myRecyclerView.setAdapter(newsAdapter);

        newsPresenter = new NewsPresenter(this);
        newsPresenter.loadDataFromNetwork();
        newsPresenter.chosenItem(newsAdapter.getPublishSubject());

        return view;
    }

    @Override
    public void setData(NewsPOJO newsPOJO) {
        newsStrings.clear();
        newsStrings.addAll(newsPOJO.getResults());
        newsAdapter.notifyDataSetChanged();
    }

    @Override
    public void openNewFragment(int i) {
        if (clickListener != null){
            clickListener.oneClickButton(i);
        }
    }


    public interface Listener{
        void oneClickButton(int id);
    }


    @Override
    public void onDestroy() {
        newsPresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {
        unbinder.unbind();
        super.onDestroyView();
    }
}
