import React, {useEffect} from 'react';
import {DeviceEventEmitter} from 'react-native';
import axios from 'axios';

import TurboModule from 'rtn-purevpn/js/NativePurevpn';

const App = () => {
  const ask = async () => {
    const res = await TurboModule?.getDeviceModel();
    console.log('Device Model', res);
  };

  useEffect(() => {
    ask();
  }, []);

  DeviceEventEmitter.removeAllListeners('fetchCountries');
  DeviceEventEmitter.addListener('fetchCountries', async () => {
    console.log('Event received: fetchCountries');
    try {
      const response = await axios(
        'https://bpc-prod-a230.s3.serverwild.com/bpc/res_5d4565b42f2c5/inventory/shared/android/v3/app.json',
      );
      const countries = await response.data;
      console.log('Fetched countries:', countries);

      const formattedData = countries.body.countries.map((i: any) => ({
        name: i.name,
        iso_code: i.country,
      }));

      TurboModule?.onData('fetchCountries');
    } catch (error) {
      console.error('Failed to fetch countries:', error);
    }
  });

  return null;
};

export default App;
