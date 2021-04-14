package com.sayantanbanerjee.transactionmanagementapp.data

import android.content.SharedPreferences

// Implementation of the [PreferenceHelper] abstract class.
// This class implements all the methods to be called in usage of Shared Preference in the application.
// Unlike SQLite database which is used to hold large chunks of data,
// Shared Preference is used to store small data in the application.
class AppPreferenceHelper(private val sharedPreferences: SharedPreferences) : PreferenceHelper {

    // Fetching and Setting User Mobile Number
    override fun getUserMobileNumber(): String? {
        return sharedPreferences.getString(PREF_KEY_MOBILE_NUMBER, null)
    }
    override fun setUserMobileNumber(mobileNumber: String?) {
        sharedPreferences.edit().putString(PREF_KEY_MOBILE_NUMBER, mobileNumber).apply()
    }

    // Fetching and Setting User ISO Code
    override fun getUserISOCode(): String? {
        return sharedPreferences.getString(PREF_KEY_ISO_CODE, null)
    }
    override fun setUserISOCode(isoCode: String?) {
        sharedPreferences.edit().putString(PREF_KEY_ISO_CODE, isoCode).apply()
    }

    // Fetching anf Setting Authentication state
    override fun isUserAuthenticated(): Boolean {
        return sharedPreferences.getBoolean(PREF_KEY_IS_AUTHENTICATED, false)
    }
    override fun setUserAuthenticated(authStatus: Boolean) {
        sharedPreferences.edit().putBoolean(PREF_KEY_IS_AUTHENTICATED, authStatus).apply()
    }

    companion object {
        private const val PREF_KEY_MOBILE_NUMBER = "PREF_KEY_MOBILE_NUMBER"
        private const val PREF_KEY_ISO_CODE = "PREF_KEY_ISO_CODE"
        private const val PREF_KEY_IS_AUTHENTICATED = "PREF_KEY_IS_AUTHENTICATED"
    }
}
