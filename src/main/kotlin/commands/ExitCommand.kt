package commands

import context.Context
import enums.CommandNames
import kotlin.system.exitProcess

class ExitCommand(
    override val name: String = CommandNames.EXIT,
    override val description: String = "Команда выхода",
    override val example: String = CommandNames.EXIT,
    override val neededNumberArgs: Int = 0
) : Command {
    override fun execute(context: Context, args: List<String>) {
        exitProcess(0)
    }
}