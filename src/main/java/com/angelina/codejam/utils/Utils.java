package com.angelina.codejam.utils;

import java.util.Random;

/**
 * Created by Angelina on 02.06.2017.
 */
public final class Utils {

    /* Finds maximum length sequence of symbol in string
    *
    * @param str finds sequence here
    * @symbol sequence of this symbol
    * @return position of the first meet
    * */
    public static int findMaxQuantitySequence(String str, char symbol) {
        int s = 0;      // counter
        int m = 0;      // quantity together
        int p0 = 0;     // first pos of next sequence
        int p = 0;      // pos last sequence

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == symbol)
                s++;
            if (i + 1 == str.length() || str.charAt(i) != symbol) {
                if (s > m) {
                    m = s;
                    p = p0;
                }
                p0 = i + 1;
                s = 0;
            }
        }
        if (m != 0) {
            return p;
        }

        return -1;
    }

    /* Returns random string with + or - (eg '+--++-')
    *
    * @param length the string's length
    * @return string with random + and -
    * */

    public static String randomFlipperString(int length) {
        StringBuilder result = new StringBuilder();
        Random rand = new Random(0L);
        for (int i = 0; i < length; i++) {
            result.append(rand.nextBoolean() ? '+' : '-');
        }
        return result.toString();
    }
}
