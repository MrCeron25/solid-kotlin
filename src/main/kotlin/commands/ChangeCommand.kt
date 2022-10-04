package commands

import student.StudentImpl
import dataBase.DataBaseImpl
import enums.CommandName
import enums.Sex
import factory.SimpleStudentFactory

class ChangeCommand(
    private val repository: DataBaseImpl<StudentImpl>,
    private val studentFactory: SimpleStudentFactory,
    override val name: String = CommandName.CHANGE.stringValue,
    override val description: String = "Команда изменения",
    override val example: String = "${CommandName.CHANGE.stringValue} changeIndex surname name patronymic age(Int) sex(m/w)",
    override val neededNumberArgs: Int = 6
) : Command {
    // /change 1 qwe asd grte 456 m
    override fun execute(args: List<String>) {
        if (args.size == neededNumberArgs) {
            val (_index, _surname, _name, _patronymic, _age, _sex) = args
            val index = _index.toIntOrNull()
            if (index != null) {
                val res = repository.change(index, studentFactory.create {
                    surname = _surname
                    name = _name
                    patronymic = _patronymic
                    age = _age.toIntOrNull()
                    sex = Sex.parseSex(_sex)
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
            println("Ошибка параметров. Пример $example.")
        }
    }
}

// https://stackoverflow.com/questions/57035733/destructuring-declaration-initializer-of-type-liststring-must-have-a-componen
private operator fun <E> List<E>.component6(): E = get(5)
