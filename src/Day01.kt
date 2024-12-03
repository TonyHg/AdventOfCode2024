import kotlin.math.abs

fun main() {
    fun sumOfSmallestDistance(list1: MutableList<Int>, list2: MutableList<Int>): Int {
        list1.sort()
        list2.sort()
        return list1.zip(list2).fold(0) { acc, (n1, n2) -> acc + abs(n1 - n2) }
    }

    val input = readInput("Day01")
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()

    input.forEach { line ->
        val (n1, n2) = line.split("   ").map { it.toInt() }
        list1.add(n1)
        list2.add(n2)
    }

    sumOfSmallestDistance(list1, list2).println()
}
