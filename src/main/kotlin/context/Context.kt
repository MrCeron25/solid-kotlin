package context

import dataBase.DataBaseImpl
import dataBase.PrintDataBase
import factory.SimpleStudentFactory

class Context<T : Any> {
    val repository = DataBaseImpl<T>()
    val studentFactory = SimpleStudentFactory()
    val printDataBase = PrintDataBase<T>()
}