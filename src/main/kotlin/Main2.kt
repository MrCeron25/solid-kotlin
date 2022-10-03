import command.*
import dataBase.DataBaseImpl
import dataBase.PrintDataBase
import factory.SimpleStudentFactory
import parser.ArgParserImpl
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
    var addCommand = AddCommand(studentFactory = studentFactory, repository = repository)

    val commander = CommanderImpl().apply {
        add(exitCommand)
        add(printCommand)
        add(addCommand)
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
            inputResult.startsWith("/add") -> {
//                addCommand = AddCommand(arguments, studentFactory, repository)
                addCommand.run()
            }

            inputResult.startsWith("/replace") -> {
                if (arguments.size == 7) {
                    val index = arguments[1].toIntOrNull()
                    if (index != null) {
                        val res = repository.replace(index, studentFactory.create {
                            surname = arguments[2]
                            name = arguments[3]
                            patronymic = arguments[4]
                            age = arguments[5].toIntOrNull()
                            sex = Sex.parseSex(arguments[6])
                        })
                        if (res) {
                            println("Студент №$index изменён : ${repository.data[index - 1]}")
                        } else {
                            println("Ошибка изменения.")
                        }
                    } else {
                        println("Ошибка ввода номера записи.")
                    }
                } else {
                    println(
                        "Ошибка изменения. Пример : \"/replace replaceIndex surname" + " name patronymic age(Int) sex(M/W)\""
                    )
                }
            }

            inputResult.startsWith("/del") -> {
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