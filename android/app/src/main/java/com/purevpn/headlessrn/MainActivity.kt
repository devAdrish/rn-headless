// package com.purevpn.headlessrn

// import com.facebook.react.ReactActivity
// import com.facebook.react.ReactActivityDelegate
// import com.facebook.react.defaults.DefaultNewArchitectureEntryPoint.fabricEnabled
// import com.facebook.react.defaults.DefaultReactActivityDelegate

// class MainActivity : ReactActivity() {

//   /**
//    * Returns the name of the main component registered from JavaScript. This is used to schedule
//    * rendering of the component.
//    */
//   override fun getMainComponentName(): String = "PureVPN App"

//   /**
//    * Returns the instance of the [ReactActivityDelegate]. We use [DefaultReactActivityDelegate]
//    * which allows you to enable New Architecture with a single boolean flags [fabricEnabled]
//    */
//   override fun createReactActivityDelegate(): ReactActivityDelegate =
//       DefaultReactActivityDelegate(this, mainComponentName, fabricEnabled)
// }

package com.purevpn.headlessrn

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.BuildConfig
import com.facebook.react.PackageList
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.facebook.react.common.LifecycleState
import com.facebook.react.modules.core.DeviceEventManagerModule

class MainActivity : AppCompatActivity() {
    private lateinit var mReactRootView: ReactRootView
    private lateinit var mReactInstanceManager: ReactInstanceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mReactRootView = ReactRootView(this)

        val packages: List<ReactPackage> = PackageList(application).packages.apply {}

        mReactInstanceManager =
                ReactInstanceManager.builder()
                        .setApplication(application)
                        .setCurrentActivity(this)
                        .setBundleAssetName("index.android.bundle")
                        .setJSMainModulePath("index.js")
                        .addPackages(packages)
                        .setUseDeveloperSupport(BuildConfig.DEBUG)
                        .setInitialLifecycleState(LifecycleState.RESUMED)
                        .build()

        mReactRootView.startReactApplication(mReactInstanceManager, "PureVPN", null)

        val reactLayout = findViewById<LinearLayout>(R.id.reactlayout)
        reactLayout.addView(mReactRootView)

        val button = findViewById<Button>(R.id.button_fetch_countries)
        button.setOnClickListener() {
            Log.d("JS EVENT", "Button clicked, emitting event.")

            val context = mReactInstanceManager.currentReactContext
            if (context != null) {
                context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
                        .emit("fetchCountries", null)
                Log.d("JS EVENT", "Event emitted.")
            } else {
                Log.d("JS EVENT", "React Context is null.")
            }
        }
    }
}
