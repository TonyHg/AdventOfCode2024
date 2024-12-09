fun main() {

    fun checkSum(input: List<Int>): Long {
        return input.foldIndexed(0L) { index, acc, i -> if (i == -1) acc else acc + (i * index) }
    }

    fun parseInput(input: List<String>): List<Int> {
        val line = mutableListOf<Int>()
        var i = 0
        var gap = false
        input[0].toCharArray().map(Char::toString).map(String::toInt).forEach {
            for (j in 0 until it) {
                line.add(if (gap) -1 else i)
            }
            gap = !gap
            if (gap) i++
        }
        return line
    }

    fun diskMap(input: List<String>): Long {
        val line = parseInput(input).toMutableList()
        val size = line.filter { it != -1 }.size

        var end = line.size - 1
        for (begin in 0 until size) {
            if (line[begin] != -1) continue
            while (line[end] == -1) {
                end--
            }
            line[begin] = line[end]
            line[end] = -1
        }

        return checkSum(line)
    }

    fun getPairs(input: String): List<Pair<Int, Int>> {
        var i = 0
        var gap = false
        return input.toCharArray().map(Char::toString).map(String::toInt).map {
            val pair = Pair(if (gap) -1 else i, it)
            gap = !gap
            if (gap) i++
            pair
        }
    }

    fun pairsToDiskMap(input: List<Pair<Int, Int>>): List<Int> {
        val line = mutableListOf<Int>()
        input.forEach {
            for (j in 0 until it.second) {
                line.add(it.first)
            }
        }
        return line
    }

    fun fasterDiskMap(input: List<String>): Long {
        val files = getPairs(input[0]).toMutableList()
        val line = files.toMutableList()

        while (files.isNotEmpty()) {
            val (n, size) = files.removeLast()
            for (i in 0 until line.size) {
                val (currN, currSize) = line[i]
                if (currN == n) break
                if (currN != -1) continue
                if (currSize >= size) {
                    line.forEachIndexed { index, pair ->
                        if (pair.first == n) {
                            line[index] = Pair(-1, pair.second)
                        }
                    }
                    line.removeAll { it.second == 0 }

                    line[i] = Pair(n, size)
                    if (currSize > size) line.add(i + 1, Pair(-1, currSize - size))
                    break
                }
            }
        }

        return checkSum(pairsToDiskMap(line))
    }

    val input = readInput("Day09")
    println("diskMap ${diskMap(input)}")
    println("fasterDiskMap ${fasterDiskMap(input)}")
}
