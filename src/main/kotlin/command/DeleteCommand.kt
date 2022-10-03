package command

import dataBase.DataBaseImpl
import student.StudentImpl

class DeleteCommand(
    private val repository: DataBaseImpl<StudentImpl>,
    override val name: String = "delete",
    override val description: String = "Команда удаления"
) : Command {
    // /del 1
    fun execute(arguments: List<String>) {
        if (arguments.size == 2) {
            val index = arguments[1].toIntOrNull()
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