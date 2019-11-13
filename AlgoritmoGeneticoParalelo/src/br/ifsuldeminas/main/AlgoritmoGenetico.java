package br.ifsuldeminas.main;

import java.util.ArrayList;
import java.util.Random;

public class AlgoritmoGenetico {

    private ArrayList<IndividuoModel> populacao;
    private int probabilidadeMutacao;
    private final int TAM_CROMOSSOMO = 5;

    public AlgoritmoGenetico(int tam, int prob) {
        this.populacao = new ArrayList();
        this.probabilidadeMutacao = prob;
        inicializarPopulacao(tam);
    }

    public void mostrarPopulacao() {
        for (IndividuoModel i : this.populacao) {
            i.mostrarIndividuo();
        }
    }

    public void inicializarPopulacao(int tam) {
        Random r = new Random();

        for (int i = 0; i < tam; i++) {
            int cromossomo[] = new int[TAM_CROMOSSOMO];

            //Primeira posicao do cromossomo > -1 e < 2
            cromossomo[0] = r.nextInt(3) - 1;
            //Ultima posicao do cromossomo != 0
            cromossomo[TAM_CROMOSSOMO - 1] = r.nextInt(9) + 1;

            for (int j = 1; j < TAM_CROMOSSOMO - 1; j++) {
                cromossomo[j] = r.nextInt(10);
            }
            IndividuoModel novoIndividuo = new IndividuoModel(cromossomo);
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
}
