package com.yammobots.samplenetwork.mvp.views;

import com.yammobots.samplenetwork.data.vo.CountryVO;

/**
 * Created by yepyaesonetun on 6/19/18.
 **/

public interface CountryDetailView extends BaseView {

    void displayDetailData(CountryVO countryVO);
}
