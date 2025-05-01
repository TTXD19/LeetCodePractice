package Kotlin

import java.util.HashMap
import java.util.LinkedList


fun main() {
    val matrix = Matrix()

    val grid = listOf(
        listOf(0, 0, 0, 0),
        listOf(1, 1, 0, 0),
        listOf(0, 0, 0, 1),
        listOf(0, 1, 0, 0)
    )

    val grid2 = listOf(
        listOf(0, 1),
        listOf(1, 0)
    )

//    val path = matrix.dfs(grid, 0, 0, mutableListOf())
//    println(path)

//    val shortestPath = matrix.bfs(grid2)
//    println(shortestPath)

    val path = matrix.gridFindUniquePath(3, 2, hashMapOf())
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

    fun bfs(grid: List<List<Int>>): Int {

        val queue = LinkedList<Pair<Int, Int>>()
        val visits = mutableSetOf<Pair<Int, Int>>()

        queue.add(Pair(0, 0))

        if (grid[0][0] == 1) {
            return -1
        }

        var length = 0
        while (queue.isNotEmpty()) {
            val size = queue.size
            for (i in 0 until size) {
                val point = queue.poll()
                val r = point.first
                val c = point.second
                val movements = listOf(
                    Pair(0, 1),
                    Pair(0, -1),
                    Pair(1, 0),
                    Pair(-1, 0),
                    Pair(1, 1),
                    Pair(-1, -1),
                    Pair(1, -1),
                    Pair(-1, 1)
                )
                if (r == grid.size - 1 && c == grid[0].size - 1) {
                    return length + 1
                }
                for (movement in movements) {
                    val newR = r + movement.first
                    val newC = c + movement.second
                    if (
                        minOf(newR, newC) < 0
                        || newR > grid.size - 1 || newC > grid[0].size - 1
                        || Pair(newR, newC) in visits
                        || grid[newR][newC] == 1
                    ) {
                        continue
                    }
                    visits.add(Pair(newR, newC))
                    queue.add(Pair(newR, newC))
                }
            }
            length++
        }

        return -1
    }

    // Brute Force
    fun dfsBruteForce(r: Int, c: Int, rows: Int, columns: Int): Int {
        if (r == rows - 1 && c == columns - 1) {
            return 1
        }
        if (r > rows || c > columns) {
            return 0
        }

        return dfsBruteForce(r + 1, c, rows, columns) + dfsBruteForce(r, c + 1, rows, columns)
    }

    fun gridFindUniquePath(r: Int, c: Int, cache: HashMap<Pair<Int, Int>, Int>): Int {
        if (r == 1 && c == 1){
            return 1
        }
        if (r < 1 || c < 1){
            return 0
        }
        if (Pair(r, c) !in cache){
            cache[Pair(r, c)] = (gridFindUniquePath(r - 1, c, cache) + gridFindUniquePath(r, c - 1, cache))
        }

        return cache[Pair(r, c)]!!
    }

}