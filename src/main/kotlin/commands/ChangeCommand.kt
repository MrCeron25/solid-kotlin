package commands

import context.Context
import enums.CommandNames
import enums.Sex

class ChangeCommand(
    override val name: String = CommandNames.CHANGE,
    override val description: String = "Команда изменения",
    override val example: String = "${CommandNames.CHANGE} changeIndex surname name patronymic age(Int) sex(m/w)",
    override val neededNumberArgs: Int = 6
) : Command {
    // /change 1 qwe asd zxc 456 m
    override fun execute(context: Context, args: List<String>) {
        if (args.size == neededNumberArgs) {
            val (_index, _surname, _name, _patronymic, _age, _sex) = args
            val index = _index.toIntOrNull()
            if (index != null) {
                val res = context.dataBase.change(index, context.studentFactory.create {
                    surname = _surname
                    name = _name
                    patronymic = _patronymic
                    age = _age.toIntOrNull()
                    sex = Sex.parseSex(_sex)
                })
                if (res) {
                    println("Студент №$index изменён : ${context.dataBase.data[index - 1]}")
                } else {
                    println("Ошибка изменения.")
                }
            } else {
                println("Ошибка ввода номера записи.")
            }
        } else {
            println("Ошибка параметров. Пример $example.")
        }
    }
}

// https://stackoverflow.com/questions/57035733/destructuring-declaration-initializer-of-type-liststring-must-have-a-componen
private operator fun <E> List<E>.component6(): E = get(5)
