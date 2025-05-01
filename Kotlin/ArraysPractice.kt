package Kotlin


fun main() {
    val arraysPractice = ArraysPractice()
    val strs = arrayOf("eat", "tea", "tan", "ate", "nat", "bat")
    val result = arraysPractice.groupAnagrams(strs)
    println(result)
}

class ArraysPractice {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val result = hashMapOf<String, MutableList<String>>()
        for (s in strs) {
            val count = IntArray(26)
            for (char in s) {
                count[char - 'a']++
            }
            val key = count.joinToString("#")
            if (key !in result) {
                result[key] = mutableListOf()
            }
            result[key]?.add(s)
        }

        return result.values.toList()
    }
}