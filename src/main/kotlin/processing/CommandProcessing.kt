package processing

import commandManager.CommandManager
import commands.Command
import context.ContextImpl

interface CommandProcessing {
    val context: ContextImpl
    val commandManager: CommandManager<Command>
    fun processing()
}