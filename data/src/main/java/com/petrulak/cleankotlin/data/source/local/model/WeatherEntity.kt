package com.petrulak.cleankotlin.data.source.local.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity
data class WeatherEntity(
    @PrimaryKey()
    val uid: Long,
    val name: String,
    val visibility: Int
)

