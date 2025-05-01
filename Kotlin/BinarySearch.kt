package Kotlin

fun main() {
    val binarySearch = BinarySearch()
    val result = binarySearch.search(intArrayOf(-1, 0, 3, 5, 9, 12), 9)
    println(result)
}

class BinarySearch {
    fun minEatingSpeed(piles: IntArray, h: Int): Int {
        var low = 1
        var high = piles.maxOrNull()!! // Use maxOrNull for null safety

        while (low < high) {
            val mid = low + (high - low) / 2
            if (canEatAllBananas(piles, h, mid)) {
                high = mid // Update minimum k if possible
            } else {
                low = mid + 1
            }
        }

        return low
    }

    private fun canEatAllBananas(piles: IntArray, h: Int, k: Int): Boolean {
        var hours = 0
        for (pile in piles) {
            hours += (pile + k - 1) / k // Integer division with rounding up
        }
        return hours <= h
    }

    fun findMin(nums: IntArray): Int {
        var lPoint = 0
        var rPoint = nums.lastIndex
        while (lPoint < rPoint) {
            val middlePoint = lPoint + (rPoint - lPoint) / 2
            if (nums[middlePoint] > nums[rPoint]) {
                lPoint = middlePoint + 1
            } else {
                rPoint = middlePoint
            }
        }
        return nums[lPoint]
    }

    fun search(nums: IntArray, target: Int): Int {
        var lPoint = 0
        var rPoint = nums.lastIndex
        while (lPoint <= rPoint) {
            val middlePoint = lPoint + (rPoint - lPoint) / 2
            if (nums[middlePoint] == target) {
                return middlePoint
            }
            if (nums[middlePoint] < target) {
                lPoint = middlePoint + 1
            } else {
                rPoint = middlePoint - 1
            }
        }
        return -1
    }
}

class TimeMap() {

    private val hashMap = hashMapOf<String, MutableList<Pair<Int, String>>>()

    fun set(key: String, value: String, timestamp: Int) {
        val list = hashMap.getOrPut(key) { mutableListOf() }
        list.add(Pair(timestamp, value))
    }

    fun get(key: String, timestamp: Int): String {
        val list = hashMap[key] ?: return ""
        var lPoint = 0
        var rPoint = list.lastIndex
        while (lPoint <= rPoint) {
            val middlePointer = lPoint + (rPoint - lPoint) / 2
            if (list[middlePointer].first == timestamp) {
                return list[middlePointer].second
            }
            if (list[middlePointer].first < timestamp) {
                lPoint = middlePointer + 1
            } else {
                rPoint = middlePointer - 1
            }
        }
        return if (rPoint >= 0) list[rPoint].second else ""
    }
}