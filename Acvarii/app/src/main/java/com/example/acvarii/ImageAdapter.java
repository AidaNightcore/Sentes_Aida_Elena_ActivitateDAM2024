package com.example.acvarii;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private List<ImaginiAcvariu> imaginiAcvariu = null;
    private Context ctx;
    private int resursaLayout;
    public ImageAdapter(List<ImaginiAcvariu> acvarii, Context ctx, int resursaLayout)
    {
        this.imaginiAcvariu =acvarii;
        this.ctx=ctx;
        this.resursaLayout = resursaLayout;
    }

    @Override
    public int getCount() {
        return imaginiAcvariu.size();
    }
    @Override
    public Object getItem(int i){
        return imaginiAcvariu.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout,viewGroup, false);

        TextView textAfisat = v.findViewById(R.id.textTV);
        ImageView imagine= v.findViewById(R.id.imageIV);

        ImaginiAcvariu ImaginiAcvariu = (ImaginiAcvariu) getItem(i);



        return v;
    }
}
