package cz.dusanrychnovsky.programmingpearls.ch02.eB;

import java.util.List;

public class Reversion<T>  {

    public void apply(List<T> list) {
        apply(list, 0, list.size() - 1);
    }

    public void apply(List<T> list, int fromPos, int toPos) {
        while (fromPos < toPos) {
            swap(list, fromPos, toPos);
            fromPos++;
            toPos--;
        }
    }

    private void swap(List<T> vector, int firstPos, int secondPos) {
        T tmp = vector.get(firstPos);
        vector.set(firstPos, vector.get(secondPos));
        vector.set(secondPos, tmp);
    }
}
