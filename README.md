This is a POC for running [**React Native**](https://reactnative.dev) Headless.

This idea of having a headless react native app is that you can write all your business logic in JavaScript, and use
it for the all platforms. No UI should be made in React Native.

### Note that this project is setup for android only and is using Turbo Modules for 2-way communication.

# Getting Started

>**Note**: Make sure you have completed the [React Native - Environment Setup](https://reactnative.dev/docs/environment-setup) instructions till "Creating a new application" step, before proceeding.


## Step 1: Setup
Go to the App folder and run the following commands

```bash
yarn

yarn sync-tm

yarn bundle-android
```

These will install dependencies and add Turbo Module (android)

## Step 2: Run the app

Here you have two options:

### With Android Studio

1. Open the project with android studio. To do so, from the main screen of android studio, open the android folder located inside the App folder of your project. 

2. Let the gradle sync, or do it manually.

3. Run the app.

### With Terminal

1. Start **Metro**, run the following command from the _app_ folder of your project:

```bash
yarn start
```

2. In another terminal, run from the same folder.

```bash
yarn android
```

This should boot up an android emulator and build the app. However in case it doesn't, you need to run the emualator manually.

## Step 3: Modifying your App

Now that you have successfully run the app, let's modify it.

#### TRIGGER JS EVENT FROM NATIVE CODE:
In the MainActivity.kt, you can see that _context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java).emit('event name')_ is used to trigger event on javascript side. <br /> <br />
The event is registered by _DeviceEventEmitter.addListener('event name')_ in the App.tsx.

#### SEND JS EVENT RESPONSE TO NATIVE CODE:
In the App.tsx, we are using _TurboModule?.onData('response data')_ to send the response back to native code. The response handle can be seen inside the PurevpnModule.kt nested inside the RTNPurevpn folder of the project.

### Updating the code:

1. Whenever you make changes in PurevpnModule.kt or when either adding or removing a React Method, from the App folder you need to run:
```bash
yarn sync-tm
```

2. If you're running with android studio, after making any changes to javascript code, from the App folder you need to run:

```bash
yarn bundle-android
```