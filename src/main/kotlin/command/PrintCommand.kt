package command

import dataBase.DataBase
import dataBase.PrintDataBase
import enums.CommandName

class PrintCommand<T : Any>(
    private val dataBase: DataBase<T>,
    private val printDataBase: PrintDataBase<T>,
    override val name: String = CommandName.PRINT.stringValue,
    override val description: String = "Команда вывода"
) : Command {

    override fun execute(args: List<String>) {
        printDataBase.print(dataBase)
    }

}