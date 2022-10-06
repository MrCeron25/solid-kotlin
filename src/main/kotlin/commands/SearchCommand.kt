package commands

import agrParser.ArgParserImpl
import context.Context
import enums.CommandNames
import enums.Sex
import student.StudentImpl
import kotlin.reflect.full.memberProperties

class SearchCommand(
    override val name: String = CommandNames.SEARCH,
    override val description: String = "Команда поиска",
    override val example: String = "${CommandNames.SEARCH} fieldName=fieldValue",
    override val neededNumberArgs: Int = 0
) : Command {
    // search age=18
    // [name=<>] [surname=<>] [age=<int>] [sex=<M/W>]
    override fun execute(context: Context, args: List<String>) {
        // search age=18
//        println(argParser.parse("name=Art age=45"))
        if (args.isNotEmpty()) {
//            println("args=${args}")
            val studentProps = StudentImpl::class.memberProperties.map { it.name }
//            println("studentProps=${studentProps}")
            val mapArgs = ArgParserImpl().parse(args).filter { it.key in studentProps }
            if (mapArgs.isNotEmpty()) {
//                println("mapArgs=${mapArgs}")
                // booleanVariable = if (booleanMethod()) exp else true;
                // booleanVariable = !booleanMethod() || exp;
                val result = context.dataBase.search(
                    { mapArgs["surname"] == null || it.surname == mapArgs["surname"] },
                    { mapArgs["name"] == null || it.name == mapArgs["name"] },
                    { mapArgs["patronymic"] == null || it.patronymic == mapArgs["patronymic"] },
                    { mapArgs["age"] == null || it.age == mapArgs["age"].toString().toIntOrNull() },
                    {
                        mapArgs["sex"] == null || it.sex ==
                                Sex.parseSex(mapArgs["sex"].toString())
                    }
                )
                if (result.isNotEmpty()) {
                    result.forEach { println(it) }
                } else {
                    println("Записи не найдены.")
                }
            } else {
                println("Параметры не найдены.")
            }
        } else {
            println("Вы ввели команду без параметров.")
        }
    }
}