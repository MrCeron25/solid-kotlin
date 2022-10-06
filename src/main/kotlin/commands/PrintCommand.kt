package commands

import context.Context
import enums.CommandNames

class PrintCommand(
    override val name: String = CommandNames.PRINT,
    override val description: String = "Команда вывода",
    override val example: String = CommandNames.PRINT,
    override val neededNumberArgs: Int = 0
) : Command {

    override fun execute(context: Context, args: List<String>) {
        context.printStudentDataBase.print(context.dataBase)
    }

}