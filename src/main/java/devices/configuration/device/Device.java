package devices.configuration.device;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Device {
    private final String deviceId;
    private Ownership ownership;
    private OpeningHours openingHours;
    private Location location;
    private Settings settings;

    public void assignTo(Ownership ownership) {
        this.ownership = ownership;
    }

    public void updateOpeningHours(OpeningHours openingHours) {
        this.openingHours = openingHours;
    }

    public void updateLocation(Location location) {
        this.location = location;
    }

    public void updateSettings(Settings settings) {
        this.settings = this.settings.merge(settings);
    }

    public Violations getViolations() {
        return Violations.builder()
                .operatorNotAssigned(ownership == null || ownership.getOperator() == null)
                .providerNotAssigned(ownership == null || ownership.getProvider() == null)
                .locationMissing(location == null)
                .showOnMapButMissingLocation(settings.isShowOnMap() && location == null)
                .showOnMapButNoPublicAccess(settings.isShowOnMap() && !settings.isPublicAccess())
                .build();
    }

    public Visibility getVisibility() {
        return Visibility.of(
                getViolations().isValid(),
                settings.isPublicAccess(),
                settings.isShowOnMap());
    }
}
