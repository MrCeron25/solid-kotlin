package processing

import command.Command
import commander.CommanderImpl

interface CommandProcessing {
    val commander: CommanderImpl<Command>
    fun processing()
}