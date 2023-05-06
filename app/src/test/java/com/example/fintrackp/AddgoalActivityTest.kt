package com.example.fintrackp

//import com.example.fintrackp.activities.AddgoalActivity
//import junit.framework.Assert.assertEquals
//import org.junit.Before
//import org.junit.Test
//
//class AddgoalActivityTest {
//
//    private lateinit var activity: AddgoalActivity
//
//    @Before
//    fun setUp() {
//        activity = AddgoalActivity()
//    }
//
//    @Test
//    fun financial_goal_empty() {
//        activity.etGoal.setText("")
//        activity.etpaymenttime.setText("6 months")
//        activity.etAmount.setText("1000")
//        activity.saveGoalData()
//        assertEquals("Please enter financial goal", activity.etGoal.error.toString())
//    }
//
//    @Test
//    fun payment_time_empty() {
//        activity.etGoal.setText("Buy a new laptop")
//        activity.etpaymenttime.setText("")
//        activity.etAmount.setText("1000")
//        activity.saveGoalData()
//        assertEquals("Please enter financial goal", activity.etpaymenttime.error.toString())
//    }
//
//    @Test
//    fun amount() {
//        activity.etGoal.setText("Buy a new laptop")
//        activity.etpaymenttime.setText("6 months")
//        activity.etAmount.setText("")
//        activity.saveGoalData()
//        assertEquals("Please enter financial goal", activity.etAmount.error.toString())
//    }
//
//    @Test
//    fun successfully() {
//        activity.etGoal.setText("Buy a new laptop")
//        activity.etpaymenttime.setText("6 months")
//        activity.etAmount.setText("1000")
//        activity.saveGoalData()
//        assertEquals("", activity.etGoal.error.toString())
//        assertEquals("", activity.etpaymenttime.error.toString())
//        assertEquals("", activity.etAmount.error.toString())
//    }
//}