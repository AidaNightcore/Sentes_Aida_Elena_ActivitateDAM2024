package com.example.acvarii;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AcvariuDAO {
    @Insert
    public void insertAcvariu (Acvariu acvariu);

    @Delete
    public void deleteAcvariu (Acvariu acvariu);

    @Query("select * from acvarii")
    public List<Acvariu> getAcvarii();
}
