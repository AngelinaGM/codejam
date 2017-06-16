package com.angelina.codejam.bathroomStalls;

import com.angelina.codejam.tidyNumbers.Counter;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Angelina on 15.06.2017.
 */
public class Resolver {
    private static int n = 12;
    private static ArrayList<Byte> row;

    public static void fooSlow(int k) {
        row.set(0, (byte) -1);
        row.set(n + 1, (byte) -1);

        for (int i = 0; i < k; i++) {
            HashMap<Integer, Integer> ls = new HashMap<>();
            HashMap<Integer, Integer> rs = new HashMap<>();
            for (int j = 1; j < n + 1; j++) {
                if (row.get(j) == 0) {    // if stall j is empty
                    ls.put(j, leftEmpty(j));
                    rs.put(j, rightEmpty(j));
                }
            }
            HashMap<Integer, Integer> set = new HashMap<>();
            Integer s = -1;
            Integer result = 0;
            for (Integer key : ls.keySet()) {
                if (ls.get(key) < rs.get(key))
                    set.put(key, ls.get(key));
                else
                    set.put(key, rs.get(key));

                if (s < set.get(key)) {
                    s = set.get(key); // maximum from min(LS, RS)
                    result = key;
                } else if (s == set.get(key)) {
                    int mxF = 0;
                    int mxS = 0;
                    if (ls.get(result) > rs.get(result))
                        mxF = ls.get(result);
                    else
                        mxF = rs.get(result);
                    if (ls.get(key) > rs.get(key))
                        mxS = ls.get(key);
                    else
                        mxS = rs.get(key);
                    if (mxS > mxF) {
                        s = set.get(key);
                        result = key;
                    }
                    if (mxS == mxF) {
                        result = result > key ? key : result;
                    }
                }
            }
            row.set(result, (byte) 1);
        }
        System.out.println(row);
    }

    private static int leftEmpty(int i) {
        int result = 0;
        for (int j = i - 1; j > 0; j--) {
            if (row.get(j) == 0)
                result++;
            else
                return result;
        }
        return result;
    }

    private static int rightEmpty(int i) {
        int result = 0;
        for (int j = i + 1; j < n + 2; j++) {
            if (row.get(j) == 0)
                result++;
            else
                return result;
        }
        return result;
    }


    /*
    * It works for first small input only
    * */
    public static String fooFast(int n, int k) {
        Long firstStall = new Long(n / 2 + n % 2);
        ArrayList<Long> occupiedStalls = new ArrayList<>();
        occupiedStalls.add(firstStall);
        int someone = 1;
        Long max = 0l;
        Long positionMax = 0l;
        while (someone < k) {
//            find first position of max empty stalls (max and position)
            max = occupiedStalls.get(0) - 1;
            positionMax = 0l;
            for (int i = 0; i < occupiedStalls.size() - 1; i++) {
                if (occupiedStalls.get(i + 1) - occupiedStalls.get(i) - 1 > max) {
                    max = occupiedStalls.get(i + 1) - occupiedStalls.get(i) - 1;
                    positionMax = occupiedStalls.get(i);
                }
            }
            if (n - occupiedStalls.get(occupiedStalls.size() - 1) > max) {
                max = n - occupiedStalls.get(occupiedStalls.size() - 1);
                positionMax = occupiedStalls.get(occupiedStalls.size() - 1);
            }

            occupiedStalls.add(positionMax + max / 2 + max % 2);
            someone++;
            // sort occupiedStalls
            Collections.sort(occupiedStalls);
        }
        String result;
        if (k != 1) {
            int ind = occupiedStalls.indexOf(positionMax + max / 2 + max % 2);
            long left = ind > 0 ? occupiedStalls.get(ind) - occupiedStalls.get(ind - 1) - 1 : occupiedStalls.get(ind) - 1;
            long right = ind < k - 1 ? occupiedStalls.get(ind + 1) - occupiedStalls.get(ind) - 1 : n - occupiedStalls.get(ind);
            result = left > right ? String.valueOf(left) + " " + String.valueOf(right) : String.valueOf(right) + " " + String.valueOf(left);
        } else {
            result = String.valueOf(n - occupiedStalls.get(0)) + " " + String.valueOf(occupiedStalls.get(0) - 1);
        }

        return result;
    }


    public static void main(String[] args) {


        long startTime = System.currentTimeMillis();
        fooFast(949792, 714292);

//        Path file = Paths.get("D:\\Projects\\CodeJam\\src\\main\\resources\\C-small-practice-2.in");
//        Path p = Paths.get("./C-small-practice-2.out");
//        Charset charset = Charset.forName("UTF-8");
//        try (BufferedReader reader = Files.newBufferedReader(file, charset);
//             OutputStream out = new BufferedOutputStream(
//                     Files.newOutputStream(p, StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
//            String line = reader.readLine();
//            Integer t = new Integer(line);
//            System.out.println("T: " + t);
//            int j = 1;
//            while ((line = reader.readLine()) != null) {
//                String[] split = line.split(" ");
//                String output = "Case #" + j + ": " + fooFast(Integer.valueOf(split[0]), Integer.valueOf(split[1])) + "\n";
//                byte data[] = output.getBytes();
//                out.write(data);
//                j++;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        long endTime = System.currentTimeMillis();

        long duration = (endTime - startTime);
        System.out.println(duration);
    }
}
