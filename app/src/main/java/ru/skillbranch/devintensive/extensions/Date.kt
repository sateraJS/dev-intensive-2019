package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.utils.Plurals
import java.text.SimpleDateFormat
import java.util.*

const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND) : Date {
    var time = this.time

    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

fun Date.humanizeDiff(): String {
    var interval = this.time - Date().time
    if (interval < 0) {
        interval *= -1
        return when (interval) {
            in 0..SECOND -> "только что"
            in SECOND..55 * SECOND -> "несколько секунд назад"
            in 55 * SECOND..65 * SECOND -> "минуту назад"
            in 65 * SECOND..55 * MINUTE -> "${TimeUnits.MINUTE.plural((interval / MINUTE).toInt())} назад"
            in 55 * MINUTE..65 * MINUTE -> "час назад"
            in 65 * MINUTE..23 * HOUR -> "${TimeUnits.HOUR.plural((interval / HOUR).toInt())} назад"
            in 23 * HOUR..25 * HOUR -> "день назад"
            in 25 * HOUR..365 * DAY -> "${TimeUnits.DAY.plural((interval / DAY).toInt())} назад"
            else -> "более года назад"
        }
    } else {
        return when (interval) {
            in 0..SECOND -> "только что"
            in SECOND..55 * SECOND -> "через несколько секунд"
            in 55 * SECOND..65 * SECOND -> "через минуту"
            in 65 * SECOND..55 * MINUTE -> "через ${TimeUnits.MINUTE.plural((interval / MINUTE).toInt())}"
            in 55 * MINUTE..65 * MINUTE -> "через час"
            in 65 * MINUTE..23 * HOUR -> "через ${TimeUnits.HOUR.plural((interval / HOUR).toInt())}"
            in 23 * HOUR..25 * HOUR -> "через день"
            in 25 * HOUR..365 * DAY -> "через ${TimeUnits.DAY.plural((interval / DAY).toInt())}"
            else -> "более чем через год"
        }
    }
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY;

    fun plural(value: Int): String {
        return Plurals.getPlurals(value, this)
    }
}