package parsers

class IntParserImpl : Parser<String, Int?> {

    override fun parse(data: String): Int? {
        return data.toIntOrNull()
    }

}
