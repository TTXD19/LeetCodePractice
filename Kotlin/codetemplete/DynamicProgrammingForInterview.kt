package Kotlin.codetemplete

import kotlin.math.min

fun main() {
    val dynamicProgrammingForInterview = DynamicProgrammingForInterview()

    // 🚫 計算 fib(40)，使用純遞迴（無記憶體），效能非常差
    println(dynamicProgrammingForInterview.fibRecursionNoMemo(40))  // 慢

    // ✅ 計算 fib(500)，使用遞迴 + 記憶體化（memoization）
    println(dynamicProgrammingForInterview.fibRecursion(500, hashMapOf()))  // 快

    // ✅ 使用 Tabulation（表格法）計算費氏數列第 6 項
    println(dynamicProgrammingForInterview.fibTabulation(6))

    // 🚫 計算 2x3 格子走法數量，無記憶體，效能差
    println(dynamicProgrammingForInterview.gridTravelerNoMemo(2, 3))  // 慢

    // ✅ 計算 2x3 格子走法數量，使用記憶體
    println(dynamicProgrammingForInterview.gridTraveler(2, 3, hashMapOf()))  // 快

    println(dynamicProgrammingForInterview.gridTravelerTabulation(3, 3))
}

class DynamicProgrammingForInterview {

    /**
     * 🧮 計算第 n 項的費氏數列（不使用記憶體）
     * 範例：fib(5) = 5，fib(6) = 8
     *
     * 時間複雜度：O(2^n)，非常差
     * 空間複雜度：O(n)
     */
    fun fibRecursionNoMemo(n: Int): Int {
        if (n == 1) return 1
        if (n == 0) return 0
        return fibRecursionNoMemo(n - 1) + fibRecursionNoMemo(n - 2)
    }

    /**
     * 🧠 使用 Memoization 技巧計算第 n 項費氏數列（記憶體遞迴）
     * 範例：fib(500) 也能快速計算
     *
     * 時間複雜度：O(n)
     * 空間複雜度：O(n)
     */
    fun fibRecursion(n: Int, memo: HashMap<Int, Int>): Int {
        if (n == 1) return 1
        if (n == 0) return 0
        if (memo.contains(n)) return memo[n]!!

        memo[n] = fibRecursion(n - 1, memo) + fibRecursion(n - 2, memo)
        return memo[n]!!
    }

    /**
     * 使用 Tabulation（表格法）計算費氏數列第 n 項
     *
     * 範例：fib(5) = 5
     *
     * 時間複雜度：O(n)
     * 空間複雜度：O(n)
     */
    fun fibTabulation(n: Int): Int {
        // ✅ 特殊情況直接回傳
        if (n == 0) return 0
        if (n == 1) return 1

        // 建立長度為 n+1 的 list，初始值都是 0
        val fibList = MutableList(n + 1) { 0 }

        // 初始值定義
        fibList[1] = 1

        // 根據公式：fib(n) = fib(n-1) + fib(n-2)
        for (i in 2..n) {
            fibList[i] = fibList[i - 1] + fibList[i - 2]
        }

        // 回傳第 n 項
        return fibList[n]
    }

    /**
     * 🧭 計算在 n x m 格子中，從左上角走到右下角的總走法（只能向下或向右）
     * 範例：2x3 的走法為 3
     *
     * 時間複雜度：O(2^(n+m))
     * 空間複雜度：O(n + m)
     */
    fun gridTravelerNoMemo(n: Int, m: Int): Int {
        if (n == 1 && m == 1) return 1
        if (minOf(n, m) <= 0) return 0
        return gridTravelerNoMemo(n - 1, m) + gridTravelerNoMemo(n, m - 1)
    }

    /**
     * 🧠 使用 Memoization 計算格子走法（走法總數）
     *
     * 優化版本，使用 HashMap 記錄重複子問題
     *
     * 時間複雜度：O(n * m)
     * 空間複雜度：O(n * m)
     */
    fun gridTraveler(n: Int, m: Int, memo: HashMap<Pair<Int, Int>, Int>): Int {
        if (n == 1 && m == 1) return 1
        if (minOf(n, m) <= 0) return 0

        // ✅ 儲存 key 時用 (min, max) 可減少重複
        val key = Pair(n, m)
        if (memo.contains(key)) return memo[key]!!

        // 計算後存入記憶體
        memo[key] = gridTraveler(n - 1, m, memo) + gridTraveler(n, m - 1, memo)
        return memo[key]!!
    }

    fun gridTravelerTabulation(row: Int, column: Int): Int {
        val grid = MutableList(row + 1){ MutableList(column + 1) { 0 } }
        grid[1][1] = 1
        for (rowIndex in 0..row){
            for (columnIndex in 0..column){
                val current = grid[rowIndex][columnIndex]
                // 將走法數量傳遞給下方格子
                if (rowIndex + 1 <= row) {
                    grid[rowIndex + 1][columnIndex] += current
                }

                // 將走法數量傳遞給右方格子
                if (columnIndex + 1 <= column) {
                    grid[rowIndex][columnIndex + 1] += current
                }
            }
        }

        return grid[row][column]
    }
}
