package com.example.vedrochat.extensions

import java.lang.IllegalStateException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))

    return dateFormat.format(this)
}

fun Date.add(value: Long, units: TimeUnit): Date {
    var time = this.time

    time += when (units) {
        TimeUnit.SECONDS -> TimeUnit.SECONDS.toMillis(value)
        TimeUnit.MINUTES -> TimeUnit.MINUTES.toMillis(value)
        TimeUnit.HOURS -> TimeUnit.HOURS.toMillis(value)
        TimeUnit.DAYS -> TimeUnit.DAYS.toMillis(value)
        else -> throw IllegalStateException("invalid date units")
    }
    this.time = time

    return this
}

// TODO
fun Date.humanizeDiff() {}