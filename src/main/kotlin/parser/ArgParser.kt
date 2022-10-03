package parser

interface ArgParser {
    fun parse(string: String): Map<String, Any>
}