package command

class PrintCommander {
    fun print(commander: Commander<Command>) {
        commander.commands.forEach {
            println("/${it.name} - ${it.description}.")
        }
    }
}