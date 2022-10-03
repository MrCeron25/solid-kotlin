package student

import enums.Sex

data class StudentImpl(
    override var surname: String = "",
    override var name: String = "",
    override var patronymic: String? = "",
    override var age: Int? = 0,
    override var sex: Sex = Sex.UNDEFINED
) : Student {

    override fun toString(): String {
        return "$surname $name $patronymic $age $sex"
    }

}