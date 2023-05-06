package com.example.fintrack
//
//import com.example.fintrack.activities.CalculatorActivity
//import junit.framework.TestCase.assertEquals
//import org.junit.Before
//import org.junit.Test
//
//
//
//class CalculatorUnitTest {
//    private lateinit var calculator: CalculatorActivity
//
//    @Before
//    fun setUp() {
//        calculator = CalculatorActivity()
//    }
//
//    @Test
//    fun testAddition() {
//        val result = calculator.evaluateExpression("2+3")
//        assertEquals(5.0, result, 0.001)
//    }
//
//    @Test
//    fun testSubtraction() {
//        val result = calculator.evaluateExpression("5-2")
//        assertEquals(3.0, result, 0.001)
//    }
//
//    @Test
//    fun testMultiplication() {
//        val result = calculator.evaluateExpression("2*3")
//        assertEquals(6.0, result, 0.001)
//    }
//
//    @Test
//    fun testDivision() {
//        val result = calculator.evaluateExpression("10/5")
//        assertEquals(2.0, result, 0.001)
//    }
//
//    @Test(expected = IllegalArgumentException::class)
//    fun testDivisionByZero() {
//        calculator.evaluateExpression("10/0")
//    }
//
//    @Test(expected = IllegalArgumentException::class)
//    fun testInvalidExpression() {
//        calculator.evaluateExpression("2+")
//    }
//}