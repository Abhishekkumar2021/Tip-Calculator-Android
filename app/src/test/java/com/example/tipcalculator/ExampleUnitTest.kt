package com.example.tipcalculator

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

// Test Class
class Test(val a: Int, val b: Int, val res: Int)

class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        // tests
        val tests = listOf(
            Test(1, 1, 2),
            Test(2, 2, 4),
            Test(3, 3, 6),
            Test(4, 4, 8),
            Test(5, 5, 10),
            Test(6, 6, 12),
            Test(7, 7, 14),
            Test(8, 8, 16),
            Test(9, 9, 18),
            Test(10, 10, 20)
        )

        // loop through tests
        for (test in tests) {
            assertEquals(test.res, add(test.a, test.b))
        }
    }

    @Test
    fun subtraction_isCorrect() {
        // tests
        val tests = listOf(
            Test(1, 1, 0),
            Test(2, 2, 0),
            Test(3, 3, 0),
            Test(4, 4, 0),
            Test(5, 5, 0),
            Test(6, 6, 0),
            Test(7, 7, 0),
            Test(8, 8, 0),
            Test(9, 9, 0),
            Test(10, 10, 0)
        )

        // loop through tests
        for (test in tests) {
            assertEquals(test.res, subtract(test.a, test.b))
        }
    }
}