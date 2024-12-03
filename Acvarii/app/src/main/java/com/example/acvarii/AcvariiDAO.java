package com.example.acvarii;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AcvariiDAO {
    @Insert
    void Insert(Acvariu acvariu);

    @Query("SELECT * FROM Acvarii")
    List<Acvariu> GetAll();

    @Delete
    void Delete(Acvariu acvariu);
}
