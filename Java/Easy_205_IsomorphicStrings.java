package Java;

import kotlin.Pair;

import java.util.HashMap;
import java.util.HashSet;

public class Easy_205_IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        HashMap<Character, Character> sMap = new HashMap<>();
        for (int i = 0; i <= s.length() - 1; i++) {
            if (sMap.containsKey(s.charAt(i))) {
                if (!sMap.get(s.charAt(i)).equals(t.charAt(i))) {
                    return false;
                }
            }
            sMap.put(s.charAt(i), t.charAt(i));
            boolean hasDupeValues = new HashSet<>(sMap.values()).size() != sMap.size();
            if (hasDupeValues) {
                return false;
            }
        }
        return true;
    }
}
