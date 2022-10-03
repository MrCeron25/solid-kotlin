package command

import enums.CommandName
import kotlin.system.exitProcess

class ExitCommand(
    override val name: String = CommandName.EXIT.stringValue,
    override val description: String = "Команда выхода"
) : Command {
    override fun execute(args: List<String>) {
        exitProcess(0)
    }
}