package orders.appup_kw.newsapp.di;

import javax.inject.Singleton;

import dagger.Component;
import orders.appup_kw.newsapp.fragment.singleInformation.MovieFragment;
import orders.appup_kw.newsapp.fragment.singleInformation.NewFragment;
import orders.appup_kw.newsapp.presenter.MoviesPresenter;
import orders.appup_kw.newsapp.presenter.NewsPresenter;
import orders.appup_kw.newsapp.use_case.MoviesUseCase;
import orders.appup_kw.newsapp.use_case.NewsUseCase;

@Singleton
@Component(modules = {NetworkModule.class})
public interface AppComponent {

    void inject(NewsUseCase newsUseCase);
    void inject(NewsPresenter newsPresenter);
    void inject(NewFragment newFragment);
    void inject(MoviesPresenter moviesPresenter);
    void inject(MoviesUseCase moviesUseCase);
    void inject(MovieFragment moviesUseCase);
}
