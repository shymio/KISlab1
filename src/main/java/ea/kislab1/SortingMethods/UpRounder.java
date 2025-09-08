package ea.kislab1.SortingMethods;

import org.springframework.stereotype.Service;

@Service("up")
public class UpRounder implements IRoundingStrategy {
    @Override
    public double round(double value) {
        return Math.ceil(value);
    }
}
