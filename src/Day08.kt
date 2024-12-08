typealias Nodes = Map<Char, List<Position>>

fun main() {
    fun getAntiNode(node: Position, other: Position): List<Position> {
        return listOf(
            Pair(2 * node.first - other.first, 2 * node.second - other.second),
            Pair(2 * other.first - node.first, 2 * other.second - node.second)
        )
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

    fun getMoreAntiNodes(node: Position, other: Position): List<Position> {
        val distY = node.first - other.first
        val distX = node.second - other.second

        val antiNodes = mutableListOf<Position>()
        // TODO: improve by only adding when the position is in the grid
        for (i in 1..100) {
            antiNodes.add(Pair(node.first + i * distY, node.second + i * distX))
            antiNodes.add(Pair(other.first - i * distY, other.second - i * distX))
        }
        return antiNodes
    }

    fun moreAntiNodes(nodes: Nodes, height: Int, width: Int): Int {
        val antiNodes = mutableSetOf<Position>()

        nodes.forEach { (_, value) ->
            value.forEachIndexed { index, curr ->
                value.drop(index + 1).flatMap { getMoreAntiNodes(curr, it) }
                    .filter { it.first in 0..<height && it.second in 0..<width }
                    .forEach { antiNodes.add(it) }
            }

            if (value.size > 1) {
                value.forEach { antiNodes.add(it) }
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
    println("moreAntiNodes ${moreAntiNodes(parsedInput, input.size, input[0].length)}")
}
