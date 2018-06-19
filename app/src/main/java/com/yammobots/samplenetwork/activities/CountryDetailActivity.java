package com.yammobots.samplenetwork.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.yammobots.samplenetwork.R;
import com.yammobots.samplenetwork.data.models.CountryModel;
import com.yammobots.samplenetwork.data.vo.CountryVO;
import com.yammobots.samplenetwork.data.vo.CurrencyVO;
import com.yammobots.samplenetwork.utils.SvgSoftwareLayerSetter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class CountryDetailActivity extends AppCompatActivity {

    private ImageView imgVFlag;
    private TextView tvCountryName ;
    private TextView tvCapitalCity;
    private TextView tvPopulation;
    private TextView tvCurrencySymbol;
    private TextView tvCurrencyCode;
    private TextView tvCurrencyName;
    private RequestBuilder<PictureDrawable> requestBuilder;

    public static Intent getNewIntent(Context context, String countryName){
        Intent intent = new Intent(context, CountryDetailActivity.class);
        intent.putExtra("country_name", countryName);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        imgVFlag = findViewById(R.id.img_v_dt_country_flag);
        tvCountryName = findViewById(R.id.tv_dt_country_name);
        tvCapitalCity = findViewById(R.id.tv_dt_capital_city);
        tvPopulation = findViewById(R.id.tv_dt_population);
        tvCurrencySymbol = findViewById(R.id.tv_dt_currency_symbol);
        tvCurrencyCode = findViewById(R.id.tv_dt_currency_code);
        tvCurrencyName = findViewById(R.id.tv_dt_currency_name);

        requestBuilder = Glide.with(this)
                .as(PictureDrawable.class)
                .transition(withCrossFade())
                .listener(new SvgSoftwareLayerSetter());

        String countryName = getIntent().getStringExtra("country_name");
        CountryVO countryVO = CountryModel.getInstance().getCountryByName(countryName);
        bindView(countryVO);

    }

    private void bindView(CountryVO country) {
        Uri uri = Uri.parse(country.getFlag());
        requestBuilder.load(uri).into(imgVFlag);

        tvCountryName.setText(country.getName());
        tvCapitalCity.setText(country.getCapital());
        tvPopulation.setText(country.getPopulation());

        for (CurrencyVO currencyVO : country.getCurrencies()){
            tvCurrencySymbol.setText(currencyVO.getSymbol());
            tvCurrencyCode.setText(currencyVO.getCode());
            tvCurrencyName.setText(currencyVO.getName());
        }
    }
}
