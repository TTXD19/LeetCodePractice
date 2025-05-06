package Kotlin

import java.util.Stack
import kotlin.math.min

fun main() {
    val result = change(5, intArrayOf(1, 2, 5))
    println(result)
}

fun change(amount: Int, coins: IntArray): Int {
    return helpChange(amount, coins)
}

fun helpChange(amount: Int, coins: IntArray): Int {
    if (amount < 0) {
        return 0
    }
    if (amount == 0) {
        return 1
    }
    var result = 0
    for (coin in coins){
        result += helpChange(amount - coin, coins)
    }
    return result
}

fun maxProduct(n: Int): Int {
    val array = n.toString().toCharArray()
    var firstLarge: Int? = null
    var secondLarge: Int? = null
    for (i in 0..array.size - 1) {
        val num = array[i].digitToInt()
        if (firstLarge == null) {
            firstLarge = num
            continue
        }
        if (secondLarge == null) {
            if (num > firstLarge) {
                secondLarge = firstLarge
                firstLarge = num
            } else {
                secondLarge = num
            }
            continue
        }
        if (num > firstLarge) {
            secondLarge = firstLarge
            firstLarge = num
            continue
        }
        if (num > secondLarge) {
            secondLarge = num
        }
    }

    val result = (firstLarge ?: 1) * (secondLarge ?: 1)
    return result
}

fun productExceptSelf(nums: IntArray): IntArray {
    val size = nums.size
    val result = IntArray(size) { 1 }
    var leftPrefix = 1
    for (num in 0 until size) {
        result[num] = leftPrefix
        leftPrefix *= nums[num]
    }
    println(result)

    var rightPrefix = 1
    for (num in size - 1 downTo 0) {
        result[num] *= rightPrefix
        rightPrefix *= nums[num]
    }

    return result
}


fun isValidSudoku(board: Array<CharArray>): Boolean {
    val n = 9
    val rowList = Array(n) { mutableSetOf<Char>() }
    val columnList = Array(n) { mutableSetOf<Char>() }
    val boxList = Array(n) { mutableSetOf<Char>() }

    for (i in 0 until n) {
        for (j in 0 until n) {
            val charValue = board[i][j]
            if (charValue != '.') {
                val boxIndex = (i / 3) * 3 + (j / 3)

                if (charValue in rowList[i] || charValue in columnList[j] || charValue in boxList[boxIndex]) {
                    return false
                }
                rowList[i].add(charValue)
                columnList[j].add(charValue)
                boxList[boxIndex].add(charValue)
            }
        }
    }
    return true
}

fun longestConsecutive(nums: IntArray): Int {
    val numSet = nums.toHashSet()
    var maxCount = 0
    for (num in numSet) {
        if (!numSet.contains(num - 1)) {
            var currentNum = num
            var currentCount = 1
            while (numSet.contains(currentNum + 1)) {
                currentNum++
                currentCount++
            }
            maxCount = maxOf(currentCount, maxCount)
        }
    }
    return maxCount
}

fun twoSum(nums: IntArray, target: Int): IntArray {
    val hashMap = hashMapOf<Int, Int>()
    for ((index, num) in nums.withIndex()) {
        val diff = target - nums[index]
        if (hashMap.containsKey(diff)) {
            return intArrayOf(hashMap[diff]!!, index)
        }
        hashMap[num] = index
    }
    return intArrayOf(0, 0)
}

fun isValid(s: String): Boolean {
    val charList = s.toCharArray()
    val stack = Stack<Char>()
    val charMap = hashMapOf(
        '[' to ']', '(' to ')', '{' to '}'
    )

    for (char in charList) {
        if (char in charMap.keys) {
            stack.push(char)
        } else if (char in charMap.values) {
            if (stack.isEmpty() || charMap[stack.pop()] != char) {
                return false
            }
        } else {
            return false
        }
    }
    return stack.isEmpty()
}


class MinStack() {

    private val stack = Stack<Int>()
    private val minStack = Stack<Int>()

    fun push(`val`: Int) {
        stack.push(`val`)
        if (minStack.isEmpty() || `val` <= minStack.peek()) {
            minStack.push(`val`)
        }
    }

    fun pop() {
        if (stack.peek() == minStack.peek()) {
            minStack.pop()
        }
        stack.pop()
    }

    fun top(): Int {
        return stack.peek()
    }

    fun getMin(): Int {
        return minStack.peek()
    }
}


