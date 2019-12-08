package com.example.exercise1

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonCalculate.setOnClickListener { calculate(it) }
        buttonReset.setOnClickListener { reset(it) }
    }

    private fun reset(it: View) {
        editTextCarPrice.text.clear()
        editTextLoanPeriod.text.clear()
        editTextDownPayment.text.clear()
        editTextInterestRate.text.clear()
        textViewLoan.text= getText(R.string.loan).toString()
        textViewInterest.text=getText(R.string.interest).toString()
        textViewMonthlyRepayment.text=getText(R.string.monthly_repayment).toString()
        hideKeyboard(it)
    }

    private fun calculate(view: View) {
        if(editTextCarPrice.text.length==0 || editTextDownPayment.text.length==0 || editTextLoanPeriod.text.length==0 || editTextInterestRate.text.length==0){
            Dialog()
        }else{
            var carLoan = editTextCarPrice.text.toString().toInt()-editTextDownPayment.text.toString().toInt()
            var interest = carLoan*(editTextInterestRate.text.toString().toDouble()/100)*editTextLoanPeriod.text.toString().toInt()
            var repayment = (carLoan+interest)/editTextLoanPeriod.text.toString().toInt()/12
            textViewLoan.text= getText(R.string.loan).toString()+" "+carLoan
            textViewInterest.text=getText(R.string.interest).toString()+" "+interest
            textViewMonthlyRepayment.text=getText(R.string.monthly_repayment).toString()+" "+repayment
        }
        hideKeyboard(view);
    }

    private fun Dialog(){
        val builder = AlertDialog.Builder(this);
        builder.setTitle("Information")
            .setMessage("All Information is required")
            .setPositiveButton("OK",{ dialogInterface: DialogInterface, i: Int -> })

        builder.show()
    }


    fun hideKeyboard(view: View) {
        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }
}
