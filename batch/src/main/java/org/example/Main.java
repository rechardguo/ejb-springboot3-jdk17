package org.example;

import org.wildfly.security.auth.client.AuthenticationConfiguration;
import org.wildfly.security.auth.client.AuthenticationContext;
import org.wildfly.security.auth.client.MatchRule;
import rechard.learn.ejb.Counter;
import rechard.learn.ejb.CounterBeanHome;
import rechard.learn.ejb.CounterBeanRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;
import java.util.concurrent.Callable;

public class Main {
    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();

        // properties.put("remote.connections","default");
        // properties.put("remote.connection.default","127.0.0.1");
        // properties.put("remote.connection.default.port",8180);

        properties.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
        //properties.put(Context.PROVIDER_URL,"remote+https://127.0.0.1:8543");
         properties.put(Context.PROVIDER_URL,"https-remoting://localhost:8543");

        // properties.put(Context.PROVIDER_URL,"remote+http://127.0.0.1:8180");
        //properties.put(Context.PROVIDER_URL,"http-remoting://127.0.0.1:8180");
        properties.put(Context.SECURITY_CREDENTIALS,"password123");
        properties.put(Context.SECURITY_PRINCIPAL,"user1");
        InitialContext context = new InitialContext(properties);

        //If the EJB is in the same JVM as the Client that is calling it, then the Client should follow the JavaEE spec and defined Portable JNDI Naming Syntax such as java:global/, java:app/, java:module
        //If the Client is not in the same JVM as the EJB, then the 'ejb:' / ejb-client methods below would be used
        CounterBeanRemote remote= (CounterBeanRemote)context.lookup("ejb:/ejbweb/CounterService!rechard.learn.ejb.CounterBeanRemote");
        remote.increment();
        System.out.println(remote.getCount());
    }
}