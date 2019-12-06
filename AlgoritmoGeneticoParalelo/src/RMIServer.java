
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIServer extends UnicastRemoteObject implements RMI {

    private static final long serialVersionUID = 1L;
    private final ArrayList<IndividuoModel> lista;
    private ArrayList<IndividuoModel> novaLista;
    private int numIteracao = Integer.MIN_VALUE;

    public ArrayList<IndividuoModel> getIndividuosVizinho() throws RemoteException {
        return this.lista;
    }

    public RMIServer(ArrayList<IndividuoModel> lista, int numIteracao) throws RemoteException {
        this.lista = lista;
        this.novaLista = new ArrayList<IndividuoModel>();
        this.numIteracao = numIteracao;
    }

    public ArrayList<IndividuoModel> getNovaLista() {
        return novaLista;
    }

    public void start() throws NotBoundException, MalformedURLException, RemoteException, InterruptedException {
        System.setProperty("java.rmi.server.hostname", "192.168.2.5");
        Naming.rebind("rmi://192.168.2.5/RMIServer", this);

        Thread.currentThread();
        Thread.sleep(5000);

        int numIteracao = 0;
        RMI remoteObjectReference = null;

        do {
            remoteObjectReference = (RMI) Naming.lookup("rmi://192.168.2.2/RMIServer");
            numIteracao = remoteObjectReference.getIteracaoVizinho();
        } while (this.numIteracao != numIteracao);

        this.novaLista = remoteObjectReference.getIndividuosVizinho();

        /* MOSTRAR NOVOS INDIVIDUOS */
        System.out.println("Individuos da iteração " + this.numIteracao + " recebidos");
        for (IndividuoModel individuo : remoteObjectReference.getIndividuosVizinho()) {
            individuo.mostrarIndividuo();
        }
    }

    @Override
    public int getIteracaoVizinho() throws RemoteException {
        return this.numIteracao;
    }
}
