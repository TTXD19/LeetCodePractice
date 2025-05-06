package Kotlin.codetemplete

import java.util.HashMap
import java.util.LinkedList
import java.util.Queue

// 定義邊（無向圖）
val edges: MutableList<List<String>> = mutableListOf(
    listOf("i", "j"),
    listOf("k", "i"),
    listOf("m", "k"),
    listOf("k", "l"),
    listOf("o", "n"),
    listOf("n", "p"),
    listOf("p", "y")
)
val edge_case_2: MutableList<List<String>> = mutableListOf(
    listOf("a", "b"),
    listOf("b", "c"),
    listOf("d", "e"),
    listOf("f", "g"),
    listOf("g", "h"),
    listOf("i", "j")
)

val grid: MutableList<List<String>> = mutableListOf(
    listOf("0", "1", "1", "0", "0"),
    listOf("0", "1", "1", "0", "1"),
    listOf("1", "0", "0", "1", "1"),
    listOf("1", "0", "0", "0", "0"),
    listOf("1", "1", "1", "0", "1")
)

val grid_case_2: MutableList<List<String>> = mutableListOf(
    listOf("1", "0", "1", "0", "0"),
    listOf("0", "0", "1", "0", "0"),
    listOf("1", "0", "1", "1", "0"),
    listOf("1", "1", "0", "0", "1"),
    listOf("0", "0", "1", "1", "1")
)


fun main() {
    val graphForInterView = GraphForInterView()

    // ✅ 使用 edge 資料建構無向圖（鄰接表形式）
    val graph = graphForInterView.buildGraph(edges)

    // ✅ 測試「節點 o 是否能走到節點 p」（是否存在一條連通路徑）
    println(graphForInterView.dfsHasPath(graph, hashSetOf(), "o", "p")) // 預期輸出 true

    // ✅ 計算圖中有幾個「不連通」的群組（connected components）
    println(graphForInterView.dfsCountComponent(graph)) // 預期輸出 4

    // ✅ 找出圖中「最大的連通區塊」的節點數（最大 connected component 大小）
    println(graphForInterView.largestComponent(graph))

    // ✅ 使用 BFS 找出從 o 到 y 的「最短距離」（邊數），若無法到達，回傳 -1
    println(graphForInterView.bfsShortestPath(graph, "o", "y"))

    // ✅ 計算 grid 中有幾座「島嶼」：1 表示陸地，0 表示水
    println(graphForInterView.islandCount(grid))

    // ✅ 在 grid_case_2 中找出「最小的一座島」的面積（包含多少格）
    println(graphForInterView.islandSize(grid_case_2))
}

class GraphForInterView {

    /**
     * 建立無向圖的鄰接表
     *
     * 範例：
     * 輸入：[["a", "b"], ["b", "c"]]
     * 輸出：{
     *   "a": ["b"],
     *   "b": ["a", "c"],
     *   "c": ["b"]
     * }
     */
    fun buildGraph(edges: List<List<String>>): HashMap<String, MutableList<String>> {
        val graph = hashMapOf<String, MutableList<String>>()
        for (edge in edges) {
            // 初始化節點
            if (!graph.containsKey(edge[0])) graph[edge[0]] = mutableListOf()
            if (!graph.containsKey(edge[1])) graph[edge[1]] = mutableListOf()

            // 加入雙向邊（鄰居關係）
            graph[edge[0]]?.add(edge[1]) // edge[1] 是 edge[0] 的鄰居
            graph[edge[1]]?.add(edge[0]) // edge[0] 是 edge[1] 的鄰居
        }
        return graph
    }

    /**
     * 判斷兩節點之間是否存在路徑（使用 DFS）
     *
     * 用途：圖論中的經典「兩點是否連通」問題。
     * 題目範例：給定無向圖與兩個節點，判斷是否存在從 source 到 destination 的路徑。
     */
    fun dfsHasPath(
        edges: HashMap<String, MutableList<String>>,
        visited: HashSet<String>,
        source: String,
        destination: String
    ): Boolean {
        if (source == destination) return true
        if (visited.contains(source)) return false

        visited.add(source)

        // 遍歷所有鄰居節點
        edges[source]?.forEach { neighbour ->
            if (dfsHasPath(edges, visited, neighbour, destination)) {
                return true
            }
        }

        return false
    }

