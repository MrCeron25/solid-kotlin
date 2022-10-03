package command

import dataBase.DataBaseImpl
import dataBase.PrintDataBase
import student.StudentImpl

class SortCommand(
    private val repository: DataBaseImpl<StudentImpl>,
    private val printDataBase: PrintDataBase<StudentImpl>,
    override val name: String = "sort",
    override val description: String = "Команда сортировки"
) : Command {
    // /change 1 qwe asd grte 456 m
    override fun execute(args: List<String>) {
        if (args.size == 2) {
            val index = args[1].toIntOrNull()
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
            println("Ошибка сортировки. Пример : \"/sort sortIndex\"")
        }
    }
}