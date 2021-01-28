package orders.appup_kw.newsapp.fragment.singleInformation;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import orders.appup_kw.newsapp.model.NewPOJO;
import orders.appup_kw.newsapp.model.PlacePOJO;
import orders.appup_kw.newsapp.network.Api;


public class PlaceFragment extends Fragment {

    @BindView(R.id.news_text1_places)
    TextView textViewTitle;
    @BindView(R.id.newsText1_places)
    TextView newsText;
    @BindView(R.id.textView4_places)
    TextView countPosition;

    @BindView(R.id.viewPager_places)
    ViewPager viewPager;

    @Inject
    Api api;

    List<String> imageList = new ArrayList<>();
    ViewPagerAdapter viewPagerAdapter;
    Unbinder unbinder;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    private int id = 0;
    private FragmentActivity myContext;

    @Override
    public void onAttach(Context activity) {
        myContext = (FragmentActivity) activity;
        super.onAttach(activity);
    }


    public static PlaceFragment newInstance(int id) {
        Bundle map = new Bundle();
        map.putInt("id", id);
        PlaceFragment fragment = new PlaceFragment();
        fragment.setArguments(map);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        id = getArguments().getInt("id");
    }

    private Observable<PlacePOJO> getPlace(){

        return api.getPlace(id);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_place, container, false);

        unbinder = ButterKnife.bind(this, view);

        App.getAppComponent().inject(this);



        viewPagerAdapter = new ViewPagerAdapter(myContext.getSupportFragmentManager(), 1, imageList);
        viewPager.setAdapter(viewPagerAdapter);



        compositeDisposable.add(
                getPlace()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(this::setLayoutInformation, throwable -> {
                            throwable.printStackTrace();
                            Log.e("TAGG_place","Error! id =" + id);//1734
                            Toast.makeText(getActivity(),"Error! id =" + id, Toast.LENGTH_SHORT).show();
                        }));


        return view;
    }


    private void setLayoutInformation(PlacePOJO placePOJO){

        countPosition.setText("1/" + placePOJO.getImages().size());

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                countPosition.setText((i + 1) + "/" + placePOJO.getImages().size());
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        textViewTitle.setText(placePOJO.getTitle());
        newsText.setText(Html.fromHtml(placePOJO.getBodyText()));


        imageList.clear();
        for (PlacePOJO.Image imageLink: placePOJO.getImages()) {
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