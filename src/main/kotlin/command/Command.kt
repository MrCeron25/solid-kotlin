package command

interface Command {
    fun run() {}
    val name: String
    val description: String
}