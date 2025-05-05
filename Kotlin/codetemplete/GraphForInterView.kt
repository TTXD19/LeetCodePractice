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
    listOf("n", "p")
)
val edge_case_2: MutableList<List<String>> = mutableListOf(
    listOf("a", "b"),
    listOf("b", "c"),
    listOf("d", "e"),
    listOf("f", "g"),
    listOf("g", "h"),
    listOf("i", "j")
)

fun main() {
    val graphForInterView = GraphForInterView()

    // 建構鄰接表表示的圖
    val graph = graphForInterView.buildGraph(edges)

    // 測試 j 能否走到 m（兩點是否連通）
    println(graphForInterView.dfsHasPath(graph, hashSetOf(), "o", "p")) // true

    // 計算圖中有幾個不連通的區塊（幾個 connected components）
    println(graphForInterView.dfsCountComponent(graph)) // 4

    println(graphForInterView.largestComponent(graph))

    println(graphForInterView.bfsShortestPath(graph, "o", "p"))
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

    fun bfsShortestPath(edges: HashMap<String, MutableList<String>>, source: String, destination: String): Int {
        val queue = LinkedList<String>()
        var pathCount = 0
        val visited = hashSetOf<String>()
        queue.offer(source)
        while (queue.isNotEmpty()) {
            val currentNode = queue.pop()
            if (visited.contains(currentNode)){
                continue
            }
            if (currentNode == destination){
                return pathCount
            }
            pathCount++
            val neighborsList = edges[currentNode]
            if (neighborsList?.isNotEmpty() == true){
                for (neighbor in neighborsList){
                    queue.offer(neighbor)
                }
            }
        }

        return pathCount
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
}
