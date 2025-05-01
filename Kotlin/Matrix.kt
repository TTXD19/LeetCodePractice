package Kotlin


fun main() {
    val matrix = Matrix()

    val grid = listOf(
        listOf(0, 0, 0, 0),
        listOf(1, 1, 0, 0),
        listOf(0, 0, 0, 1),
        listOf(0, 1, 0, 0)
    )

    val path = matrix.dfs(grid, 0, 0, mutableListOf())
    println(path)
}

class Matrix {

    fun dfs(grid: List<List<Int>>, r: Int, c: Int, visit: MutableList<Pair<Int, Int>>): Int {
        if (
            r < 0 || c < 0 || r >= grid.size || c >= grid[0].size
            || grid[r][c] == 1
            || visit.contains(Pair(r, c))
        ) {
            return 0
        }
        if (r == grid.size - 1 && c == grid[0].size - 1) {
            return 1
        }

        visit.add(Pair(r, c))

        var count = 0
        count += dfs(grid, r + 1, c, visit)
        count += dfs(grid, r - 1, c, visit)
        count += dfs(grid, r, c + 1, visit)
        count += dfs(grid, r, c - 1, visit)

        visit.remove(Pair(r, c))

        return count
    }

}