package devices.configuration;

import devices.configuration.configs.FeaturesConfigurationRepository;
import devices.configuration.remote.IntervalRules;
import devices.configuration.remote.IntervalRulesFixture;
import devices.configuration.remote.IntervalRulesRepository;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class FakeRepositoriesConfiguration {

    @Bean
    @Primary
    public IntervalRulesRepository fakeIntervalRulesRepository() {
        return new FakeIntervalRulesRepository();
    }

    @Bean
    @Primary
    public FeaturesConfigurationRepository fakeFeaturesConfigurationRepository() {
        return Mockito.mock(FeaturesConfigurationRepository.class);
    }

    public static class FakeIntervalRulesRepository implements IntervalRulesRepository {

        private IntervalRules rules = IntervalRulesFixture.currentRules();

        public void setRules(IntervalRules rules) {
            this.rules = rules;
        }

        @Override
        public IntervalRules get() {
            return rules;
        }
    }
}
