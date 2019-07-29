package ru.skillbranch.devintensive.extensions

fun String?.trimOrNull(): String? {
    val resultString = this?.trim()

    return if (resultString?.isEmpty() == true) null else resultString
}

fun String?.truncate(lenght: Int = 16): String? {
    val str = this?.trim()
    return when (str?.length){
        in 0..lenght -> str
        else -> "${str?.take(lenght)?.trim()}..."
    }
}

fun String?.stripHtml(): String? {
    return this
        ?.replace("<.*?>".toRegex(), "")
        ?.replace("\\s+".toRegex(), " ")
}