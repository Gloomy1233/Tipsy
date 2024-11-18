package com.example.composeApp.commons

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import com.example.composeApp.utility.AppConfig

class PreferenceHelper(mContext: Context) {

    // mContext.getSharedPreferences(AppConfig.PREFERENCE_DATA.PREF_FILE, Context.MODE_PRIVATE)
    private val IS_INTRO_EXECUTED = "IS_INTRO_EXECUTED"
    private val TABLE_HEADER = "TABLE_HEADER"
    private val SORT_FILTER = "SORT_FILTER"

    private var editor: SharedPreferences.Editor? = null
    private val mPreference: SharedPreferences =
        mContext.getSharedPreferences(AppConfig.PREFERENCE_DATA.PREF_FILE, Context.MODE_PRIVATE)


    fun clearAllPrefs() {
        if (mPreference.all != null) {
            for (preference in mPreference.all?.entries!!) {
                if (!preference.key.equals(IS_INTRO_EXECUTED, true))
                    mPreference.edit().remove(preference.key).apply()
            }
        } else {
            mPreference.edit().clear().apply()
        }
    }

    fun clearPreference(string: String) {
        mPreference.edit().remove(string).apply()
    }

    fun isKeyExist(key: String): Boolean {
        if (mPreference.contains(key)) {
            return true
        }
        return false
    }

    @SuppressLint("CommitPrefEdits")
    fun initPref() {
        editor = mPreference.edit()
    }

    fun applyPref() {
        editor!!.apply()
    }

    fun saveStringPref(key: String, value: String) {
        initPref()
        editor!!.putString(key, value)
        applyPref()
    }

    fun loadStringPref(key: String, defaultValue: String): String? {
        return mPreference.getString(key, defaultValue)
    }

    fun loadStringPref(key: String): String? {
        return mPreference.getString(key, "")
    }

    fun saveIntPref(key: String, value: Int) {
        initPref()
        editor!!.putInt(key, value)
        applyPref()
    }

    fun saveLongPref(key: String, value: Long) {
        initPref()
        editor!!.putLong(key, value)
        applyPref()
    }

    fun loadLongPref(key: String, defaultValue: Long): Long {
        return try {
            mPreference.getLong(key, defaultValue)
        } catch (e: ClassCastException) {
            mPreference.getInt(key, defaultValue.toInt()).toLong()
        }
    }

    fun loadLongPref(key: String): Long {
        return mPreference.getLong(key, 0)
    }

    fun loadIntPref(key: String, defaultValue: Int): Int {
        return mPreference.getInt(key, defaultValue)
    }

    fun loadIntPref(key: String): Int {
        return mPreference.getInt(key, 0)
    }

    fun saveBooleanPref(key: String, value: Boolean) {
        initPref()
        editor!!.putBoolean(key, value)
        applyPref()
    }

    fun loadBooleanPref(key: String, defaultValue: Boolean): Boolean {
        return mPreference.getBoolean(key, defaultValue)
    }

    fun isIntroExecuted(): Boolean {
        return mPreference.getBoolean(IS_INTRO_EXECUTED, false)
    }

    fun setIntroExecuted(isIntroExecuted: Boolean?) {
        initPref()
        editor!!.putBoolean(IS_INTRO_EXECUTED, isIntroExecuted!!)
        applyPref()
    }

    fun saveSetPref(key: String, value: Set<String>) {
        initPref()
        editor!!.putStringSet(key, value)
        applyPref()
    }

    fun loadSetPref(key: String, value: Set<String>): Set<String>? {
        return mPreference.getStringSet(key, value)
    }

    fun saveLoginToken(key: String, value: String) {
        initPref()
        editor!!.putString(key, value).apply()

    }

    fun takeLoginToken(key: String): String? {
        return mPreference.getString(key, "")!!
    }


}