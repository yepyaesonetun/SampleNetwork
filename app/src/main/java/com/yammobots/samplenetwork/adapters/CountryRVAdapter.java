package com.yammobots.samplenetwork.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yammobots.samplenetwork.R;
import com.yammobots.samplenetwork.data.vo.CountryVO;
import com.yammobots.samplenetwork.delegates.CountryItemDelegate;
import com.yammobots.samplenetwork.viewholders.CountryViewHolder;

/**
 * Created by yepyaesonetun on 6/18/18.
 **/

public class CountryRVAdapter extends BaseRecyclerAdapter<CountryViewHolder, CountryVO> {

    private CountryItemDelegate mDelegate;

    public CountryRVAdapter(Context context, CountryItemDelegate delegate) {
        super(context);
        mDelegate = delegate;
    }

    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false);
        return new CountryViewHolder(view, mDelegate);
    }
}
