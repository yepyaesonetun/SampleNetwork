package com.yammobots.samplenetwork.mvp.presenters;

import com.yammobots.samplenetwork.data.models.CountryModel;
import com.yammobots.samplenetwork.data.vo.CountryVO;
import com.yammobots.samplenetwork.delegates.CountryItemDelegate;
import com.yammobots.samplenetwork.mvp.views.CountryListView;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by yepyaesonetun on 6/19/18.
 **/

public class CountryListPresenter extends BasePresenter<CountryListView>
        implements CountryItemDelegate {

    private PublishSubject<List<CountryVO>> mCountrySubject;

    public CountryListPresenter(CountryListView mView) {
        super(mView);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mCountrySubject = PublishSubject.create();
        mCountrySubject.subscribe(new Observer<List<CountryVO>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<CountryVO> countryVOList) {
                mView.displayCountryListData(countryVOList);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        CountryModel.getInstance().startLoadingCountryData(mCountrySubject);
    }

    @Override
    public void onTapCountry(CountryVO countryVO) {
        mView.launchDetailScreen(countryVO.getName());
    }
}
