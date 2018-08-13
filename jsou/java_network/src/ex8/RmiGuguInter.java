package ex8;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RmiGuguInter extends Remote{
	String guguDan(int dan) throws RemoteException;
}
