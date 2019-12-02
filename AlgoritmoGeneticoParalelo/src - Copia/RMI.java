
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMI extends Remote {

    public ArrayList<IndividuoModel> hello() throws RemoteException;

}
