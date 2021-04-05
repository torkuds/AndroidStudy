package com.torkuds.androidstudy.database;


import com.torkuds.androidstudy.entity.News;

import java.util.List;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;

@Dao
public interface NewsDao {

    @Query("select * from news")
    Observable<List<News>> getAllNews();

    @Insert
    Completable addAll(List<News> newsList);
}
