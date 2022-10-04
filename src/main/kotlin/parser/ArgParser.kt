package parser

interface ArgParser {
    fun parse(args: List<String>): Map<String, Any>
}