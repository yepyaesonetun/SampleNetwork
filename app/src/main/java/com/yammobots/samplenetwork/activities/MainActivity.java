package com.yammobots.samplenetwork.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

public class MainActivity extends AppCompatActivity implements CountryItemDelegate{

    private RecyclerView mRecyclerView;
    private CountryRVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv_main);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new CountryRVAdapter(this, this);
        mRecyclerView.setAdapter(adapter);

        CountryModel.getInstance().startloadingCountryData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNewsDataLoaded(RestApiEvent.CounrtyDataLoadedEvent event) {
        adapter.appendNewData(event.getCountryVOList());
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onErrorInvokingAPI(RestApiEvent.ErrorInvokingAPIEvent event) {
        Toast.makeText(this, event.getErrorMessage(), Toast.LENGTH_LONG).show();
    }


    @Override
    public void onTapCountry(CountryVO countryVO) {
        Intent intent = CountryDetailActivity.getNewIntent(this, countryVO.getName());
        startActivity(intent);
    }
}
