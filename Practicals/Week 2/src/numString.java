public class numString {
    public static String num(int value) {
        switch(value) {
            case 0:
                return "Zero";
            case 1:
                return "One";
            case 2:
                return "Two";
            case 3:
                return "Three";
            case 4:
                return "Four";
            case 5:
                return "Five";
            case 6:
                return "Six";
            case 7:
                return "Seven";
            case 8:
                return "Eight";
            case 9:
                return "Nine";
            default:
                return "error";
        }
    }

    public static String num1(int value) {
        String[] numbers = {"Zero", "One", "Two", "Three", "Four", "Five",
                "Six", "Seven", "Eight", "Nine"};

        return numbers[value];
    }

    public static int fib(int n) {
        if (n <= 2)
            return 1;
        return fib(n - 1) + fib(n - 2);
    }

    public static int ackermann(short n, short m) {
        if (m == 0) {
            return n + 1;
        } if (m > 0 && n == 0) {
            return ackermann((short)(m-1), (short)1);
        } else if (m > 0 && n > 0) {
            return ackermann((short)(m-1), (short)ackermann(m, (short)(n-1)));
        }
        return 0;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            System.out.println(num(i));
        }

        System.out.println("\n");

        for (int i = 0; i < 10; i++) {
            System.out.println(num1(i));
        }

        for (int i = 1; i <= 25; i++) {
            System.out.println(i + ": " + fib(i));
        }

        System.out.println(ackermann((short)1, (short)1));

    }
}
