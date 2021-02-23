package devices.configuration.configs;

import devices.configuration.remote.IntervalRules;
import org.jetbrains.annotations.NotNull;

public class FeaturesConfigurationFixture {

    @NotNull
    public static FeaturesConfigurationEntity entity(String name, IntervalRules configuration) {
        FeaturesConfigurationEntity entity = new FeaturesConfigurationEntity();
        entity.setName(name);
        entity.setConfiguration(configuration);
        return entity;
    }
}
