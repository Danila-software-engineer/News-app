package orders.appup_kw.newsapp.fragment.multipleInformation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import orders.appup_kw.newsapp.R;
import orders.appup_kw.newsapp.activity.MainActivity;
import orders.appup_kw.newsapp.adaper.MoviesAdapter;
import orders.appup_kw.newsapp.adaper.NewsAdapter;
import orders.appup_kw.newsapp.contract.MoviesContract;
import orders.appup_kw.newsapp.fragment.BaseFragment;
import orders.appup_kw.newsapp.model.MoviesPOJO;
import orders.appup_kw.newsapp.presenter.MoviesPresenter;

public class FragmentMovies extends BaseFragment implements MoviesContract {
    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;
    ArrayList<MoviesPOJO.Result> movies = new ArrayList<>();
    Unbinder unbinder;
    private ListenerMovies clickListener;
    MoviesAdapter moviesAdapter;
    MoviesPresenter moviesPresenter;

    public static FragmentMovies newInstance() {
        return new FragmentMovies();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        if (context instanceof MainActivity){
            clickListener = (MainActivity) context;
        }
        super.onAttach(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        moviesAdapter = new MoviesAdapter(getContext(), movies);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);
        unbinder = ButterKnife.bind(this, view);
        updateActivityTitle(getString(R.string.movies));


        myRecyclerView.setAdapter(moviesAdapter);

        moviesPresenter = new MoviesPresenter(this);
        moviesPresenter.loadDataFromNetwork();
        moviesPresenter.chosenItem(moviesAdapter.getPublishSubject());

        return view;
    }

    @Override
    public void setData(MoviesPOJO moviesPOJO) {
        movies.clear();
        movies.addAll(moviesPOJO.getResults());
        moviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void openNewFragment(int i) {
        if (clickListener != null){
            clickListener.oneClickButtonMovies(i);
        }
    }



    @Override
    public void onDestroyView() {
        unbinder.unbind();
        moviesPresenter.destroy();
        super.onDestroyView();
    }

    public interface ListenerMovies{
        void oneClickButtonMovies(int id);
    }


}
