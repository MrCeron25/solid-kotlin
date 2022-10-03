package command

interface Command {
    fun execute() {}
    val name: String
    val description: String
}