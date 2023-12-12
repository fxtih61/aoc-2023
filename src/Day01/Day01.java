package Day01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day01 {
    public static void main(String[] args) throws IOException {
        loadInput();
        mitBuchstaben();
    }

    // Load the input file
    public static void loadInput() throws IOException {
        // Suche in jeder einzelnen Zeile nach der ersten und letzten Ziffer
        // Wenn gefunden, dann verkette die erste Zahl mit der Zweiten Zahl. (Beispiel:
        // as2fsd5gg7 -> 27)
        // Wenn es nur eine Zahl gibt hängen wir hinten die gleiche Zahl nochmal an.
        // (Beispiel: as2fsd -> 22)
        // Summiere alle Zahlen auf und gib das Ergebnis aus. (Beispiel: 27 + 22 = 49)
        // 1abc2(12) pqr3stu8vwx(38) a1b2c3d4e5f(15) treb7uchet(77) == 142

        FileReader fr = new FileReader("src/Day01/day01input.csv");

        int i;
        int sum = 0;
        int first = 0;
        int last = 0;
        int count = 0;

        while ((i = fr.read()) != -1) {
            if (i >= 48 && i <= 57) {
                if (count == 0) {
                    first = i - 48;
                    count++;
                } else {
                    last = i - 48;
                    count++;
                }
            }
            if (i == 10) {
                if (count == 1) {
                    int newNumber = first * 10 + first;
                    sum += newNumber;
                } else {
                    int newNumber = first * 10 + last;
                    sum += newNumber;
                }
                count = 0;
            }
        }
        System.out.println(sum);
    }

    public static void mitBuchstaben() throws IOException {

        int sum = 0;

        // String test =
        // "two1nine\neightwothree\nabcone2threexyz\nxtwone3four\n4nineeightseven2\nzoneight234\n7pqrstsixteen";
        ArrayList<Integer> aL = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("src/Day01/file.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                for (int i = 0; i < line.length(); i++) {
                    if (line.charAt(i) <= 57 && line.charAt(i) >= 48) {
                        aL.add((int) (line.charAt(i) - 48));
                        continue;
                    }

                    for (int j = 1; j < 10; j++) {
                        if (mB(line.substring(i), j)) {
                            aL.add(j);
                        }
                    }
                }
                if (aL.size() == 1) {
                    sum += aL.get(0) * 10 + aL.get(0);
                } else {
                    sum += aL.get(0) * 10 + aL.get(aL.size() - 1);
                }
                aL.clear();
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println(sum);
    }

    public static boolean mB(String part, int digit) {
        String[] numbers = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine" };
        if (part.length() < numbers[digit].length()) {
            return false;
        }
        for (int i = 0; i < numbers[digit].length(); i++) {
            if (part.charAt(i) != numbers[digit].charAt(i)) {
                return false;
            }
        }
        return true;
    }

}