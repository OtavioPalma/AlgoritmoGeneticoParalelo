
import java.util.ArrayList;
import java.util.Random;

public class Operacoes {

    public IndividuoModel[] crossover(IndividuoModel i1, IndividuoModel i2) {
        Random r = new Random();
        int tam = i1.getTam();
        int pontoCorte = r.nextInt(tam);

        // Impede de alterar a primeira posicao
        if (pontoCorte == 0) {
            pontoCorte++;
        }

        IndividuoModel ni1 = i1.clonar();
        IndividuoModel ni2 = i2.clonar();

        for (int i = pontoCorte; i < tam; i++) {
            ni1.setGene(i, i2.getGene(i));
            ni2.setGene(i, i1.getGene(i));
        }

        return new IndividuoModel[]{ni1, ni2};
    }

    public void mutacao(IndividuoModel individuo, int probabilidade) {
        Random r = new Random();

        int pontoCorte = r.nextInt(101);

        if (probabilidade >= pontoCorte) {
            int tam = individuo.getTam();

            for (int i = 1; i < tam; i++) {
                individuo.setGene(i, r.nextInt(4) + 1);
            }
        }
    }

    public IndividuoModel roleta(ArrayList<IndividuoModel> populacao) {
        Random r = new Random();
        double aptidaoTotal = 0.0;
        double aptidaoAcumulada = 0.0;

        for (IndividuoModel i : populacao) {
            aptidaoTotal += i.getAptidao();
        }

        double pontoCorte = r.nextDouble() * aptidaoTotal;

        for (IndividuoModel i : populacao) {
            aptidaoAcumulada += i.getAptidao();
            if (pontoCorte <= aptidaoAcumulada) {
                return i;
            }
        }
        return null;
    }
}
