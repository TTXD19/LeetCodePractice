package Kotlin.codetemplete

import java.util.PriorityQueue
import kotlin.collections.HashMap

fun main() {

    val list = mutableListOf(
        listOf("A", "C", "3"),
        listOf("A", "B", "10"),
        listOf("C", "B", "4"),
        listOf("C", "E", "2")
    )

    val graphAdvanced = GraphAdvanced()
    println(graphAdvanced.findShortestPath(list, 4, "A"))
}

class GraphAdvanced {

    fun findShortestPath(edges: MutableList<List<String>>, nodes: Int, source: String): HashMap<String, Int> {
        val adjacentList = HashMap<String, MutableList<Pair<String, Int>>>()
        for (edge in edges) {
            val node = edge[0]
            val neighbor = edge[1]
            val weight = edge[2].toInt()
            if (adjacentList[node] == null) {
                adjacentList[node] = mutableListOf()
            }
            adjacentList[node]?.add(Pair(neighbor, weight))
        }

        val priorityQueue = PriorityQueue<Pair<String, Int>>( compareBy { it.second })
        val paths = HashMap<String, Int>()
        priorityQueue.offer(Pair(source, 0))
        while (priorityQueue.isNotEmpty()){
            val currentSource = priorityQueue.poll()
            if (paths.containsKey(currentSource.first)){
                continue
            }
            paths[currentSource.first] = currentSource.second
            val neighbors = adjacentList[currentSource.first]
            if (neighbors.isNullOrEmpty()){
                continue
            }
            for (neighbor in adjacentList[currentSource.first]!!){
                priorityQueue.offer(Pair(neighbor.first, neighbor.second + currentSource.second))
            }
        }

        return paths
    }

}