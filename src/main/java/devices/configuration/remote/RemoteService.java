package devices.configuration.remote;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@AllArgsConstructor
public class RemoteService {
    private final IntervalRulesRepository repository;

    public Duration intervalFor(Deviceish device) {
        IntervalRules rules = repository.get();
        return rules.calculateInterval(device);
    }
}
