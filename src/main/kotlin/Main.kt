import commandManager.CommandManagerImpl
import commands.*
import context.ContextImpl
import dataBase.DataBaseImpl
import dataBase.PrintStudentDataBase
import enums.Sex
import factory.SimpleStudentFactory
import processing.CommandProcessingImpl
import student.StudentImpl

fun main() {
    val studentFactory = SimpleStudentFactory()
    val dataBase = DataBaseImpl<StudentImpl>().apply {
        add(studentFactory.create {
            surname = "Алексеев"
            name = "Артём"
            patronymic = "Анатольевич"
            age = 18
            sex = Sex.WOMAN
        })
        add(studentFactory.create {
            surname = "Сергей"
            name = "Самохин"
            patronymic = "Витальевич"
            age = 91
            sex = Sex.WOMAN
        })
        add(studentFactory.create {
            surname = "Иван"
            name = "Курилин"
            patronymic = "Павлович"
            age = 48
            sex = Sex.MAN
        })
    }
    val printStudentDataBase = PrintStudentDataBase<StudentImpl>()

    val context = ContextImpl(dataBase, studentFactory, printStudentDataBase)

    //region Commands
    val exitCommand = ExitCommand()
    val deleteCommand = DeleteCommand()
    val addCommand = AddCommand()
    val changeCommand = ChangeCommand()
    val printCommand = PrintCommand()
    val sortCommand = SortCommand()
    val searchCommand = SearchCommand()
    //endregion

    val commandManager = CommandManagerImpl<Command>().apply {
        addCommand(exitCommand)
        addCommand(deleteCommand)
        addCommand(addCommand)
        addCommand(changeCommand)
        addCommand(printCommand)
        addCommand(sortCommand)
        addCommand(searchCommand)
    }
    val helpCommand = HelpCommand(commandManager.commands.values.toList())
    commandManager.addCommand(helpCommand)

    val commandProcessing = CommandProcessingImpl(context, commandManager)
    commandProcessing.processing()
}