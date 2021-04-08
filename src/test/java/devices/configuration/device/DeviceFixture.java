package devices.configuration.device;

import java.util.ArrayList;

public class DeviceFixture {
    public static Device notConfigured(String deviceId) {
        return new Device(
                deviceId,
                new ArrayList<>(),
                null,
                null,
                null,
                Settings.defaultSettings()
        );
    }
}
