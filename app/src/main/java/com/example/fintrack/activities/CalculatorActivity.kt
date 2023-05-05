package com.example.fintrack.activities
import com.example.fintrack.R
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import org.mariuszgromada.math.mxparser.Expression

class CalculatorActivity : AppCompatActivity() {
    private lateinit var tvInput: TextView
    private lateinit var tvOutput: TextView
    private lateinit var btnClear: Button
    private lateinit var btnLeftB: Button
    private lateinit var btnRightB: Button
    private lateinit var btnDivide: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btnMultiply: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btnSubtract: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button
    private lateinit var btnAdd: Button
    private lateinit var btn0: Button
    private lateinit var btnDecimal: Button
    private lateinit var btnEquals: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        tvInput = findViewById(R.id.tvInput)
        tvOutput = findViewById(R.id.tvOutput)
        btnClear = findViewById(R.id.btnClear)
        btnLeftB = findViewById(R.id.btnLeftB)
        btnRightB = findViewById(R.id.btnRightB)
        btnDivide = findViewById(R.id.btnDivide)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btnMultiply = findViewById(R.id.btnMultiply)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btnSubtract = findViewById(R.id.btnMinus)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)
        btnAdd = findViewById(R.id.btnPlus)
        btn0 = findViewById(R.id.btn0)
        btnDecimal = findViewById(R.id.btnDot)
        btnEquals = findViewById(R.id.btnEqual)

        //Number listeners
//        btn00.setOnClickListener { appendOnClick(true, "00") }
        btn0.setOnClickListener { appendOnClick(true, "0") }
        btn1.setOnClickListener { appendOnClick(true, "1") }
        btn2.setOnClickListener { appendOnClick(true, "2") }
        btn3.setOnClickListener { appendOnClick(true, "3") }
        btn4.setOnClickListener { appendOnClick(true, "4") }
        btn5.setOnClickListener { appendOnClick(true, "5") }
        btn6.setOnClickListener { appendOnClick(true, "6") }
        btn7.setOnClickListener { appendOnClick(true, "7") }
        btn8.setOnClickListener { appendOnClick(true, "8") }
        btn9.setOnClickListener { appendOnClick(true, "9") }
        btnDecimal.setOnClickListener { appendOnClick(true, ".") }


        //Operator Listeners
        btnAdd.setOnClickListener { appendOnClick(false, "+") }
        btnSubtract.setOnClickListener { appendOnClick(false, "-") }
        btnMultiply.setOnClickListener { appendOnClick(false, "*") }
        btnDivide.setOnClickListener { appendOnClick(false, "/") }
        btnLeftB.setOnClickListener { appendOnClick(false, "(") }
        btnRightB.setOnClickListener { appendOnClick(false, ")") }


        btnClear.setOnClickListener {
            clear()
        }

        btnEquals.setOnClickListener {
            calculate()
        }


    }

    //now create methods

    private fun appendOnClick(clear: Boolean, string: String) {

        if (clear) {
            tvOutput.text = ""
            tvInput.append(string)
        } else {
            tvInput.append(tvOutput.text)
            tvInput.append(string)
            tvOutput.text = ""
        }
    }

    private fun clear() {
        tvInput.text = ""
        tvOutput.text = ""

    }

    private fun calculate() {
        try {
            val input = Expression(tvInput.text.toString())
            val output = input.calculate()
            val longOutput = output.toLong()
            if (output == longOutput.toDouble()) {
                tvOutput.text = longOutput.toString()
            } else {
                tvOutput.text = output.toString()
            }
        } catch (e: Exception) {
            Toast.makeText(this@CalculatorActivity, e.message, Toast.LENGTH_LONG).show()
        }
    }


}
