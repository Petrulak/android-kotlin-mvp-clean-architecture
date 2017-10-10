package com.petrulak.cleankotlin.data.mapper.base

import java.util.*

abstract class Mapper<To, From> {

    protected val invalidInt = -1
    protected val invalidLong = (-1).toLong()
    protected val invalidDouble = -1.0
    protected val emptyString = ""

    abstract fun map(from: From): To

    abstract fun reverse(to: To): From

    fun map(list: List<From>?): List<To> {
        if (list != null) {
            val result = ArrayList<To>(list.size)
            list.mapTo(result) { map(it) }
            return result
        }
        return emptyList()
    }

    fun reverse(list: List<To>?): List<From> {
        if (list != null) {
            val result = ArrayList<From>(list.size)
            list.mapTo(result) { reverse(it) }
            return result
        }
        return emptyList()
    }
}
