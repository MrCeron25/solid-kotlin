package commands

import context.Context
import enums.CommandNames
import parsers.IntParserImpl
import parsers.Parser

class SortCommand(
    override val name: String = CommandNames.SORT,
    override val description: String = "Команда сортировки",
    override val example: String = "${CommandNames.SORT} sortField(1..5)",
    override val neededNumberArgs: Int = 1,
    private val intParser: Parser<String, Int?> = IntParserImpl()
) : Command {
    // /sort 1
    override fun execute(context: Context, args: List<String>) {
        if (args.size == neededNumberArgs) {
            val (_index) = args
            val resIndex = intParser.parse(_index)
            if (resIndex != null) {
                context.dataBase.sortWith(compareBy {
                    when (resIndex) {
                        2 -> it.name
                        3 -> it.patronymic
                        4 -> it.age
                        5 -> it.sex
                        else -> it.surname // 1 -> it.surname
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