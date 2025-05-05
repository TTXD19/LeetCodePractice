package Kotlin

fun main() {

    val tabulation = Tabulation()
    println(tabulation.tabulation(6))

}

class Tabulation {

    fun tabulation(n: Int): Int {

        val list = mutableListOf<Int>()
        var first = 0
        list.add(first)
        var second =  1
        list.add(second)

        for (i in 2..n){
            val result = first + second
            first = second
            second = result
            list.add(result)
        }

        println(list)

        return second
    }

}