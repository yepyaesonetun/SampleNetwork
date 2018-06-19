package com.yammobots.samplenetwork.mvp.presenters;

import com.yammobots.samplenetwork.data.models.CountryModel;
import com.yammobots.samplenetwork.data.vo.CountryVO;
import com.yammobots.samplenetwork.mvp.views.CountryDetailView;

/**
 * Created by yepyaesonetun on 6/19/18.
 **/

public class CountryDetailPresenter extends BasePresenter<CountryDetailView> {

    public CountryDetailPresenter(CountryDetailView mView) {
        super(mView);
    }

    public void onFinishUIComponentsSetUp(String countryName){
        CountryVO countryVO = CountryModel.getInstance().getCountryByName(countryName);
        mView.displayDetailData(countryVO);
    }
}
