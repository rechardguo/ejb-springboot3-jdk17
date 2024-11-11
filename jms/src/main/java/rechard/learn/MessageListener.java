package rechard.learn;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
public class MessageListener {


    @JmsListener(destination = "Q2")
    public void onMessage(Message msg){

        System.out.println(msg);

    }
}
