fun main() {
    fun regexMul(line: String): Int {
        val regex = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")
        return regex.findAll(line).sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
    }

    fun removeBetweenDontAndDo(input: String): String {
        val regex = Regex("don't\\(\\).*?(do\\(\\)|$)")
        return input.replace(regex, "")
    }

    val input = readInput("Day03")
    val result = input.sumOf { regexMul(it) }
    println("regexMul $result")
    val result2 = regexMul(removeBetweenDontAndDo(input.joinToString("")))
    println("removeBetweenDontAndDo $result2")
}
