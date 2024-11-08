package rechard.learn.ejb;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import rechard.learn.ejb.api.FirstApi;
import rechard.learn.ejb.api.SecondApi;

@Stateless
public class SecondApiBean implements SecondApi{
	
	@TransactionAttribute
	public String service(String name) {
		InitialContext context;
		try {
			context = new InitialContext();
			FirstApi helloBean = (FirstApi) context.lookup("java:global/ejbweb/FirstApiBean!rechard.learn.ejb.api.FirstApi");
			return "2nd service : "+helloBean.sayHello("EJB User");
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return "2nd service:"+name;
	}
}
