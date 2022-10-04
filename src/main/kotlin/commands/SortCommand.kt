package commands

import dataBase.DataBaseImpl
import dataBase.PrintDataBase
import enums.CommandName
import student.StudentImpl

class SortCommand(
    private val repository: DataBaseImpl<StudentImpl>,
    private val printDataBase: PrintDataBase<StudentImpl>,
    override val name: String = CommandName.SORT.stringValue,
    override val description: String = "Команда сортировки",
    override val example: String = "${CommandName.SORT.stringValue} sortField",
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