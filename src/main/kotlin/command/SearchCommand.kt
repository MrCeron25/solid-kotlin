package command

import dataBase.DataBaseImpl
import dataBase.PrintDataBase
import parser.ArgParser
import student.StudentImpl

class SearchCommand(
    private val argParser: ArgParser,
    private val repository: DataBaseImpl<StudentImpl>,
    private val printDataBase: PrintDataBase<StudentImpl>,
    override val name: String = "search",
    override val description: String = "Команда поиска"
) : Command {
    // /search name=18
    // [name=<>] [surname=<>] [age=<int>] [sex=<M/W>]
    override fun execute(arguments: List<String>) {
        //    println(argParser.parse("name=Art age=45"))


        //val studentProps = StudentImpl::class.memberProperties.map { it.name }
//        //  /search name=Иван age
//        val mapArgs = argParser.parse(inputResult.replace("/search", "")).filter { it.key in studentProps }
//        //  /search age=18
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
////                        { !mapArgs.containsKey("sex") || it.sex == Sex.parseSex(mapArgs.getValue("sex").toString()) },
//                all = false
//            )
//        }
    }
}