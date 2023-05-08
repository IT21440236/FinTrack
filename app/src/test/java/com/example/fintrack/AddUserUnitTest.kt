package com.example.fintrack
//
//import com.example.fintrack.activities.AddUser
//import com.example.fintrack.models.MemberModel
//import junit.framework.Assert.assertEquals
//import junit.framework.Assert.assertTrue
//import kotlinx.coroutines.tasks.await
//import org.junit.Before
//import org.junit.Test
//
//class AddUserUnitTest {
//    private lateinit var addUser: AddUser
//
//    @Before
//    fun setUp() {
//        addUser = AddUser()
//    }
//
//    @Test
//    fun `saveMemberData saves member data successfully`() {
//        // Given
//        addUser.edt_unPg31.setText("John")
//        addUser.edt_mailPg31.setText("john@example.com")
//        addUser.edt_occPg31.setText("Software Engineer")
//        addUser.edt_workplacePg31.setText("Google")
//
//        val expectedMember = MemberModel("1", "John", "john@example.com", "Software Engineer", "Google")
//
//        // When
//        addUser.saveMemberData()
//
//        // Then
//        val actualMember = addUser.dbRef.child("1").get().addOnSuccessListener { dataSnapshot ->
//            dataSnapshot.getValue(MemberModel::class.java)
//        }.await()
//
//        assertEquals(expectedMember, actualMember)
//    }
//
//    @Test
//    fun `saveMemberData returns error when name is empty`() {
//        // Given
//        addUser.edt_unPg31.setText("")
//        addUser.edt_mailPg31.setText("john@example.com")
//        addUser.edt_occPg31.setText("Software Engineer")
//        addUser.edt_workplacePg31.setText("Google")
//
//        // When
//        addUser.saveMemberData()
//
//        // Then
//        assertTrue(addUser.edt_unPg31.error.isNotEmpty())
//    }
//
//    @Test
//    fun `saveMemberData returns error when email is empty`() {
//        // Given
//        addUser.edt_unPg31.setText("John")
//        addUser.edt_mailPg31.setText("")
//        addUser.edt_occPg31.setText("Software Engineer")
//        addUser.edt_workplacePg31.setText("Google")
//
//        // When
//        addUser.saveMemberData()
//
//        // Then
//        assertTrue(addUser.edt_mailPg31.error.isNotEmpty())
//    }
//
//    @Test
//    fun `saveMemberData returns error when email is not valid`() {
//        // Given
//        addUser.edt_unPg31.setText("John")
//        addUser.edt_mailPg31.setText("johnexample.com")
//        addUser.edt_occPg31.setText("Software Engineer")
//        addUser.edt_workplacePg31.setText("Google")
//
//        // When
//        addUser.saveMemberData()
//
//        // Then
//        assertTrue(addUser.edt_mailPg31.error.isNotEmpty())
//    }
//
//    @Test
//    fun `saveMemberData returns error when occupation is empty`() {
//        // Given
//        addUser.edt_unPg31.setText("John")
//        addUser.edt_mailPg31.setText("john@example.com")
//        addUser.edt_occPg31.setText("")
//        addUser.edt_workplacePg31.setText("Google")
//
//        // When
//        addUser.saveMemberData()
//
//        // Then
//        assertTrue(addUser.edt_occPg31.error.isNotEmpty())
//    }
//
//    @Test
//    fun `saveMemberData returns error when workplace is empty`() {
//        // Given
//        addUser.edt_unPg31.setText("John")
//        addUser.edt_mailPg31.setText("john@example.com")
//        addUser.edt_occPg31.setText("Software Engineer")
//        addUser.edt_workplacePg31.setText("")
//
//        // When
//        addUser.saveMemberData()
//
//        // Then
//        assertTrue(addUser.edt_workplacePg31.error.isNotEmpty())
//    }
//}