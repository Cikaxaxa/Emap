package com.example.signup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class Login : AppCompatActivity() {

    private lateinit var btnlogin : Button
    private lateinit var etusername : EditText
    private lateinit var etpassword : EditText
    private lateinit var db : DbHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        etusername = findViewById(R.id.editTextUsername)
        etpassword = findViewById(R.id.editTextPassword)
        btnlogin = findViewById(R.id.btnlogin)
        db = DbHelper(this)


        btnlogin.setOnClickListener{
            val tusername = etusername.text.toString()
            val tpassword = etpassword.text.toString()

            if (TextUtils.isEmpty(tusername) || TextUtils.isEmpty(tpassword)){
                Toast.makeText(this,"Add username, Password ", Toast.LENGTH_SHORT).show()
            }
            else{
                val checkuser = db.checkuserpass(tusername,tpassword)
                if(checkuser==true){
                    Toast.makeText(this,"Login Successfully", Toast.LENGTH_SHORT).show()
                    val intent= Intent(applicationContext, Map::class.java)
                    startActivity(intent)
                }
                else{
                    Toast.makeText(this,"Wrong Username or Password", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}