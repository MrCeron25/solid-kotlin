package command

class CommanderImpl(
    override val commands: MutableList<Command> = mutableListOf()
) : Commander<Command> {

    override fun add(command: Command) {
        commands.add(command)
    }

}