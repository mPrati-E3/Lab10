package it.polito.tdp.bar.model;

public class Model {
	
	Simulator simulatore = new Simulator();

	//fa partire il simulatore
	public String Simula() {
		
		return this.simulatore.run();
	}

}
