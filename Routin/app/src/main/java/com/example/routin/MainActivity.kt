package com.example.routin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.inflate
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.routin.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import java.util.Calendar.getInstance
import java.util.Currency.getInstance


class MainActivity : AppCompatActivity() {

    private lateinit var  binding:ActivityMainBinding
    private lateinit var  firebaseAuth: FirebaseAuth



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        firebaseAuth = FirebaseAuth.getInstance()

        binding.regBtn.setOnClickListener{
            val email = binding.emailid.text.toString()
            val password = binding.passwordEmail.text.toString()
            val confirmpassword = binding.retypePassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmpassword.isNotEmpty()){
                if (password == confirmpassword){
                    firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener{
                        if (it.isSuccessful){
                            val intent = Intent(this,login::class.java)
                            startActivity(intent)

                        }else{
                            Toast.makeText(this,it.exception.toString(),Toast.LENGTH_SHORT).show()
                        }
                    }
                }else{
                    Toast.makeText(this,"password is not same",Toast.LENGTH_SHORT).show()

                }
            }else{
                Toast.makeText(this,"fieid cannot to empty",Toast.LENGTH_SHORT).show()

            }
        }


    }
}