package com.example.googlmapschacker

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.AccessToken
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.collections.Map

val callbackManager: CallbackManager = CallbackManager.Factory.create()
val accessToken = AccessToken.getCurrentAccessToken()


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        login_button.setReadPermissions("email");
        // Callback registration

        // Callback registration
        login_button.registerCallback(callbackManager, object : FacebookCallback<LoginResult?> {
            override fun onSuccess(loginResult: LoginResult?) {

            }

            override fun onCancel() {

            }

            override fun onError(exception: FacebookException) {
                // App code
            }
        })

        LoginManager.getInstance().registerCallback(callbackManager,
            object : FacebookCallback<LoginResult?> {
                override fun onSuccess(loginResult: LoginResult?) {
                    val intent = Intent(this@MainActivity, com.example.googlmapschacker.Map::class.java)
                    startActivity(intent)
                    finish()
                }

                override fun onCancel() {

                }

                override fun onError(exception: FacebookException) {
                    // App code
                }
            })

        if(accessToken != null && !accessToken.isExpired()){
            val intent = Intent(this@MainActivity, com.example.googlmapschacker.Map::class.java)
            startActivity(intent)
            finish()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callbackManager.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

}