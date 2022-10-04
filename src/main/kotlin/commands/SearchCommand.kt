package commands

import dataBase.DataBaseImpl
import dataBase.PrintDataBase
import enums.CommandName
import agrParser.ArgParser
import agrParser.ArgParserImpl
import enums.Sex
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
    // search age=18
    // [name=<>] [surname=<>] [age=<int>] [sex=<M/W>]
    override fun execute(args: List<String>) {
        // search age=18
//        println(argParser.parse("name=Art age=45"))
        if (args.isNotEmpty()) {
//            println("args=${args}")
            val studentProps = StudentImpl::class.memberProperties.map { it.name }
//            println("studentProps=${studentProps}")
            val mapArgs = argParser.parse(args).filter { it.key in studentProps }
            if (mapArgs.isNotEmpty()) {
                println("mapArgs=${mapArgs}")
                val result = repository.search(
                    { it.surname == mapArgs["surname"] },
                    { it.name == mapArgs["name"] },
                    { it.patronymic == mapArgs["patronymic"] },
                    { it.age == mapArgs["age"].toString().toIntOrNull() },
                    { it.sex == Sex.parseSex(mapArgs["sex"].toString()) }
                )
                result.forEach { println(it) }
            } else {
                println("Параметры не найдены.")
            }
        } else {
            println("Вы ввели команду без параметров.")
        }
    }
}