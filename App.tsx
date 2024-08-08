/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 */

import React from 'react';
import type {PropsWithChildren} from 'react';
import {
  SafeAreaView,
  ScrollView,
  StatusBar,
  StyleSheet,
  Text,
  useColorScheme,
  View,
  DeviceEventEmitter,
  NativeModules,
  Platform,
  Alert,
} from 'react-native';

import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';

type SectionProps = PropsWithChildren<{
  title: string;
}>;

const dispatchAction = (name: string, payload: any) => {
  try {
    Platform.OS === 'windows' &&
      NativeModules.RNModule.onData({action: name, payload});
    Platform.OS === 'android' &&
      NativeModules.RNModule.onData({action: name, payload});
    Platform.OS === 'ios' &&
      NativeModules.RNEmitter.onData({action: name, payload});
    Platform.OS === 'macos' &&
      NativeModules.RNEmitter.onData({action: name, payload});
  } catch (e) {
    Alert.alert("Couldn't dispatch result from RN event");
  }
};

const fetchCountries = async () => {
  const countries: any = await fetch(
    'https://bpc-prod-a230.s3.serverwild.com/bpc/res_5d4565b42f2c5/inventory/shared/android/v3/app.json',
  );

  const formattedData: any[] = [];
  countries.data.body.countries.map((i: any) => {
    let payload;
    payload = {name: i.name, iso_code: i.country};
    formattedData.push(payload);
  });

  dispatchAction('fetchCountries', formattedData);
};

function Section({children, title}: SectionProps): React.JSX.Element {
  DeviceEventEmitter.addListener('fetchCountries', fetchCountries);

  const isDarkMode = useColorScheme() === 'dark';
  return (
    <View style={styles.sectionContainer}>
      <Text
        style={[
          styles.sectionTitle,
          {
            color: isDarkMode ? Colors.white : Colors.black,
          },
        ]}>
        {title}
      </Text>
      <Text
        style={[
          styles.sectionDescription,
          {
            color: isDarkMode ? Colors.light : Colors.dark,
          },
        ]}>
        {children}
      </Text>
    </View>
  );
}

function App(): React.JSX.Element {
  const isDarkMode = useColorScheme() === 'dark';

  const backgroundStyle = {
    backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  };

  return (
    <SafeAreaView style={backgroundStyle}>
      <StatusBar
        barStyle={isDarkMode ? 'light-content' : 'dark-content'}
        backgroundColor={backgroundStyle.backgroundColor}
      />
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={backgroundStyle}>
        <Header />
        <View
          style={{
            backgroundColor: isDarkMode ? Colors.black : Colors.white,
          }}>
          <Section title="Step One">
            Edit <Text style={styles.highlight}>App.tsx</Text> to change this
            screen and then come back to see your edits.
          </Section>
          <Section title="See Your Changes">
            <ReloadInstructions />
          </Section>
          <Section title="Debug">
            <DebugInstructions />
          </Section>
          <Section title="Learn More">
            Read the docs to discover what to do next:
          </Section>
          <LearnMoreLinks />
        </View>
      </ScrollView>
    </SafeAreaView>
  );
}

const styles = StyleSheet.create({
  sectionContainer: {
    marginTop: 32,
    paddingHorizontal: 24,
  },
  sectionTitle: {
    fontSize: 24,
    fontWeight: '600',
  },
  sectionDescription: {
    marginTop: 8,
    fontSize: 18,
    fontWeight: '400',
  },
  highlight: {
    fontWeight: '700',
  },
});

export default App;
