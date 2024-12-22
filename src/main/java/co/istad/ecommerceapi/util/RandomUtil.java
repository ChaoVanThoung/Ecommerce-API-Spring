package co.istad.ecommerceapi.util;

import java.util.Random;

public class RandomUtil {

    public static String random6Digits() {
        Random rand = new Random();
        int number = rand.nextInt(999999);

        return String.format("%06d", number);
    }
}
