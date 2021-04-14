package com.sayantanbanerjee.transactionmanagementapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.IdpConfig.PhoneBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber

// This activity is called from the [SplashActivity.kt].
// Here if user is not previously authenticated, then we authenticate the User using Firebase Auth.
// An OTP is sent to the user, and after successful verification, Data is stored in the shared preference.
// User is re-directed to the [HomeActivity.kt]
class AuthActivity : AppCompatActivity() {

    private val RC_SIGN_IN = 1
    private var mUserPhoneNumber: String? = null
    private lateinit var ANONYMOUS: String
    private lateinit var mFirebaseAuth: FirebaseAuth
    private var mAuthStateListener: AuthStateListener? = null

    override fun onResume() {
        super.onResume()
        mFirebaseAuth.addAuthStateListener(mAuthStateListener!!)
    }

    override fun onPause() {
        super.onPause()
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener!!)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        ANONYMOUS = getString(R.string.ANONYMOUS)
        mFirebaseAuth = FirebaseAuth.getInstance()


        // Authentication Listener to listen to change of authentication
        mAuthStateListener = AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                // If some user is already authenticated, store his phone number and then re-direct him to other task.
                mUserPhoneNumber = user.phoneNumber
                Log.i("#####PHONE NUMBER", mUserPhoneNumber.toString())
                onSignedInInitialize(mUserPhoneNumber.toString())
            } else {
                // Authenticate user using the AuthUI Phone Builder.
                onSignedOutCleanup()
                startActivityForResult(
                    AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setIsSmartLockEnabled(false)
                        .setAvailableProviders(
                            listOf(
                                PhoneBuilder().build()
                            )
                        )
                        .build(),
                    RC_SIGN_IN
                )
            }
        }

    }

    // Method called when user is successfully authenticated.
    private fun onSignedInInitialize(username: String) {
        val phoneUtil: PhoneNumberUtil = PhoneNumberUtil.getInstance()
        try {
            val numberProto: Phonenumber.PhoneNumber = phoneUtil.parse(username, "IN")
            val countryCode: Int = numberProto.countryCode
            Log.i("#####COUNTRY CODE", countryCode.toString())
        } catch (e: NumberParseException) {
            System.err.println("NumberParseException was thrown: $e")
        }
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    // To sign-out from the Application and detach the listener
    private fun onSignedOutCleanup() {
        mUserPhoneNumber = ANONYMOUS
        if (mAuthStateListener != null) {
            mFirebaseAuth.removeAuthStateListener(mAuthStateListener!!)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            if (resultCode == RESULT_OK) {
            } else if (resultCode == RESULT_CANCELED) {
                finish()
            }
        }
    }
}
