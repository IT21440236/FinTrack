package com.example.fintrack.activities
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Button
//import android.widget.EditText
//import android.widget.TextView
//import android.widget.Toast
//import com.example.fintrack.R
//import com.example.fintrack.models.GoalModel
//import com.google.firebase.auth.FirebaseAuth
//import com.google.firebase.database.*
//
//class EditProfile : AppCompatActivity() {
//
//    private lateinit var lbUserName: TextView
//    private lateinit var etName: EditText
//    private lateinit var etEmail: EditText
//    private lateinit var etTel: EditText
//    private lateinit var etNIC: EditText
//    private lateinit var btnUpdate: Button
//    private lateinit var btnDelete: Button
//
////    private  lateinit var firebaseAuth: FirebaseAuth
//    var databaseReference :  DatabaseReference? = null
//    var database: FirebaseDatabase? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_edit_profile)
//
//        lbUserName = findViewById(R.id.lbUserNameEP)
//        etName = findViewById(R.id.etNameEP)
//        etEmail = findViewById(R.id.etEmailEP)
//        etTel = findViewById(R.id.etTelEP)
//        etNIC = findViewById(R.id.etNicEP)
//        btnUpdate = findViewById(R.id.btnUpdateEP)
//        btnDelete = findViewById(R.id.btnDeleteEP)
//
////        firebaseAuth = FirebaseAuth.getInstance()
//
//        database = FirebaseDatabase.getInstance()
//        databaseReference = database?.reference!!.child("profile")
//
//        setValuesToViews()
//
//        btnUpdate.setOnClickListener {
//            updateProfile(
//                etName.text.toString(),
//                etEmail.text.toString(),
//                etTel.text.toString(),
//                etNIC.text.toString()
//            )
//        }
//
//
//    }
//
//    private fun setValuesToViews(){
////        val user = firebaseAuth.currentUser
////        val userreference = databaseReference?.child(user?.uid!!)
//
////        userreference?.addValueEventListener(object : ValueEventListener {
////            override fun onDataChange(snapshot: DataSnapshot) {
////                lbUserName.text = snapshot.child("username").value.toString()
////
////                etName.setText(snapshot.child("username").value.toString())
////                etEmail.setText(snapshot.child("useremail").value.toString())
////                etTel.setText(snapshot.child("tel").value.toString())
////                etNIC.setText(snapshot.child("nic").value.toString())
////
////            }
////
////            override fun onCancelled(error: DatabaseError) {
////                TODO("Not yet implemented")
////            }
////
////        })
//    }
//
//    private fun updateProfile(
//        name:String,
//        email:String,
//        tel:String,
//        nic:String
//    ){
////        val user = firebaseAuth.currentUser
////        val userreference = databaseReference?.child(user?.uid!!)
//
//        val updateUser: MutableMap<String, Any> = HashMap()
//        updateUser["username"] = name
//        updateUser["useremail"] = email
//        updateUser["tel"] = tel
//        updateUser["nic"] = nic
//
//        userreference?.updateChildren(updateUser)?.addOnCompleteListener { task ->
//            if(task.isSuccessful){
//                Toast.makeText(this, "Data updated successfully", Toast.LENGTH_LONG).show()
//            }else{
//                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
//            }
//        }
//    }
//
////    private fun updateProfile(
////        Name:String,
////        Email:String,
////        Tel:String,
////        Nic:String
////    ){
////        val currentUser = firebaseAuth.currentUser
////        val dbRef = FirebaseDatabase.getInstance().getReference("profile").child(currentUser?.uid!!)
////
////
////    }
//}