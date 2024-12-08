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

    fun getReversedRules(rules: Rules): Rules {
        val reversedRules = mutableMapOf<Int, List<Int>>()
        rules.forEach { (key, value) ->
            value.forEach {
                reversedRules[it] = reversedRules.getOrDefault(it, mutableListOf()) + key
            }
        }
        return reversedRules
    }

    fun correctUpdate(update: Update, rules: Rules): Update {
        val correctedUpdate = mutableListOf<Int>()
        val reversedRules = getReversedRules(rules)

        val used = mutableSetOf<Int>()
        val currUpdate = update.toMutableList()
        while (currUpdate.isNotEmpty()) {
            val value = currUpdate.removeAt(0)
            if (reversedRules.containsKey(value)) {
                val forbidden = reversedRules[value]!!
                if (forbidden.toSet().intersect(update.toSet()).subtract(correctedUpdate.toSet()).isEmpty()) {
                    correctedUpdate.add(value)
                } else {
                    currUpdate.add(value)
                    continue
                }
            } else {
                correctedUpdate.add(value)
            }
            used.add(value)
        }

        return correctedUpdate
    }

    fun sumOfIncorrectMiddlePage(updates: Updates, rules: Rules): Int {
        return updates.sumOf { if (!isUpdateCorrect(it, rules)) correctUpdate(it, rules)[it.size / 2] else 0 }
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
    println("sumOfIncorrectMiddlePage ${sumOfIncorrectMiddlePage(updates, rules)}")
}
