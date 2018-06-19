package com.yammobots.samplenetwork.mvp.views;

import com.yammobots.samplenetwork.data.vo.CountryVO;

import java.util.List;

/**
 * Created by yepyaesonetun on 6/19/18.
 **/

public interface CountryListView extends BaseView{

    void displayCountryListData(List<CountryVO> countryVOList);

    void launchDetailScreen(String name);    // name as ID
}
