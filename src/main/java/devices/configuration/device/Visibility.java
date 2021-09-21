package devices.configuration.device;

import lombok.Value;

@Value
public class Visibility {
    boolean roamingEnabled;
    ForCustomer forCustomer;

    public static Visibility basedOn(Violations violations, Settings settings) {
        boolean usable = violations.isValid() && settings.isPublicAccess();
        return new Visibility(
                usable,
                ForCustomer.calculateForCustomer(usable, settings.isShowOnMap())
        );
    }

    public enum ForCustomer {
        USABLE_AND_VISIBLE_ON_MAP, USABLE_BUT_HIDDEN_ON_MAP, INACCESSIBLE_AND_HIDDEN_ON_MAP;

        private static Visibility.ForCustomer calculateForCustomer(boolean usable, boolean showOnMap) {
            if (!usable) {
                return Visibility.ForCustomer.INACCESSIBLE_AND_HIDDEN_ON_MAP;
            } else if (showOnMap) {
                return Visibility.ForCustomer.USABLE_AND_VISIBLE_ON_MAP;
            } else {
                return Visibility.ForCustomer.USABLE_BUT_HIDDEN_ON_MAP;
            }
        }
    }
}
