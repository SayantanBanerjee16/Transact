@file:Suppress("DEPRECATION")

package com.sayantanbanerjee.transactionmanagementapp.presenter

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.AuthUI.IdpConfig.PhoneBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuth.AuthStateListener
import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber
import com.sayantanbanerjee.transactionmanagementapp.R
import com.sayantanbanerjee.transactionmanagementapp.data.AppPreferenceHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

// This activity is called from the [SplashActivity.kt].
// Here if user is not previously authenticated, then we authenticate the User using Firebase Auth.
// An OTP is sent to the user, and after successful verification, Data is stored in the shared preference.
// User is re-directed to the [HomeActivity.kt]

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {

    // Unique request code for Firebase Auth
    private val RC_SIGN_IN = 1

    // This variable is used to declare a generalized user-number
    private lateinit var ANONYMOUS: String

    private var mUserPhoneNumber: String? = null
    private lateinit var mFirebaseAuth: FirebaseAuth
    private var mAuthStateListener: AuthStateListener? = null

    @Inject
    lateinit var sharedPreferences: SharedPreferences

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
                // If user is authenticated, call the onSignInInitialize() method.
                mUserPhoneNumber = user.phoneNumber
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
    // First store the basic general details into shared preference, i.e, authentication done, mobile number and ISO Code.
    // After this re-direct him to the [HomeActivity.kt].
    private fun onSignedInInitialize(mobileNumber: String) {
        AppPreferenceHelper(sharedPreferences).setUserAuthenticated(true)
        AppPreferenceHelper(sharedPreferences).setUserMobileNumber(mobileNumber)
        val phoneUtil: PhoneNumberUtil = PhoneNumberUtil.getInstance()
        try {
            val numberProto: Phonenumber.PhoneNumber = phoneUtil.parse(mobileNumber, "IN")
            val countryCode: Int = numberProto.countryCode
            AppPreferenceHelper(sharedPreferences).setUserISOCode(countryCode.toString())
        } catch (e: NumberParseException) {
            System.err.println("NumberParseException was thrown: $e")
        }
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    // To clean up when signing up!
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
                // all ok
            } else if (resultCode == RESULT_CANCELED) {
                finish()
            }
        }
    }
}
