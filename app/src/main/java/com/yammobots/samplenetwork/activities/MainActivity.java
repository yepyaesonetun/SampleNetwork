package com.yammobots.samplenetwork.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.yammobots.samplenetwork.R;
import com.yammobots.samplenetwork.adapters.CountryRVAdapter;
import com.yammobots.samplenetwork.data.models.CountryModel;
import com.yammobots.samplenetwork.data.vo.CountryVO;
import com.yammobots.samplenetwork.delegates.CountryItemDelegate;
import com.yammobots.samplenetwork.events.RestApiEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

public class MainActivity extends AppCompatActivity implements CountryItemDelegate {

    @BindView(R.id.rv_main)
    RecyclerView mRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private CountryRVAdapter adapter;
    private PublishSubject<List<CountryVO>> mCountrySubject;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        toolbar.setTitle(R.string.app_name);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new CountryRVAdapter(this, this);
        mRecyclerView.setAdapter(adapter);

        mCountrySubject = PublishSubject.create();
        mCountrySubject.subscribe(new Observer<List<CountryVO>>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(List<CountryVO> countryVOS) {
                adapter.appendNewData(countryVOS);
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
        Intent intent = CountryDetailActivity.getNewIntent(this, countryVO.getName());
        startActivity(intent);
    }
}
