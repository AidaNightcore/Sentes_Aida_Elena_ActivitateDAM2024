package com.example.acvarii;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.view.ViewCompat;

import org.w3c.dom.Text;

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
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View v = inflater.inflate(resursaLayout,viewGroup, false);

        TextView idAcvariu = v.findViewById(R.id.idAcvariu);
        TextView grosimeAcvariu = v.findViewById(R.id.grosimeAcvariu);
        TextView pretAcvariu = v.findViewById(R.id.pretAcvariu);
        TextView greutateAcvariu = v.findViewById(R.id.greutateAcvariu);
        TextView formaAcvariu = v.findViewById(R.id.formaAcvariu);

        Acvariu acvariu = (Acvariu) getItem(i);

        grosimeAcvariu.setText(acvariu.getGrosimeSticla());
        pretAcvariu.setText(((int) acvariu.getPret()));
        greutateAcvariu.setText((int) acvariu.getGreutate());
        formaAcvariu.setText(acvariu.getForma());
        
        return v;
    }

}


