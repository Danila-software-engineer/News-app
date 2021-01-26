package orders.appup_kw.newsapp.contract;

import orders.appup_kw.newsapp.model.MoviesPOJO;
import orders.appup_kw.newsapp.model.NewsPOJO;

public interface MoviesContract {
    void setData(MoviesPOJO moviesPOJO);
    void openNewFragment(int i);
}
