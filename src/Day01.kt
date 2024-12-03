import kotlin.math.abs

fun main() {
    fun sumOfSmallestDistance(list1: List<Int>, list2: List<Int>): Int {
        val sortedList1 = list1.sorted()
        val sortedList2 = list2.sorted()
        return sortedList1.zip(sortedList2).fold(0) { acc, (n1, n2) -> acc + abs(n1 - n2) }
    }

    fun similarityScore(list1: List<Int>, list2: List<Int>): Int {
        val histogram = mutableMapOf<Int, Int>()
        list2.forEach { n2 ->
            histogram[n2] = histogram.getOrDefault(n2, 0) + 1
        }

        return list1.sumOf { n1 -> n1 * (histogram[n1] ?: 0) }
    }

    val input = readInput("Day01")
    val list1 = mutableListOf<Int>()
    val list2 = mutableListOf<Int>()

    input.forEach { line ->
        val (n1, n2) = line.split("   ").map { it.toInt() }
        list1.add(n1)
        list2.add(n2)
    }

    println("sumOfSmallestDistance ${sumOfSmallestDistance(list1, list2)}")
    println("similarityScore ${similarityScore(list1, list2)}")
}
