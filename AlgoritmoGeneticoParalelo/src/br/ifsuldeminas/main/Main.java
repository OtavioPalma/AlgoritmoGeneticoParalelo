package br.ifsuldeminas.main;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        AlgoritmoGenetico ag = new AlgoritmoGenetico(1000, 10);
        ag.mostrarPopulacao();
        ag.evolucao(100);
        ag.mostrarPopulacao();
    }
}
