package com.example.fintrack.activities
//
//import android.content.Intent
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.Toast
//import com.example.fintrack.R
//import com.example.fintrack.models.FeedbackModel
//import com.google.firebase.database.DatabaseReference
//import com.google.firebase.database.FirebaseDatabase
//
//class FeedbackActivity : AppCompatActivity() {
//
//    private lateinit var etFeedback: EditText
//    private lateinit var btnFeedback:Button
//
//    private lateinit var dbRef: DatabaseReference
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_feedback)
//
//        etFeedback = findViewById(R.id.editTextTextMultiLine)
//        btnFeedback = findViewById(R.id.btnAddAP)
//
//        dbRef = FirebaseDatabase.getInstance().getReference("Feedback")
//
//        btnFeedback.setOnClickListener {
//            saveFeedbackData()
//        }
//    }
//    private fun saveFeedbackData(){
//        val feedbacktxt = etFeedback.text.toString()
//
//        if(feedbacktxt.isEmpty()){
//            etFeedback.error = "Please enter feedback"
//        }
//
//        val feedbackId = dbRef.push().key!!
//
//        val feedback = FeedbackModel(feedbackId, feedbacktxt)
//
//        dbRef.child(feedbackId).setValue(feedback).addOnCompleteListener {
//            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_LONG).show()
//
//            etFeedback.text.clear()
//
//            startActivity(Intent(this@FeedbackActivity, UserActivity::class.java))
//        }.addOnFailureListener { err ->
//            Toast.makeText(this, "Error ${err.message}", Toast.LENGTH_LONG).show()
//        }
//    }
//
//}