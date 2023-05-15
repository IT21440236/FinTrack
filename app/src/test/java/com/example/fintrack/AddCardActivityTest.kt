package com.example.fintrack
//
//import com.example.fintrack.activities.AddCardActivity
//import com.example.fintrack.models.CardModel
//import com.google.firebase.database.DatabaseError
//import junit.framework.TestCase.*
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//
//class AddCardActivityTest {
//
//    private lateinit var activity: AddCardActivity
//
//    @get:Rule
//    var rule: ActivityTestRule<AddCardActivity> = ActivityTestRule(AddCardActivity::class.java)
////@Before is a JUnit annotation that denotes a method to be executed before each
////test method is run. In the code you provided, the setUp() method annotated with
////@Before is called before each test method is executed, and it instantiates an
////instance of the AddUser class and assigns it to the addUser variable.
////This allows you to reuse the addUser instance across multiple test methods,
////without having to create a new instance for each test. This is a common pattern
////in unit testing,where you want to set up some initial state or dependencies before each
////test is run.
//    @Before
//    fun setUp() {
//        activity = rule.activity
//    }
//
//    @Test
//    fun saveCardData_emptyCardNo_showError() {
//        activity.etCardNo.setText("")
//        activity.etName.setText("Card Name")
//        activity.etExpiryDate.setText("12/25")
//        activity.etCVV.setText("123")
//
//        activity.saveCardData()
//
//        val errorMsg = activity.etCardNo.error
//        assertNotNull(errorMsg)
//        assertEquals("Please enter card number", errorMsg.toString())
//    }
//
//    @Test
//    fun saveCardData_emptyCardName_showError() {
//        activity.etCardNo.setText("1234567890123456")
//        activity.etName.setText("")
//        activity.etExpiryDate.setText("12/25")
//        activity.etCVV.setText("123")
//
//        activity.saveCardData()
//
//        val errorMsg = activity.etName.error
//        assertNotNull(errorMsg)
//        assertEquals("Please enter card name", errorMsg.toString())
//    }
//
//    @Test
//    fun saveCardData_emptyExpiryDate_showError() {
//        activity.etCardNo.setText("1234567890123456")
//        activity.etName.setText("Card Name")
//        activity.etExpiryDate.setText("")
//        activity.etCVV.setText("123")
//
//        activity.saveCardData()
//
//        val errorMsg = activity.etExpiryDate.error
//        assertNotNull(errorMsg)
//        assertEquals("Please enter expiry date", errorMsg.toString())
//    }
//
//    @Test
//    fun saveCardData_emptyCVV_showError() {
//        activity.etCardNo.setText("1234567890123456")
//        activity.etName.setText("Card Name")
//        activity.etExpiryDate.setText("12/25")
//        activity.etCVV.setText("")
//
//        activity.saveCardData()
//
//        val errorMsg = activity.etCVV.error
//        assertNotNull(errorMsg)
//        assertEquals("Please enter cvv number", errorMsg.toString())
//    }
//
//    @Test
//    fun saveCardData_validCardData_saveSuccessfully() {
//        val cardNo = "1234567890123456"
//        val cardName = "Card Name"
//        val expiryDate = "12/25"
//        val cvv = "123"
//
//        activity.etCardNo.setText(cardNo)
//        activity.etName.setText(cardName)
//        activity.etExpiryDate.setText(expiryDate)
//        activity.etCVV.setText(cvv)
//
//        activity.saveCardData()
//
//        val expectedCard = CardModel("", cardNo, cardName, expiryDate, cvv)
//
//        val query = activity.dbRef.orderByChild("cardNo").equalTo(cardNo)
//        query.addListenerForSingleValueEvent(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                val cards = mutableListOf<CardModel>()
//                for (cardSnapshot in snapshot.children) {
//                    val card = cardSnapshot.getValue(CardModel::class.java)
//                    if (card != null) {
//                        cards.add(card)
//                    }
//                }
//                assertTrue(cards.size == 1)
//                val actualCard = cards[0]
//                expectedCard.id = actualCard.id
//                assertEquals(expectedCard, actualCard)
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                fail("Test query cancelled: ${error.message}")
//            }
//        })
//    }
//}