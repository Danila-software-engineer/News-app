package orders.appup_kw.newsapp.di;


import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import orders.appup_kw.newsapp.network.Api;
import orders.appup_kw.newsapp.use_case.MoviesUseCase;
import orders.appup_kw.newsapp.use_case.NewsUseCase;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    private static final String BASE_URL = "https://kudago.com/";

    @Provides
    @Singleton
    public HttpLoggingInterceptor provideHttpLoggingInterceptor(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    @Provides
    @Singleton
    public OkHttpClient.Builder provideOkHttpClient(HttpLoggingInterceptor interceptor){
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(interceptor);
        return client;
    }


    @Provides
    @Singleton
    public Api provideApi(OkHttpClient.Builder client){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client.build())
                .build()
                .create(Api.class);
    }



    @Provides
    @Singleton
    public NewsUseCase provideNewsUseCase(){
        return new NewsUseCase();
    }

    @Provides
    @Singleton
    public MoviesUseCase provideMoviesUseCase(){
        return new MoviesUseCase();
    }
}
