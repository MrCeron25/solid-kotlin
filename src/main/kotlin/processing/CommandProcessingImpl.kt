package processing

import commandManager.CommandManagerImpl
import commands.Command
import context.ContextImpl
import enums.CommandNames
import exception.CommandExecutionException

class CommandProcessingImpl(
    override val context: ContextImpl,
    override val commandManager: CommandManagerImpl<Command>
) : CommandProcessing {

    override fun processing() {
        commandManager.tryExecute(context, CommandNames.PRINT.split(" ").filter { it.isNotEmpty() })
        commandManager.tryExecute(context, CommandNames.HELP.split(" ").filter { it.isNotEmpty() })
        while (true) {
            val inputResult = readln().trim()
            try {
                commandManager.tryExecute(context, inputResult.split(" ").filter { it.isNotEmpty() })
            } catch (e: CommandExecutionException) {
                if (e.message == null) continue
                println(e.message)
            }
        }
    }
}

