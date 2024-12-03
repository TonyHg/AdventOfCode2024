import kotlin.math.abs

fun main() {
    fun isStepValid(increasing: Boolean, a: Int, b: Int): Boolean {
        return (increasing && a < b || !increasing && a > b) && abs(a - b) <= 3
    }

    fun isLevelSafe(level: List<Int>): Boolean {
        if (level.size < 2) return true
        val increasing = level[0] - level[1] < 0

        return level.zipWithNext().all { (a, b) -> isStepValid(increasing, a, b) }
    }

    fun isLevelSafeWithTolerance(level: List<Int>): Boolean {
        if (isLevelSafe(level)) return true

        for (i in level.indices) {
            val mutableList = level.toMutableList()
            mutableList.removeAt(i)
            if (isLevelSafe(mutableList)) return true
        }

        return false
    }

    fun nbSafeLevels(levels: List<List<Int>>): Int {
        return levels.count { isLevelSafe(it) }
    }

    fun nbSafeTolerantLevels(levels: List<List<Int>>): Int {
        return levels.count { isLevelSafeWithTolerance(it) }
    }

    val input = readInput("Day02")
    val levels = input.map { line -> line.split(" ").map { it.toInt() } }

    println("nbSafeLevels ${nbSafeLevels(levels)}")
    println("nbSafeTolerantLevels ${nbSafeTolerantLevels(levels)}")
}
