package ru.skillbranch.devintensive.utils
import java.util.*

object Utils {
    fun parseFullName(fullName: String?): Pair<String?, String?> {

        val parts: List<String>? =
            if (fullName?.isNullOrBlank() == false)
                fullName.split(" ")
            else
                null
        val firstName = parts?.getOrNull(0)
        val lastName = parts?.getOrNull(1)

        return firstName to lastName
    }

    fun transliteration(payload: String, divider: String = " "): String {
        val result = StringBuilder()
        payload.toCharArray().forEach {
            if (it.isWhitespace())
                result.append(divider)
            else
            {
                if (it.isUpperCase())
                {
                    var translate = Transliteration.translateLatter(it.toLowerCase().toString())
                    result.append(translate.first().toUpperCase())
                    result.append(translate.substring(1))
                }
                else
                    result.append(Transliteration.translateLatter(it.toString()))
            }
        }
        return result.toString()
    }

    fun toInitials(firstName: String?, lastName: String?): String? {
        val firstInitial =
            if(firstName?.isNullOrBlank() == false)
                firstName.first().toUpperCase().toString()
            else
                ""
        val secondInitial =
            if(lastName?.isNullOrBlank() == false)
                lastName.first().toUpperCase().toString()
            else
                ""
        return if (firstInitial.isEmpty() && secondInitial.isEmpty()) {
            null
        } else {
            "$firstInitial$secondInitial"
        }
    }
}