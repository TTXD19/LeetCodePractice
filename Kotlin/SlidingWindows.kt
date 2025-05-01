package Kotlin


fun main() {
    val slidingWindows = SlidingWindows()
    val result = slidingWindows.maxProfit(intArrayOf(7,1,5,3,6,4))
    println(result)
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
            if (prices[lowIndex] <= prices[highIndex] ){
                maxProfit = maxOf(maxProfit, prices[highIndex] - prices[lowIndex])
                highIndex++
            } else {
                lowIndex++
            }
        }

        return maxProfit
    }

}