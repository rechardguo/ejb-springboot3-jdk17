package rechard.learn.ejb;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;

import rechard.learn.ejb.api.FirstApi;

@Stateless
public class FirstApiBean implements FirstApi{

	@TransactionAttribute
	public String sayHello(String name) {
		return "FirstApi hello"+name;
	}
}
