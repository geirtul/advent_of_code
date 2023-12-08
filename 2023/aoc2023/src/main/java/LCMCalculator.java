public class LCMCalculator {

    public static long calculateLCM(long[] numbers) {
        if (numbers.length == 0) {
            throw new IllegalArgumentException("Array must not be empty");
        }

        long lcm = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            lcm = calculateLCM(lcm, numbers[i]);
        }

        return lcm;
    }

    public static long calculateLCM(long a, long b) {
        return Math.abs(a * b) / calculateGCD(a, b);
    }

    public static long calculateGCD(long a, long b) {
        while (b > 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
