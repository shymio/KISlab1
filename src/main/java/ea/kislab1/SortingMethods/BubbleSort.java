package ea.kislab1.SortingMethods;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("bubble")
@Primary
public class BubbleSort implements ICollectionSorter {
    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        boolean repeat = true;
        while (repeat) {
            repeat = false;
            for (int i = 0, j = i + 1; j < list.size(); i++, j++) {
                T bubbleItem = list.get(i);
                T item = list.get(j);
                if (bubbleItem.compareTo(item) > 0) {
                    Collections.swap(list, i, j);
                    repeat = true;
                }
            }
        }
    }
}
