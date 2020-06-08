package me.gosimple.nbvcxz.matching;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class MungeTable {
    private static final String splitRegex = "((?<=\\Q%s\\E)|(?=\\Q%s\\E))";
    private Map<String, String[]> table;
    private List<String> keys;
    private Map<String, Pattern> regexPatterns;

    public MungeTable() {
        table = new HashMap<>();
        keys = new ArrayList<>();
        regexPatterns = new HashMap<>();
    }

    public MungeTable addSub(String key, String...subs) {
        table.put(key, subs);
        keys.add(key);
        regexPatterns.put(key, Pattern.compile(String.format(splitRegex, key, key)));
        return this;
    }

    public void sort() {
        // sort keys by their length, descending
        keys.sort(
                (String k1, String k2) -> -Integer.compare(k1.length(), k2.length())
        );
    }

    public List<String> getKeys() {
        return keys;
    }

    public String[] getSubs(String key) {
        return table.get(key);
    }

    public Pattern getKeyPattern(String key) {
        return regexPatterns.get(key);
    }
}
