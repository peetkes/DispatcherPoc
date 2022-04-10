package nl.koop.transform;

import lombok.extern.slf4j.Slf4j;
import net.sf.saxon.s9api.Processor;
import net.sf.saxon.s9api.SaxonApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
public class TransformApp {

  private TransformApp() {

  }
  public static void main(String[] args) {
    log.info("Run TransformApp");
    Transform tx = new TransformImpl();
    try {
      tx.run();
    } catch (SaxonApiException e) {
      e.printStackTrace();
    }
  }
}
