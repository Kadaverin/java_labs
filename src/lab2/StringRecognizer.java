package lab2;

import java.util.Optional;
import java.util.stream.Stream;

public class StringRecognizer {

    private static StringRecognizer instance;

    private StringRecognizer() { }

    public static StringRecognizer getInstance() {
        if( instance == null ) {
            instance = new StringRecognizer();
        }

        return instance;
    }

    public Optional<String> getInputType(String input) {
        return Stream.of(Analyzers.values())
                .filter(analyzer -> analyzer.match(input))
                .findFirst()
                .map(Analyzers::toString);
    }

    public boolean inputHasType(String input, String type) throws IllegalArgumentException {
        Analyzers analyzer = Analyzers.valueOf(type);
        return analyzer.match(input);
    }
}
