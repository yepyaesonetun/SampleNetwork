package com.yammobots.samplenetwork.network;

import com.yammobots.samplenetwork.data.vo.CountryVO;

import java.util.ArrayList;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by yepyaesonetun on 6/18/18.
 **/

public interface CountryAPI {
    @GET("/rest/v2/all?fields=name;capital;currencies;population;flag")
    Observable<ArrayList<CountryVO>> getAllCountries();
}
