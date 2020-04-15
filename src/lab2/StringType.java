package lab2;

import java.util.regex.Pattern;
import java.util.stream.Stream;

public enum StringType {
    GUID(RegExpStrings.GUID),
    MAC_ADDRESS(RegExpStrings.MAC_ADDRESS),
    EMAIL(RegExpStrings.EMAIL),
    HEX_COLOR(RegExpStrings.HEX_COLOR),
    UNKNOWN();

    private Pattern pattern;

    StringType(String regExpString) {
        this.pattern = Pattern.compile(regExpString);
    }

    StringType() {
        this.pattern = null;
    }

    public boolean matches(String input) {
        return getPattern() != null && getPattern().matcher(input).matches();
    }

    @Override
    public String toString() {
        return name().toLowerCase().replace('_', ' ');
    }

    private Pattern getPattern() {
        return this.pattern;
    }

    public static StringType getInputType(String input) {
        return Stream.of(StringType.values())
                .filter(analyzer -> analyzer.matches(input))
                .findFirst()
                .orElse(UNKNOWN);
    }
}

