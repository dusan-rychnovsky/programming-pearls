package cz.dusanrychnovsky.programmingpearls.ch02.eB;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class ShiftTest {

    private Random rnd = new Random();

    @Test
    public void shiftOfLength0HasNoEffectOnTheList() {

        List<Integer> rndList = generateRandomList(10, 100);
        List<Integer> origList = new ArrayList<>(rndList);

        new ReversionBasedLeftShift().apply(rndList, 0);
        assertEquals(origList, rndList);

        new ReversionBasedRightShift().apply(rndList, 0);
        assertEquals(origList, rndList);
    }

    @Test
    public void aRightShiftRevertsAPreviousLeftShiftWithTheSameLength() {

        List<Integer> rndList = generateRandomList(10, 100);
        List<Integer> origList = new ArrayList<>(rndList);

        new ReversionBasedLeftShift().apply(rndList, 2);
        new ReversionBasedRightShift().apply(rndList, 2);

        assertEquals(origList, rndList);
    }

    private List<Integer> generateRandomList(int length, int maxVal) {

        List<Integer> result = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            result.add(rnd.nextInt(maxVal));
        }

        return result;
    }
}
