public class InputArgumentBasic {

    public static void main(String[] args) {
        System.out.println("Commandline arguments:");

        for (String s : args) {
            System.out.println(s);
        }
    }
}
