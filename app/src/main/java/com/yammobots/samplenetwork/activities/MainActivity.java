package com.yammobots.samplenetwork.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.yammobots.samplenetwork.R;
import com.yammobots.samplenetwork.adapters.CountryRVAdapter;
import com.yammobots.samplenetwork.data.vo.CountryVO;
import com.yammobots.samplenetwork.mvp.presenters.CountryListPresenter;
import com.yammobots.samplenetwork.mvp.views.CountryListView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements CountryListView {

    @BindView(R.id.rv_main)
    RecyclerView mRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private CountryRVAdapter adapter;
    private CountryListPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        mPresenter = new CountryListPresenter(this);
        mPresenter.onCreate();

        toolbar.setTitle(R.string.app_name);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapter = new CountryRVAdapter(this, mPresenter);
        mRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPresenter.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.onDestory();
    }

   @Override
    public void displayErrorMsg(String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayCountryListData(List<CountryVO> countryVOList) {
        adapter.appendNewData(countryVOList);
    }

    @Override
    public void launchDetailScreen(String name) {
        Intent intent = CountryDetailActivity.getNewIntent(this, name);
        startActivity(intent);
    }

}
