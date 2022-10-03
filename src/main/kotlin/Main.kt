import command.*
import dataBase.DataBaseImpl
import dataBase.PrintDataBase
import factory.SimpleStudentFactory
import parser.ArgParserImpl
import student.StudentImpl
import java.util.*
import kotlin.reflect.full.memberProperties

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

    val commander = CommanderImpl().apply {
        add(exitCommand)
        add(printCommand)
        add(addCommand)
        add(changeCommand)
        add(deleteCommand)
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

    val studentProps = StudentImpl::class.memberProperties.map { it.name }
    printDataBase.print(repository)
//    println(argParser.parse("name=Art age=45"))

    var inputResult = readln().trim().lowercase(Locale.getDefault())
    while (true) {
        val arguments = inputResult.split(' ').filter { it.isNotEmpty() }
        when {
            inputResult.startsWith("/add") -> addCommand.run(arguments)

            inputResult.startsWith("/change") -> changeCommand.run(arguments)

            inputResult.startsWith("/del") -> deleteCommand.run(arguments)

            inputResult.startsWith("/sort") -> {
                if (arguments.size == 2) {
                    val index = arguments[1].toIntOrNull()
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

            // [name=<>] [surname=<>] [age=<int>] [sex=<M/W>]
            inputResult.startsWith("/search") -> {
                //  /search name=Иван age
                val mapArgs = argParser.parse(inputResult.replace("/search", "")).filter { it.key in studentProps }
                //  /search age=18
                println(mapArgs)
                if (mapArgs.isEmpty()) {
                    println("Неверные параметры.")
                } else {
//                    println(mapArgs.keys)
//                    println(mapArgs.containsKey("age"))
//                    val ageVal = mapArgs.getValue("age").toString().toIntOrNull()
                    repository.search(
                        // if (booleanMethod()) exp else true;
                        // !booleanMethod() || exp;
                        { !mapArgs.containsKey("surname") || it.surname == mapArgs.getValue("surname") },
//                        { if (mapArgs.containsKey("surname")) it.surname == mapArgs.getValue("surname") else true }
//                        { !mapArgs.containsKey("name") || it.name == mapArgs.getValue("name") },
//                        { !mapArgs.containsKey("patronymic") || it.patronymic == mapArgs.getValue("patronymic") },
//                        { ageVal == null || it.age == ageVal },
//                        { !mapArgs.containsKey("sex") || it.sex == Sex.parseSex(mapArgs.getValue("sex").toString()) },
                        all = false
                    )
                }
            }

            inputResult == "/print" -> printCommand.run()

            inputResult == "/help" -> printCommander.print(commander)

            inputResult == "/exit" -> exitCommand.run()

            else -> println("Команда не найдена. Введите /help для справки.")
        }
        inputResult = readln().trim().lowercase(Locale.getDefault())
    }
}