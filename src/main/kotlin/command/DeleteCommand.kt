package command

import dataBase.DataBaseImpl
import enums.CommandName
import student.StudentImpl

class DeleteCommand(
    private val repository: DataBaseImpl<StudentImpl>,
    override val name: String = CommandName.DELETE.stringValue,
    override val description: String = "Команда удаления"
) : Command {
    // /del 1
    override fun execute(args: List<String>) {
        if (args.size == 2) {
            val index = args[1].toIntOrNull()
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
            println("Ошибка удаления. Пример : \"/del deleteIndex\"")
        }
    }
}