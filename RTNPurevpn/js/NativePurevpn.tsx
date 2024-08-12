import type { TurboModule } from "react-native/Libraries/TurboModule/RCTExport";
import { TurboModuleRegistry } from "react-native";

export interface Spec extends TurboModule {
  getDeviceModel(): Promise<string>;
  onData(action: string): void;
}

export default TurboModuleRegistry.get<Spec>("PureVPN") as Spec | null;
