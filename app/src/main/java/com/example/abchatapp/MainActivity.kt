package com.example.abchatapp



import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var entermail: EditText
    private lateinit var enterpass: EditText
    private lateinit var loginbutton: Button
    private lateinit var signupbutton: Button
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private lateinit var showpass: Switch

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        try {
            this.supportActionBar!!.hide()
        } catch (e: NullPointerException) {
        }

        setContentView(R.layout.activity_main)

        mAuth= FirebaseAuth.getInstance()

        entermail=findViewById(R.id.textemail)
        enterpass=findViewById(R.id.textpass)
        loginbutton=findViewById(R.id.login)
        signupbutton=findViewById(R.id.signup)
        showpass=findViewById(R.id.showpass)


        showpass.setOnClickListener{
            if(showpass.isChecked){
                enterpass.transformationMethod = HideReturnsTransformationMethod.getInstance()

            } else{
                enterpass.transformationMethod = PasswordTransformationMethod.getInstance()
            }
        }
        signupbutton.setOnClickListener {
            val intent = Intent(this, SignUp::class.java)
            startActivity(intent)
        }

        loginbutton.setOnClickListener {
            val email = entermail.text.toString()
            val password = enterpass.text.toString()
            login(email,password)
        }
    }

    private fun login(email: String, password: String) {
        mAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent=Intent(this@MainActivity,LogIn::class.java)
                    finish()
                    startActivity(intent)
                } else {
                    Toast.makeText(this@MainActivity,"User does not found!!",Toast.LENGTH_SHORT ).show()

                }
            }
    }
}
