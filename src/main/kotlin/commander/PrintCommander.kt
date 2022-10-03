package commander

import command.Command

class PrintCommander {
    fun print(commander: Commander<Command>) {
        commander.commands.forEach {
            println(it.getInfo())
//            println("/${it.name} - ${it.description}.")
        }
    }
}