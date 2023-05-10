package tn.esprit.pidev4sae2back;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.esprit.pidev4sae2back.configuration.CorsConfig;

@EnableScheduling
@SpringBootApplication

@Import(CorsConfig.class)
public class Pidev4Sae2BackApplication {



    public static void main(String[] args) {
        SpringApplication.run(Pidev4Sae2BackApplication.class, args);

    }



}
