package coords;

import java.util.Random;

public class Utils {
    public static int randomIntFromRange(int min, int max) {
        return new Random().nextInt((max - min) + 1) + min;
    }
}
