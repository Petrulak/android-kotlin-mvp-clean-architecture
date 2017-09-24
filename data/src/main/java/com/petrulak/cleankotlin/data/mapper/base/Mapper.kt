package com.petrulak.cleankotlin.data.mapper.base

import java.util.*

abstract class Mapper<To, From> {

    protected val invalidInt = -1
    protected val invalidLong = (-1).toLong()
    protected val invalidDouble = -1.0
    protected val emptyString = ""

    abstract fun map(from: From): To

    abstract fun reverse(to: To): From

    fun map(froms: List<From>?): List<To> {
        if (froms != null) {
            val result = ArrayList<To>(froms.size)
            froms.mapTo(result) { map(it) }
            return result
        }
        return emptyList()
    }

    fun reverse(tos: List<To>?): List<From> {
        if (tos != null) {
            val result = ArrayList<From>(tos.size)
            tos.mapTo(result) { reverse(it) }
            return result
        }
        return emptyList()
    }
}
