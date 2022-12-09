package parsers

interface Parser<T, R> {
    fun parse(data: T): R
}