package lab2;


import java.util.regex.Pattern;

public enum Analyzers {
    GUID(RegExpStrings.GUID),
    MAC_ADDRESS(RegExpStrings.MAC_ADDRESS),
    EMAIL(RegExpStrings.EMAIL),
    HEX_COLOR(RegExpStrings.HEX_COLOR);

    private Pattern pattern;

    Analyzers(String regExpString) {
        this.pattern = Pattern.compile(regExpString);
    }

    public boolean matches(String input) {
        return getPattern().matcher(input).matches();
    }

    @Override
    public String toString() {
        return name().toLowerCase().replace('_', ' ');
    }

    private Pattern getPattern() {
        return this.pattern;
    }
}

