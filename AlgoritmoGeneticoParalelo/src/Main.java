
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main {

    public static void main(String[] args) throws RemoteException, MalformedURLException, InterruptedException, NotBoundException {
        AlgoritmoGenetico ag = new AlgoritmoGenetico(1000, 10);
        ag.mostrarPopulacao();
        ag.evolucao(100);
        ag.ordena();
        ag.mostrarPopulacao();
        /*for (int i = 0; i < 25; i++) {
            ag.evolucao(10);

            RMIServer server = new RMIServer(ag.getPopulacao());
            server.start();

            if (server.recebeu()) {
                ag.addLista(server.getNovaLista());
            }
        }
        System.out.println("Finalizado!");*/
    }
}
