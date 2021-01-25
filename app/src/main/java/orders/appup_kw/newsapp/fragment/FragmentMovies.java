package orders.appup_kw.newsapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import orders.appup_kw.newsapp.R;
import orders.appup_kw.newsapp.adaper.MoviesAdapter;
import orders.appup_kw.newsapp.model.Movie;

public class FragmentMovies extends BaseFragment{
    @BindView(R.id.myRecyclerView)
    RecyclerView myRecyclerView;

    Unbinder unbinder;

    public static FragmentMovies newInstance() {
        FragmentMovies fragment = new FragmentMovies();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movies, container, false);//Находим xml
        unbinder = ButterKnife.bind(this, view);
        updateActivityTitle(getString(R.string.movies));


        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Бэтмен", "+16", "США"));
        movies.add(new Movie("Такси", "+16", "Франция"));
        movies.add(new Movie("Колобок", "+3", "Россия"));


        //Создаем адаптер
        MoviesAdapter moviesAdapter = new MoviesAdapter(getContext(), movies);
        myRecyclerView.setAdapter(moviesAdapter);

        return view;
    }


    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();

    }
}
