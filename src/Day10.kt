fun main() {
    fun trailToTop(input: MutableList<String>, i: Int, j: Int, curr: Char): Int {
        if (curr == '.') return 0
        if (curr == '9') {
//            input[i] = input[i].replaceRange(j, j + 1, ".") // for part 1
            return 1
        }

        val nextLevel = curr.inc()
        var sum = 0

        if (i - 1 >= 0 && input[i - 1][j] == nextLevel) sum += trailToTop(input, i - 1, j, nextLevel)
        if (j - 1 >= 0 && input[i][j - 1] == nextLevel) sum += trailToTop(input, i, j - 1, nextLevel)
        if (i + 1 < input.size && input[i + 1][j] == nextLevel) sum += trailToTop(input, i + 1, j, nextLevel)
        if (j + 1 < input[i].length && input[i][j + 1] == nextLevel) sum += trailToTop(input, i, j + 1, nextLevel)

        return sum
    }

    fun computeScore(input: List<String>): Int {
        var sum = 0
        for (i in input.indices) {
            for (j in input[i].indices) {
                if (input[i][j] == '0') {
                    sum += trailToTop(input.toMutableList(), i, j, '0')
                }
            }
        }
        return sum
    }

    val input = readInput("Day10")
    println("computeScore ${computeScore(input)}")
}
