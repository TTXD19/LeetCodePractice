package Java.easy;

public class Easy_844_BackspaceStringCompare {

    public static String backSpaceCompare(String s) {

        StringBuilder text = new StringBuilder();
        char[] chars = s.toCharArray();
        for (char ch: chars){
            if (ch == '#'){
                if (text.length() != 0){
                    text.deleteCharAt(text.length() - 1);
                }
            } else {
                text.append(ch);
            }
        }

        return text.toString();
    }

    public static void main(String[] args) {
        String s = "ab##c";
        String t = "c#d#c";
        System.out.println(backSpaceCompare(s).equals(backSpaceCompare(t)));
    }
}
