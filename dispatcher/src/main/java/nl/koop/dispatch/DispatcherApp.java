package nl.koop.dispatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

@SpringBootApplication
@ComponentScan(basePackages = "nl.koop.dispatch.*")
public class DispatcherApp {

  public static void main(String[] args) throws IOException {
    SpringApplication.run(DispatcherApp.class, args);
  }
}
