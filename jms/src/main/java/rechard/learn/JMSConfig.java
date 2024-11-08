package rechard.learn;


import jakarta.jms.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListenerConfigurer;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerEndpointRegistrar;
import org.springframework.jms.config.SimpleJmsListenerEndpoint;
import org.springframework.jndi.JndiLocatorDelegate;

import javax.naming.NamingException;

@Configuration
@EnableJms
public class JMSConfig implements JmsListenerConfigurer {

    private  final  String mqConnectionJndi="";
    @Bean
    public ConnectionFactory jmsListenerContainerFactory() {
        JndiLocatorDelegate jndiLocatorDelegate = JndiLocatorDelegate.createDefaultResourceRefLocator();
        try {
            return jndiLocatorDelegate.lookup(mqConnectionJndi, ConnectionFactory.class);
        } catch (NamingException e) {
           e.printStackTrace();
        }
        return null;
    }

    @Override
    public void configureJmsListeners(JmsListenerEndpointRegistrar registrar) {
        SimpleJmsListenerEndpoint myEndpoint = new SimpleJmsListenerEndpoint();
        registrar.registerEndpoint(myEndpoint, jmsListenerContainerFactory());
    }
}
