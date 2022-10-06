package commands

import enums.CommandNames
import dataBase.DataBaseImpl
import student.StudentImpl

class DeleteCommand(
    private val repository: DataBaseImpl<StudentImpl>,
    override val name: String = CommandNames.DELETE,
    override val description: String = "Команда удаления",
    override val example: String = "${CommandNames.DELETE} deleteIndex",
    override val neededNumberArgs: Int = 1
) : Command {
    // /del 1
    override fun execute(args: List<String>) {
        if (args.size == neededNumberArgs) {
            val (_index) = args
            val index = _index.toIntOrNull()
            if (index != null) {
                if (repository.delete(index)) {
                    println("Студент №$index удалён.")
                } else {
                    println("Ошибка удаления.")
                }
            } else {
                println("Ошибка ввода номера записи.")
            }
        } else {
            println("Ошибка параметров. Пример $example.")
        }
    }
}