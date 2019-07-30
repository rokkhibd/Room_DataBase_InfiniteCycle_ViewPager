package com.rokkhi.room_database_infinitecycle_viewpager

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.view.PagerAdapter
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    var images:Array<Int> = arrayOf(R.drawable.pen_one,R.drawable.pen_two,R.drawable.pen_three)
    var adapter:PagerAdapter=MyAdapter(this,images)
    //var all_words:Array<String> = arrayOf("Sick","Exercise","Normal","Hard")
    //var adapter:PagerAdapter=New_Adapter(this,all_words)

    lateinit var mAuth:FirebaseAuth

    lateinit var mAuthListener:FirebaseAuth.AuthStateListener

    lateinit var providers:List<AuthUI.IdpConfig>
    val MY_REQUEST_CODE: Int=99

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        providers= Arrays.asList<AuthUI.IdpConfig>(
            AuthUI.IdpConfig.PhoneBuilder().build()
        )

        showSignInOption()
        //mAuth= FirebaseAuth.getInstance()
        cycle_viewPager.adapter=adapter

        floating_button.setOnClickListener {
            val intent=Intent(this,User_Word_Input_Activity::class.java)
            startActivity(intent)

            /*AuthUI.getInstance().signOut(this)
                .addOnCompleteListener {
                    floating_button.isEnabled=true
                    showSignInOption()
                }
                .addOnFailureListener {

                    e-> Toast.makeText(this,e.message,Toast.LENGTH_SHORT).show()
                }*/
        }




    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode==MY_REQUEST_CODE){
            val response= IdpResponse.fromResultIntent(data)
            if(resultCode== Activity.RESULT_OK){
                val user=FirebaseAuth.getInstance().currentUser

                floating_button.isEnabled=true
            }else{
                Toast.makeText(this,""+response!!.error!!.message,Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showSignInOption(){
        startActivityForResult(AuthUI.getInstance().createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .setTheme(R.style.MyTheme)
            .build(),MY_REQUEST_CODE
        )
    }
}
