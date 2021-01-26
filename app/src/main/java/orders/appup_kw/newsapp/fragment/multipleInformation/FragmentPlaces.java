package orders.appup_kw.newsapp.fragment.multipleInformation;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import orders.appup_kw.newsapp.R;
import orders.appup_kw.newsapp.fragment.BaseFragment;

public class FragmentPlaces extends BaseFragment implements OnMapReadyCallback {
    private GoogleMap mMap;

    public static FragmentPlaces newInstance() {
        return new FragmentPlaces();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places, container, false);
        updateActivityTitle(getString(R.string.places));

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng moscow = new LatLng(55.663018, 37.536144);
        mMap.addMarker(new MarkerOptions().position(moscow).title("Marker in Sydney"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(55.817837, 37.546490)).title("Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(55.816129, 37.758099)).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(moscow, 10f));
    }
}
