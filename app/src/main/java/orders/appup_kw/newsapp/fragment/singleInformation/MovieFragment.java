package orders.appup_kw.newsapp.fragment.singleInformation;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import orders.appup_kw.newsapp.App;
import orders.appup_kw.newsapp.R;
import orders.appup_kw.newsapp.adaper.ViewPagerAdapter;
import orders.appup_kw.newsapp.contract.MoviesContract;
import orders.appup_kw.newsapp.contract.single_contract.MovieContract;
import orders.appup_kw.newsapp.model.MoviePOJO;
import orders.appup_kw.newsapp.model.NewPOJO;
import orders.appup_kw.newsapp.network.Api;
import orders.appup_kw.newsapp.presenter.single_presenter.MoviePresenter;


public class MovieFragment extends Fragment implements MovieContract {

    private int id = 0;
    @BindView(R.id.news_text1_movie)
    TextView textViewTitle;
    @BindView(R.id.newsText1_movie)
    TextView newsText;
    @BindView(R.id.textView4_movie)
    TextView countPosition;

    @BindView(R.id.trailer)
    Button trailer;

    @BindView(R.id.viewPager_movie)
    ViewPager viewPager;

    List<String> imageList = new ArrayList<>();
    ViewPagerAdapter viewPagerAdapter;
    Unbinder unbinder;
    MoviePresenter moviePresenter;


    private FragmentActivity myContext;

    @Override
    public void onAttach(Context activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }


    public static MovieFragment newInstance(int id) {
        Bundle map = new Bundle();
        map.putInt("id", id);
        MovieFragment fragment = new MovieFragment();
        fragment.setArguments(map);
        return fragment;

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("id");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        unbinder = ButterKnife.bind(this, view);

        App.getAppComponent().inject(this);


        viewPagerAdapter = new ViewPagerAdapter(myContext.getSupportFragmentManager(), 1, imageList);
        viewPager.setAdapter(viewPagerAdapter);

        moviePresenter = new MoviePresenter(this);
        moviePresenter.loadData(id);


        return view;
    }

    private void setLayoutInformation(MoviePOJO moviePOJO){

        countPosition.setText("1/" + moviePOJO.getImages().size());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                countPosition.setText((i + 1) + "/" + moviePOJO.getImages().size());
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        textViewTitle.setText(moviePOJO.getTitle());
        newsText.setText(Html.fromHtml(moviePOJO.getBodyText()));


        trailer.setOnClickListener(v -> {
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(moviePOJO.getTrailer()));
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse(moviePOJO.getTrailer()));
            try {
                this.startActivity(appIntent);
            } catch (ActivityNotFoundException ex) {
                this.startActivity(webIntent);
            }
        });


        imageList.clear();
        for (MoviePOJO.Image imageLink: moviePOJO.getImages()) {
            imageList.add(imageLink.getImage());
        }
        viewPagerAdapter.notifyDataSetChanged();

    }

    @Override
    public void setData(MoviePOJO moviePOJO) {
        setLayoutInformation(moviePOJO);
    }

    @Override
    public void onDestroy() {

        moviePresenter.destroy();
        super.onDestroy();
    }

    @Override
    public void onDestroyView() {

        unbinder.unbind();
        super.onDestroyView();
    }


}