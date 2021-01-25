package orders.appup_kw.newsapp.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import orders.appup_kw.newsapp.R;

public class InfoFragment extends BaseFragment {

    public static InfoFragment newInstance() {
        InfoFragment fragment = new InfoFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info, container, false);
        updateActivityTitle(getString(R.string.about_app));
        return view;
    }
}
