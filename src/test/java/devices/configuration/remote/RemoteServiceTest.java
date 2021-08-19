package devices.configuration.remote;

import devices.configuration.FakeRepositoriesConfiguration;
import org.assertj.core.api.Assertions;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@Import(FakeRepositoriesConfiguration.class)
class RemoteServiceTest {

    @Autowired
    RemoteService service;

    @Test
    void serviceCalculatesIntervalWithSomeRules() {
        Duration interval = service.intervalFor(givenDevice().build());
        Assertions.assertThat(interval).isNotNull();
    }

    @NotNull
    private Deviceish.DeviceishBuilder givenDevice() {
        return Deviceish.builder()
                .deviceId("EVB-P4123437")
                .model("Garo")
                .vendor("CPF25 Family")
                .protocol(Protocols.IoT16);
    }
}
