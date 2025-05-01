package Kotlin

import kotlin.math.max
import kotlin.math.min


fun main() {
    val nums = intArrayOf(-1, 0, 1, 2, -1, -4)
    val result = threeSum(nums)
    println(result)
}

fun maxArea(height: IntArray): Int {
    var startPointer = 0
    var endPointer = height.lastIndex
    var currentMaxValue = 0
    while (endPointer > startPointer) {
        val heightValue = min(height[startPointer], height[endPointer])
        val lengthValue = endPointer - startPointer
        val area = heightValue * lengthValue
        if (height[startPointer] >= height[endPointer]) {
            endPointer--
        } else {
            startPointer++
        }
        currentMaxValue = max(currentMaxValue, area)
    }
    return currentMaxValue
}

fun trap(height: IntArray): Int {
    var currentMax = 0
    var leftPointer = 0
    var rightPointer = height.lastIndex
    var leftMax = height[leftPointer]
    var rightMax = height[rightPointer]

    while (rightPointer > leftPointer) {
        if (rightMax >= leftMax) {
            leftPointer++
            leftMax = maxOf(height[leftPointer], leftMax)
            val result = (minOf(leftMax, rightMax)) - height[leftPointer]
            if (result > 0) {
                currentMax += result
            }
        } else {
            rightPointer--
            rightMax = maxOf(height[rightPointer], rightMax)
            val result = (minOf(leftMax, rightMax)) - height[rightPointer]
            if (result > 0) {
                currentMax += result
            }
        }
    }
    return currentMax
}

fun threeSum(nums: IntArray): List<List<Int>> {
    val sortedNumList = nums.sorted()
    val result = mutableListOf<List<Int>>()
    val n = sortedNumList.size

    for (i in 0 until n - 2) {
        // Avoid duplicate values for the first element
        if (i > 0 && sortedNumList[i] == sortedNumList[i - 1]) continue

        var left = i + 1
        var right = n - 1

        while (left < right) {
            val sum = sortedNumList[i] + sortedNumList[left] + sortedNumList[right]

            when {
                sum == 0 -> {
                    result.add(listOf(sortedNumList[i], sortedNumList[left], sortedNumList[right]))
                    left++
                    right--
                    // Avoid duplicates for the second and third elements
                    while (left < right && sortedNumList[left] == sortedNumList[left - 1]) left++
                    while (left < right && sortedNumList[right] == sortedNumList[right + 1]) right--
                }

                sum < 0 -> left++
                else -> right--
            }
        }
    }

    return result
}

fun isPalindrome(s: String): Boolean {

    fun cleanString(input: String): String {
        return input.filter { it.isLetterOrDigit() }.toLowerCase()
    }

    val arrayList = cleanString(s).toCharArray()
    var lPoint = 0
    var rPoint = arrayList.lastIndex

    while (lPoint < rPoint) {
        if (arrayList[lPoint] != arrayList[rPoint]) {
            return false
        }
        lPoint++
        rPoint--
    }
    return true

}