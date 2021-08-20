package devices.configuration;

import devices.configuration.configs.FeaturesConfigurationRepository;
import devices.configuration.remote.IntervalRules;
import devices.configuration.remote.IntervalRulesFixture;
import devices.configuration.remote.IntervalRulesRepository;
import lombok.SneakyThrows;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@TestConfiguration
public class FakeRepositoriesConfiguration {

    @Bean
    @Primary
    public FakeIntervalRulesRepository fakeIntervalRulesRepository() {
        return new FakeIntervalRulesRepository();
    }

    @Bean
    @Primary
    public FeaturesConfigurationRepository fakeFeaturesConfigurationRepository() {
        return Mockito.mock(FeaturesConfigurationRepository.class);
    }

    public static class FakeIntervalRulesRepository implements IntervalRulesRepository {

        private IntervalRules rules = IntervalRulesFixture.currentRules();

        public FakeIntervalRulesRepository withRules(IntervalRules rules) {
            this.rules = rules;
            return this;
        }

        @Override
        public IntervalRules get() {
            return rules;
        }

        @Override
        @SneakyThrows
        public String save(IntervalRules object) {
            return JsonConfiguration.OBJECT_MAPPER.writeValueAsString(object);
        }
    }
}
