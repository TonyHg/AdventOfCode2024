import kotlin.math.abs

fun main() {
    fun isLevelSafe(level: List<Int>): Boolean {
        if (level.size < 2) return true
        val increasing = level[0] - level[1] < 0

        return level.zipWithNext().all { (a, b) ->
            val diff = abs(a - b)
            (increasing && a < b || !increasing && a > b) && diff <= 3
        }
    }

    fun nbSafeLevels(levels: List<List<Int>>): Int {
        return levels.count { isLevelSafe(it) }
    }

    val input = readInput("Day02")
    val levels = input.map { line -> line.split(" ").map { it.toInt() } }

    println("nbSafeLevels ${nbSafeLevels(levels)}")
}
