package rechard.learn.ejb;

import jakarta.jms.Message;
import jakarta.jms.MessageListener;
/**
 * MessageDrivenBean no required since 3.0
 */
 
// @MessageDriven(
//     name="TestMDB",  
//     messageListenerInterface=MessageListener.class,  
//     activationConfig = {
//         @ActivationConfigProperty(
//             propertyName = "clientId",
//             propertyValue = "TestMDB"),
//         @ActivationConfigProperty(
//             propertyName = "acknowledgeMode",
//             propertyValue = "Auto_acknowledge"),
//         @ActivationConfigProperty(
//             propertyName = "destinationType",
//             propertyValue = "jakarta.jms.Queue"),
//         @ActivationConfigProperty(
//             propertyName = "connectionFactoryLookup",
//             propertyValue = "java:/jboss/jms/XAConnectionFactory"),    
//         @ActivationConfigProperty(
//             propertyName = "destination",
//             propertyValue = "java:/jboss/jms/queue/TestQueue")
//     }
// )
public class TestMDB implements  MessageListener {
        public void onMessage(Message message){
        System.out.println("receive message:"+message);
    }
}
