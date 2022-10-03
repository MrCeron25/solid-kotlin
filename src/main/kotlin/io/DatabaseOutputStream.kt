package io

import dataBase.DataBase

interface DatabaseOutputStream {

    fun <T> printDatabase(dataBase: DataBase<T>)

}