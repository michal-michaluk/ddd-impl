package devices.configuration;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.time.Duration;

@Value
@ConstructorBinding
@ConfigurationProperties(prefix = "fancy")
public class ExampleProperties {
    String prop;
    Duration dur;
}
