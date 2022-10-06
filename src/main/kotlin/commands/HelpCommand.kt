package commands

import enums.CommandNames

class HelpCommand(
    private val commands: List<Command> = emptyList(),
    override val name: String = CommandNames.HELP,
    override val description: String = "Команда помощи",
    override val example: String = CommandNames.HELP,
    override val neededNumberArgs: Int = 0
) : Command {

    override fun execute(args: List<String>) {
        commands.forEach { println(it.getInfo()) }
    }

}