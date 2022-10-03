package command

interface Commander<T : Command> {
    fun add(command: T)

    val commands: MutableList<T>
}