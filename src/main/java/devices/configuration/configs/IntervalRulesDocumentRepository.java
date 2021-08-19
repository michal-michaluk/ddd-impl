package devices.configuration.configs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import devices.configuration.remote.IntervalRules;
import devices.configuration.remote.IntervalRulesRepository;
import io.vavr.control.Try;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class IntervalRulesDocumentRepository implements IntervalRulesRepository {

    public static final String CONFIG_NAME = "IntervalRules";
    private final FeaturesConfigurationRepository repository;
    private final ObjectMapper mapper;

    @Override
    public IntervalRules get() {
        return repository.findByName(CONFIG_NAME)
                .map(FeaturesConfigurationEntity::getConfiguration)
                .map(this::parse)
                .orElse(IntervalRules.defaultRules());
    }

    public String save(IntervalRules configuration) {
        return repository.findByName(CONFIG_NAME)
                .orElseGet(() -> repository.save(new FeaturesConfigurationEntity(CONFIG_NAME)))
                .withConfiguration(json(configuration))
                .getConfiguration();
    }

    private IntervalRules parse(String json) {
        return Try.of(() -> mapper.readValue(json, IntervalRules.class))
                .getOrElseThrow(((Function<Throwable, RuntimeException>) RuntimeException::new));
    }

    private String json(IntervalRules object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
