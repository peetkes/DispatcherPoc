package nl.koop.message;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;

@Configuration
@EnableJms
public class JmsConfig {
  @Value( "${activemq.url}" )
  private String brokerUrl;

  @Value( "${activemq.user}" )
  private String user;

  @Value( "${activemq.password}" )
  private String password;

  @Value( "${activemq.queue.out}" )
  private String queue;

  @Value( "${activemq.client.id}" )
  private String clientId;

  @Bean
  public ActiveMQConnectionFactory connectionFactory() {
    if ("".equals(user)) {
      return new ActiveMQConnectionFactory(brokerUrl);
    }
    return new ActiveMQConnectionFactory(user, password, brokerUrl);
  }

  @Bean
  public Queue queue() {
    return new ActiveMQQueue(queue);
  }

  @Bean
  public JmsListenerContainerFactory jmsFactoryTopic(ConnectionFactory connectionFactory,
                                                     DefaultJmsListenerContainerFactoryConfigurer configurer) {
    DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
    configurer.configure(factory, connectionFactory);
    factory.setPubSubDomain(true);
    factory.setClientId(clientId);
    factory.setSubscriptionDurable(true);
    return factory;
  }

  @Bean
  public JmsTemplate jmsTemplate() {
    return new JmsTemplate(connectionFactory());
  }

  @Bean
  public JmsTemplate jmsTemplateTopic() {
    JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory());
    jmsTemplate.setPubSubDomain(true);
    return jmsTemplate;
  }
}
