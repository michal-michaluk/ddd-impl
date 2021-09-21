package devices.configuration.device;

import java.util.Objects;

public class Device {
    private final String deviceId;
    private Ownership ownership;
    private Location location;
    private OpeningHours openingHours;
    private Settings settings;

    public Device(String deviceId, Ownership ownership, Location location, OpeningHours openingHours, Settings settings) {
        Objects.requireNonNull(deviceId, "deviceId is null");
        this.deviceId = deviceId;
        this.ownership = Ownership.orUnowned(ownership);
        this.location = location;
        this.openingHours = OpeningHours.alwaysOpenOrGiven(openingHours);
        this.settings = Settings.orDefault(settings);
    }

    public void assignTo(Ownership ownership) {
        this.ownership = Ownership.orUnowned(ownership);
    }

    public void updateLocation(Location location) {
        this.location = location;
    }

    public void updateOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public void updateSettings(Settings settings) {
        Settings merged = this.settings.merge(settings);
        merged.ensureSettingCorrect();
        this.settings = merged;
    }

    private Violations getViolations() {
        return Violations.builder()
                .operatorNotAssigned(ownership.notAssignedToOperator())
                .providerNotAssigned(ownership.notAssignedToProvider())
                .locationMissing(location == null)
                .showOnMapButMissingLocation(settings.isShowOnMap() && location == null)
                .showOnMapButNoPublicAccess(settings.isShowOnMap() && !settings.isPublicAccess())
                .build();
    }

    private Visibility getVisibility() {
        return Visibility.basedOn(getViolations(), settings);
    }
}
