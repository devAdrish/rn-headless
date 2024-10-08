package com.purevpn

import android.os.Build
import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.ReadableArray
import android.widget.TextView
import android.app.Activity


class PurevpnModule(reactContext: ReactApplicationContext) :
        ReactContextBaseJavaModule(reactContext) {

  override fun getName(): String {
    return NAME
  }

  @ReactMethod
  fun getDeviceModel(promise: Promise) {
    val manufacturer: String = Build.MANUFACTURER
    val model: String = Build.MODEL
    promise.resolve("$manufacturer $model")
  }

  @ReactMethod
    fun onData(action: String) {
        Log.d("PurevpnModule", "Action: $action")

        // Get the current activity
        val currentActivity = reactApplicationContext.currentActivity as? Activity

        currentActivity?.runOnUiThread {
            // Dynamically find the ID with explicit type
            val textViewId: Int = currentActivity.resources.getIdentifier(
                "textView", 
                "id", 
                currentActivity.packageName
            )
            // Explicitly specifying the type of TextView
            val textView: TextView? = currentActivity.findViewById<TextView>(textViewId)
            textView?.text = "Action: $action"
        }
    }

  companion object {
    const val NAME = "PureVPN"
  }
}
