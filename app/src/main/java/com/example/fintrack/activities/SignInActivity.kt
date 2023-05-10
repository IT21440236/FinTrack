package com.example.fintrack.activities

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.fintrack.R
import com.google.firebase.auth.FirebaseAuth

class SignInActivity : AppCompatActivity() {
    private lateinit var etUserEmail: EditText
    private lateinit var etPass: EditText
    private lateinit var tvRegister:TextView

    private lateinit var btnSignIn: Button
    private lateinit var firebaseAuth: FirebaseAuth


//    private val CHANNEL_ID = "channel_id_example_01"
//    private val notificationId = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        etUserEmail = findViewById(R.id.emailEt)
        etPass= findViewById(R.id.passET)
        tvRegister= findViewById(R.id.textView)
        btnSignIn = findViewById(R.id.button)

        firebaseAuth = FirebaseAuth.getInstance()

//        createNotificationChannel()

        val currentuser = firebaseAuth.currentUser
        if(currentuser != null){
            startActivity(Intent(this@SignInActivity, UserActivity::class.java))
            finish()
        }

        tvRegister.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }

        btnSignIn.setOnClickListener {
            val email = etUserEmail.text.toString()
            val pass = etPass.text.toString()

            if(email.isNotEmpty() && pass.isNotEmpty()){
                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
//                        sendNotification()
                        val intent = Intent(this@SignInActivity, UserActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()

                    }
                }
            }else{
                Toast.makeText(this, "Empty Fields Are not Allowed !!", Toast.LENGTH_SHORT).show()
            }


        }


    }

    //create notification channel
//    private fun createNotificationChannel(){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ){
//            val name = "Notification Title"
//            val descriptionText = "Notification Description"
//            val importance = NotificationManager.IMPORTANCE_DEFAULT
//            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
//                description = descriptionText
//            }
//
//            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//    }

    //create function for send notification
//    private fun sendNotification(){
//        val intent = Intent(this, UserActivity::class.java).apply {
//            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//        }
//
//        val pendingIntent: PendingIntent = PendingIntent.getActivity(this, 0, intent, 0)
//
//        val bitmap = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.logofintrack)
//        val bitmapLargeIcon = BitmapFactory.decodeResource(applicationContext.resources, R.drawable.logofintrack)
//
//
//        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
//            .setSmallIcon(R.drawable.ic_launcher_foreground)
//            .setContentTitle("FinTrack")
//            .setContentText("Login Successfully")
//            .setLargeIcon(bitmapLargeIcon)
//            .setStyle(NotificationCompat.BigPictureStyle().bigPicture(bitmap))
//            .setContentIntent(pendingIntent)
//            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//
//        with(NotificationManagerCompat.from(this)){
//            notify(notificationId, builder.build())
//        }
//    }








}