    /**
     * 使用 BFS（廣度優先搜尋）尋找從 source 到 destination 的最短路徑長度。
     *
     * @param edges 圖的鄰接表表示法，每個節點對應其鄰居列表。
     * @param source 起點節點。
     * @param destination 終點節點。
     * @return 最短路徑的邊數，若無法到達 destination 則回傳 -1。
     */
    fun bfsShortestPath(edges: HashMap<String, MutableList<String>>, source: String, destination: String): Int {
        // 使用 queue 儲存 Pair(當前節點, 與起點的距離)
        val queue = LinkedList<Pair<String, Int>>()

        // 記錄已訪問過的節點，避免重複拜訪造成無限迴圈
        val visited = hashSetOf<String>()

        // 起點加入 queue，距離為 0
        queue.offer(Pair(source, 0))

        // 開始 BFS 探索
        while (queue.isNotEmpty()) {
            val currentNode = queue.pop() // 取出當前節點與距離

            // 如果當前節點就是目標，回傳距離
            if (currentNode.first == destination) return currentNode.second

            // 若已訪問過，跳過
            if (visited.contains(currentNode.first)) continue

            // 標記當前節點為已訪問
            visited.add(currentNode.first)

            // 拜訪所有鄰居節點，加入 queue，距離 +1
            val neighborsList = edges[currentNode.first]
            if (neighborsList?.isNotEmpty() == true) {
                for (neighbor in neighborsList) {
                    queue.offer(Pair(neighbor, currentNode.second + 1))
                }
            }
        }

        // 若未找到目標節點，回傳 -1 表示無法到達
        return -1
    }


    /**
     * 計算圖中有幾個連通元件（connected components）
     *
     * ✅ 用途：
     *    - 判斷圖中總共有幾個獨立的子圖（互不相連的區塊）
     *
     * ✅ 題目範例：
     *    假設輸入的邊為：
     *        [["a", "b"], ["b", "c"], ["d", "e"], ["f", "g"], ["g", "h"], ["i", "j"]]
     *
     *    對應的圖像結構為：
     *        Component 1:     Component 2:   Component 3:     Component 4:
     *           a---b---c        d---e         f---g---h         i---j
     *
     *    每組相連的節點群就是一個「連通元件」
     *
     *    ➤ 答案為：4
     *
     * ✅ 解法：
     *    - 遍歷圖中所有節點
     *    - 如果節點尚未拜訪（visited），從該節點開始 DFS 拜訪整個區塊
     *    - 每完成一塊區塊的 DFS，就把區塊數加 1
     */
    fun dfsCountComponent(edges: HashMap<String, MutableList<String>>): Int {
        val visited: HashSet<String> = hashSetOf()
        var count = 0

        for ((node, _) in edges) {
            if (visited.contains(node)) continue

            // 從每個未訪問節點出發，找出整個元件
            explore(edges, visited, node)
            count++
        }
        return count
    }

    /**
     * 遞迴輔助函數：探索一個連通元件的所有節點
     *
     * 用途：配合 dfsCountComponent 使用，幫助標記整個元件。
     */
    private fun explore(
        edges: HashMap<String, MutableList<String>>,
        visited: HashSet<String>,
        node: String
    ) {
        if (visited.contains(node)) return
        visited.add(node)

        // 拜訪所有鄰居節點
        edges[node]?.forEach { neighbour ->
            explore(edges, visited, neighbour)
        }
    }

    /**
     * 計算圖中最大連通元件的大小（節點數）
     *
     * ✅ 用途：
     *     - 找出在整個圖中，節點彼此相連的最大群體有多大。
     * ✅ 題目範例：
     *     Input: [["a", "b"], ["b", "c"], ["d", "e"], ["f", "g"], ["g", "h"], ["i", "j"]]
     *     圖中有以下元件：
     *         a-b-c → 3 個節點
     *         d-e   → 2 個節點
     *         f-g-h → 3 個節點
     *         i-j   → 2 個節點
     *     ➤ 答案為：3
     */
    fun largestComponent(edges: HashMap<String, MutableList<String>>): Int {
        val visited: HashSet<String> = hashSetOf()
        var maxComponentCount = 0

        for ((node, _) in edges) {
            if (visited.contains(node)) continue

            // 從每個未訪問節點出發，找出整個元件
            val componentCount = exploreSize(edges, visited, node, 0)
            maxComponentCount = maxOf(maxComponentCount, componentCount)
        }
        return maxComponentCount
    }

