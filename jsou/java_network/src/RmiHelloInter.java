import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiHelloInter extends Remote {
	String sayHello(String name) throws RemoteException;
}
