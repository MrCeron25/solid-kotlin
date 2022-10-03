import command.*
import dataBase.DataBaseImpl
import dataBase.PrintDataBase
import factory.SimpleStudentFactory
import parser.ArgParserImpl
import student.StudentImpl
import java.util.*

fun main() {
    val repository = DataBaseImpl<StudentImpl>()
    val printDataBase = PrintDataBase<StudentImpl>()
    val studentFactory = SimpleStudentFactory()
    val argParser = ArgParserImpl()
    val printCommander = PrintCommander()

    val exitCommand = ExitCommand()
    val printCommand = PrintCommand(repository, printDataBase)
    val addCommand = AddCommand(repository, studentFactory)
    val changeCommand = ChangeCommand(repository, studentFactory)
    val deleteCommand = DeleteCommand(repository)
    val sortCommand = SortCommand(repository, printDataBase)
    val searchCommand = SearchCommand(argParser, repository, printDataBase)

    val commander = CommanderImpl().apply {
        add(exitCommand)
        add(printCommand)
        add(addCommand)
        add(changeCommand)
        add(deleteCommand)
        add(sortCommand)
        add(searchCommand)
    }
// executer
// CommandDispatcher

    repository.add(studentFactory.create {
        surname = "Алексеев"
        name = "Артём"
        patronymic = "Алексеев"
        age = 18
        sex = Sex.WOMAN
    })
    repository.add(studentFactory.create {
        surname = "Сергей"
        name = "Самохин"
        patronymic = "Витальевич"
        age = 91
        sex = Sex.WOMAN
    })
    repository.add(studentFactory.create {
        surname = "Иван"
        name = "Курилин"
        patronymic = "Павлович"
        age = 48
        sex = Sex.MAN
    })

    printDataBase.print(repository)

    var inputResult = readln().trim().lowercase(Locale.getDefault())
    while (true) {
        val arguments = inputResult.split(' ').filter { it.isNotEmpty() }
        when {
            inputResult.startsWith("/add") -> addCommand.execute(arguments)

            inputResult.startsWith("/change") -> changeCommand.execute(arguments)

            inputResult.startsWith("/del") -> deleteCommand.execute(arguments)

            inputResult.startsWith("/sort") -> sortCommand.execute(arguments)

            inputResult.startsWith("/search") -> searchCommand.execute(arguments)

            inputResult == "/print" -> printCommand.execute()

            inputResult == "/help" -> printCommander.print(commander)

            inputResult == "/exit" -> exitCommand.execute()

            else -> println("Команда не найдена. Введите /help для справки.")
        }
        inputResult = readln().trim().lowercase(Locale.getDefault())
    }
}