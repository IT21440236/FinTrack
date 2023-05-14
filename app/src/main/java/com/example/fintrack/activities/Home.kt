package com.example.fintrack.activities


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fintrack.R
import android.content.Intent
import android.widget.ImageView



        class Home : AppCompatActivity() {

            private lateinit var ImgVWalletPg6: ImageView


            override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                setContentView(R.layout.activity_home)


//         ImgVWalletPg6 = findViewById(R.id.ImgVWalletPg6)


//         ImgVWalletPg6.setOnClickListener {
//             val intent = Intent(this, WalletActivity::class.java)
//             startActivity(intent)
//         }

                val ImgVHomePg6 = findViewById<ImageView>(R.id.ImgVHomePg6)


                ImgVHomePg6.setOnClickListener {
                    val intent = Intent(this, Home::class.java)
                    startActivity(intent)
                }

                val ImgVWalletPg6 = findViewById<ImageView>(R.id.ImgVWalletPg6)


                ImgVWalletPg6.setOnClickListener {
                    val intent = Intent(this, WalletActivity::class.java)
                    startActivity(intent)
                }

                val ImgVUserPg6 = findViewById<ImageView>(R.id.ImgVUserPg6)


                ImgVUserPg6.setOnClickListener {
                    val intent = Intent(this, UserActivity::class.java)
                    startActivity(intent)
                }

                val ImgVIncomePg6 = findViewById<ImageView>(R.id.ImgVIncomePg6)


                ImgVIncomePg6.setOnClickListener {
                    val intent = Intent(this, Income::class.java)
                    startActivity(intent)
                }
                val ImgVExpenditurePg6 = findViewById<ImageView>(R.id.ImgVExpenditurePg6)


                ImgVExpenditurePg6.setOnClickListener {
                    val intent = Intent(this, Expense::class.java)
                    startActivity(intent)
                }
                val ImgVKidPg6 = findViewById<ImageView>(R.id.ImgVKidPg6)


                ImgVKidPg6.setOnClickListener {
                    val intent = Intent(this, AddKid::class.java)
                    startActivity(intent)
                }
                val ImgVSetupPg6 = findViewById<ImageView>(R.id.ImgVSetupPg6)


                ImgVSetupPg6.setOnClickListener {
                    val intent = Intent(this, AddUser::class.java)
                    startActivity(intent)
                }
                val ImgVCalculatorPg6 = findViewById<ImageView>(R.id.ImgVCalculatorPg6)


                ImgVCalculatorPg6.setOnClickListener {
                    val intent = Intent(this, CalculatorActivity::class.java)
                    startActivity(intent)
                }
                val imageView3 = findViewById<ImageView>(R.id.imageView3)


                imageView3.setOnClickListener {
                    val intent = Intent(this, GoalActivity::class.java)
                    startActivity(intent)
                }
                val ImgVSubscriptionsPg6 = findViewById<ImageView>(R.id.ImgVSubscriptionsPg6)


        ImgVSubscriptionsPg6.setOnClickListener{
            val intent= Intent(this, KidsLogin::class.java)
            startActivity(intent)
        }
                val ImgVAnalyticsPg6 = findViewById<ImageView>(R.id.ImgVAnalyticsPg6)


        ImgVAnalyticsPg6.setOnClickListener{
            val intent= Intent(this, NewBudget::class.java)
            startActivity(intent)
        }

            }

        }






