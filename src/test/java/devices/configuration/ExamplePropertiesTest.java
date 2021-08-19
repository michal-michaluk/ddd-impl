package devices.configuration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.time.Duration;

@SpringBootTest
@ActiveProfiles(profiles = "test")
@Import(FakeRepositoriesConfiguration.class)
//or shorter @SpringBootTest(properties = "spring.profiles.active:test")
//and @EnableAutoConfiguration(exclude = {
//        DataSourceAutoConfiguration.class,
//        DataSourceTransactionManagerAutoConfiguration.class,
//        HibernateJpaAutoConfiguration.class
//}) instead used entry in application-test.yml
public class ExamplePropertiesTest {

    @Autowired
    ExampleProperties exampleProperties;

    @Test
    void checkConfiguredParameters() {
        Assertions.assertThat(exampleProperties)
                .isNotNull()
                .extracting(
                        ExampleProperties::getProp,
                        ExampleProperties::getDur)
                .containsExactly(
                        "hello",
                        Duration.ofSeconds(12));
    }
}
