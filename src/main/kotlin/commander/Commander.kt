package commander

import command.Command
import enums.CommandName

interface Commander<T : Command> {
    fun addCommand(command: T): Boolean

    fun getCommandByCommandName(commandName: CommandName): T?

    val commands: MutableList<T>
}