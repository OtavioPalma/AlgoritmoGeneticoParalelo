
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIServer extends UnicastRemoteObject implements RMI {

    private final ArrayList<IndividuoModel> lista;
    private ArrayList<IndividuoModel> novaLista;
    private boolean recebeu;

    public ArrayList<IndividuoModel> hello() throws RemoteException {
        return this.lista;
    }

    public RMIServer(ArrayList<IndividuoModel> lista) throws RemoteException {
        this.lista = lista;
        this.novaLista = new ArrayList();
        this.recebeu = false;
    }

    public ArrayList<IndividuoModel> getNovaLista() {
        return novaLista;
    }

    public void start() throws NotBoundException, MalformedURLException, RemoteException, InterruptedException {
        System.setProperty("java.rmi.server.hostname", "192.168.2.5");
        Naming.rebind("rmi://192.168.2.5/RMIServer", this);

        Thread.currentThread().sleep(5000);

        RMI remoteObjectReference = null;
        while (this.novaLista.isEmpty()) {
            remoteObjectReference = (RMI) Naming.lookup("rmi://192.168.2.2/RMIServer");
            this.novaLista = remoteObjectReference.hello();
            this.recebeu = true;
        }

        if (remoteObjectReference != null) {
            /* MOSTRAR NOVOS INDIVIDUOS */
            System.out.println("Otavio PC enviou os individuos: ");
            for (IndividuoModel individuo : remoteObjectReference.hello()) {
                individuo.mostrarIndividuo();
            }
        }
    }

    public boolean recebeu() {
        return recebeu;
    }
}
