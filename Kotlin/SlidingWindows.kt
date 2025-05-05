package Kotlin

import kotlin.math.max


fun main() {
    val slidingWindows = SlidingWindows()
//    val result = slidingWindows.maxProfit(intArrayOf(7, 1, 5, 3, 6, 4))
//    println(result)

    println(slidingWindows.maxSumOfArray(listOf(-1, -2, 10, -1, 2)))
}

class SlidingWindows {

    fun lengthOfLongestSubstring(s: String): Int {
        val charSet = mutableSetOf<Char>()
        var startIndex = 0
        var secondIndex = 0
        val charArray = s.toCharArray()
        var maxSublistSize = 0

        while (secondIndex < charArray.size) {
            if (charSet.add(charArray[secondIndex])) {
                secondIndex++
                maxSublistSize = maxOf(maxSublistSize, secondIndex - startIndex)
            } else {
                charSet.remove(charArray[startIndex])
                startIndex++
            }
        }

        return maxSublistSize
    }

    fun maxProfit(prices: IntArray): Int {
        var lowIndex = 0
        var highIndex = 0
        var maxProfit = 0

        while (highIndex < prices.size) {
            if (prices[lowIndex] <= prices[highIndex]) {
                maxProfit = maxOf(maxProfit, prices[highIndex] - prices[lowIndex])
                highIndex++
            } else {
                lowIndex++
            }
        }

        return maxProfit
    }

    // 2,3,-10,7,8
    fun maxSumOfArray(nums: List<Int>): List<Int> {

        val list = nums.toMutableList()
        list.addAll(nums.toMutableList())

        var maxSum = nums.first()
        var currentSum = 0
        var firstPointer = 0
        var secondPointer = 0
        var previousMaxSum = maxSum

        nums.forEachIndexed { index, num ->
            currentSum+=num
            if(currentSum < num){
                currentSum = num
                firstPointer = index
            }
            maxSum = maxOf(currentSum, maxSum)
            if (maxSum > previousMaxSum){
                previousMaxSum = maxSum
                secondPointer = index
            }
        }

        return nums.subList(firstPointer, secondPointer + 1)
    }

}