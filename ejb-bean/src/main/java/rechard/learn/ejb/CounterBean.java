package rechard.learn.ejb;

import java.rmi.RemoteException;

import jakarta.ejb.EJBException;
import jakarta.ejb.SessionBean;
import jakarta.ejb.SessionContext;

public class CounterBean implements Counter,SessionBean {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int count = 0;

    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
    }

    SessionContext ctx;


	@Override
	public void ejbRemove() throws EJBException, RemoteException {
		
	}

	@Override
	public void ejbActivate() throws EJBException, RemoteException {
		
	}

	@Override
	public void ejbPassivate() throws EJBException, RemoteException {
		
	}

	// @Override
	// public void setSessionContext(jakarta.ejb.SessionContext ctx) throws EJBException, RemoteException {
	// 	this.ctx = ctx;
	// }

    @Override
	public void setSessionContext(SessionContext ctx) throws EJBException, RemoteException {
		this.ctx = ctx;
	}

	
}