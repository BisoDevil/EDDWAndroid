package com.innovationcodes.eddw.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.innovationcodes.eddw.R
import com.innovationcodes.eddw.controller.ServerOperations
import kotlinx.android.synthetic.main.activity_checkout.*


class CheckoutActivity : AppCompatActivity() {

    private lateinit var operations: ServerOperations
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)
        operations = ServerOperations(this)
        designCardDetails()
    }


    private fun designCardDetails() {
        txtCardNumber.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val divided = s!!.replace("(.{4})".toRegex(), "$0 - ")

                if (s.toString().length > 15) {
                    tvCardNumber.text = divided.removeRange(divided.length - 2, divided.length)
                } else {
                    tvCardNumber.text = divided
                }

            }

        })
        txtCardHolderName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

                tvCardName.text = s
            }

        })
        txtExpDate.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {

            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val divided = s!!.replace("(.{2})".toRegex(), "$0/")
                when (start) {
                    0 -> {
                        if (s.toString().toIntOrNull() ?: 4 > 1) {
                            Toast.makeText(
                                this@CheckoutActivity,
                                "Wrong number",
                                Toast.LENGTH_SHORT
                            ).show()
                            return
                        }

                    }
                    1 -> {
                        if (s.toString().toIntOrNull() ?: 13 > 12) {
                            Toast.makeText(
                                this@CheckoutActivity,
                                "Wrong number",
                                Toast.LENGTH_SHORT
                            ).show()
                            return
                        }
                    }
                    2, 3 -> {
                        if ("${s.toString().first()}${s.toString()[1]}".toIntOrNull() ?: 0 > 12) {
                            Toast.makeText(
                                this@CheckoutActivity,
                                "Wrong number",
                                Toast.LENGTH_SHORT
                            ).show()
                            return
                        }
                    }
                }

                if (s.toString().length > 3) {

                    tvCardExpire.text = divided.removeRange(divided.length - 1, divided.length)
                } else {
                    tvCardExpire.text = divided
                }

            }

        })

    }
}
