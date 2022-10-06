package processing

import commandManager.CommandManagerImpl
import commands.Command
import enums.CommandNames
import exception.CommandExecutionException

class CommandProcessingImpl(
    override val commandManager: CommandManagerImpl<Command>
) : CommandProcessing {

    override fun processing() {
        commandManager.tryExecute(CommandNames.PRINT.split(" ").filter { it.isNotEmpty() })
        commandManager.tryExecute(CommandNames.HELP.split(" ").filter { it.isNotEmpty() })
        while (true) {
            val inputResult = readln().trim()
            try {
                commandManager.tryExecute(inputResult.split(" ").filter { it.isNotEmpty() })
            } catch (e: CommandExecutionException) {
                if (e.message == null) continue
                println(e.message)
            }
        }
    }
}

