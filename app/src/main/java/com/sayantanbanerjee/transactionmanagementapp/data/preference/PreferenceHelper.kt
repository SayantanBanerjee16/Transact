package com.sayantanbanerjee.transactionmanagementapp.data.preference

// Interface which defines all the methods to be called in usage of Shared Preference in the application.
interface PreferenceHelper {

    // user mobile - number
    fun getUserMobileNumber(): String?
    fun setUserMobileNumber(mobileNumber: String?)

    // user mobile ISO-Code
    fun getUserISOCode(): String?
    fun setUserISOCode(isoCode: String?)

    // user authentication status
    fun isUserAuthenticated(): Boolean
    fun setUserAuthenticated(authStatus: Boolean)

}
