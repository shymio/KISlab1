package ea.kislab1.SortingMethods;

import org.springframework.stereotype.Service;

@Service("down")
public class DownRounder implements IRoundingStrategy {
    @Override
    public double round(double value) {
        // Округление вниз
        return Math.ceil(value);
    }
}
