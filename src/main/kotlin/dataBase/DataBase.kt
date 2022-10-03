package dataBase

interface DataBase<T> {
    fun add(item: T): Boolean
    fun delete(index: Int): Boolean
    fun replace(index: Int, item: T): Boolean
    fun search(vararg predicates: (T) -> Boolean, all: Boolean)

    //    fun search(predicate: (T) -> Boolean): List<T>
    fun sortWith(comparator: Comparator<in T>)

    val data: List<T>
}
//ADD, REPLACE, DELETE, SORT, SEARCH, HELP