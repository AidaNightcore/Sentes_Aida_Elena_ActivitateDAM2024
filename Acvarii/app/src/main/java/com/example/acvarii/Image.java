package com.example.acvarii;

import android.graphics.Bitmap;

public class Image {
    public Bitmap getImagine() {
        return imagine;
    }

    public void setImagine(Bitmap imagine) {
        this.imagine = imagine;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Image(Bitmap imagine, String descriere, String link) {
        this.imagine = imagine;
        this.descriere = descriere;
        this.link = link;
    }

    public Image() {
        this.imagine = null;
        this.descriere = "";
        this.link = "";
    }

    Bitmap imagine;
    String descriere;
    String link;
}
