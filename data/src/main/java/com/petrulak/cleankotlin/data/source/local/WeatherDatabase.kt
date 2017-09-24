package com.petrulak.cleankotlin.data.source.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.petrulak.cleankotlin.data.source.local.dao.WeatherDao
import com.petrulak.cleankotlin.data.source.local.model.WeatherEntity


@Database(entities = arrayOf(WeatherEntity::class), version = 1)
abstract class WeatherDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}