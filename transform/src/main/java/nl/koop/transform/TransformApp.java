package nl.koop.transform;

import lombok.extern.slf4j.Slf4j;
import net.sf.saxon.s9api.SaxonApiException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TransformApp implements CommandLineRunner {

  public static void main(String[] args) {
    log.info("start the application");
    SpringApplication.run(TransformApp.class, args);
    log.info("Application finished");
  }

  @Override
  public void run(String... args) throws Exception {
    log.info("Run TransformApp");
    Transform tx = new TransformXsltImpl();
    try {
      tx.run();
    } catch (SaxonApiException e) {
      e.printStackTrace();
    }
  }
}
