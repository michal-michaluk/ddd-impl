package devices.configuration.device;

import lombok.Value;

@Value
public class Ownership {
    String operator;
    String provider;

    public static Ownership unowned() {
        return new Ownership(null, null);
    }

    public static Ownership orUnowned(Ownership ownership) {
        return ownership != null ? ownership : Ownership.unowned();
    }

    public boolean notAssignedToOperator() {
        return operator == null;
    }

    public boolean notAssignedToProvider() {
        return provider == null;
    }
}
