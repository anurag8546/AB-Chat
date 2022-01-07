package com.example.abchatapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class SignUp : AppCompatActivity() {


    private lateinit var entername: EditText
    private lateinit var entermail: EditText
    private lateinit var enterpass: EditText
    private lateinit var confirmpass: EditText
    private lateinit var register: Button
    private lateinit var mAuth: FirebaseAuth
    private lateinit var mDbRef: DatabaseReference




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        mAuth=FirebaseAuth.getInstance()

        entername=findViewById(R.id.signupname)
        entermail=findViewById(R.id.signupemail)
        enterpass=findViewById(R.id.signuppass)
        confirmpass=findViewById(R.id.signupconfirmpass)
        register=findViewById(R.id.register)


        register=findViewById(R.id.register)
        register.setOnClickListener {
            val name = entername.text.toString()
            val email = entermail.text.toString()
            val password = enterpass.text.toString()

            signUp(name,email,password)
        }
    }

    private fun signUp(name: String,email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    addUserToDatabase(name,email,mAuth.currentUser?.uid!!)
                    Toast.makeText(this@SignUp,"Registered!",Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@SignUp,MainActivity::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    // If sign in fails, display a message to the user.
                    Toast.makeText(this@SignUp,"Authentication Failed!",Toast.LENGTH_SHORT).show()
                }
            }
    }


    private fun addUserToDatabase(name: String,email: String,uid: String){
        mDbRef=FirebaseDatabase.getInstance().reference
        mDbRef.child("user").child(uid).setValue(User(name,email,uid))

    }
}