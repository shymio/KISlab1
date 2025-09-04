package ea.kislab1.Component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class AppStart {
    @Value("${elementSize}")
    private int elementSize;
    @Autowired
    private CollectionSort<Integer> collectionSort;

    private List<Integer> integerList = new ArrayList<>();

    public String getServiceName() {
        return collectionSort.getServiceName();
    }

    public List<Integer> getIntegerList() {
        return integerList;
    }

    public void initArray() {
        for (int i = 0; i < elementSize; i++) {
            integerList.add((int) (Math.random()*1000));
        }
    }

    public void start() {
        collectionSort.sort(integerList);
    }
}
