package commands

import enums.CommandNames
import student.StudentImpl
import dataBase.DataBaseImpl

import enums.Sex
import factory.SimpleStudentFactory

class AddCommand(
    private val repository: DataBaseImpl<StudentImpl>,
    private val studentFactory: SimpleStudentFactory,
    override val name: String = CommandNames.ADD,
    override val description: String = "Команда добавления",
    override val example: String = "${CommandNames.ADD} surname name patronymic age(Int) sex(m/w)",
    override val neededNumberArgs: Int = 5
) : Command {
    override fun execute(args: List<String>) {
        if (args.size == neededNumberArgs) {
            val (_surname, _name, _patronymic, _age, _sex) = args
            val res = repository.add(studentFactory.create {
                surname = _surname
                name = _name
                patronymic = _patronymic
                age = _age.toIntOrNull()
                sex = Sex.parseSex(_sex)
            })
            if (res) {
                println("Студент добавлен : ${repository.data.last()}.")
            } else {
                println("Ошибка добавления.")
            }
        } else {
            println("Ошибка параметров. Пример $example.")
        }
    }
}