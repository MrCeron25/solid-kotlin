package command

import kotlin.system.exitProcess

class ExitCommand(
    override val name: String = "exit",
    override val description: String = "Команда выхода"
) : Command {
    override fun execute() {
        exitProcess(0)
    }
}