package com.example.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class SignUp : AppCompatActivity() {

    private lateinit var etusername: EditText
    private lateinit var etpassword: EditText
    private lateinit var etpassword2: EditText
    private lateinit var etemail: EditText
    private lateinit var btnsignup: Button
    private lateinit var db: DbHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        etusername = findViewById(R.id.etusername)
        etpassword = findViewById(R.id.etpassword)
        etpassword2 = findViewById(R.id.etpassword2)
        etemail = findViewById(R.id.etemail)
        btnsignup = findViewById(R.id.btnsignup)
        db = DbHelper(this)
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]"


        btnsignup.setOnClickListener {
            val tusername = etusername.text.toString()
            val tpassword = etpassword.text.toString()
            val tpassword2 = etpassword2.text.toString()
            val temail = etemail.text.toString()
            val savedata = db.insertdata(tusername, tpassword)

            if (TextUtils.isEmpty(tusername) || TextUtils.isEmpty(tpassword) || TextUtils.isEmpty(tpassword2) || TextUtils.isEmpty(temail) || Patterns.EMAIL_ADDRESS.matcher(temail).matches()){
                Toast.makeText(this, "Add username, Password & Confirm Password", Toast.LENGTH_SHORT).show()
            }
            else {
                    if (tpassword == tpassword2) {
                        if (savedata == true) {
                            Toast.makeText(this, "Sign Up Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(applicationContext, Login::class.java)
                            startActivity(intent)
                        } else {
                            Toast.makeText(this, "User Existed", Toast.LENGTH_SHORT).show()
                        }

                    } else {
                        Toast.makeText(this, "Password Not Match", Toast.LENGTH_SHORT).show()
                    }
            }

        }

    }

}