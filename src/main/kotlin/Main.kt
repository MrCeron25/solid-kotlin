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
    val repository = DataBaseImpl<StudentImpl>().apply {
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

//    val context = ContextImpl(repository, simpleStudentFactory,printDataBase)

    //region Commands
    val exitCommand = ExitCommand()
    val deleteCommand = DeleteCommand(repository)
    val addCommand = AddCommand(repository, studentFactory)
    val changeCommand = ChangeCommand(repository, studentFactory)
    val printCommand = PrintCommand(repository, printStudentDataBase)
    val sortCommand = SortCommand(repository, printStudentDataBase)
    val searchCommand = SearchCommand(repository, printStudentDataBase)
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

    val commandProcessing = CommandProcessingImpl(commandManager)
    commandProcessing.processing()
}