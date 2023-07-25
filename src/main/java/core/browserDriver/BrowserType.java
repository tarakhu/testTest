package core.browserDriver;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BrowserType {

    CHROME("chrome"),
    FIREFOX("firefox");

    private final String typeString;


    public static BrowserType getBrowserType(final String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new UnsupportedOperationException("Value required");
        }
        for (BrowserType browserType: BrowserType.values()) {
            if (value.equalsIgnoreCase(browserType.name())) {
                return browserType;
            }
        }
        throw new IllegalArgumentException("value did not match existing value");
    }
}
