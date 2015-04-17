package cz.dusanrychnovsky.programmingpearls.ch02.eB;

import java.util.List;

public class ReversionBasedRightShift<T> implements RightShift<T> {

    @Override
    public void apply(List<T> list, int distance) {

        new Reversion().apply(list);
        new ReversionBasedLeftShift().apply(list, distance);
        new Reversion().apply(list);
    }
}
