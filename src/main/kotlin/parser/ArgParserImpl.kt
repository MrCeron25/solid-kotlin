package parser

import java.util.*

class ArgParserImpl : ArgParser {
    override fun parse(string: String): Map<String, Any> {
        //        println(string)
//        val regex = Regex(string)
//        val matches = regex.find("""\w+=\w+""")
//        println(matches?.value)w
//        // /search qwe =   45   f    =  eer
////        /search qwe   =   45   skl    =    798    wrvb        =         48974
//        println(args
//            .map { it.split("=") }
//            .map { it.reduce() { a, b -> a + b } }
//        )
//        //.associate { it.first() to it.last() }
////        args.forEach { println("${it.key}=${it.value}") }
        return string
            .trim()
            .lowercase(Locale.getDefault())
            .split(" ")
            .filter { it.isNotEmpty() }
            .map { it.split("=") }
            .associate { it.first() to it.last() }
    }
}