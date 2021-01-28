package orders.appup_kw.newsapp.fragment.multipleInformation;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import orders.appup_kw.newsapp.R;
import orders.appup_kw.newsapp.activity.MainActivity;
import orders.appup_kw.newsapp.contract.PlacesContract;
import orders.appup_kw.newsapp.fragment.BaseFragment;
import orders.appup_kw.newsapp.model.PlacesPOJO;
import orders.appup_kw.newsapp.presenter.NewsPresenter;
import orders.appup_kw.newsapp.presenter.PlacesPresenter;

public class FragmentPlaces extends BaseFragment implements OnMapReadyCallback, PlacesContract {
    private GoogleMap mMap;
    PlacesPresenter placesPresenter;

    private FragmentPlaces.ListenerPlaces clickListener;

    public static FragmentPlaces newInstance() {
        return new FragmentPlaces();
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
        View view = inflater.inflate(R.layout.fragment_places, container, false);
        updateActivityTitle(getString(R.string.places));

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        placesPresenter = new PlacesPresenter(this);


        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        placesPresenter.loadDataFromNetwork();
    }

    @Override
    public void setData(PlacesPOJO placesPOJO) {

        LatLng msk = new LatLng(placesPOJO.getResults().get(1).getCoords().getLat(),
                placesPOJO.getResults().get(1).getCoords().getLon());

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(msk, 10f));
        for (PlacesPOJO.Result results: placesPOJO.getResults()) {
            //Log.e("TAGG",results.getTitle());
            mMap.addMarker(
                    new MarkerOptions().position(
                            new LatLng(results.getCoords().getLat(),results.getCoords().getLon())
                    )).setTag(results.getId());
        }

        mMap.setOnMarkerClickListener((GoogleMap.OnMarkerClickListener) m -> {

            openNewFragment((int) m.getTag());
            return true;
        });

    }

    public void openNewFragment(int i) {
        if (clickListener != null){
            mMap.clear();
            clickListener.oneClickButtonPlaces(i);
        }
    }

    public interface ListenerPlaces{
        void oneClickButtonPlaces(int id);
    }

    @Override
    public void onDestroy() {
        placesPresenter.destroy();
        super.onDestroy();
    }

}
