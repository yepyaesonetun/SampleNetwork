package com.yammobots.samplenetwork.viewholders;

import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.yammobots.samplenetwork.R;
import com.yammobots.samplenetwork.data.vo.CountryVO;
import com.yammobots.samplenetwork.data.vo.CurrencyVO;
import com.yammobots.samplenetwork.delegates.CountryItemDelegate;
import com.yammobots.samplenetwork.utils.SvgSoftwareLayerSetter;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Created by yepyaesonetun on 6/18/18.
 **/

public class CountryViewHolder extends BaseViewHolder<CountryVO> {

    @BindView(R.id.tv_name)
    TextView tvCountryName;
    @BindView(R.id.tv_capital)
    TextView tvCapitalCityName;
    @BindView(R.id.tv_population)
    TextView tvPopulation;
    @BindView(R.id.img_v_country_flag)
    ImageView imgFlag;

    private RequestBuilder<PictureDrawable> requestBuilder;
    private CountryItemDelegate mDelegate;

    public CountryViewHolder(View itemView, CountryItemDelegate delegate) {
        super(itemView);
        mDelegate = delegate;

        ButterKnife.bind(this, itemView);

        requestBuilder = Glide.with(itemView.getContext())
                .as(PictureDrawable.class)
                .transition(withCrossFade())
                .listener(new SvgSoftwareLayerSetter());


    }

    @Override
    public void setData(CountryVO data) {
        mData = data;
        tvCountryName.setText(data.getName());
        tvCapitalCityName.setText(data.getCapital());
        tvPopulation.setText(data.getPopulation());

        Uri uri = Uri.parse(data.getFlag());
        requestBuilder.load(uri).into(imgFlag);

    }

    @Override
    public void onClick(View v) {
        mDelegate.onTapCountry(mData);
    }
}
