package cz.dusanrychnovsky.programmingpearls.ch02.eB;

import java.util.List;

public class ReversionBasedLeftShift<T> implements LeftShift<T> {

    @Override
    public void apply(List<T> list, int distance) {

        new Reversion().apply(list, 0, distance - 1);
        new Reversion().apply(list, distance, list.size() - 1);
        new Reversion().apply(list);
    }
}
