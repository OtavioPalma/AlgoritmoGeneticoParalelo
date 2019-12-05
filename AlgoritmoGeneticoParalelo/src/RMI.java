
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;

public interface RMI extends Remote {

    public ArrayList<IndividuoModel> getIndividuosVizinho() throws RemoteException;

    public int getIteracaoVizinho() throws RemoteException;
}
