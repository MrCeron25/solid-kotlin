package command

import student.StudentImpl
import dataBase.DataBaseImpl
import factory.SimpleStudentFactory

class ChangeCommand(
    private val repository: DataBaseImpl<StudentImpl>,
    private val studentFactory: SimpleStudentFactory,
    override val name: String = "change",
    override val description: String = "Команда изменения"
) : Command {
    // /change 1 qwe asd grte 456 m
    fun execute(arguments: List<String>) {
        if (arguments.size == 7) {
            val index = arguments[1].toIntOrNull()
            if (index != null) {
                val res = repository.change(index, studentFactory.create {
                    surname = arguments[2]
                    name = arguments[3]
                    patronymic = arguments[4]
                    age = arguments[5].toIntOrNull()
                    sex = Sex.parseSex(arguments[6])
                })
                if (res) {
                    println("Студент №$index изменён : ${repository.data[index - 1]}")
                } else {
                    println("Ошибка изменения.")
                }
            } else {
                println("Ошибка ввода номера записи.")
            }
        } else {
            println("Ошибка изменения. Пример : \"/change changeIndex surname" + " name patronymic age(Int) sex(M/W)\"")
        }
    }
}