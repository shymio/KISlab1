package ea.kislab1.Component;

import ea.kislab1.Interceptors.ProfileInterceptor;
import ea.kislab1.SortingMethods.IRoundingStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import jakarta.annotation.PostConstruct;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.List;

@Service
public class CollectionRound {

    @Value("${serviceName:}")
    private String serviceName;

    private IRoundingStrategy rounder;

    private String resolvedServiceName = "";

    @Autowired
    private ApplicationContext context;

    /**
     * Опциональный инжект: если раскомментировать и поставить нужный @Qualifier,
     * Spring будет использовать конкретную реализацию.
     */
//    @Autowired(required = false)
//    @Qualifier("up")
    private IRoundingStrategy qualifiedRounder;

    @Autowired
    ProfileInterceptor profileInterceptor;

    public String getServiceName() {
        return (resolvedServiceName == null || resolvedServiceName.isBlank())
                ? serviceName
                : resolvedServiceName;
    }

    @PostConstruct
    public void init() {
        try {
            if (qualifiedRounder != null) {
                this.rounder = qualifiedRounder;
                this.resolvedServiceName = "Qualifier - " + qualifiedRounder.getClass().getSimpleName();
                return;
            }

            if (serviceName != null && !serviceName.isBlank()) {
                rounder = (IRoundingStrategy) context.getBean(serviceName);
                resolvedServiceName = "bean-name:" + serviceName;
                return;
            }

            InputStream is = getClass().getClassLoader().getResourceAsStream("rounding-config.xml");
            if (is != null) {
                Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
                doc.getDocumentElement().normalize();
                String className = doc.getElementsByTagName("rounder-class")
                        .item(0)
                        .getTextContent()
                        .trim();
                Class<?> clazz = Class.forName(className);
                rounder = (IRoundingStrategy) context.getBean(clazz);
                resolvedServiceName = "From-XML - " + clazz.getSimpleName();
                return;
            }

            throw new IllegalStateException("rounding-config.xml not found and serviceName is empty and no qualifier provided");
        } catch (Exception e) {
            throw new RuntimeException("Не удалось инициализировать IRoundingStrategy: " + e.getMessage(), e);
        }
    }

    public void round(List<Double> list) {
        if (rounder == null) {
            throw new IllegalStateException("IRoundingStrategy не инициализирован");
        }
        for (int i = 0; i < list.size(); i++) {
            double original = list.get(i);
            double rounded = rounder.round(original);
            list.set(i, rounded);
        }
    }
}
