package lab2;


import java.util.regex.Pattern;

public enum Analyzers {
    GUID {
        protected Pattern getPattern() {
            return Pattern.compile(RegExpStrings.GUID);
        }
    },

    MAC_ADDRESS {
        protected Pattern getPattern() {
            return Pattern.compile(RegExpStrings.MAC_ADDRESS);
        }
    },

    EMAIL {
        protected Pattern getPattern() {
            return Pattern.compile(RegExpStrings.EMAIL);
        }
    },

    HEX_COLOR {
        protected Pattern getPattern() {
            return Pattern.compile(RegExpStrings.HEX_COLOR);
        }
    };



    protected abstract Pattern getPattern();

    public boolean match(String input) {
        return getPattern().matcher(input).matches();
    }

    @Override
    public String toString() {
        return name().toLowerCase().replace('_', ' ');
    }
}

