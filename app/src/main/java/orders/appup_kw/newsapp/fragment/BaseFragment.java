package orders.appup_kw.newsapp.fragment;


import androidx.fragment.app.Fragment;

import orders.appup_kw.newsapp.activity.BaseActivity;

public class BaseFragment extends Fragment {

    public void updateActivityTitle(String title){
        if (getActivity() instanceof BaseActivity){
            BaseActivity baseActivity = (BaseActivity) getActivity();
            baseActivity.updateActivityTitle(title);
        }
    }
}
