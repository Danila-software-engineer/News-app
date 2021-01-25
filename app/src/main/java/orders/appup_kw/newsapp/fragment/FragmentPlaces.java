package orders.appup_kw.newsapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.gms.maps.GoogleMap;

import orders.appup_kw.newsapp.R;

public class FragmentPlaces extends BaseFragment {
    private GoogleMap mMap;

    public static FragmentPlaces newInstance() {
        FragmentPlaces fragment = new FragmentPlaces();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_places, container, false);
        updateActivityTitle(getString(R.string.places));

        return view;
    }

}
