package Kotlin

fun main() {
    val looping = Looping()
    val list = listOf(1, 2, 3)
    looping.forLoop(list)
    looping.forLoop2(list)
    looping.forLoop3(list)
}

class Looping {

    fun forLoop(list: List<Int>){
        for (i in 0 until list.size){
            println(list[i])
        }
    }

    fun forLoop2(list: List<Int>){
        for (i in 0 until list.size - 1){
            println(list[i])
        }
    }

    fun forLoop3(list: List<Int>){
        for (i in 0..list.size - 1){
            println(list[i])
        }
    }

}