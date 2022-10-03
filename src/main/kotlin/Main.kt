import command.*
import commander.CommanderImpl
import dataBase.DataBaseImpl
import dataBase.PrintDataBase
import enums.Sex
import factory.SimpleStudentFactory
import parser.ArgParserImpl
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
    val printDataBase = PrintDataBase<StudentImpl>()
    val argParser = ArgParserImpl()

    //region Commands
    val exitCommand = ExitCommand()
    val deleteCommand = DeleteCommand(repository)
    val addCommand = AddCommand(repository, studentFactory)
    val changeCommand = ChangeCommand(repository, studentFactory)
    val printCommand = PrintCommand(repository, printDataBase)
    val sortCommand = SortCommand(repository, printDataBase)
    val searchCommand = SearchCommand(argParser, repository, printDataBase)
    //endregion

    val commander = CommanderImpl<Command>().apply {
        addCommand(exitCommand)
        addCommand(deleteCommand)
        addCommand(addCommand)
        addCommand(changeCommand)
        addCommand(printCommand)
        addCommand(sortCommand)
        addCommand(searchCommand)
    }

    val commandProcessing = CommandProcessingImpl(commander)
    // CommandDispatcher

    commandProcessing.processing()
}