package commander

import commands.Command

class PrintCommander {
    fun print(commander: Commander<Command>) {
        commander.commands.forEach { println(it.getInfo()) }
    }
}