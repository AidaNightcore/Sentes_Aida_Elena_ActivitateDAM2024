package com.example.acvarii;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ImageAdapter extends BaseAdapter {
    public ImageAdapter(Context ctx, List<Image> imageList, int layoutId) {
        this.ctx = ctx;
        this.imageList = imageList;
        this.layoutId = layoutId;
    }

    private Context ctx;
    private List<Image> imageList;
    private int layoutId;


    @Override
    public int getCount() {
        return imageList.size();
    }

    @Override
    public Object getItem(int position) {
        return imageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(layoutId, parent, false);

        TextView twDescriere = v.findViewById(R.id.TWDescriere);
        ImageView ivImage = v.findViewById(R.id.IVImage);

        Image image = imageList.get(position);

        twDescriere.setText(image.getDescriere());
        ivImage.setImageBitmap(image.getImagine());

        return v;
    }
}
