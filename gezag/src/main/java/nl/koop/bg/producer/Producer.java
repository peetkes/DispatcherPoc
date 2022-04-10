package nl.koop.bg.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.koop.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.Queue;

@RestController
@RequestMapping("/produce")
public class Producer {
  @Autowired
  private JmsTemplate jmsTemplate;

  @Autowired
  private Queue queue;

  @PostMapping("/message")
  public Student sendMessage(@RequestBody Student student) {

    try {
      ObjectMapper mapper = new ObjectMapper();
      String studentAsJson = mapper.writeValueAsString(student);

      jmsTemplate.convertAndSend(queue, studentAsJson);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return student;
  }
}
