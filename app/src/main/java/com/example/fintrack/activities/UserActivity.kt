package com.example.fintrack.activities
//
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import com.example.fintrack.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class UserActivity : AppCompatActivity() {
    private lateinit var tvUserName: TextView
    private lateinit var tvUserEmail:TextView
    private lateinit var tvLogOut:TextView
    private lateinit var tvFeedback:TextView
    private lateinit var tvContactus:TextView

    private lateinit var tvAnalytics:TextView
    private lateinit var btnEdit: Button
    
    private lateinit var btnUser: ImageButton
    private lateinit var btnWallet: ImageButton
    private lateinit var btnHome: ImageButton

    private  lateinit var firebaseAuth: FirebaseAuth
    var databaseReference :  DatabaseReference? = null
    var database: FirebaseDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        tvUserName = findViewById(R.id.lbUserNameUP)
        tvUserEmail = findViewById(R.id.lbUserEmailUP)
        tvLogOut = findViewById(R.id.textView8)
        tvFeedback = findViewById(R.id.textView6)
        tvContactus = findViewById(R.id.textView11)
        btnEdit = findViewById(R.id.btnEditUP)
        
        btnUser=findViewById<ImageButton>(R.id.btnUserUP)
        btnWallet=findViewById<ImageButton>(R.id.btnWalletUP)
        btnHome=findViewById<ImageButton>(R.id.btnHomeUP)

        tvAnalytics = findViewById(R.id.textView5)


        firebaseAuth = FirebaseAuth.getInstance()

        database = FirebaseDatabase.getInstance()
        databaseReference = database?.reference!!.child("profile")

        loadProfile()

        tvFeedback.setOnClickListener {
            startActivity(Intent(this@UserActivity, FeedbackActivity::class.java))
            finish()
        }

        tvContactus.setOnClickListener {
            startActivity(Intent(this@UserActivity, ContactusActivity::class.java))
            finish()
        }

        tvAnalytics.setOnClickListener {
            startActivity(Intent(this@UserActivity, GoalActivity::class.java))
            finish()
        }

        btnEdit.setOnClickListener {
            startActivity(Intent(this@UserActivity, EditProfile::class.java))
            finish()
        }
        
        btnUser.setOnClickListener {
            startActivity(Intent(this@UserActivity, UserActivity::class.java))
        }

        btnWallet.setOnClickListener {
            startActivity(Intent(this@UserActivity, WalletActivity::class.java))
        }
        
        btnHome.setOnClickListener {
            startActivity(Intent(this@UserActivity, Home::class.java))
        }


    }

    //load profile
    private fun loadProfile(){
        val user = firebaseAuth.currentUser
        val userreference = databaseReference?.child(user?.uid!!)

        userreference?.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                tvUserName.text = snapshot.child("username").value.toString()
                tvUserEmail.text = snapshot.child("useremail").value.toString()

            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        tvLogOut.setOnClickListener {
            firebaseAuth.signOut()
            startActivity(Intent(this@UserActivity, SignInActivity::class.java))
            finish()
        }


    }
}
