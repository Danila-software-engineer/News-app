package orders.appup_kw.newsapp.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import orders.appup_kw.newsapp.R;

public class ScreenSlidePageFragment extends Fragment {
    private static final String link_KEY = "link";

    public static ScreenSlidePageFragment launchFragment(String link){
        Bundle bundle = new Bundle();
        bundle.putString(link_KEY, link);
        ScreenSlidePageFragment fragment = new ScreenSlidePageFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = (ViewGroup) inflater.inflate(R.layout.image_fragment, container, false);
        ImageView imageView = rootView.findViewById(R.id.image_view);
        ProgressBar progressBar = rootView.findViewById(R.id.progressBar2);


        String drawable = getArguments().getString(link_KEY);

        try {
            Glide.with(this)
                    .load(drawable)
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            imageView.setImageResource(R.drawable.ic_launcher_background);
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(imageView);
        } catch (Exception e) {
            e.printStackTrace();
        }




        return rootView;
    }
}