package processing

import command.Command
import commander.CommanderImpl
import commander.PrintCommander
import enums.CommandName
import java.util.*


class CommandProcessingImpl(
    override val commander: CommanderImpl<Command> = CommanderImpl(),
    private val printCommander: PrintCommander = PrintCommander()
) : CommandProcessing {

    override fun processing() {
        execute(CommandName.PRINT)
        var inputResult = readln().trim().lowercase(Locale.getDefault())
        while (true) {
            val args = inputResult.split(' ').filter { it.isNotEmpty() }
            when {
                inputResult.startsWith("/add") -> execute(CommandName.ADD, args)

                inputResult.startsWith("/change") -> execute(CommandName.CHANGE, args)

                inputResult.startsWith("/del") -> execute(CommandName.DELETE, args)

                inputResult.startsWith("/sort") -> execute(CommandName.SORT, args)

                inputResult.startsWith("/search") -> execute(CommandName.SEARCH, args)

                inputResult == "/print" -> execute(CommandName.PRINT)

                inputResult == "/help" -> printCommander.print(commander)

                inputResult == "/exit" -> execute(CommandName.EXIT)

                else -> println("Команда не найдена. Введите /help для справки.")
            }
            inputResult = readln().trim().lowercase(Locale.getDefault())
        }
    }

    private fun execute(commandName: CommandName, args: List<String> = emptyList()) {
        commander.getCommandByCommandName(commandName).execute(args)
    }
}