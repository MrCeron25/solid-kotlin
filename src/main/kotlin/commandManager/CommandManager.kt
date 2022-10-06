package commandManager

import commands.Command
import context.Context

interface CommandManager<T : Command> {
    fun addCommand(command: T)

    fun tryExecute(context: Context, args: List<String>)

    val commands: MutableMap<String, T>
}