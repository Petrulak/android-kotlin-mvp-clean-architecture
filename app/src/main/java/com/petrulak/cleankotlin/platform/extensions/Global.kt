package com.petrulak.cleankotlin.platform.extensions

/**
 * Since we don't need our objects to be thread safe most of the time, we can use  `LazyThreadSafetyMode.NONE` which has lower
 * overhead.
 */
fun <T> lazyFast(operation: () -> T): Lazy<T> = lazy(LazyThreadSafetyMode.NONE) {
    operation()
}