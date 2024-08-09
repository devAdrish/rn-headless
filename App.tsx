import React from 'react';
import {DeviceEventEmitter, NativeModules, Platform, Alert} from 'react-native';
import axios from 'axios';


const dispatchAction = (name: string, payload: any) => {
  try {
    // Platform.OS === 'android' &&
    NativeModules.RNModule.onData({action: name, payload});
    // Platform.OS === 'ios' &&
    //   NativeModules.RNEmitter.onData({action: name, payload});
    // Platform.OS === 'macos' &&
    //   NativeModules.RNEmitter.onData({action: name, payload});
    //   Platform.OS === 'windows' &&
    //   NativeModules.RNModule.onData({action: name, payload});
  } catch (e) {
    Alert.alert("Couldn't dispatch result from RN event");
  }
};

const App = () => {
  DeviceEventEmitter.removeAllListeners('fetchCountries');
  DeviceEventEmitter.addListener('fetchCountries', async () => {
    console.log('Event received: fetchCountries');
    try {
      // const response = await axios(
      //   'https://bpc-prod-a230.s3.serverwild.com/bpc/res_5d4565b42f2c5/inventory/shared/android/v3/app.json',
      // );
      // const countries = await response.data;
      // console.log('Fetched countries:', countries);

      // const formattedData = countries.data.body.countries.map((i: any) => ({
      //   name: i.name,
      //   iso_code: i.country,
      // }));

      // console.log('Formatted Data:', formattedData);
      // dispatchAction('fetchCountries', formattedData);
      NativeModules.RNModule.onData();
    } catch (error) {
      console.error('Failed to fetch countries:', error);
    }
  });

  return null;
};

export default App;