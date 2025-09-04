package ea.kislab1.SortingMethods;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("quicksort")
public class Quicksort implements ICollectionSorter {
    @Override
    public <T extends Comparable<? super T>> void sort(List<T> list) {
        doSort(list,0,list.size()-1);
    }

    private <T extends Comparable<? super T>>  void doSort(List<T> list, int start, int end) {
        if (start >= end)
            return;
        int i = start, j = end;
        int cur = i - (i - j) / 2;
        while (i < j) {
            while (i < cur && (list.get(i).compareTo(list.get(cur)) <= 0)) {
                i++;
            }
            while (j > cur && (list.get(cur).compareTo(list.get(j)) <= 0)) {
                j--;
            }
            if (i < j) {
                Collections.swap(list,i,j);
                if (i == cur)
                    cur = j;
                else if (j == cur)
                    cur = i;
            }
        }
        doSort(list, start, cur);
        doSort(list, cur+1, end);
    }
}
