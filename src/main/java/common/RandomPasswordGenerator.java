package common;

import java.util.Random;

public class RandomPasswordGenerator {
    public static String generateRandomString() {

        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random rnd = new Random();
        while (password.length() < 8) {
            int index = (int) (rnd.nextFloat() * chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }
}
