package br.ifsuldeminas.main;

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
        return (this.cromossomo * Math.sin(10 * this.cromossomo * Math.PI) + 1);
    }

    public Double getAptidao() {
        double fenotipo = ((getFenotipo() - 1) * (-1));

        if (fenotipo > 0.0) {
            return fenotipo * (100.00);
        } else {
            return fenotipo * (-1.0);
        }
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

    public void inverte() {
        this.cromossomo = -this.cromossomo;
    }
}
