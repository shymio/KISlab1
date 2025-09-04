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
    private CollectionRound collectionRound;

    private List<Double> integerList = new ArrayList<>();

    public String getServiceName() {
        return collectionRound.getServiceName();
    }

    public List<Double> getIntegerList() {
        return integerList;
    }

    public void initArray() {
        integerList.clear();
        for (int i = 0; i < elementSize; i++) {
            integerList.add(Math.random() * 1000.0);
        }
    }

    public void start() {
        collectionRound.round(integerList);
    }
}