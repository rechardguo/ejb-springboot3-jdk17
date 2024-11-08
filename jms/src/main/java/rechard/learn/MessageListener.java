package rechard.learn;

import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;


@Component
public class MessageListener {


    @JmsListener(destination = "TIW.IDEAL.IN.OTT.RCF.REQ")
    public void onMessage(Message msg){

        System.out.println(msg);

    }
}
