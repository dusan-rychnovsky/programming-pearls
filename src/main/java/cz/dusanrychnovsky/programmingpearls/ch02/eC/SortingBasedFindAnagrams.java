package cz.dusanrychnovsky.programmingpearls.ch02.eC;

import lombok.Value;
import java.util.*;

public class SortingBasedFindAnagrams implements FindAnagrams {

    @Override
    public List<List<String>> apply(List<String> words) {

        LinkedList<Entry> entries = buildEntries(words);
        entries.sort(Comparator.comparing(e -> e.getCannonical()));
        return extractGroups(entries);
    }

    private LinkedList<Entry> buildEntries(List<String> words) {

        LinkedList<Entry> result = new LinkedList<>();
        for (String word : words) {
            result.add(new Entry(word, sort(word)));
        }

        return result;
    }

    private String sort(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private List<List<String>> extractGroups(LinkedList<Entry> entries) {

        List<List<String>> result = new LinkedList<>();
        while (!entries.isEmpty()) {
            result.add(extractGroup(entries));
        }

        return result;
    }

    private List<String> extractGroup(LinkedList<Entry> entries) {

        if (entries.isEmpty()) {
            throw new IllegalArgumentException("Cannot extract a group from an empty list.");
        }

        List<String> result = new LinkedList<>();

        Entry first = entries.pop();
        result.add(first.getWord());

        while (!entries.isEmpty()) {

            Entry current = entries.peek();
            if (!current.getCannonical().equals(first.getCannonical())) {
                return result;
            }

            result.add(current.getWord());
            entries.removeFirst();
        }

        return result;
    }

    @Value
    private static class Entry {

        private String word;
        private String cannonical;

    }
}
