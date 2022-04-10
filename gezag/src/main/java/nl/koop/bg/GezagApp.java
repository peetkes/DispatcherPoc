package nl.koop.bg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "nl.koop.bg.*")
public class GezagApp {
  public static void main(String[] args) {
    ApplicationContext ctx = SpringApplication.run(GezagApp.class, args);
  }
}
