package ru.geekbrains;


import ru.geekbrains.persist.ProductCategory;
import ru.geekbrains.service.ProductRepresentative;

import javax.jms.*;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Properties;

public class JmsClient {
    public static void main(String[] args) throws IOException, NamingException {
        Context context = createInitialContext();

        ConnectionFactory connectionFactory = (ConnectionFactory) context.lookup("jms/RemoteConnectionFactory");
        JMSContext jmsContext = connectionFactory.createContext("jms-user", "123");

        Destination dest=(Destination)context.lookup("jms/queue/productQueue");
        JMSProducer producer = jmsContext.createProducer();
        ObjectMessage msg = jmsContext.createObjectMessage(new ProductRepresentative(null, "Mango", "Fruit perfecto", new BigDecimal(33.5), new ProductCategory(1L)));

        producer.send(dest,msg);
    }

    public static Context createInitialContext() throws IOException, NamingException {
        final Properties env = new Properties();
        env.load(EjbClient.class.getClassLoader().getResourceAsStream("wildfly-jndi.properties"));
        return new InitialContext(env);
    }
}
