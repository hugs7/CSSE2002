public class Switch {

    public static char next_vowel1(char c) {
        char result;
        if (c == 'A') {
            result = 'E';
        } else if (c == 'E') {
            result = 'I';
        } else if (c == 'I') {
            result = 'O';
        } else if (c == 'O') {
            result = 'U';
        } else if (c == 'U') {
            result = 'A';
        } else {
            result = c;
        }
        return result;
    }

    public static char next_vowel2(char c) {
        char result;
        switch (c) {
            case 'A':
                result = 'E';
                break;
            case 'E':
                result = 'I';
                break;
            case 'I':
                result = 'O';
                break;
            case 'O':
                result = 'U';
                break;
            case 'U':
                result = 'A';
                break;
            default:
                result = c;
        }
        return result;
    }


    public static void main(String[] args) {
        char[] letters = {'A', 'E', 'I', 'O', 'U', 'Z', 'B'}; // an array literal

        char c;
        c = letters[0];
        System.out.println("" + c + "->" + next_vowel1(c) + " | " + next_vowel2(c));

        c = letters[1];
        System.out.println("" + c + "->" + next_vowel1(c) + " | " + next_vowel2(c));

        c = letters[2];
        System.out.println("" + c + "->" + next_vowel1(c) + " | " + next_vowel2(c));

        c = letters[3];
        System.out.println("" + c + "->" + next_vowel1(c) + " | " + next_vowel2(c));

        c = letters[4];
        System.out.println("" + c + "->" + next_vowel1(c) + " | " + next_vowel2(c));

        c = letters[5];
        System.out.println("" + c + "->" + next_vowel1(c) + " | " + next_vowel2(c));

        c = letters[6];
        System.out.println("" + c + "->" + next_vowel1(c) + " | " + next_vowel2(c));

        System.exit(0);
    }
}