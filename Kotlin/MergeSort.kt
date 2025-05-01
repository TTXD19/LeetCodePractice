package Kotlin


fun main(){

    val arr = listOf(5, 2, 4, 7, 1, 3, 6, 8)
    println(mergeSort(arr)) // [1, 2, 3, 4, 5, 6, 7, 8]

}

fun mergeSort(list: List<Int>): List<Int>{
    if (list.size <= 1) return list

    val mid = list.size / 2

    val leftList = mergeSort(list.subList(0, mid))
    val rightList = mergeSort(list.subList(mid, list.size))
    val mergedList = mergeSortedList(leftList, rightList)

    return mergedList
}

fun mergeSortedList(leftList: List<Int>, rightList: List<Int>): List<Int> {

    val finalList = mutableListOf<Int>()
    var l = 0
    var r = 0

    while (l < leftList.size && r < rightList.size){
        if (leftList[l] <= rightList[r]){
            finalList.add(leftList[l])
            l++
        } else {
            finalList.add(rightList[r])
            r++
        }
    }

    finalList.addAll(leftList.drop(l))
    finalList.addAll(rightList.drop(r))

    return finalList
}
