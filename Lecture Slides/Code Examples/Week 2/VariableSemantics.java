public class VariableSemantics {

    public static void f(int a, int[] arr, int[] x) {
        System.out.println("" + a + " " + arr[0] + " " + x[0]);
        a = 100;
        arr[0] = -1;
        x = new int[2];
        x[0] = -10;
        System.out.println("" + a + " " + arr[0] + " " + x[0]);
    }

    public static void main(String args[]) {
        int a = 5;
        int ar1[] = {1, 2, 3};
        int ar2[] = {4, 5, 6};

        System.out.println("" + a + " " + ar1[0] + " " + ar2[0]);
        f(a, ar1, ar2);
        System.out.println("" + a + " " + ar1[0] + " " + ar2[0]);
    }
}
