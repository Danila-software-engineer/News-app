package orders.appup_kw.newsapp.contract;

import orders.appup_kw.newsapp.model.NewsPOJO;

public interface NewsContract {

    void setData(NewsPOJO newsPOJO);
    void openNewFragment(int i);
}
