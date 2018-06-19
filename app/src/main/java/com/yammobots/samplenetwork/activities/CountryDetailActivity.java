package com.yammobots.samplenetwork.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.yammobots.samplenetwork.R;
import com.yammobots.samplenetwork.data.models.CountryModel;
import com.yammobots.samplenetwork.data.vo.CountryVO;
import com.yammobots.samplenetwork.data.vo.CurrencyVO;
import com.yammobots.samplenetwork.mvp.presenters.CountryDetailPresenter;
import com.yammobots.samplenetwork.mvp.views.CountryDetailView;
import com.yammobots.samplenetwork.utils.SvgSoftwareLayerSetter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class CountryDetailActivity extends AppCompatActivity implements CountryDetailView{

    private static final String IE_COUNTRY_NAME = "IE_COUNTRY_NAME";

    @BindView(R.id.img_v_dt_country_flag)
    ImageView imgVFlag;
    @BindView(R.id.tv_dt_country_name)
    TextView tvCountryName;
    @BindView(R.id.tv_dt_capital_city)
    TextView tvCapitalCity;
    @BindView(R.id.tv_dt_population)
    TextView tvPopulation;
    @BindView(R.id.tv_dt_currency_symbol)
    TextView tvCurrencySymbol;
    @BindView(R.id.tv_dt_currency_code)
    TextView tvCurrencyCode;
    @BindView(R.id.tv_dt_currency_name)
    TextView tvCurrencyName;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private RequestBuilder<PictureDrawable> requestBuilder;
    private CountryDetailPresenter mPresenter;


    public static Intent getNewIntent(Context context, String countryName) {
        Intent intent = new Intent(context, CountryDetailActivity.class);
        intent.putExtra(IE_COUNTRY_NAME, countryName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());

        requestBuilder = Glide.with(this)
                .as(PictureDrawable.class)
                .transition(withCrossFade())
                .listener(new SvgSoftwareLayerSetter());

        String countryName = getIntent().getStringExtra(IE_COUNTRY_NAME);

        mPresenter = new CountryDetailPresenter(this);
        mPresenter.onFinishUIComponentsSetUp(countryName);

    }


    @Override
    public void displayErrorMsg(String errorMsg) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayDetailData(CountryVO countryVO) {
        bindData(countryVO);
    }

    private void bindData(CountryVO country) {
        Uri uri = Uri.parse(country.getFlag());
        requestBuilder.load(uri).into(imgVFlag);

        tvCountryName.setText(country.getName());
        tvCapitalCity.setText(country.getCapital());
        tvPopulation.setText(country.getPopulation());

        for (CurrencyVO currencyVO : country.getCurrencies()) {
            tvCurrencySymbol.setText(currencyVO.getSymbol());
            tvCurrencyCode.setText(currencyVO.getCode());
            tvCurrencyName.setText(currencyVO.getName());
        }
    }
}
