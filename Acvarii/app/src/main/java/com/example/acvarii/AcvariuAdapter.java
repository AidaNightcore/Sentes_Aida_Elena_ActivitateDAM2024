package com.example.acvarii;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;
import java.util.Objects;

public class AcvariuAdapter extends BaseAdapter {
    private List<Acvariu> acvarii = null;
    private Context ctx;
    private int resursaLayout;
    public AcvariuAdapter(List<Acvariu> acvarii, Context ctx, int resursaLayout)
    {
        this.acvarii=acvarii;
        this.ctx=ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return acvarii.size();
    }
    @Override
    public Object getItem(int i){
        return acvarii.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        return null;
    }

}


