fun main() {
    fun canBeSolved(result: Long, numbers: List<Long>, current: Long): Boolean {
        if (result == current) return true
        if (numbers.isEmpty()) return false
        return canBeSolved(result, numbers.drop(1), current + numbers.first()) ||
                canBeSolved(result, numbers.drop(1), current * numbers.first())
    }

    fun retrieveOperators(input: List<Pair<Long, List<Long>>>): Long {
        return input.sumOf { if (canBeSolved(it.first, it.second, 0)) it.first else 0 }
    }

    fun parseInput(input: List<String>): List<Pair<Long, List<Long>>> {
        return input.map {
            val split = it.split(":")
            Pair(split[0].toLong(), split[1].split(" ").drop(1).map(String::toLong))
        }
    }

    val input = readInput("Day07")
    val parsedInput = parseInput(input)
    println("retrieveOperators ${retrieveOperators(parsedInput)}")
}
