typealias Field = List<MutableList<Char>>
typealias Position = Pair<Int, Int>

fun main() {
    fun findGuard(map: List<List<Char>>):Position {
        for (i in map.indices) {
            for (j in map[i].indices) {
                if (map[i][j] in arrayOf('^', '>', 'v', '<')) {
                    return Pair(i, j)
                }
            }
        }
        return Pair(-1, -1)
    }

    fun countDistinctPositions(map: Field): Int {
        return map.sumOf { it.count { it == 'X' } }
    }

    fun rotate90(guard: Char): Char {
        return when (guard) {
            '^' -> '>'
            '>' -> 'v'
            'v' -> '<'
            '<' -> '^'
            else -> guard
        }
    }

    fun distinctPositions(map: Field): Int {
        var (i, j) = findGuard(map)

        while (i >= 0 && i < map.size && j >= 0 && j < map[i].size) {
            val guard = map[i][j]

            val (nextI, nextJ) = when (guard) {
                '^' -> Pair(i - 1, j)
                '>' -> Pair(i, j + 1)
                '<' -> Pair(i, j - 1)
                'v' -> Pair(i + 1, j)
                else -> Pair(i, j)
            }
            map[i][j] = 'X'
            if (nextI < 0 || nextI >= map.size || nextJ < 0 || nextJ >= map[nextI].size) {
                break
            }
            if (map[nextI][nextJ] == '#') {
                map[i][j] = rotate90(guard)
                continue
            }
            map[nextI][nextJ] = guard
            i = nextI
            j = nextJ
        }
        return countDistinctPositions(map)
    }

    val input = readInput("Day06")
    val map = input.map { line -> line.toMutableList() }
    println("distinctPositions ${distinctPositions(map)}")
}
