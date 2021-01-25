package orders.appup_kw.newsapp.adaper;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import java.util.List;
import orders.appup_kw.newsapp.fragment.ScreenSlidePageFragment;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    List<String> imageList;

    public ViewPagerAdapter(FragmentManager fm, int amount, List<String> imageList) {
        super(fm, amount);
        this.imageList = imageList;
    }

    @Override
    public Fragment getItem(int position) {
        return ScreenSlidePageFragment.launchFragment(imageList.get(position));
    }

    @Override
    public int getCount() {
        return imageList.size();
    }
}
