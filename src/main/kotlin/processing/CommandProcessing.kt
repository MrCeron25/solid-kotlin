package processing

import commandManager.CommandManager
import commands.Command

interface CommandProcessing {
    val commandManager: CommandManager<Command>
    fun processing()
}