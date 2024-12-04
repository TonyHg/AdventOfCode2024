fun main() {
    fun checkTopLeft(matrix: List<List<Char>>, i: Int, j: Int): Int {
        return if (i - 3 >= 0 && j - 3 >= 0 && matrix[i][j] == 'X' && matrix[i - 1][j - 1] == 'M'
            && matrix[i - 2][j - 2] == 'A' && matrix[i - 3][j - 3] == 'S'
        ) 1 else 0
    }

    fun checkTop(matrix: List<List<Char>>, i: Int, j: Int): Int {
        return if (j - 3 >= 0 && matrix[i][j] == 'X' && matrix[i][j - 1] == 'M'
            && matrix[i][j - 2] == 'A' && matrix[i][j - 3] == 'S'
        ) 1 else 0
    }

    fun checkTopRight(matrix: List<List<Char>>, i: Int, j: Int): Int {
        return if (i + 3 < matrix.size && j - 3 >= 0 && matrix[i][j] == 'X' && matrix[i + 1][j - 1] == 'M'
            && matrix[i + 2][j - 2] == 'A' && matrix[i + 3][j - 3] == 'S'
        ) 1 else 0
    }

    fun checkLeft(matrix: List<List<Char>>, i: Int, j: Int): Int {
        return if (i - 3 >= 0 && matrix[i][j] == 'X' && matrix[i - 1][j] == 'M'
            && matrix[i - 2][j] == 'A' && matrix[i - 3][j] == 'S'
        ) 1 else 0
    }

    fun checkRight(matrix: List<List<Char>>, i: Int, j: Int): Int {
        return if (i + 3 < matrix.size && matrix[i][j] == 'X' && matrix[i + 1][j] == 'M'
            && matrix[i + 2][j] == 'A' && matrix[i + 3][j] == 'S'
        ) 1 else 0
    }

    fun checkBottomLeft(matrix: List<List<Char>>, i: Int, j: Int): Int {
        return if (i - 3 >= 0 && j + 3 < matrix[0].size && matrix[i][j] == 'X' && matrix[i - 1][j + 1] == 'M'
            && matrix[i - 2][j + 2] == 'A' && matrix[i - 3][j + 3] == 'S'
        ) 1 else 0
    }

    fun checkBottom(matrix: List<List<Char>>, i: Int, j: Int): Int {
        return if (j + 3 < matrix[0].size && matrix[i][j] == 'X' && matrix[i][j + 1] == 'M'
            && matrix[i][j + 2] == 'A' && matrix[i][j + 3] == 'S'
        ) 1 else 0
    }

    fun checkBottomRight(matrix: List<List<Char>>, i: Int, j: Int): Int {
        return if (i + 3 < matrix.size && j + 3 < matrix[0].size && matrix[i][j] == 'X' && matrix[i + 1][j + 1] == 'M'
            && matrix[i + 2][j + 2] == 'A' && matrix[i + 3][j + 3] == 'S'
        ) 1 else 0
    }

    fun countXMAS(matrix: List<List<Char>>): Int {
        var count = 0

        for (i in matrix.indices) {
            for (j in matrix[i].indices) {
                if (matrix[i][j] == 'X')
                    count += checkTopLeft(matrix, i, j) + checkTop(matrix, i, j) + checkTopRight(matrix, i, j) +
                            checkLeft(matrix, i, j) + checkRight(matrix, i, j) +
                            checkBottomLeft(matrix, i, j) + checkBottom(matrix, i, j) + checkBottomRight(matrix, i, j)
            }
        }
        return count
    }

    fun checkCross(matrix: List<List<Char>>, i: Int, j: Int): Boolean {
        return ((matrix[i - 1][j - 1] == 'M' && matrix[i + 1][j + 1] == 'S') || (matrix[i - 1][j - 1] == 'S' && matrix[i + 1][j + 1] == 'M')) &&
                ((matrix[i - 1][j + 1] == 'M' && matrix[i + 1][j - 1] == 'S') || (matrix[i - 1][j + 1] == 'S' && matrix[i + 1][j - 1] == 'M'))
    }

    fun countXMAS2(matrix: List<List<Char>>): Int {
        var count = 0

        for (i in 1..matrix.size - 2) {
            for (j in 1..matrix[i].size - 2) {
                if (matrix[i][j] == 'A' && checkCross(matrix, i, j))
                    count++
            }
        }

        return count
    }

    val input = readInput("Day04")
    val matrix = input.map { line -> line.toList() }
    println("countXMAS ${countXMAS(matrix)}")
    println("countXMAS2 ${countXMAS2(matrix)}")
}
