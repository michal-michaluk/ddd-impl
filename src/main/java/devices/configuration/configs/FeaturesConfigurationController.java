package devices.configuration.configs;

import devices.configuration.remote.IntervalRules;
import devices.configuration.remote.IntervalRulesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
class FeaturesConfigurationController {

    private final FeaturesConfigurationRepository repository;
    private final IntervalRulesRepository intervalRules;

    @GetMapping(path = "/configs/{configName}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String get(@PathVariable String configName) {
        return repository.findByName(configName)
                .map(FeaturesConfigurationEntity::getConfiguration)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @PutMapping(path = "/configs/{configName}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String put(@PathVariable String configName, @RequestBody String configuration) {
        return repository.findByName(configName)
                .orElseGet(() -> repository.save(new FeaturesConfigurationEntity(configName)))
                .withConfiguration(configuration)
                .getConfiguration();
    }

    @GetMapping(path = "/configs/" + IntervalRulesDocumentRepository.CONFIG_NAME,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public IntervalRules getIntervalRules() {
        return intervalRules.get();
    }

    @PutMapping(path = "/configs/" + IntervalRulesDocumentRepository.CONFIG_NAME,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String putIntervalRules(@RequestBody IntervalRules configuration) {
        return intervalRules.save(configuration);
    }
}
