package command

interface Command {
    val name: String
    val description: String
    val nameWithSlash: String
        get() = "/$name"

    //    val info: String
//         get() = "/$name - $description."
//    или
    fun getInfo(): String = "/$name - $description."

    fun execute(args: List<String> = emptyList())
}