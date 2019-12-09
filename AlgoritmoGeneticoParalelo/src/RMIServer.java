
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RMIServer extends UnicastRemoteObject implements RMI {

    private static final long serialVersionUID = 1L;
    private ArrayList<IndividuoModel> lista;
    private ArrayList<IndividuoModel> novaLista;
    private int numIteracao = Integer.MIN_VALUE;
    private RMI remoteObjectReference;

    public RMIServer() throws RemoteException, MalformedURLException, NotBoundException {
        this.novaLista = new ArrayList<IndividuoModel>();
        System.setProperty("java.rmi.server.hostname", "172.16.104.59");
        Naming.rebind("rmi://172.16.104.59/RMIServer", this);
    }
    
     public ArrayList<IndividuoModel> getNovaLista() {
        return novaLista;
    }

    public void start() throws RemoteException, InterruptedException {
        int numIteracao = 0;
                
        do {
            Thread.currentThread().sleep(1000);
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

    public ArrayList<IndividuoModel> getIndividuosVizinho() throws RemoteException {
        return this.lista;
    }

    public void setLista(ArrayList<IndividuoModel> lista) {
        this.lista = lista;
    }

    public void setNumIteracao(int numIteracao) {
        this.numIteracao = numIteracao;
    }

    public void setObjectReference(String vizinho) throws MalformedURLException, RemoteException, NotBoundException {
        this.remoteObjectReference = (RMI) Naming.lookup("rmi://" + vizinho + "/RMIServer");
    }
}
