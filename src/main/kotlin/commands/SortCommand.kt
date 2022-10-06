package commands

import enums.CommandNames
import dataBase.DataBaseImpl
import dataBase.PrintStudentDataBase
import student.StudentImpl

class SortCommand(
    private val repository: DataBaseImpl<StudentImpl>,
    private val printDataBase: PrintStudentDataBase<StudentImpl>,
    override val name: String = CommandNames.SORT,
    override val description: String = "Команда сортировки",
    override val example: String = "${CommandNames.SORT} sortField(1..5)",
    override val neededNumberArgs: Int = 1
) : Command {
    // /sort 1
    override fun execute(args: List<String>) {
        if (args.size == neededNumberArgs) {
            val(_index) = args
            val index = _index.toIntOrNull()
            if (index != null) {
                repository.sortWith(compareBy {
                    when (index) {
                        1 -> it.surname
                        2 -> it.name
                        3 -> it.patronymic
                        4 -> it.age
                        5 -> it.sex
                        else -> it.surname
                    }
                })
                printDataBase.print(repository)
            } else {
                println("Ошибка ввода поля сортировки.")
            }
        } else {
            println("Ошибка параметров. Пример $example.")
        }
    }
}