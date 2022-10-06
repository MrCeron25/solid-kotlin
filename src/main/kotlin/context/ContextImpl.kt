package context

import dataBase.DataBaseImpl
import dataBase.PrintStudentDataBase
import factory.SimpleStudentFactory
import student.StudentImpl

class ContextImpl(
    override val repository: DataBaseImpl<StudentImpl>,
    override val simpleStudentFactory: SimpleStudentFactory,
    override val printStudentDataBase: PrintStudentDataBase<StudentImpl>
) : Context