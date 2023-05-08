//package com.example.fintrack
//
//import com.example.fintrack.activities.Income
//import com.example.fintrack.models.IncomeModel
//import org.junit.Assert.assertEquals
//import org.junit.Before
//import org.junit.Test
//
//class IncomeTest {
//
//    private lateinit var incomeActivity: Income
//
//    @Before
//    fun setup() {
//        incomeActivity = Income()
//    }
//
//    @Test
//    fun testCalculateIncomeTotal() {
//        val incomeList = ArrayList<IncomeModel>()
//        incomeList.add(IncomeModel("1", "Salary", "Income", 2000.0, "2023-05-01"))
//        incomeList.add(IncomeModel("2", "Bonus", "Income", 500.0, "2023-05-05"))
//        incomeList.add(IncomeModel("3", "Freelancing", "Income", 300.0, "2023-05-03"))
//
//        val totalIncome = incomeActivity.calculateIncomeTotal(incomeList)
//
//        assertEquals(2800.0, totalIncome, 0.0)
//    }
//
//    @Test
//    fun testFormatIncomeAmount() {
//        val amount = 1500.0
//        val formattedAmount = incomeActivity.formatIncomeAmount(amount)
//
//        assertEquals("Rs 1,500.00", formattedAmount)
//    }
//
//    // Add more test cases for other income-related functions if needed
//
//}