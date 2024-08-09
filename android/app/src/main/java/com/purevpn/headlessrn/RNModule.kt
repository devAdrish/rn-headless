package com.purevpn.headlessrn
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import android.util.Log

class RNModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {
    override fun getName(): String {
        return "RNModule"
    }

    @ReactMethod
    fun onData() {
        Log.d("JS EVENT", "GOT DATA")
    }
}