enum OS {MAC, WINDOWS, LINUX, VMS}
public class Enums {
    public static String getStatus(OS os) {
        String result = "";
        switch (os) {
            case MAC:
                result += "Designer?";
                break;
            case WINDOWS:
                result += "Coder?";
                break;
            case LINUX:
                result += "Hacker?";
                break;
            case VMS:
                result += "COBOL Coder?";
                break;
            default:
                result += "What?";
        }
        return result;
    }
    public static void main(String... args) {
        System.out.println(getStatus(OS.WINDOWS));
    }
}
