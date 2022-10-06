package commands

import enums.CommandNames
import dataBase.DataBase
import dataBase.PrintStudentDataBase
import student.StudentImpl

class PrintCommand(
    private val dataBase: DataBase<StudentImpl>,
    private val printDataBase: PrintStudentDataBase<StudentImpl>,
    override val name: String = CommandNames.PRINT,
    override val description: String = "Команда вывода",
    override val example: String = CommandNames.PRINT,
    override val neededNumberArgs: Int = 0
) : Command {

    override fun execute(args: List<String>) {
        printDataBase.print(dataBase)
    }

}