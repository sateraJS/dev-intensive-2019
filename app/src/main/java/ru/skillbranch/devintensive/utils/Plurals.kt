package ru.skillbranch.devintensive.utils

import ru.skillbranch.devintensive.extensions.TimeUnits

object Plurals{

    fun getPlurals(value: Int, timeUnits: TimeUnits): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(value)
        stringBuilder.append(" ")
        val result = when (value) {
            1 -> getPluralFor1(timeUnits)
            in 2..4 -> getPluralFor2To4(timeUnits)
            else -> getPluralOther(timeUnits)
        }
        stringBuilder.append(result)
        return stringBuilder.toString()
    }

    private fun getPluralFor1(timeUnits: TimeUnits): String {
        return when(timeUnits) {
            TimeUnits.SECOND -> "секунду"
            TimeUnits.MINUTE -> "минуту"
            TimeUnits.HOUR -> "час"
            TimeUnits.DAY -> "день"
        }
    }

    private fun getPluralFor2To4(timeUnits: TimeUnits): String {
        return when (timeUnits) {
            TimeUnits.SECOND -> "секунды"
            TimeUnits.MINUTE -> "минуты"
            TimeUnits.HOUR -> "часа"
            TimeUnits.DAY -> "дня"
        }
    }

    private fun getPluralOther(timeUnits: TimeUnits): String {
        return when (timeUnits) {
            TimeUnits.SECOND -> "секунд"
            TimeUnits.MINUTE -> "минут"
            TimeUnits.HOUR -> "часов"
            TimeUnits.DAY -> "дней"
        }
    }
}