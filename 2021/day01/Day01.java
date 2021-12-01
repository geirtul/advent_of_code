import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;

public class Day01 {

    public static void main(String[] args) throws FileNotFoundException {
        // Get input from args
        File input = new File(args[0]);

        // Init scanner
        Scanner sc = new Scanner(input);

        // Loop over input and store ints in a list
        ArrayList<Integer> heights = new ArrayList<Integer>();
        while (sc.hasNext()) {
            int height = sc.nextInt();
            heights.add(height);
        }
        int count = 0;
        for (int i = 1; i < heights.size(); i++) {
            if (heights.get(i) > heights.get(i-1)) {
                count++;
            }
        }
        System.out.println(count);
        sc.close();

        // Count directly while scanning

        Scanner s = new Scanner(input);
        int prev, curr, counter;
        prev = s.nextInt();
        counter = 0;
        while (s.hasNextLine()) {
            curr = s.nextInt();
            if (curr > prev) {
                counter++;
            }
            prev = curr;
        }
        System.out.println(counter);
    }
}