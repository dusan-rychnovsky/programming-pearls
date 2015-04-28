package cz.dusanrychnovsky.programmingpearls.ch02.eC;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class FindAnagramsTest {

    private FindAnagrams impl = new SortingBasedFindAnagrams();

    @Test
    public void initialTest() {

        List<String> words = asList(
            "pots",
            "microphotographics",
            "photomicrographics",
            "stop",
            "tops"
        );

        List<List<String>> groups = impl.apply(words);

        assertEquals(2, groups.size());
        assertTrue(groups.contains(asList("pots", "stop", "tops")));
        assertTrue(groups.contains(asList("microphotographics", "photomicrographics")));
    }


}
