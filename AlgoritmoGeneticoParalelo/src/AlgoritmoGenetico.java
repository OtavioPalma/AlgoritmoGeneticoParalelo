
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class AlgoritmoGenetico {

    private ArrayList<IndividuoModel> populacao;
    private int probabilidadeMutacao;

    public AlgoritmoGenetico(int tam, int prob) {
        this.populacao = new ArrayList();
        this.probabilidadeMutacao = prob;
        inicializarPopulacao(tam);
    }

    public void mostrarPopulacao() {
        int j = 0;
        for (IndividuoModel i : this.populacao) {
            System.out.print(j + ":");
            i.mostrarIndividuo();
            j++;
        }
    }

    public void inicializarPopulacao(int tam) {
        Random r = new Random();
        int tamanhoCromossomo = 10;

        for (int i = 0; i < tam; i++) {
            int cromossomo[] = new int[tamanhoCromossomo];

            //Primeira posicao do cromossomo > -1 e < 2
            cromossomo[0] = r.nextInt(2);
            //Ultima posicao do cromossomo != 0
            cromossomo[tamanhoCromossomo - 1] = r.nextInt(9) + 1;

            for (int j = 1; j < tamanhoCromossomo - 1; j++) {
                cromossomo[j] = r.nextInt(10);
            }
            IndividuoModel novoIndividuo = new IndividuoModel(cromossomo);

            if (cromossomo[0] == 0) {
                if (new Random().nextInt(101) > 50) {
                    novoIndividuo.inverte();
                }
            }
            this.populacao.add(novoIndividuo);
        }
    }

    public void evolucao(int numGeracoes) {
        Operacoes operacoes = new Operacoes();

        while (numGeracoes > 0) {
            ArrayList<IndividuoModel> novaPopulacao = new ArrayList();

            while (novaPopulacao.size() < this.populacao.size()) {
                IndividuoModel i1 = operacoes.roleta(this.populacao);
                IndividuoModel i2 = operacoes.roleta(this.populacao);
                IndividuoModel filhos[] = operacoes.crossover(i1, i2);
                operacoes.mutacao(filhos[0], this.probabilidadeMutacao);
                operacoes.mutacao(filhos[1], this.probabilidadeMutacao);
                novaPopulacao.add(filhos[0]);
                novaPopulacao.add(filhos[1]);
            }
            this.populacao = novaPopulacao;
            numGeracoes--;
        }
        System.out.println("\nEVOLUÇÃO:\n");
    }

    public ArrayList<IndividuoModel> getPopulacao() {
        Collections.sort(populacao, (i1, i2) -> i1.getAptidao() < i2.getAptidao() ? 1 : i1.getAptidao() > i2.getAptidao() ? -1 : 0);
        ArrayList<IndividuoModel> melhoresIndividuos = new ArrayList();
        int tam = populacao.size() / 10;

        for (int i = 0; i < tam; i++) {
            melhoresIndividuos.add(populacao.get(i));
        }

        populacao.removeAll(melhoresIndividuos);
        return melhoresIndividuos;
    }

    public void addLista(ArrayList<IndividuoModel> lista) {
        this.populacao.addAll(lista);
    }

    public void ordena() {
        Collections.sort(populacao, (i1, i2) -> i1.getAptidao() < i2.getAptidao() ? 1 : i1.getAptidao() > i2.getAptidao() ? -1 : 0);
    }
}
