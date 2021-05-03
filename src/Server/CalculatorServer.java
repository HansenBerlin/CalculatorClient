package Server;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CalculatorServer 
{

	public static void main(String[] args) {
				
		try 
		{
			ServerImplementation proxy = new ServerImplementation();
			LocateRegistry.createRegistry(1234);
			//LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
			Naming.rebind("rmi://localhost/Calculator", proxy);
			System.out.print("Server registriert und bereit");
		} 
		catch (RemoteException e) 
		{
			e.printStackTrace();
		} 
		catch (MalformedURLException e) 
		{
			e.printStackTrace();    
		}
	}
}
