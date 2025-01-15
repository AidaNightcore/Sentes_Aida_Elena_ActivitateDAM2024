package com.example.acvarii;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class AcvariuAdapter extends BaseAdapter {
    private Context ctx;
    private List<Acvariu> acvariuList;
    private int layoutID;
    public Context getCtx() {
        return ctx;
    }

    public void setCtx(Context ctx) {
        this.ctx = ctx;
    }

    public List<Acvariu> getAcvariuList() {
        return acvariuList;
    }

    public void setAcvariuList(List<Acvariu> acvariuList) {
        this.acvariuList = acvariuList;
    }

    public int getLayoutID() {
        return layoutID;
    }

    public void setLayoutID(int layoutID) {
        this.layoutID = layoutID;
    }

    public AcvariuAdapter(Context ctx, List<Acvariu> acvariuList, int layoutID) {
        this.ctx = ctx;
        this.acvariuList = acvariuList;
        this.layoutID = layoutID;
    }

    @Override
    public int getCount() {
        return acvariuList.size();
    }

    @Override
    public Object getItem(int position) {
        return acvariuList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(layoutID, parent, false);

        TextView twNumePesti = v.findViewById(R.id.TWNumePesti);
        TextView twNrPesti = v.findViewById(R.id.TWNrPesti);
        TextView twMarimeAcvariu = v.findViewById(R.id.TWMarimeAcvariu);
        TextView twBrandAcvariu = v.findViewById(R.id.TWBrandAcvariu);

        Acvariu acvariu = acvariuList.get(position);

        twNumePesti.setText(acvariu.getNumePesti());
        twNrPesti.setText(String.valueOf(acvariu.getNrPesti()));
        twMarimeAcvariu.setText(String.valueOf(acvariu.getMarimeAcvariu()));
        twBrandAcvariu.setText(acvariu.getBrandAcvariu());

        return v;
    }

}
