package devices.configuration.device;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Violations {
    boolean operatorNotAssigned;
    boolean providerNotAssigned;
    boolean locationMissing;
    boolean showOnMapButMissingLocation;
    boolean showOnMapButNoPublicAccess;

    public boolean isValid() {
        return !hasAny();
    }

    public boolean hasAny() {
        return operatorNotAssigned
                || providerNotAssigned
                || locationMissing
                || showOnMapButMissingLocation
                || showOnMapButNoPublicAccess;
    }
}
