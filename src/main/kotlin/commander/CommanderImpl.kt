package commander

import command.Command
import enums.CommandName

class CommanderImpl<T : Command>(
    override val commands: MutableList<T> = mutableListOf()
) : Commander<T> {

    override fun addCommand(command: T): Boolean {
        return if (commands.find { it.name == command.name } == null) {
            commands.add(command)
        } else {
            throw Error("Команда с таким именем уже есть.")
        }
    }

    override fun getCommandByCommandName(commandName: CommandName): T =
        commands.find { it.name == commandName.stringValue } ?: throw Error("Команда не найдена.")
}