package com.purevpn

import android.os.Build
import android.util.Log
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.ReadableArray

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
  fun onData(action: String, data: Any?) {
    Log.d("PurevpnModule", "Action: $action")
  }

  companion object {
    const val NAME = "PureVPN"
  }
}
