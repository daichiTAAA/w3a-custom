package com.folios.plugin.w3acustom

import android.app.Activity
import android.util.Log

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.getcapacitor.PluginCall
import com.google.gson.Gson
import com.web3auth.core.Web3Auth
import com.web3auth.core.types.LoginParams
import com.web3auth.core.types.Provider
import com.web3auth.core.types.Web3AuthOptions
import com.web3auth.core.types.Web3AuthResponse
import java8.util.concurrent.CompletableFuture

class MyObserver : DefaultLifecycleObserver {
    override fun onCreate(owner: LifecycleOwner) {
    }

    override fun onResume(owner: LifecycleOwner) {
    }

    override fun onPause(owner: LifecycleOwner) {
    }

    override fun onStart(owner: LifecycleOwner) {
    }

    override fun onStop(owner: LifecycleOwner) {
    }
}

class W3aCustom : AppCompatActivity() {
    private lateinit var web3Auth: Web3Auth

    private val gson = Gson()

    fun echo(): String {
        val value = "Hello! Android."
        Log.i("Echo", value)
        return value
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        lifecycle.addObserver(MyObserver())

        web3Auth = Web3Auth(
            Web3AuthOptions(
                context = this,
                clientId = "BHV75ODX9QpTBg3yxoQ0MNnTbQ4ksELPEDvkQN_KUAWdFkNdqgzmUZc2p48W1prowdNugWT91_4ydRFFBwap1dE",
                network = Web3Auth.Network.TESTNET, // MAINNET, TESTNET or CYAN
                redirectUrl = Uri.parse("com.folios.app://auth"),
            )
        )

        //         Handle user signing in when app is not alive
        web3Auth.setResultUrl(intent?.data)

        // Setup UI and event handlers
        val signInButton = findViewById<Button>(R.id.signInButton)
        signInButton.setOnClickListener { signIn() }

        val signOutButton = findViewById<Button>(R.id.signOutButton)
        signOutButton.setOnClickListener { signOut() }

        reRender(Web3AuthResponse())
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        // Handle user signing in when app is active
        web3Auth.setResultUrl(intent?.data)

    }

    private fun signIn() {
        web3Auth = Web3Auth(
            Web3AuthOptions(
                context = this,
                clientId = "BHV75ODX9QpTBg3yxoQ0MNnTbQ4ksELPEDvkQN_KUAWdFkNdqgzmUZc2p48W1prowdNugWT91_4ydRFFBwap1dE",
                network = Web3Auth.Network.TESTNET, // MAINNET, TESTNET or CYAN
                redirectUrl = Uri.parse("com.folios.app://auth"),
            )
        )

        val selectedLoginProvider = Provider.GOOGLE   // Can be GOOGLE, FACEBOOK, TWITCH etc.
        val loginCompletableFuture: CompletableFuture<Web3AuthResponse> =
            web3Auth.login(
                LoginParams(
                    selectedLoginProvider
                )
            )

        loginCompletableFuture.whenComplete { loginResponse, error ->
            if (error == null) {
                reRender(loginResponse)
            } else {
                Log.d("MainActivity_Web3Auth", error.message ?: "Something went wrong")
            }
        }
    }

    private fun signOut() {
        val logoutCompletableFuture = web3Auth.logout()
        logoutCompletableFuture.whenComplete { _, error ->
            if (error == null) {
                reRender(Web3AuthResponse())
            } else {
                Log.d("MainActivity_Web3Auth", error.message ?: "Something went wrong")
            }
        }
        recreate()
    }

    private fun reRender(web3AuthResponse: Web3AuthResponse) {
        val contentTextView = findViewById<TextView>(R.id.contentTextView)
        val signInButton = findViewById<Button>(R.id.signInButton)
        val signOutButton = findViewById<Button>(R.id.signOutButton)

        val key = web3AuthResponse.privKey
        val userInfo = web3AuthResponse.userInfo

        if (key is String && key.isNotEmpty()) {
            contentTextView.text = gson.toJson(web3AuthResponse)
            contentTextView.visibility = View.VISIBLE
            signInButton.visibility = View.GONE
            signOutButton.visibility = View.VISIBLE
        } else {
            contentTextView.text = getString(R.string.not_logged_in)
            contentTextView.visibility = View.GONE
            signInButton.visibility = View.VISIBLE
            signOutButton.visibility = View.GONE
        }
    }



    fun login() {
        lifecycle.addObserver(MyObserver())
        signIn()
    }


}