package command

import student.StudentImpl
import dataBase.DataBaseImpl
import enums.CommandName
import enums.Sex
import factory.SimpleStudentFactory

class AddCommand(
    private val repository: DataBaseImpl<StudentImpl>,
    private val studentFactory: SimpleStudentFactory,
    override val name: String = CommandName.ADD.stringValue,
    override val description: String = "Команда добавления"
) : Command {
    override fun execute(args: List<String>) {
        if (args.size == 6) {
            val res = repository.add(studentFactory.create {
                surname = args[1]
                name = args[2]
                patronymic = args[3]
                age = args[4].toIntOrNull()
                sex = Sex.parseSex(args[5])
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
}