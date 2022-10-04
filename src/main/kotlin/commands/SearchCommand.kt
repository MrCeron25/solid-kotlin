package commands

import dataBase.DataBaseImpl
import dataBase.PrintDataBase
import enums.CommandName
import parser.ArgParser
import parser.ArgParserImpl
import student.StudentImpl
import kotlin.reflect.full.memberProperties

class SearchCommand(
    private val repository: DataBaseImpl<StudentImpl>,
    private val printDataBase: PrintDataBase<StudentImpl>,
    private val argParser: ArgParser = ArgParserImpl(),
    override val name: String = CommandName.SEARCH.stringValue,
    override val description: String = "Команда поиска",
    override val example: String = "${CommandName.SEARCH.stringValue} age=18",
    override val neededNumberArgs: Int = 0
) : Command {
    // /search name=18
    // [name=<>] [surname=<>] [age=<int>] [sex=<M/W>]
    override fun execute(args: List<String>) {
//        println(argParser.parse("name=Art age=45"))
        if (args.isNotEmpty()) {
            println("args=${args}")
            val studentProps = StudentImpl::class.memberProperties.map { it.name }
            println("studentProps=${studentProps}")
            val mapArgs = argParser.parse(args).filter { it.key in studentProps }
            println("mapArgs=${mapArgs}")

        } else {
            println("Вы ввели команду без параметров.")
        }
//        //  /search name=Иван age
//        val mapArgs = argParser.parse(inputResult.replace(CommandName.SEARCH, "")).filter { it.key in studentProps }
//        //  search age=18
//        println(mapArgs)
//        if (mapArgs.isEmpty()) {
//            println("Неверные параметры.")
//        } else {
////                    println(mapArgs.keys)
////                    println(mapArgs.containsKey("age"))
////                    val ageVal = mapArgs.getValue("age").toString().toIntOrNull()
//            repository.search(
//                // if (booleanMethod()) exp else true;
//                // !booleanMethod() || exp;
//                { !mapArgs.containsKey("surname") || it.surname == mapArgs.getValue("surname") },
////                        { if (mapArgs.containsKey("surname")) it.surname == mapArgs.getValue("surname") else true }
////                        { !mapArgs.containsKey("name") || it.name == mapArgs.getValue("name") },
////                        { !mapArgs.containsKey("patronymic") || it.patronymic == mapArgs.getValue("patronymic") },
////                        { ageVal == null || it.age == ageVal },
////                        { !mapArgs.containsKey("sex") || it.sex == enums.Sex.parseSex(mapArgs.getValue("sex").toString()) },
//                all = false
//            )
//        }
    }
}