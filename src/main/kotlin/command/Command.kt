package command

interface Command {
    fun execute(args: List<String> = emptyList())
    val name: String
    val description: String
}