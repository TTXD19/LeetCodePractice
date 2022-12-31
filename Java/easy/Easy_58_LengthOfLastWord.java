package Java.easy;

public class Easy_58_LengthOfLastWord {

    public static int lengthOfLastWord(String s) {
        String[] segments = s.split(" ");
        return segments[segments.length - 1].length();
    }

    public static void main(String[] args) {
        String testCase_1 = "Hello World";
        String testCase_2 = "   fly me   to   the moon  ";
        String testCase_3 = "luffy is still joyboy";

        System.out.println(lengthOfLastWord(testCase_3));
    }
}
