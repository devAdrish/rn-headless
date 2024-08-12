package com.purevpn.headlessrn

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.modules.core.DeviceEventManagerModule
import android.util.Log

class RNEmitterModule(private val reactContext: ReactApplicationContext) :
        ReactContextBaseJavaModule(reactContext) {
    override fun getName(): String {
        return "RNEmitterModule"
    }

    @ReactMethod
    fun triggerAction(name: String, payload: Any?) {
        Log.d("JS EVENT", "Emitting event.")
        reactContext
                .getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
                .emit(name, payload)
    }

    fun emit(name: String, payload: Any?) {
        val context = reactContext.currentActivity?.applicationContext ?: return
        val module =
                (context as ReactApplicationContext).getNativeModule(RNEmitterModule::class.java)
        module?.triggerAction(name, payload)
    }
}
