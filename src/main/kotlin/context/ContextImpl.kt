package context

import dataBase.DataBaseImpl
import dataBase.PrintStudentDataBase
import factory.SimpleStudentFactory
import student.StudentImpl

/**
* Контекст - это класс который содержит постоянно использующиеся классы
 */
class ContextImpl(
    override val dataBase: DataBaseImpl<StudentImpl>,
    override val studentFactory: SimpleStudentFactory,
    override val printStudentDataBase: PrintStudentDataBase<StudentImpl>
) : Context
