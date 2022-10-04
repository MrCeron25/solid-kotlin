package processing

import commands.Command
import commander.CommanderImpl

interface CommandProcessing {
    val commander: CommanderImpl<Command>
    fun processing()
}