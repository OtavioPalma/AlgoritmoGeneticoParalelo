
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args)
            throws RemoteException, MalformedURLException, InterruptedException, NotBoundException {
        AlgoritmoGenetico ag = new AlgoritmoGenetico(1000, 10);
        RMIServer server = new RMIServer();

        new Scanner(System.in).nextLine();

        server.setObjectReference("172.16.104.117");

        for (int i = 0; i < 10; i++) {
            ag.evolucao(25);
            server.setLista(ag.getPopulacao());
            server.setNumIteracao(i);
            server.start();
            ag.addLista(server.getNovaLista());
        }
        System.out.println("Finalizado!");
        ag.mostrarPopulacao();
    }
}
