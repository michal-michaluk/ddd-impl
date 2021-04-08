package devices.configuration;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import devices.configuration.device.events.LocationUpdated;
import devices.configuration.device.events.OpeningHoursUpdated;
import devices.configuration.device.events.OwnershipUpdated;
import devices.configuration.device.events.SettingsUpdated;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = OwnershipUpdated.class, name = "OwnershipUpdated"),
        @JsonSubTypes.Type(value = OpeningHoursUpdated.class, name = "OpeningHoursUpdated"),
        @JsonSubTypes.Type(value = LocationUpdated.class, name = "LocationUpdated"),
        @JsonSubTypes.Type(value = SettingsUpdated.class, name = "SettingsUpdated"),
})
public interface DomainEvent {
}
