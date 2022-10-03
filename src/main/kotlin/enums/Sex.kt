package enums

import java.util.Locale

enum class Sex {
    MAN, WOMAN, UNDEFINED;

    companion object {
        fun parseSex(str: String): Sex {
            return when (str.trim().lowercase(Locale.getDefault())) {
                "m" -> MAN
                "w" -> WOMAN
                else -> UNDEFINED
            }
        }
    }
}