package com.yammobots.samplenetwork.data.models;

import com.yammobots.samplenetwork.data.vo.CountryVO;
import com.yammobots.samplenetwork.events.RestApiEvent;
import com.yammobots.samplenetwork.network.CountryDataAgentImpl;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;

/**
 * Created by yepyaesonetun on 6/18/18.
 **/

public class CountryModel {

    private static CountryModel sObjInstance;
    private HashMap<String, CountryVO> mCountryMap;

    private CountryModel() {
        EventBus.getDefault().register(this);
        mCountryMap = new HashMap<>();
    }

    public void startloadingCountryData(){
        CountryDataAgentImpl.getInstance().loadCountries();
    }

    public static CountryModel getInstance(){
        if(sObjInstance == null){
            sObjInstance = new CountryModel();
        }
        return sObjInstance;
    }

    public CountryVO getCountryByName(String name){
        return mCountryMap.get(name);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onCountriesDataLoaded(RestApiEvent.CounrtyDataLoadedEvent event){
        for (CountryVO countryVO: event.getCountryVOList()){
            mCountryMap.put(countryVO.getName(), countryVO);
        }
    }
}
