package br.ifsuldeminas.main;

import com.sun.javafx.geom.AreaOp;
import java.util.Arrays;

public class IndividuoModel {

    private Double cromossomo;
    private int[] cromossomoGene;

    public IndividuoModel(int[] cromossomo) {
        this.cromossomoGene = cromossomo;
        String cromossomoString = cromossomo[0] + ".";

        for (int i = 1; i < cromossomo.length; i++) {
            cromossomoString += cromossomo[i];
        }
        this.cromossomo = Double.parseDouble(cromossomoString);
    }

    public IndividuoModel(Double cromossomo) {
        this.cromossomo = cromossomo;
        this.cromossomoGene = String.valueOf(this.cromossomo)
                .replaceAll("\\D", "")
                .chars()
                .map(Character::getNumericValue)
                .toArray();
    }

    public Double getFenotipo() {
        Double aux = 10.0 * this.cromossomo;
        return (this.cromossomo * Math.sin(aux * Math.PI)) + 1;
    }

    public Double getAptidao() {
        return 1 / (Math.pow(getFenotipo(), 2) + 0.001);
    }

    public void mostrarIndividuo() {
        System.out.print("I =");
        System.out.print(" " + this.cromossomo);
        System.out.print(" Fenótipo: " + getFenotipo());
        System.out.print(" Aptidão: " + getAptidao());
        System.out.println();
    }

    public IndividuoModel clonar() {
        return new IndividuoModel(this.cromossomo);
    }

    public int getTam() {
        return this.cromossomoGene.length;
    }
    
    public void setGene(int pos, int valor) {
        this.cromossomoGene[pos] = valor;
    }
    
    public int getGene(int pos) {
        return this.cromossomoGene[pos];
    }
}
