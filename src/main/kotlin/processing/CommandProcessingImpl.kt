package processing

import commands.Command
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
        var inputResult = readln().trim()
        while (true) {
            var args = inputResult.split(' ').filter { it.isNotEmpty() }
            args = args.slice(1 until args.size)

//            val command = commander.commands.find { it.nameWithSlash.startsWith(inputResult) }
//            if (command != null) {
//                execute(command.name, args)
//            } else println("Команда не найдена. Введите /help для справки.")

//            println("args=${args}")
            when {
                inputResult.startsWith(CommandName.ADD.stringValue, true) ->
                    execute(CommandName.ADD, args)

                inputResult.startsWith(CommandName.CHANGE.stringValue, true) ->
                    execute(CommandName.CHANGE, args)

                inputResult.startsWith(CommandName.DELETE.stringValue, true) ->
                    execute(CommandName.DELETE, args)

                inputResult.startsWith(CommandName.SORT.stringValue, true) ->
                    execute(CommandName.SORT, args)

                inputResult.startsWith(CommandName.SEARCH.stringValue, true) ->
                    execute(CommandName.SEARCH, args)

                inputResult.lowercase(Locale.getDefault()) ==
                        CommandName.PRINT.stringValue -> execute(CommandName.PRINT)

                inputResult.lowercase(Locale.getDefault()) ==
                        CommandName.EXIT.stringValue -> execute(CommandName.EXIT)

                inputResult.lowercase(Locale.getDefault()) ==
                        CommandName.HELP.stringValue -> printCommander.print(commander)

                else -> println("Команда не найдена. Введите ${CommandName.HELP.stringValue} для справки.")
            }
            inputResult = readln().trim()
        }
    }

    private fun execute(commandName: CommandName, args: List<String> = emptyList()) {
        commander.getCommandByCommandName(commandName).execute(args)
    }
}