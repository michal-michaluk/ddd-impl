package devices.configuration.configs;

import devices.configuration.FakeRepositoriesConfiguration;
import devices.configuration.remote.IntervalRulesFixture;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import io.restassured.module.mockmvc.config.MockMvcConfig;
import io.restassured.module.mockmvc.config.RestAssuredMockMvcConfig;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;

@WebMvcTest(FeaturesConfigurationController.class)
@Import(FakeRepositoriesConfiguration.class)
public class ContractTestingBase {

    @Autowired
    FeaturesConfigurationController controller;
    @Autowired
    FakeRepositoriesConfiguration.FakeIntervalRulesRepository repository;

    @BeforeEach
    public void setup() {
        RestAssuredMockMvc.standaloneSetup(controller);
        RestAssuredMockMvc.config = new RestAssuredMockMvcConfig()
                .mockMvcConfig(MockMvcConfig.mockMvcConfig()
                        .dontAutomaticallyApplySpringSecurityMockMvcConfigurer()
                );

        givenProviderState();
    }

    private void givenProviderState() {
        repository.withRules(IntervalRulesFixture.shortRules());
    }
}
