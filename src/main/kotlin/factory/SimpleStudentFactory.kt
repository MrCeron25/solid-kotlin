package factory

import StudentImpl

class SimpleStudentFactory : StudentFactory<StudentImpl> {

//    fun create(props: Map<String, *>): StudentImpl {
//        var student: StudentImpl? = null
//        try {
//            student = StudentImpl()
//            student.surname = props["surname"] as String
//            student.name = props["name"] as String
//            student.patronymic = props["patronymic"] as String
//            student.age = props["age"] as? Int ?: throw ClassCastException("Возраст должен быть целым числом")
//            student.sex = props["sex"] as Sex
//        } catch (error: ClassCastException) {
//            println("Ошибка создания студента: \n${error.message}")
//        }
//        return student ?: throw NullPointerException("Ошибка зарузки студента")
//    }
//
//            mapOf(
//                "surname" to "Алексеев",
//                "name" to "Артём",
//                "patronymic" to "Алексеев",
//                "age" to 18,
//                "sex" to Sex.WOMAN
//            )

    override fun create(builder: StudentImpl.() -> Unit) = StudentImpl().apply(builder)

}
