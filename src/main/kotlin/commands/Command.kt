package commands

import context.Context

interface Command {
    val name: String
    val description: String
    val example: String
    val neededNumberArgs: Int

    fun getInfo(): String = "$name - $description. Пример: \'$example\'."

    fun execute(context: Context, args: List<String> = emptyList())
}