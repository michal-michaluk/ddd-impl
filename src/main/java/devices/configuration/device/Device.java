package devices.configuration.device;

import devices.configuration.DomainEvent;
import devices.configuration.device.events.LocationUpdated;
import devices.configuration.device.events.OpeningHoursUpdated;
import devices.configuration.device.events.OwnershipUpdated;
import devices.configuration.device.events.SettingsUpdated;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class Device {
    final String deviceId;
    final List<DomainEvent> events;
    private Ownership ownership;
    private OpeningHours openingHours;
    private Location location;
    private Settings settings;

    public void assignTo(Ownership ownership) {
        if (!Objects.equals(this.ownership, ownership)) {
            this.ownership = ownership;
            events.add(new OwnershipUpdated(deviceId, ownership));
        }
    }

    public void updateOpeningHours(OpeningHours openingHours) {
        if (!Objects.equals(this.openingHours, openingHours)) {
            this.openingHours = openingHours;
            events.add(new OpeningHoursUpdated(deviceId, openingHours));
        }
    }

    public void updateLocation(Location location) {
        if (!Objects.equals(this.location, location)) {
            this.location = location;
            events.add(new LocationUpdated(deviceId, location));
        }
    }

    public void updateSettings(Settings settings) {
        if (!Objects.equals(this.settings, settings)) {
            this.settings = this.settings.merge(settings);
            events.add(new SettingsUpdated(deviceId, settings));
        }
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
