package devices.configuration.device;

import devices.configuration.IntegrationTest;
import devices.configuration.JsonAssert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static devices.configuration.TestTransaction.transactional;

@IntegrationTest
@Transactional
class DeviceRepositoryTest {

    @Autowired
    private DeviceRepository repository;

    String deviceId = "device " + UUID.randomUUID();

    @Test
    public void shouldSaveAndLoadDevice() {
        //given
        Device device = DeviceFixture.notConfigured(deviceId);
        device.assignTo(new Ownership("operator", "provider"));

        // when
        transactional(() -> repository.save(device));
        var result = transactional(() -> repository.get(deviceId));

        // then
        JsonAssert.assertThat(result).hasFieldsLike(device);
    }

}
