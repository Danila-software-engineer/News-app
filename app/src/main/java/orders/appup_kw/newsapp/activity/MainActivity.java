package orders.appup_kw.newsapp.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.navigation.NavigationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import orders.appup_kw.newsapp.R;
import orders.appup_kw.newsapp.fragment.FragmentMovies;
import orders.appup_kw.newsapp.fragment.FragmentNews;
import orders.appup_kw.newsapp.fragment.FragmentPlaces;
import orders.appup_kw.newsapp.fragment.InfoFragment;
import orders.appup_kw.newsapp.fragment.NewFragment;

public class MainActivity extends BaseActivity implements FragmentNews.Listener {

    @BindView(R.id.navigationView)
    NavigationView navigationView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    private void launchFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }


    @Override
    public void oneClickButton(int id) {
        launchFragment(NewFragment.newInstance(id));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupDrawerIcon();
        setupDrawerContent();


        launchFragment(FragmentNews.newInstance());
    }


    private void setupDrawerIcon() {
        ActionBarDrawerToggle mDrawerToggle =
                new ActionBarDrawerToggle(this, drawerLayout,0, 0);
        drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }


    private void setupDrawerContent() {

        navigationView.setNavigationItemSelectedListener(menuItem -> {


            drawerLayout.closeDrawers();

            
            switch (menuItem.getItemId()) {
                case R.id.news:
                    launchFragment(FragmentNews.newInstance());
                    break;
                case R.id.places:
                    launchFragment(FragmentPlaces.newInstance());
                    break;
                case R.id.movies:
                    launchFragment(FragmentMovies.newInstance());
                    break;
                case R.id.info:
                    launchFragment(InfoFragment.newInstance());
                    break;
            }

            return false;
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawers();
                }
                else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}
