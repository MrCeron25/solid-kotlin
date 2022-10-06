package context

import dataBase.DataBaseImpl
import dataBase.PrintStudentDataBase
import factory.SimpleStudentFactory
import student.StudentImpl

interface Context {
    val repository: DataBaseImpl<StudentImpl>
    val simpleStudentFactory: SimpleStudentFactory
    val printStudentDataBase: PrintStudentDataBase<StudentImpl>
}