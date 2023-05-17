package com.estiam.mybestyoutube;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface YoutubeVideoDAO {

    @Query("SELECT * FROM youtubeVideo WHERE id= :id")
    public YoutubeVideo find(Long id);

    @Query("SELECT * FROM youtubeVideo")
    public List<YoutubeVideo> list();

    @Insert
    public void add(YoutubeVideo... todos);

    @Update
    public void update(YoutubeVideo... todos);

    @Delete
    public void delete(YoutubeVideo... todos);

}
