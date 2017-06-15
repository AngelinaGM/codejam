package com.angelina.codejam.tidyNumbers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Angelina on 09.06.2017.
 */
public class Counter {
    /* go from number to 1 and finding first tidy number
    * */

    /* представить число в виде листа, каждый элемент которого - цифра числа, а потом
        вызвать Collection.sort(list) и сравнить с изначальным
        если одинаковые => tidy
    * */
    public static Integer lastTidy(Integer number) {
        while (number > 0) {
            if (isTidy(number)) {
                return number;
            }
            number--;
        }
        return -1;
    }

    private static boolean isTidy(Integer number) {
        List<Integer> digits = new ArrayList<>();
        while (number > 0) {
            digits.add(0, number % 10);
            number = number / 10;
        }
        List<Integer> digitsSort = new ArrayList<>(digits);
        Collections.sort(digitsSort);

        if (digits.equals(digitsSort)) {
            return true;
        }
        return false;
    }

    /* this is too long for processing Long number, because we step by 1 number
    *
    * */
    public static Long lastTidy(Long number) {
        Long numberCopy = number;
        while (numberCopy > 0) {
            long lastDig = numberCopy % 10;
            long preLastDig = (numberCopy / 10) % 10;
            if (lastDig < preLastDig) {
//                the number is NOT tidy, have to check number-1
                number--;
                numberCopy = number;
            } else
                numberCopy = numberCopy / 10;
        }
        return number;
    }

    /*
    * this is quick operation for finding max tidy number less then @number
    * */
    public static Long lastTidyQ(Long number) {
        StringBuilder result = new StringBuilder(number.toString());
        int i;
        while ( (i = isNotTidy(result)) != -1) {
            result.setCharAt(i, (char) (Integer.valueOf(result.charAt(i)) - 1));
            for (int j = i + 1; j < result.length(); j++) {
                result.setCharAt(j, '9');
            }
        }
        return new Long(result.toString());
    }

    /*
    * return -1 if stringBuffer correspond too tidy number or
    * position where the rule for tidy numbers is broken */
    private static int isNotTidy(StringBuilder stringBuilder) {
        for (int i = 0; i < stringBuilder.length() - 1; i++) {
            if (Integer.valueOf(stringBuilder.charAt(i + 1)) < Integer.valueOf(stringBuilder.charAt(i)))
                return i;
        }
        return -1;
    }
}
