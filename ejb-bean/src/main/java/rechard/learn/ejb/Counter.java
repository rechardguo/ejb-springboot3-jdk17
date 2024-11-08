package rechard.learn.ejb;

import java.rmi.RemoteException;

public interface Counter {
	 int getCount() throws RemoteException;


	 void increment() throws RemoteException;
}
