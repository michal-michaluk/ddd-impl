package devices.configuration.configs;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "features_configuration")
@NoArgsConstructor
class FeaturesConfigurationEntity {
    @Id
    private String name;
    @Type(type = "jsonb")
    @Column(columnDefinition = "jsonb")
    private String configuration;

    public FeaturesConfigurationEntity(String name) {
        this.name = name;
    }

    public FeaturesConfigurationEntity withConfiguration(String json) {
        configuration = json;
        return this;
    }
}
