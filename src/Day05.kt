typealias Rules = Map<Int, List<Int>>
typealias Update = List<Int>
typealias Updates = List<Update>

fun main() {
    fun isUpdateCorrect(update: Update, rules: Rules): Boolean {
        val forbidden = mutableSetOf<Int>()
        for (value in update.reversed()) {
            rules[value]?.let { forbidden.addAll(it) }
            if (value in forbidden) return false
        }
        return true
    }

    fun sumOfMiddlePage(updates: Updates, rules: Rules): Int {
        return updates.sumOf { if (isUpdateCorrect(it, rules)) it[it.size / 2] else 0 }
    }

    fun parseInput(input: List<String>): Pair<Rules, Updates> {
        val splitIndex = input.indexOf("")
        val rules = input.subList(0, splitIndex).map {
            it.split("|").map(String::toInt)
        }.groupBy({ it[0] }, { it[1] })
        val updates = input.subList(splitIndex + 1, input.size).map { it.split(",").map(String::toInt) }
        return Pair(rules, updates)
    }

    val input = readInput("Day05")
    val (rules, updates) = parseInput(input)
    println("sumOfMiddlePage ${sumOfMiddlePage(updates, rules)}")
}
