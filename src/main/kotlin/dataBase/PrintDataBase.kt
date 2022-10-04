package dataBase

class PrintDataBase<T> {
    fun print(dataBase: DataBase<T>) {
        if (dataBase.data.isEmpty()) {
            println("База данных пуста.")
        } else {
            println("№ | surname name patronymic age sex")
            println("===================================")
            dataBase.data.forEachIndexed { index, obj ->
                println("${index + 1} | $obj")
            }
            println("===================================")
        }
    }
}