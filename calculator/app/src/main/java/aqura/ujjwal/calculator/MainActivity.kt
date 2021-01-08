package aqura.ujjwal.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.NumberFormatException
//import kotlinx.android.synthetic.main.activity_main.*;

private const val STATE_PENDING_OPERATION= "PendingOperation"
private const val STATE_OPERAND1= "Operand1"
private const val STATE_OPERAND1_STORED= "Operand1_Stored"

class MainActivity : AppCompatActivity() {
    //lateinit can only be used with var
    private lateinit var result: EditText
    private lateinit var newNumber: EditText
    //lazy is by default thread safe but since it is only one thread we remove the extra overhead
    private val operation by lazy(LazyThreadSafetyMode.NONE) { findViewById<TextView>(R.id.operation) }

    //variable to hold operand
    private var operand1:Double? = null
    private var operand2:Double= 0.0
    private var pendingOperation = "="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result= findViewById(R.id.result)
        newNumber= findViewById(R.id.newNumber)

        //Data input button
        val button0:Button = findViewById(R.id.button0)
        val button1:Button = findViewById(R.id.button1)
        val button2:Button = findViewById(R.id.button2)
        val button3:Button = findViewById(R.id.button3)
        val button4:Button = findViewById(R.id.button4)
        val button5:Button = findViewById(R.id.button5)
        val button6:Button = findViewById(R.id.button6)
        val button7:Button = findViewById(R.id.button7)
        val button8:Button = findViewById(R.id.button8)
        val button9:Button = findViewById(R.id.button9)
        val button_decimal:Button = findViewById(R.id.button_decimal)

        //Operation Buttons
        val button_add = findViewById<Button>(R.id.button_add)
        val button_divide= findViewById<Button>(R.id.button_divide)
        val button_equal = findViewById<Button>(R.id.button_equal)
        val button_modulo= findViewById<Button>(R.id.button_modulo)
        val button_mul = findViewById<Button>(R.id.button_mul)
        val button_sub:Button = findViewById(R.id.button_sub)
        val button_Clear= findViewById<Button>(R.id.button_Clear)

        val listener = View.OnClickListener { v->
            val b= v as Button
            newNumber.append(b.text)
//
        }
        button0.setOnClickListener(listener)
        button1.setOnClickListener(listener)
        button2.setOnClickListener(listener)
        button3.setOnClickListener(listener)
        button4.setOnClickListener(listener)
        button5.setOnClickListener(listener)
        button6.setOnClickListener(listener)
        button7.setOnClickListener(listener)
        button8.setOnClickListener(listener)
        button9.setOnClickListener(listener)
        button_decimal.setOnClickListener(listener)


        fun performOperation(value: Double, operation: String){
            if(operand1 == null){
                operand1= value
            } else {
                operand2 = value

                if (pendingOperation == "=") {
                    pendingOperation = operation
                }
                when (pendingOperation) {
                    "=" -> operand1 = operand2
                    "+" -> operand1 = operand1!! + operand2
                    "-" -> operand1 = operand1!! - operand2
                    "X" -> operand1 = operand1!! * operand2
                    "/" -> operand1 = if (operand2 == 0.0) {
                        Double.NaN
                    } else {
                        operand1!! / operand2
                    }
                    "%" -> operand1 = operand1!! % operand2
                }
            }
            result.setText(operand1.toString())
//            newNumber.append(operand1.toString())
            newNumber.setText("")
        }

        val opListener= View.OnClickListener { v->
            val op= (v as Button).text.toString()
            try {
                val value= newNumber.text.toString().toDouble()
                performOperation(value,op)
            }catch (e:NumberFormatException){
                newNumber.setText("")
            }
            pendingOperation=op
            operation.text= pendingOperation
        }

        val negListener = View.OnClickListener { v->
            val value= newNumber.text.toString()
            if(value.isEmpty()){
                newNumber.setText("-")
            }
            else{
                try {
                    var newVal = value.toDouble()
                    newVal*=-1
                    newNumber.setText(newVal.toString())
                }catch (e: NumberFormatException){
                    newNumber.setText("")
                }
            }
        }

        button_add.setOnClickListener(opListener)
        button_sub.setOnClickListener(opListener)
        button_mul.setOnClickListener(opListener)
        button_divide.setOnClickListener(opListener)
        button_modulo.setOnClickListener(opListener)
        button_equal.setOnClickListener(opListener)
        neg.setOnClickListener(negListener)
        button_Clear.setOnClickListener({v->
            result.setText("")
            operation.setText("")
            newNumber.setText("")
            operand1=null
            operand2=0.0
        })



    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        if(operand1!=null){
            outState.putDouble(STATE_OPERAND1,operand1!!)
            outState.putBoolean(STATE_OPERAND1_STORED,true)

        }
        outState.putString(STATE_PENDING_OPERATION,pendingOperation)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        operand1 = if(savedInstanceState.getBoolean(STATE_OPERAND1_STORED,false )){
            savedInstanceState.getDouble(STATE_OPERAND1)
        }else{
            null
        }
        pendingOperation= savedInstanceState.getString(STATE_PENDING_OPERATION).toString()
        operation.text= pendingOperation
    }
}