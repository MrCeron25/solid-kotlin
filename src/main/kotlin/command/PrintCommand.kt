package command

import dataBase.DataBase
import dataBase.PrintDataBase

class PrintCommand<T : Any>(
    private val dataBase: DataBase<T>, private val printDataBase: PrintDataBase<T>,
    override val name: String = "print",
    override val description: String = "Команда вывода"
) : Command {

    override fun execute() {
        printDataBase.print(dataBase)
    }

}