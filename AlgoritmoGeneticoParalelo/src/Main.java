
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Main {

    public static void main(String[] args)
            throws RemoteException, MalformedURLException, InterruptedException, NotBoundException {
        AlgoritmoGenetico ag = new AlgoritmoGenetico(1000, 10);
        ag.evolucao(25);
        ag.mostrarPopulacao();
        /*
         * ag.mostrarPopulacao(); for (int i = 0; i < 10; i++) { ag.evolucao(10);
         * 
         * RMIServer server = new RMIServer(ag.getPopulacao(), i); server.start();
         * 
         * ag.addLista(server.getNovaLista()); } System.out.println("Finalizado!");
         */
    }
}
