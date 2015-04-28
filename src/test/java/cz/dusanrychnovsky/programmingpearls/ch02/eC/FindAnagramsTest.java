package cz.dusanrychnovsky.programmingpearls.ch02.eC;

import com.google.common.io.Files;
import lombok.Value;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.*;

import static java.util.Arrays.asList;

public class FindAnagramsTest {

    private final List<FindAnagrams> implementations = asList(new SortingBasedFindAnagrams());
    private final List<TestCase> testCases;

    public FindAnagramsTest() throws URISyntaxException, IOException {

        File dir = new File(getClass().getResource("words").toURI());
        testCases = new LinkedList<>();

        for (File file : dir.listFiles()) {
            List<String> words = Files.readLines(file, Charset.forName("UTF-8"));
            testCases.add(new TestCase(words));
        }
    }

    @Test
    public void check() {

        for (FindAnagrams implementation : implementations) {
            for (TestCase testCase : testCases) {
                List<String> words = testCase.getWords();
                List<List<String>> result = implementation.apply(words);

                checkThatResultContainsAllOriginalWords(result, words);
                checkThatTheGroupsAreFormedByMutualAnagrams(result);
            }
        }
    }

    private void checkThatTheGroupsAreFormedByMutualAnagrams(List<List<String>> result) {

        for (List<String> group : result) {
            checkThatTheGroupIsFormedByMutualAnagrams(group);
        }
    }

    private void checkThatTheGroupIsFormedByMutualAnagrams(List<String> group) {

        Iterator<String> groupIt = group.iterator();
        String firstWord = groupIt.next();

        while (groupIt.hasNext()) {
            String currWord = groupIt.next();
            if (!areAnagrams(firstWord, currWord)) {
                throw new AssertionError(
                    "Words [" + firstWord + "] and [" + currWord + "] are not anagrams."
                );
            }
        }
    }

    private boolean areAnagrams(String first, String second) {
        return sort(first).equals(sort(second));
    }

    private String sort(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    private void checkThatResultContainsAllOriginalWords(List<List<String>> result, List<String> words) {

        for (String word : words) {

            boolean present = false;
            for (List<String> group : result) {
                if (group.contains(word)) {
                    present = true;
                    break;
                }
            }

            if (!present) {
                throw new AssertionError("Word [" + word + "] does not appear in any group.");
            }
        }
    }

    @Value
    private static class TestCase {
        private List<String> words;
    }
}
