typealias Nodes = Map<Char, List<Position>>

fun main() {
    fun getAntiNode(node: Position, other: Position): List<Position> {
        return listOf(Pair(2 * node.first - other.first, 2 * node.second - other.second),
            Pair(2 * other.first - node.first, 2 * other.second - node.second))
    }

    fun computeAntiNodes(nodes: Nodes, height: Int, width: Int): Int {
        val antiNodes = mutableSetOf<Position>()
        nodes.forEach { (_, value) ->
            value.forEachIndexed { index, curr ->
                value.drop(index + 1).flatMap { getAntiNode(curr, it) }
                    .filter { it.first in 0..<height && it.second in 0..<width }
                    .forEach { antiNodes.add(it) }
            }
        }
        return antiNodes.size
    }


    fun parseInput(input: List<String>): Nodes {
        val map = mutableMapOf<Char, List<Position>>()
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] != '.') {
                    map[input[i][j]] = map.getOrDefault(input[i][j], emptyList()) + Pair(i, j)
                }
            }
        }
        return map
    }

    val input = readInput("Day08")
    val parsedInput = parseInput(input)
    println("computeAntiNodes ${computeAntiNodes(parsedInput, input.size, input[0].length)}")
}
