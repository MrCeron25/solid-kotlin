package commands

interface Command {
    val name: String
    val description: String
    val example: String
    val neededNumberArgs: Int

    fun getInfo(): String = "$name - $description. Пример : $example."

    fun execute(args: List<String> = emptyList())
}