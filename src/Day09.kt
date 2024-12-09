fun main() {

    fun checkSum(input: List<Int>): Long {
        return input.foldIndexed(0L) { index, acc, i -> acc + (i * index) }
    }

    fun parseInput(input: List<String>): List<Int> {
        val line = mutableListOf<Int>()
        var i = 0
        var gap = false
        input[0].toCharArray().map(Char::toString).map(String::toInt).forEach {
            for (j in 0 until it) {
                line.add(if(gap) -1 else i)
            }
            gap = !gap
            if (gap) i++
        }
        return line
    }

    fun diskMap(input: List<String>): Long {
        val line = parseInput(input).toMutableList()
        val size = line.filter { it != -1}.size

        var end = line.size - 1
        for (begin in 0 until size) {
            if (line[begin] != - 1) continue
            while (line[end] == -1) {
                end--
            }
            line[begin] = line[end]
            line[end] = -1
        }

        return checkSum(line.filter { it != -1 })
    }

    val input = readInput("Day09")
    println("diskMap ${diskMap(input)}")
}
