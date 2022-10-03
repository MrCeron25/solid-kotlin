package dataBase

import kotlin.reflect.KCallable

class DataBaseImpl<T : Any> : DataBase<T> {
    private val _data = mutableListOf<T>()

    override val data: List<T>
        get() = _data.toList()

    override fun add(item: T): Boolean = _data.add(item)

    fun getAttrs(): List<KCallable<*>> {
        return this::class.members.toList()
    }

    override fun change(index: Int, item: T): Boolean {
        return if ((_data.size > 0) and (index - 1 in 0 until _data.size)) {
            _data.removeAt(index - 1)
            _data.add(index - 1, item)
            true
        } else {
            false
        }
    }

    override fun delete(index: Int): Boolean {
        return if ((_data.size > 0) and (index - 1 in 0 until _data.size)) {
            _data.removeAt(index - 1) // (_data[index - 1] == _data.removeAt(index - 1))
            true
        } else {
            false
        }
    }

    //    override fun search(predicate: (T) -> Boolean): List<T> = _data.filter(predicate)
    override fun search(vararg predicates: (T) -> Boolean, all: Boolean) {
//        val buf = _data
//        predicates.map { p -> p(_data.filter { p }) }
////        _data.filter {
////        }
////        predicates.forEach { buf.filter(it) }
//        buf.forEach { println(it) }
    }

    override fun sortWith(comparator: Comparator<in T>) = _data.sortWith(comparator)
}