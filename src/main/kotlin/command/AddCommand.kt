package command

import StudentImpl
import dataBase.DataBaseImpl
import factory.SimpleStudentFactory

class AddCommand(
    private val studentFactory: SimpleStudentFactory,
    private val repository: DataBaseImpl<StudentImpl>,
    override val name: String = "add",
    override val description: String = "Команда добавления"
) : Command {
    fun run(arguments: List<String>) {
        if (arguments.size == 6) {
            val res = repository.add(studentFactory.create {
                surname = arguments[1]
                name = arguments[2]
                patronymic = arguments[3]
                age = arguments[4].toIntOrNull()
                sex = Sex.parseSex(arguments[5])
            })
            if (res) {
                println("Студент добавлен : ${repository.data.last()}")
            } else {
                println("Ошибка добавления.")
            }
        } else {
            println("Ошибка добавления. Пример : \"/add surname name patronymic age(Int) sex(M/W)\"")
        }
    }

    override fun run() {}
}