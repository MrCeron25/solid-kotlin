package commandManager

import commands.Command

interface CommandManager<T : Command> {
    fun addCommand(command: T)

    fun tryExecute(args: List<String>)

    val commands: MutableMap<String, T>
}