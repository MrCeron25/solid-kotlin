package commands

import context.Context
import enums.CommandNames

class HelpCommand(
    private val commands: List<Command> = emptyList(),
    override val name: String = CommandNames.HELP,
    override val description: String = "Команда помощи",
    override val example: String = CommandNames.HELP,
    override val neededNumberArgs: Int = 0
) : Command {

    override fun execute(context: Context, args: List<String>) {
        commands.forEach { println(it.getInfo()) }
    }

}