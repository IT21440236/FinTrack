package com.example.fintrack
//
//import android.widget.EditText
//import android.widget.ImageView
//import com.example.fintrack.activities.KidsAmount
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//import junit.framework.Assert.*
//import org.junit.Test
//
//class KidsAmountUnitTest {
//
//    @Test
//    fun testValidInput() {
//        val activity = Robolectric.buildActivity(KidsAmount::class.java).create().start().resume().visible().get()
//        val input = activity.findViewById<EditText>(R.id.lb_savePg42)
//        val piggyBank = activity.findViewById<ImageView>(R.id.ImgVPiggyBankPg45)
//        input.setText("10")
//        piggyBank.performClick()
//        val dbRefBalance = FirebaseDatabase.getInstance().getReference("kids_balance")
//        val dbRefTotal = FirebaseDatabase.getInstance().getReference("kids_total")
//        dbRefBalance.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                assertEquals(snapshot.value, 10)
//            }
//            override fun onCancelled(error: DatabaseError) {
//                fail("Database error: ${error.message}")
//            }
//        })
//        dbRefTotal.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                assertEquals(snapshot.value, 10)
//            }
//            override fun onCancelled(error: DatabaseError) {
//                fail("Database error: ${error.message}")
//            }
//        })
//    }
//
//    @Test
//    fun testInvalidInput() {
//        val activity = Robolectric.buildActivity(KidsAmount::class.java).create().start().resume().visible().get()
//        val input = activity.findViewById<EditText>(R.id.lb_savePg42)
//        val piggyBank = activity.findViewById<ImageView>(R.id.ImgVPiggyBankPg45)
//        input.setText("0")
//        piggyBank.performClick()
//        assertTrue(input.error.toString().isNotEmpty())
//    }
//
//    @Test
//    fun testClearInput() {
//        val activity = Robolectric.buildActivity(KidsAmount::class.java).create().start().resume().visible().get()
//        val input = activity.findViewById<EditText>(R.id.lb_savePg42)
//        val piggyBank = activity.findViewById<ImageView>(R.id.ImgVPiggyBankPg45)
//        input.setText("10")
//        piggyBank.performClick()
//        assertTrue(input.text.toString().isEmpty())
//    }
//
//    @Test
//    fun testUpdateBalanceWithPreviousBalance() {
//        val dbRefBalance = FirebaseDatabase.getInstance().getReference("kids_balance")
//        dbRefBalance.setValue(5)
//
//        val activity = Robolectric.buildActivity(KidsAmount::class.java).create().start().resume().visible().get()
//        val input = activity.findViewById<EditText>(R.id.lb_savePg42)
//        val piggyBank = activity.findViewById<ImageView>(R.id.ImgVPiggyBankPg45)
//
//        input.setText("10")
//        piggyBank.performClick()
//
//        dbRefBalance.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                assertEquals(snapshot.value, 15)
//            }
//            override fun onCancelled(error: DatabaseError) {
//                fail("Database error: ${error.message}")
//            }
//        })
//    }
//
//
//
//}