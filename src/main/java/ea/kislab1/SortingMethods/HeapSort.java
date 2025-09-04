package ea.kislab1.SortingMethods;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("heap")
public class HeapSort implements ICollectionSorter {
    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        list = makeHeap(list,list.size());
        for (int i = 0; i < list.size() - 1; i++) {
            Collections.swap(list, 0, list.size() - 1 - i);
            heapify(list, list.size() - i - 1, 0);
        }
    }

    private <T extends Comparable<? super T>> List<T> makeHeap(List<T> data, int size) {
        if (data == null || size <= 1) {
            return data;
        }
        for (int i = (data.size() - 2)/2; i >= 0; i--) {
            heapify(data, size, i);
        }
        return data;
    }

    private <T extends Comparable<? super T>> void heapify(List<T> data, int size, int i) {
        if (data == null || i >= size) {
            return;
        }
        int min_max = i;
        int left = 2*i + 1;
        int right = 2*i + 2;

        if (left < size && (data.get(min_max).compareTo(data.get(left)) < 0)) {
            min_max = left;
        }
        if (right < size && (data.get(min_max).compareTo(data.get(right)) < 0)) {
            min_max = right;
        }
        if (min_max != i) {
            Collections.swap(data, min_max, i);
            heapify(data, size, min_max);
        }
    }

}
