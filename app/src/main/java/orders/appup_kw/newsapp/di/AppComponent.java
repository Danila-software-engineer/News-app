package orders.appup_kw.newsapp.di;

import javax.inject.Singleton;

import dagger.Component;
import orders.appup_kw.newsapp.fragment.singleInformation.MovieFragment;
import orders.appup_kw.newsapp.fragment.singleInformation.NewFragment;
import orders.appup_kw.newsapp.fragment.singleInformation.PlaceFragment;
import orders.appup_kw.newsapp.presenter.MoviesPresenter;
import orders.appup_kw.newsapp.presenter.NewsPresenter;
import orders.appup_kw.newsapp.presenter.PlacesPresenter;
import orders.appup_kw.newsapp.use_case.MoviesUseCase;
import orders.appup_kw.newsapp.use_case.NewsUseCase;
import orders.appup_kw.newsapp.use_case.PlacesUseCase;

@Singleton
@Component(modules = {NetworkModule.class})
public interface AppComponent {

    void inject(NewsUseCase newsUseCase);
    void inject(NewsPresenter newsPresenter);
    void inject(NewFragment newFragment);
    void inject(MoviesPresenter moviesPresenter);
    void inject(MoviesUseCase moviesUseCase);
    void inject(MovieFragment moviesUseCase);
    void inject(PlacesPresenter placesPresenter);
    void inject(PlacesUseCase placesUseCase);
    void inject(PlaceFragment placeFragment);
}
