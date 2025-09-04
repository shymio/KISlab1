package ea.kislab1;

import ea.kislab1.Component.AppStart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class KiSlab1Application {
    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(KiSlab1Application.class);
        app.setBanner((environment, sourceClass, out) -> out.print("\n\n\timitmod\n\n".toUpperCase()));
        context = app.run(args);
        AppStart appStart = context.getBean(AppStart.class);
        appStart.initArray();
        appStart.start();
    }
}