package ru.geekbrains.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.geekbrains.service.ProductRepresentative;
import ru.geekbrains.service.ProductServiceLocal;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

@MessageDriven(
        activationConfig = {
                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Queue"),
                @ActivationConfigProperty(propertyName = "destination", propertyValue = "java:jboss/exported/jms/queue/productQueue")
        }
)
public class JmsProductBean implements MessageListener {
    private final Logger logger = LoggerFactory.getLogger(JmsProductBean.class);

    @EJB
    private ProductServiceLocal productService;

    @Override
    public void onMessage(Message message) {
        logger.info("New JMS message");

        if (message instanceof ObjectMessage) {
            ObjectMessage om = (ObjectMessage) message;

            try {
                productService.insert((ProductRepresentative) om.getObject());
            } catch (JMSException e) {
            logger.info("",e);
            }
        }

    }
}
