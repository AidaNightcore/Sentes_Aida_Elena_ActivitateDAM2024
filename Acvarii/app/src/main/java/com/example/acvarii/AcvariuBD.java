package com.example.acvarii;

import androidx.room.Database;

@Database(entities = {Acvariu.class}, version = 1)
public abstract class AcvariuBD implements AcvariiDAO{
    public abstract AcvariiDAO acvariiDAO();
}
