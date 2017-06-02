package com.angelina.codejam.flipper;

import com.angelina.codejam.utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Angelina on 02.06.2017.
 */
public class Main {
    /*
    * */
    public static void main(String[] args) {
        int firstArg;
        int secondArg;
        if (args.length > 0) {
            try {
                firstArg = Integer.parseInt(args[0]);
                secondArg = Integer.parseInt(args[1]);
                System.out.println("T less  " + firstArg + ". S less " +secondArg);

                int t = Math.abs(new Random().nextInt()) % firstArg + 1;
                System.out.println("T: " + t);
                List<Flipper> input = new ArrayList<Flipper>();
                for (int i = 0; i < t; i++) {
                    int length = Math.abs(new Random().nextInt()) % secondArg + 2; // 2 - 11
                    int k = Math.abs(new Random().nextInt()) % length + 1;
                    k = (k == 1) ? 2 : k;
                    String str = Utils.randomFlipperString(length);
                    input.add(new Flipper(k, str));
                    System.out.println(input.get(i).toString());
                    int answer = CookBusiness.cook(input.get(i));
                    System.out.println("Case #" + (i + 1) + ": " + (answer == -1 ? "IMPOSSIBLE" : answer));
                }

            } catch (NumberFormatException e) {
                System.err.println("Argument" + args[0] + " must be an integer.");
                System.exit(1);
            }
        }
    }
}
