package orders.appup_kw.newsapp.adaper;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;


import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

import io.reactivex.subjects.PublishSubject;
import orders.appup_kw.newsapp.R;
import orders.appup_kw.newsapp.model.NewsPOJO;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{
    private List<NewsPOJO.Result> data;
    private Context context;
    private PublishSubject<Integer> publishSubject = PublishSubject.create();

    public NewsAdapter(Context context, List<NewsPOJO.Result> data) {
        this.data = data;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.news_adapter_element, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.e("TAGG", "text - " + position);
        NewsPOJO.Result result = data.get(position);
        holder.textViewNews.setText(result.getTitle());

        try {
            Glide.with(context)
                    .load(data.get(position).getImages().get(0).getImage())
                    .listener(new RequestListener<Drawable>() {
                        @Override
                        public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                            holder.imageNews.setImageResource(R.drawable.ic_launcher_background);
                            holder.progressBar.setVisibility(View.GONE);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                            holder.progressBar.setVisibility(View.GONE);
                            return false;
                        }
                    })
                    .into(holder.imageNews);
        } catch (Exception e) {
            e.printStackTrace();

        }

        holder.itemView.setOnClickListener(v -> {
            publishSubject.onNext(result.getId());
        });

        setScaleAnimation(holder.itemView);
    }

    public PublishSubject<Integer> getPublishSubject() {
        return publishSubject;
    }


    private void setScaleAnimation(View view) {
        TranslateAnimation anim = new TranslateAnimation(1000.0f, 0.0f, 0, 0.0f);
        anim.setDuration(500);
        view.startAnimation(anim);
    }



    @Override
    public int getItemCount() {
        return data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNews;
        private ImageView imageNews;
        private ProgressBar progressBar;

        ViewHolder(View itemView) {
            super(itemView);
            textViewNews = itemView.findViewById(R.id.news_text);
            imageNews = itemView.findViewById(R.id.news_image);
            progressBar = itemView.findViewById(R.id.progressBar);
        }

    }
}
