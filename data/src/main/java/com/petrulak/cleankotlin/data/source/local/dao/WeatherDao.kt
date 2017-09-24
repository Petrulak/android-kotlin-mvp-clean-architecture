package com.petrulak.cleankotlin.data.source.local.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import com.petrulak.cleankotlin.data.source.local.model.WeatherEntity
import io.reactivex.Flowable

@Dao
interface WeatherDao {

    @Query("SELECT * FROM WeatherEntity")
    fun getAll(): Flowable<List<WeatherEntity>>

    @Query("SELECT * FROM WeatherEntity where name = :name")
    fun getByName(name: String): Flowable<WeatherEntity>

    @Insert
    fun insert(item: WeatherEntity)

    @Query("DELETE from WeatherEntity where uid =:id")
    fun deleteWeatherById(id: Long): Int

}