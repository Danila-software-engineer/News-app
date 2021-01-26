package orders.appup_kw.newsapp.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
import orders.appup_kw.newsapp.model.NewPOJO;
import orders.appup_kw.newsapp.network.Api;

public class NewFragment extends BaseFragment {

    private int id = 0;
    @BindView(R.id.news_text1)
    TextView textViewTitle;
    @BindView(R.id.newsText1)
    TextView newsText;
    @BindView(R.id.textView4)
    TextView countPosition;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    List<String> imageList = new ArrayList<>();
    ViewPagerAdapter viewPagerAdapter;
    Unbinder unbinder;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    Api api;

    private FragmentActivity myContext;

    @Override
    public void onAttach(Context activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }


    public static NewFragment newInstance(int id) {
        Bundle map = new Bundle();
        map.putInt("id", id);
        NewFragment fragment = new NewFragment();
        fragment.setArguments(map);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("id");
    }

    private Observable<NewPOJO> getNew(){

        return api.getNew(id);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new, container, false);
        unbinder = ButterKnife.bind(this, view);

        App.getAppComponent().inject(this);

        updateActivityTitle(getString(R.string.news));

        viewPagerAdapter = new ViewPagerAdapter(myContext.getSupportFragmentManager(), 1, imageList);
        viewPager.setAdapter(viewPagerAdapter);




        compositeDisposable.add(
                getNew()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(this::setLayoutInformation)
                        .subscribe());


        return view;
    }

    private void setLayoutInformation(NewPOJO newPOJO){

        countPosition.setText("1/" + newPOJO.getImages().size());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                countPosition.setText((i + 1) + "/" + newPOJO.getImages().size());
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        textViewTitle.setText(newPOJO.getTitle());
        newsText.setText(Html.fromHtml(newPOJO.getBodyText()));


        imageList.clear();
        for (NewPOJO.Image imageLink: newPOJO.getImages()) {
            imageList.add(imageLink.getImage());
        }
        viewPagerAdapter.notifyDataSetChanged();

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