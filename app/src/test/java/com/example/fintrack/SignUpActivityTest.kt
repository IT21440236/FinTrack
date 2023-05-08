package com.example.fintrack

//import android.content.Intent
//import com.example.fintrack.activities.SignUpActivity
//import org.junit.After
//import org.junit.Before
//import org.junit.Test
//import java.util.regex.Pattern.matches
//
//class SignUpActivityTest {
//    private lateinit var scenario: ActivityScenario<SignUpActivity>
//
//    @Before
//    fun setup() {
//        scenario = launchActivity(Intent(ApplicationProvider.getApplicationContext(), SignUpActivity::class.java))
//    }
//
//    @Test
//    fun checkEmptyFields() {
//        onView(withId(R.id.button)).perform(click())
//        onView(withText("Empty Fields Are not Allowed !!")).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun checkPasswordMatching() {
//        onView(withId(R.id.usernameET)).perform(typeText("testuser"))
//        onView(withId(R.id.emailEt)).perform(typeText("test@test.com"))
//        onView(withId(R.id.nicET)).perform(typeText("123456789v"))
//        onView(withId(R.id.phoneET)).perform(typeText("0712345678"))
//        onView(withId(R.id.passET)).perform(typeText("password"))
//        onView(withId(R.id.confirmPassEt)).perform(typeText("differentpassword"))
//        onView(withId(R.id.button)).perform(click())
//        onView(withText("Password is not matching")).check(matches(isDisplayed()))
//    }
//
//    @Test
//    fun checkSuccessfulSignUp() {
//        onView(withId(R.id.usernameET)).perform(typeText("testuser"))
//        onView(withId(R.id.emailEt)).perform(typeText("test@test.com"))
//        onView(withId(R.id.nicET)).perform(typeText("123456789v"))
//        onView(withId(R.id.phoneET)).perform(typeText("0712345678"))
//        onView(withId(R.id.passET)).perform(typeText("password"))
//        onView(withId(R.id.confirmPassEt)).perform(typeText("password"))
//        onView(withId(R.id.button)).perform(click())
//        onView(withText("Welcome to FinTrack")).check(matches(isDisplayed()))
//    }
//
//    @After
//    fun close() {
//        scenario.close()
//    }
//}