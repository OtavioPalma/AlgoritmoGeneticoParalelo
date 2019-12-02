echo "Iniciando script"
javac IndividuoModel.java AlgoritmoGenetico.java Main.java Operacoes.java RMI.java RMIServer.java
start rmiregistry
java Main
