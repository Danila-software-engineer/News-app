package orders.appup_kw.newsapp;

import android.app.Application;

import orders.appup_kw.newsapp.di.AppComponent;
import orders.appup_kw.newsapp.di.DaggerAppComponent;
import orders.appup_kw.newsapp.di.NetworkModule;

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = createAppComponent();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    private AppComponent createAppComponent() {
        return DaggerAppComponent
                .builder()
                .networkModule(new NetworkModule())
                .build();
    }
}
