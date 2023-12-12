package Day01;

import java.io.FileReader;
import java.io.IOException;

public class Day01 {
    public static void main(String[] args) throws IOException {
        loadInput();
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
}