    /**
     * DFS 計算從某節點出發的整個連通元件大小
     */
    private fun exploreSize(
        edges: HashMap<String, MutableList<String>>,
        visited: HashSet<String>,
        node: String,
        count: Int
    ): Int {
        if (visited.contains(node)) {
            return count
        }
        visited.add(node)
        var size = count + 1

        // 拜訪所有鄰居節點
        edges[node]?.forEach { neighbour ->
            size = exploreSize(edges, visited, neighbour, size)
        }

        return size
    }

    // 主函式：計算有幾座島
    fun islandCount(grid: MutableList<List<String>>): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        var islandCount = 0
        grid.forEachIndexed { rowIndex, column ->
            column.forEachIndexed { columnIndex, _ ->
                // ✅ 若是「島」且尚未拜訪，才進行 DFS
                if (grid[rowIndex][columnIndex] == "1" && !visited.contains(Pair(rowIndex, columnIndex))) {
                    exploreIsland(grid, rowIndex, columnIndex, visited)
                    // 找到一座新的島
                    islandCount++
                } else {
                    // 記錄已拜訪的格子
                    visited.add(Pair(rowIndex, columnIndex))
                }
            }
        }
        return islandCount
    }

    fun exploreIsland(
        grid: MutableList<List<String>>,
        rowIndex: Int,
        columnIndex: Int,
        visited: MutableSet<Pair<Int, Int>>
    ) {
        // ✅ 超出邊界或不是島就返回
        if (rowIndex < 0 || columnIndex < 0 || rowIndex > grid.size - 1 || columnIndex > grid[0].size - 1 || grid[rowIndex][columnIndex] == "0") return
        if (visited.contains(Pair(rowIndex, columnIndex))) return
        // 記錄已拜訪的格子
        visited.add(Pair(rowIndex, columnIndex))
        // (0, 1) 往右
        // (1, 0) 往下
        // (0, -1) 往左
        // (-1, 0) 往上
        val directions = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
        for (direction in directions) {
            exploreIsland(grid, rowIndex + direction.first, columnIndex + direction.second, visited)
        }
    }

    // ✅ 找出「最小島嶼」的面積大小
    fun islandSize(grid: MutableList<List<String>>): Int {
        val visited = mutableSetOf<Pair<Int, Int>>()
        var islandSize: Int? = null

        grid.forEachIndexed { rowIndex, column ->
            column.forEachIndexed { columnIndex, _ ->
                val pos = Pair(rowIndex, columnIndex)

                // ✅ 若是「島」且尚未拜訪，才進行 DFS 探索
                if (grid[rowIndex][columnIndex] == "1" && !visited.contains(pos)) {
                    val size = exploreIslandSize(grid, rowIndex, columnIndex, visited, 0)

                    // ✅ 更新最小島嶼大小
                    islandSize = if (islandSize == null) size else minOf(size, islandSize!!)
                } else {
                    // ✅ 非島或已拜訪的地方，也加進 visited（避免重複）
                    visited.add(pos)
                }
            }
        }

        return islandSize!! // 假設保證至少有一座島
    }

    // ✅ 計算某一座島的面積（以 DFS 遞迴進行）
    fun exploreIslandSize(
        grid: MutableList<List<String>>,
        rowIndex: Int,
        columnIndex: Int,
        visited: MutableSet<Pair<Int, Int>>,
        size: Int
    ): Int {
        // ✅ 超出邊界 or 是水（"0"）就不計入大小
        if (
            rowIndex < 0 || columnIndex < 0
            || rowIndex >= grid.size
            || columnIndex >= grid[0].size
            || grid[rowIndex][columnIndex] == "0"
        ) return size

        val pos = Pair(rowIndex, columnIndex)
        if (visited.contains(pos)) return size

        visited.add(pos)
        var newSize = size + 1

        // (0, 1) 往右
        // (1, 0) 往下
        // (0, -1) 往左
        // (-1, 0) 往上
        val directions = listOf(Pair(0, 1), Pair(1, 0), Pair(0, -1), Pair(-1, 0))
        for (direction in directions) {
            newSize =
                exploreIslandSize(grid, rowIndex + direction.first, columnIndex + direction.second, visited, newSize)
        }

        return newSize
    }
}
