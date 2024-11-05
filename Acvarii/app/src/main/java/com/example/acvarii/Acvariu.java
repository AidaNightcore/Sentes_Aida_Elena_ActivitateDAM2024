package com.example.acvarii;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Acvariu implements Parcelable {
    private String forma;
    private float greutate;

    private String capacitate;
    private int grosimeSticla;
    private float pret;

    protected Acvariu(Parcel in) {
        forma = in.readString();
        greutate = in.readFloat();
        capacitate = in.readString();
        grosimeSticla = in.readInt();
        pret = in.readFloat();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(forma);
        dest.writeFloat(greutate);
        dest.writeString(capacitate);
        dest.writeInt(grosimeSticla);
        dest.writeFloat(pret);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Acvariu> CREATOR = new Creator<Acvariu>() {
        @Override
        public Acvariu createFromParcel(Parcel in) {
            return new Acvariu(in);
        }

        @Override
        public Acvariu[] newArray(int size) {
            return new Acvariu[size];
        }
    };

    public String getForma() {
        return forma;
    }

    public float getGreutate() {
        return greutate;
    }

    public String getCapacitate() {
        return capacitate;
    }

    public int getGrosimeSticla() {
        return grosimeSticla;
    }

    public float getPret() {
        return pret;
    }

    public Acvariu(String forma, float greutate, String capacitate, int grosimeSticla, float pret) {
        this.forma = forma;
        this.greutate = greutate;
        this.capacitate = capacitate;
        this.grosimeSticla = grosimeSticla;
        this.pret = pret;
    }

    @Override
    public String toString() {
        return "Acvariu{" +
                "forma='" + forma + '\'' +
                ", greutate=" + greutate +
                ", capacitate=" + capacitate +
                ", grosimeSticla=" + grosimeSticla +
                ", pret=" + pret +
                '}';
    }
    public void setForma(String forma) {
        this.forma = forma;
    }

    public void setGreutate(float greutate) {
        this.greutate = greutate;
    }

    public void setCapacitate(String capacitate) {
        this.capacitate = capacitate;
    }

    public void setGrosimeSticla(int grosimeSticla) {
        this.grosimeSticla = grosimeSticla;
    }

    public void setPret(float pret) {
        this.pret = pret;
    }



}
