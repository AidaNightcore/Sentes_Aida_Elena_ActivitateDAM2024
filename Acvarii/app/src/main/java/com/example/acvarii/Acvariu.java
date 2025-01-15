package com.example.acvarii;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "acvarii")
public class Acvariu implements Parcelable {
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Acvariu{");
        sb.append("idAcvariu=").append(idAcvariu);
        sb.append(", numePesti='").append(numePesti).append('\'');
        sb.append(", nrPesti=").append(nrPesti);
        sb.append(", marimeAcvariu=").append(marimeAcvariu);
        sb.append(", brandAcvariu='").append(brandAcvariu).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @PrimaryKey(autoGenerate = true)
    private int idAcvariu;
    private String numePesti;
    private int nrPesti;
    private int marimeAcvariu;
    private String brandAcvariu;

    protected Acvariu(Parcel in) {
        idAcvariu = in.readInt();
        numePesti = in.readString();
        nrPesti = in.readInt();
        marimeAcvariu = in.readInt();
        brandAcvariu = in.readString();
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

    public String getNumePesti() {
        return numePesti;
    }
    public int getIdAcvariu() {
        return idAcvariu;
    }
    public void setIdAcvariu(int idAcvariu) {
        this.idAcvariu = idAcvariu;
    }
    public void setNumePesti(String numePesti) {
        this.numePesti = numePesti;
    }
    public int getNrPesti() {
        return nrPesti;
    }

    public void setNrPesti(int nrPesti) {
        this.nrPesti = nrPesti;
    }

    public int getMarimeAcvariu() {
        return marimeAcvariu;
    }

    public void setMarimeAcvariu(int marimeAcvariu) {
        this.marimeAcvariu = marimeAcvariu;
    }

    public String getBrandAcvariu() {
        return brandAcvariu;
    }

    public void setBrandAcvariu(String brandAcvariu) {
        this.brandAcvariu = brandAcvariu;
    }


    public Acvariu(String numePesti, int nrPesti, int marimeAcvariu, String brandAcvariu) {
        this.numePesti = numePesti;
        this.nrPesti = nrPesti;
        this.marimeAcvariu = marimeAcvariu;
        this.brandAcvariu = brandAcvariu;
    }
    public Acvariu() {
        this.numePesti = "";
        this.nrPesti = 0;
        this.marimeAcvariu = 0;
        this.brandAcvariu = "";
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(idAcvariu);
        dest.writeString(numePesti);
        dest.writeInt(nrPesti);
        dest.writeInt(marimeAcvariu);
        dest.writeString(brandAcvariu);
    }
}
