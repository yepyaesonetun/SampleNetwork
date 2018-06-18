package com.yammobots.samplenetwork.network;

import com.google.gson.Gson;
import com.yammobots.samplenetwork.data.vo.CountryVO;
import com.yammobots.samplenetwork.events.RestApiEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yepyaesonetun on 6/18/18.
 **/

public class CountryDataAgentImpl implements CountryDataAgent {

    private static CountryDataAgentImpl sObjInstance;
    private CountryAPI theAPI;

    public CountryDataAgentImpl() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://restcountries.eu/")
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .client(okHttpClient)
                .build();

        theAPI = retrofit.create(CountryAPI.class);
    }

    public static CountryDataAgentImpl getInstance() {
        if (sObjInstance == null) {
            sObjInstance = new CountryDataAgentImpl();
        }
        return sObjInstance;
    }

    @Override
    public void loadCountries() {
        Call<ArrayList<CountryVO>> callCountryList = theAPI.getAllCountries();
        callCountryList.enqueue(new Callback<ArrayList<CountryVO>>() {
            @Override
            public void onResponse(Call<ArrayList<CountryVO>> call, Response<ArrayList<CountryVO>> response) {
                if (!response.body().isEmpty() && response.isSuccessful()
                        && response.body().size() > 0) {
                    // if response success, broadcast received data
                    RestApiEvent.CounrtyDataLoadedEvent countryDataLoadedEvent =
                            new RestApiEvent.CounrtyDataLoadedEvent(response.body());
                    EventBus.getDefault().post(countryDataLoadedEvent);
                }else {
                    RestApiEvent.ErrorInvokingAPIEvent errorEvent =
                            new RestApiEvent.ErrorInvokingAPIEvent("Response Error.");
                    EventBus.getDefault().post(errorEvent);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<CountryVO>> call, Throwable t) {
                RestApiEvent.ErrorInvokingAPIEvent errorEvent =
                        new RestApiEvent.ErrorInvokingAPIEvent("Response failure.");
                EventBus.getDefault().post(errorEvent);
            }
        });

    }
}
