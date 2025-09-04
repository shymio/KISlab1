package ea.kislab1.Component;

import ea.kislab1.Interceptors.ProfileInterceptor;
import ea.kislab1.SortingMethods.ICollectionSorter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class CollectionSort<T> {

    @Value("${serviceName}")
    private String serviceName;

    private ICollectionSorter collectionSorter;

    @Autowired
    public void setCollectionSorter(ApplicationContext context) {
        collectionSorter = (ICollectionSorter) context.getBean(serviceName);
    }
/*    @Autowired
    @Qualifier("heap")
    private ICollectionSorter collectionSorter;*/

    @Autowired
    ProfileInterceptor profileInterceptor; // Перехватчик

    public String getServiceName() {
        return serviceName;
    }

    public  <T extends Comparable<? super T>> void sort(List<T> list) {
        collectionSorter.sort(list);
    }
}
