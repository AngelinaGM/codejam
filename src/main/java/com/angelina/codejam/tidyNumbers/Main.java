package com.angelina.codejam.tidyNumbers;

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
import java.util.List;

/**
 * Created by Angelina on 09.06.2017.
 */
public class Main {
    public static List<Long> input(String filepath) {
        Path file = Paths.get(filepath);
        List<Long> lst = new ArrayList<>();
        Charset charset = Charset.forName("UTF-8");
        try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
            String line = reader.readLine();
            Integer t = new Integer(line);
            System.out.println("T: " + t);
            while ((line = reader.readLine()) != null) {
                lst.add(new Long(line));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lst;
    }

    public static void main(String[] args) {
        List<Long> input = input("D:\\Projects\\CodeJam\\src\\main\\resources\\B-large-practice.in");
        int j = 1;
        Path p = Paths.get("./B-large-result.out");
        try (OutputStream out = new BufferedOutputStream(
                     Files.newOutputStream(p, StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            for (Long i : input) {
                String output =  "Case #" + j + ": " +  Counter.lastTidyQ(i) + "\n";
                byte data[] = output.getBytes();
                out.write(data);
                j++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
