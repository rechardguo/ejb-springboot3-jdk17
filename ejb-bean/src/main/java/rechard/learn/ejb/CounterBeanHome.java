package rechard.learn.ejb;

import java.rmi.RemoteException;

import jakarta.ejb.CreateException;
import jakarta.ejb.EJBHome;

public interface CounterBeanHome extends EJBHome {
	CounterBeanRemote create() throws RemoteException, CreateException;

}
