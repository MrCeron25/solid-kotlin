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
    override fun execute(args: List<String>) {
        if (args.size == 7) {
            val index = args[1].toIntOrNull()
            if (index != null) {
                val res = repository.change(index, studentFactory.create {
                    surname = args[2]
                    name = args[3]
                    patronymic = args[4]
                    age = args[5].toIntOrNull()
                    sex = Sex.parseSex(args[6])
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