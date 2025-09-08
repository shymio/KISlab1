package ea.kislab1.SortingMethods;

import org.springframework.stereotype.Service;

@Service("nearest")
public class NearestRounder implements IRoundingStrategy {
    @Override
    public double round(double value) {
        return (double) Math.round(value);
    }
}