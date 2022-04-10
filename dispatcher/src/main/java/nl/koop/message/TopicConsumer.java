package nl.koop.message;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.koop.dto.Person;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class TopicConsumer {

  @JmsListener( destination = "${activemq.topic-name}", subscription = "assinatura")
  public void listen(String message) {
    log.info(message);
    Person person = new Person();
    try {
      Gson gson = new Gson();
      person = gson.fromJson(message, Person.class);
      log.info(person.toString());

    }catch(Exception e){
      log.error(e.getMessage());
    }
  }
}
