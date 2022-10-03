package command

interface Commander<T : Command> {
    fun add(command: T): Boolean

    val commands: MutableList<T>
}