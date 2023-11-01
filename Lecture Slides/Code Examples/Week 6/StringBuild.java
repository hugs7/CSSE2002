public class StringBuild {

    public static boolean isPrime(int number) {
        for(int i = 2; i <= number/2; i++) {
            if (number % i == 0) { return false; }
        }
        return true;
    }

    public static void main(String args[]) {
        StringBuilder sb = new StringBuilder("primes: 2");
        for (int i = 3; i < 1000; i++) {
            if (isPrime(i)) {
                sb.append(',');
                sb.append(i);
            }
        }

        System.out.println(sb.toString());

        sb.insert(6, " under 1000");
        sb.setCharAt(0, 'P');

        String s = sb.toString();

        System.out.println(s);
    }

}
