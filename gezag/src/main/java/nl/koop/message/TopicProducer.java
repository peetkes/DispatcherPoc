package nl.koop.message;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.koop.dto.Person;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

@Slf4j
@RequiredArgsConstructor
@Component
public class TopicProducer {
  private final JmsTemplate jmsTemplate;

  @Value("${activemq.topic-name}")
  private String destinationTopic;

  public void send(Person person) throws JMSException {
    Gson gson = new Gson();
    String jsonPerson = gson.toJson(person);

    jmsTemplate.convertAndSend(destinationTopic, jsonPerson);
  }

}
