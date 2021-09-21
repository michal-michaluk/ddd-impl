package devices.configuration.device;

import devices.configuration.DomainEvent;
import devices.configuration.LastEvents;
import devices.configuration.device.events.LocationUpdated;
import devices.configuration.device.events.OpeningHoursUpdated;
import devices.configuration.device.events.OwnershipUpdated;
import devices.configuration.device.events.SettingsUpdated;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@AllArgsConstructor
public class DeviceRepository {

    private final DeviceEventRepository repository;

    Optional<Device> get(String deviceId) {
        List<DomainEvent> events = repository.findAllDomainEvents(deviceId);
        if (events.isEmpty()) {
            return Optional.empty();
        }
        LastEvents last = LastEvents.fromHistoryOf(events);
        Device device = new Device(deviceId, new ArrayList<>(),
                last.getOrNull(OwnershipUpdated.class, OwnershipUpdated::getOwnership),
                last.getOrNull(OpeningHoursUpdated.class, OpeningHoursUpdated::getOpeningHours),
                last.getOrNull(LocationUpdated.class, LocationUpdated::getLocation),
                last.getOrDefault(SettingsUpdated.class, SettingsUpdated::getSettings, Settings.defaultSettings())
        );
        return Optional.of(device);
    }

    void save(Device device) {
        device.events.forEach(event -> {
            repository.save(new DeviceEventEntity(device.deviceId, event));
        });
    }

}
