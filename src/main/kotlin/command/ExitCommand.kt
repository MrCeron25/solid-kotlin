package command

import kotlin.system.exitProcess

class ExitCommand(
    override val name: String = "exit",
    override val description: String = "Команда выхода"
) : Command {
    override fun run() {
        exitProcess(0)
    }
}