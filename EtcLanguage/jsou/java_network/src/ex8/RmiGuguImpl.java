package ex8;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiGuguImpl extends UnicastRemoteObject implements RmiGuguInter{	
	public RmiGuguImpl() throws RemoteException{
		
	}
	
	@Override
	public String guguDan(int dan) throws RemoteException {
		System.out.println("서버 옴");
		String gugu = "";
		for (int i = 1; i <= 9; i++) {
			//gugu = gugu + dan + "*" + i + "=" + Integer.toString(i*dan) +"\n";
			gugu += dan + "*" + i + "=" + Integer.toString(i*dan) +"\n";
		}
		return gugu;
	}
}
