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
import com.yammobots.samplenetwork.delegates.CountryItemDelegate;
import com.yammobots.samplenetwork.utils.SvgSoftwareLayerSetter;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;


/**
 * Created by yepyaesonetun on 6/18/18.
 **/

public class CountryViewHolder extends BaseViewHolder<CountryVO> {

    private TextView tvCountryName;
    private TextView tvCapitalCityName;
    private TextView tvPopulation;
    private ImageView imgFlag;

    private RequestBuilder<PictureDrawable> requestBuilder;
    private CountryItemDelegate mDelegate;

    public CountryViewHolder(View itemView, CountryItemDelegate delegate) {
        super(itemView);
        mDelegate = delegate;
        tvCountryName = itemView.findViewById(R.id.tv_name);
        tvCapitalCityName = itemView.findViewById(R.id.tv_capital);
        tvPopulation = itemView.findViewById(R.id.tv_population);
        imgFlag = itemView.findViewById(R.id.img_v_country_flag);

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
