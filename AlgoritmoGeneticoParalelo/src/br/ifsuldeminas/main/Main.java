package br.ifsuldeminas.main;

public class Main {

    public static void main(String[] args) {
        AlgoritmoGenetico ag = new AlgoritmoGenetico(5000, 10);
        ag.mostrarPopulacao();
        ag.evolucao(50);
        ag.mostrarPopulacao();
    }
}
