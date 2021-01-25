package orders.appup_kw.newsapp.adaper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import orders.appup_kw.newsapp.R;
import orders.appup_kw.newsapp.model.Movie;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder>{
    private List<Movie> rData;
    private Context context;

    public MoviesAdapter(Context context, List<Movie> data) {
        this.rData = data;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_adapter_element, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie result = rData.get(position);
        holder.textViewMovie.setText(result.getTitle());
        holder.textViewCountry.setText(result.getCountry());
        holder.textViewAge.setText(result.getAgeRestriction());
        holder.progressBar.setVisibility(View.GONE);
    }



    @Override
    public int getItemCount() {
        return rData.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewMovie;
        private TextView textViewCountry;
        private TextView textViewAge;
        private ImageView imageMovie;
        private ProgressBar progressBar;

        ViewHolder(View itemView) {
            super(itemView);
            textViewMovie = itemView.findViewById(R.id.movie_text);
            textViewAge = itemView.findViewById(R.id.movie_age);
            textViewCountry = itemView.findViewById(R.id.movie_country);
            imageMovie = itemView.findViewById(R.id.movie_image);
            progressBar = itemView.findViewById(R.id.progressBar1);
        }

    }
}
