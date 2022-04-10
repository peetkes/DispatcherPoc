package nl.koop.dispatch.config;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Queue;

@Configuration
@EnableJms
public class SpringActiveMQConfig {
  @Value( "${activemq.url}" )
  private String brokerUrl;

  @Value( "${activemq.user}" )
  private String user;

  @Value( "${activemq.password}" )
  private String password;

  @Value( "${activemq.queue.in}" )
  private String queueName;

  @Bean
  public Queue queue() {
    return new ActiveMQQueue(queueName);
  }

  @Bean
  public ActiveMQConnectionFactory activeMQConnectionFactory() {
    ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
    activeMQConnectionFactory.setBrokerURL(brokerUrl);
    if (!"".equals(user)) {
      activeMQConnectionFactory.setUserName(user);
      activeMQConnectionFactory.setPassword(password);
    }
    return activeMQConnectionFactory;
  }

  @Bean
  public JmsTemplate jmsTemplate() {
    return new JmsTemplate(activeMQConnectionFactory());
  }

}