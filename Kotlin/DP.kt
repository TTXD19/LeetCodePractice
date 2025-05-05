package Kotlin

fun main() {
    val dp = DP()

//    println(dp.canSum(7, listOf(2, 3), hashMapOf()))
//    println(dp.canSum(7, listOf(2, 4), hashMapOf()))
//    println(dp.canSum(300, listOf(7, 14), hashMapOf()))

    println(dp.howSum(7, listOf(2, 3)))

}

class DP {

    fun canSum(sum: Int, nums: List<Int>, memo: HashMap<Int, Boolean>): Boolean {

        if (memo[sum] != null) {
            return memo[sum] == true
        }

        if (sum == 0) {
            return true
        }
        if (sum < 0) {
            return false
        }

        for (num in nums) {
            val target = sum - num
            if (canSum(target, nums, memo)) {
                return true
            }
        }

        memo[sum] = false

        return false

    }


    fun howSum(sum: Int, nums: List<Int>): MutableList<Int>? {

        if (sum == 0) {
            return mutableListOf()
        }
        if (sum < 1) {
            return null
        }


        for (num in nums) {
            val remain = sum - num
            val result = howSum(remain, nums)
            if (result != null){
                result.add(num)
                return result
            }
        }

        return null
    }
}