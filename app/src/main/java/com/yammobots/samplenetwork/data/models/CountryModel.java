package com.yammobots.samplenetwork.data.models;

import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.yammobots.samplenetwork.data.vo.CountryVO;
import com.yammobots.samplenetwork.network.CountryAPI;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.PublishSubject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yepyaesonetun on 6/18/18.
 **/

public class CountryModel {

    private static CountryModel sObjInstance;
    private HashMap<String, CountryVO> mCountryMap;
    private CountryAPI theAPI;

    private CountryModel() {
        initMMNewsAPI();
        mCountryMap = new HashMap<>();
    }

    private void initMMNewsAPI() {
        final OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();

        theAPI = retrofit.create(CountryAPI.class);
    }

    public void startLoadingCountryData(final PublishSubject<List<CountryVO>> mCountrySubject) {

        Observable<ArrayList<CountryVO>> countryListObservable = theAPI.getAllCountries();
        countryListObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ArrayList<CountryVO>>() {
                    @Override
                    public void onNext(ArrayList<CountryVO> countryVOS) {
                        mCountrySubject.onNext(countryVOS);
                        for (CountryVO vo : countryVOS) {
                            mCountryMap.put(vo.getName(), vo);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    public static CountryModel getInstance() {
        if (sObjInstance == null) {
            sObjInstance = new CountryModel();
        }
        return sObjInstance;
    }

    public CountryVO getCountryByName(String name) {
        return mCountryMap.get(name);
    }

}
