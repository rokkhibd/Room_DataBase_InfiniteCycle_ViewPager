package com.rokkhi.room_database_infinitecycle_viewpager

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_user__word__input_.*
import java.util.*
import kotlin.collections.HashMap

class User_Word_Input_Activity : AppCompatActivity() {

    lateinit var db:FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user__word__input_)

        db=FirebaseFirestore.getInstance()

        submit_button.setOnClickListener {
            saveDataToFirestore()
        }


    }

    private fun saveDataToFirestore() {
        var word_input=word_input_editText.text.toString()
        var meaning_input=meaning_input_editText.text.toString()
        var synonym_input=synonym_input_editText.text.toString()
        var example_input=example_input_editText.text.toString()

        val user_input= HashMap<String, Any>()
        user_input.put("word", word_input)
        user_input.put("meaning",meaning_input)
        user_input.put("synonym",synonym_input)
        user_input.put("example",example_input)

        db.collection("Words").document(word_input)
            .set(user_input)
            .addOnSuccessListener {
                Toast.makeText(this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show()

                word_input_editText.text=null

            }.addOnFailureListener {
                e->Toast.makeText(this,"Error"+e.message,Toast.LENGTH_SHORT).show()
            }


    }


}
