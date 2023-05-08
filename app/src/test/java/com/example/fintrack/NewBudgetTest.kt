//package com.example.fintrack
//
//import android.widget.TextView
//import androidx.cardview.widget.CardView
//import com.example.fintrack.activities.NewBudget
//
//package com.example.fintrack.activities
//
//import org.junit.Assert.*
//import org.junit.Before
//import org.junit.Test
//import org.mockito.Mock
//import org.mockito.Mockito.*
//import org.mockito.MockitoAnnotations
//
//class NewBudgetTest {
//
//    @Mock
//    private lateinit var activity: NewBudget
//
//    @Before
//    fun setup() {
//        MockitoAnnotations.openMocks(this)
//        activity = NewBudget()
//    }
//
//    @Test
//    fun testGeneratePlans() {
//        // Mock the required views
//        val mockPlanView = mock(android.view.View::class.java)
//        val mockCardView = mock(androidx.cardview.widget.CardView::class.java)
//        val mockSavingsTextView = mock(android.widget.TextView::class.java)
//        val mockExpensesTextView = mock(android.widget.TextView::class.java)
//
//        `when`(mockPlanView.findViewById<CardView>(com.example.fintrack.R.id.cardView))
//            .thenReturn(mockCardView)
//        `when`(mockPlanView.findViewById<TextView>(com.example.fintrack.R.id.tvSavings))
//            .thenReturn(mockSavingsTextView)
//        `when`(mockPlanView.findViewById<TextView>(com.example.fintrack.R.id.tvExpenses))
//            .thenReturn(mockExpensesTextView)
//
//        // Call the method to be tested
//        activity.generatePlans(1000.0)
//
//        // Verify the expected behavior and assertions
//        verify(activity.llPlans, times(3)).addView(mockPlanView)
//
//        verify(mockSavingsTextView).text = "Savings: Rs 0.0"
//        verify(mockExpensesTextView).text = "Expenses: Rs 0.0"
//
//        verify(mockSavingsTextView).text = "Savings: Rs 200.0"
//        verify(mockExpensesTextView).text = "Expenses: Rs 800.0"
//
//        verify(mockSavingsTextView).text = "Savings: Rs 400.0"
//        verify(mockExpensesTextView).text = "Expenses: Rs 1600.0"
//    }
//}
