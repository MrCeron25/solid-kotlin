package context

import dataBase.DataBaseImpl
import dataBase.PrintStudentDataBase
import factory.SimpleStudentFactory
import student.StudentImpl

interface Context {
    val dataBase: DataBaseImpl<StudentImpl>
    val studentFactory: SimpleStudentFactory
    val printStudentDataBase: PrintStudentDataBase<StudentImpl>
}