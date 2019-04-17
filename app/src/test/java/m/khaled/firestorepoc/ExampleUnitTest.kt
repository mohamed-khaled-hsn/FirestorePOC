package m.khaled.firestorepoc

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(5, solution(intArrayOf(1, 2, 1, 2, 5)))
    }

    //    fun sockMerchant(n: Int, ar: Array<Int>): Int {
//        ar.forEach {  }
//
//    }
//
    private fun uniqueElement(arr: Array<Int>): Int {
        val set = HashSet<Int>()
        arr.forEach {
            if (!set.add(it))
                set.remove(it)
        }
        return set.elementAt(0)
    }

    fun solution2(A: IntArray): Int {
        // write your code in Java SE 8
        val dups = HashSet<Int>()
        var elem = -1

        A.forEach {
            if (dups.add(it)) {
                elem = it
            }
        }
        return elem
    }

    // Time: O(N)
    // Space: O(1)
    fun solution(A: IntArray): Int {
        var elem = 0
        A.forEach { elem = elem xor it }
        return elem
    }
}
