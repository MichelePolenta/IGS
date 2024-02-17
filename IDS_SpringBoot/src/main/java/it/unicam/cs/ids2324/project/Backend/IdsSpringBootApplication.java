package it.unicam.cs.ids2324.project.Backend;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class IdsSpringBootApplication {

    @Autowired
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IdsSpringBootApplication.class, args);
        IdsSpringBootApplication application = context.getBean(IdsSpringBootApplication.class);
    }

}
