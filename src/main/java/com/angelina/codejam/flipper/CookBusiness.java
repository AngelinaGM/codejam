package com.angelina.codejam.flipper;

import com.angelina.codejam.utils.Utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

/**
 * Created by Angelina on 02.06.2017.
 */
public final class CookBusiness {
    private static final Logger logger = (Logger) LogManager.getLogger(CookBusiness.class);

    /* Flips exactly K consecutive pancakes from given position
    *
    * @param flipper oversized pancake flipper that flips exactly K consecutive pancakes
    * @param position from this position flipper changes every happy-side pancake to a blank-side pancake, and vice versa
    * @return flipper after changes
    * @throws IndexOutOfBoundsException if flipper can't flip K pancakes from position
    * */
    private static Flipper flip(Flipper flipper, int position) throws IndexOutOfBoundsException {
        int flipperK = flipper.getK();
        String flipperRow = flipper.getRow();
        if (position > flipperRow.length() - flipperK || position < 0)
            throw new IndexOutOfBoundsException("You can't flip " + flipperK + " pancakes from position " + position);
        StringBuilder resultRow = new StringBuilder(flipperRow);
        for (int i = position; i < position + flipperK; i++) {
            if (resultRow.charAt(i) == '+')
                resultRow.setCharAt(i, '-');
            else
                resultRow.setCharAt(i, '+');
        }
        return new Flipper(flipperK, resultRow.toString());
    }

    /* Finds the first sequence of blank side pancakes, length of it less or equals K
    *
    * @param flipper
    * @return the first sequence position or -1 if either all pancakes on 'happy' side(zero '-')
    *         or position of first blank side pancake is more than length Row - K (the last position from there flipper can flip pancakes is length Row - K)
    */
    private static int findFirstBlankSequence(Flipper flipper) {
        String row = flipper.getRow();
        char symbol = '-';
        int k = flipper.getK();
        int s = 0;      // counter
        int m = 0;      // quantity together
        int p0 = 0;     // first pos of next sequence
        int p = 0;      // pos last sequence

        for (int i = 0; (i < row.length()) && (p0+k <= row.length()); i++) {
            if (row.charAt(i) == symbol)
                s++;
            if (i + 1 == row.length() || row.charAt(i) != symbol) {
                if (s > m) {
                    m = s;
                    p = p0;
                }
                if (m >= k)
                    return p;
                p0 = i + 1;
                s = 0;
            }
        }
        logger.info("quantity: " + m + " position: " + p);

        if (m != 0) {
            return p;
        }
        return -1;
    }

    /* Cooks pancakes
    *
    * @param flipper
    * @return either an positive integer representing the the (minimum?) number of times you will need to use the oversized pancake flipper to cook
     *          or -1 if there is no way to get all the pancakes happy side up*/
    public static int cook(Flipper flipper) {
        // if all pancakes on the 'happy' side return 0
        int counter = 0;
        if (Utils.findMaxQuantitySequence(flipper.getRow(), '-') == -1) {
            logger.info(counter);
            return 0;
        }
        //
        int pos = findFirstBlankSequence(flipper);
        if (pos == -1)
            return -1;

        // else next steps for analyze
        Flipper f = Flipper.initFlipper(flipper);
        logger.info("The current state of the pancakes: " + f.toString());
        while (pos != -1) {
            f = flip(f, pos);
            counter++;
            logger.info("After " + counter + " flip: " + f.toString());
            if (!f.equals(flipper))
                pos = findFirstBlankSequence(f);
            else {
                logger.info("Answer: IMPOSSIBLE");
                return -1;
            }
        }

        if (Utils.findMaxQuantitySequence(f.getRow(), '-') == -1) {
            logger.info("Answer: " + counter);
            return counter;
        }
        else {
            logger.info("Answer: IMPOSSIBLE");
            return -1;
        }
    }
}
