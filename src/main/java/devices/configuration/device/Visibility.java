package devices.configuration.device;

import lombok.Value;

@Value
public class Visibility {
    ForCustomer forCustomer;
    boolean roamingEnable;

    public static Visibility of(boolean valid, boolean publicAccess, boolean visibleOnMap) {
        boolean usable = valid && publicAccess;
        return new Visibility(ForCustomer.of(usable, visibleOnMap), usable);
    }

    enum ForCustomer {
        USABLE_AND_VISIBLE_ON_MAP, USABLE_BUT_HIDDEN_ON_MAP, INACCESSIBLE_AND_HIDDEN_ON_MAP;

        public static ForCustomer of(boolean usable, boolean visibleOnMap) {
            if (usable && visibleOnMap) return ForCustomer.USABLE_AND_VISIBLE_ON_MAP;
            if (visibleOnMap) return ForCustomer.USABLE_BUT_HIDDEN_ON_MAP;
            return ForCustomer.INACCESSIBLE_AND_HIDDEN_ON_MAP;
        }
    }
}
