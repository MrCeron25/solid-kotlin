package commands

import context.Context
import enums.CommandNames

import enums.Sex

class AddCommand(
    override val name: String = CommandNames.ADD,
    override val description: String = "Команда добавления",
    override val example: String = "${CommandNames.ADD} surname name patronymic age(Int) sex(m/w)",
    override val neededNumberArgs: Int = 5
) : Command {
    override fun execute(context: Context, args: List<String>) {
        if (args.size == neededNumberArgs) {
            val (_surname, _name, _patronymic, _age, _sex) = args
            val res = context.dataBase.add(context.studentFactory.create {
                surname = _surname
                name = _name
                patronymic = _patronymic
                age = _age.toIntOrNull()
                sex = Sex.parseSex(_sex)
            })
            if (res) {
                println("Студент добавлен : ${context.dataBase.data.last()}.")
            } else {
                println("Ошибка добавления.")
            }
        } else {
            println("Ошибка параметров. Пример $example.")
        }
    }
}