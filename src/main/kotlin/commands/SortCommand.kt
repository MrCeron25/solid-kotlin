package commands

import context.Context
import enums.CommandNames

class SortCommand(
    override val name: String = CommandNames.SORT,
    override val description: String = "Команда сортировки",
    override val example: String = "${CommandNames.SORT} sortField(1..5)",
    override val neededNumberArgs: Int = 1
) : Command {
    // /sort 1
    override fun execute(context: Context, args: List<String>) {
        if (args.size == neededNumberArgs) {
            val (_index) = args
            val index = _index.toIntOrNull()
            if (index != null) {
                context.dataBase.sortWith(compareBy {
                    when (index) {
                        1 -> it.surname
                        2 -> it.name
                        3 -> it.patronymic
                        4 -> it.age
                        5 -> it.sex
                        else -> it.surname
                    }
                })
                context.printStudentDataBase.print(context.dataBase)
            } else {
                println("Ошибка ввода поля сортировки.")
            }
        } else {
            println("Ошибка параметров. Пример $example.")
        }
    }
}