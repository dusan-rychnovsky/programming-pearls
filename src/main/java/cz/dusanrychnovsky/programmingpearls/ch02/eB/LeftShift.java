package cz.dusanrychnovsky.programmingpearls.ch02.eB;

import java.util.List;

public interface LeftShift<T> {

    public void apply(List<T> list, int distance);
}
