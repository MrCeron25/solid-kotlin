package commands

import enums.CommandName
import kotlin.system.exitProcess

class ExitCommand(
    override val name: String = CommandName.EXIT.stringValue,
    override val description: String = "Команда выхода",
    override val example: String = CommandName.EXIT.stringValue,
    override val neededNumberArgs: Int = 0
) : Command {
    override fun execute(args: List<String>) {
        exitProcess(0)
    }
}