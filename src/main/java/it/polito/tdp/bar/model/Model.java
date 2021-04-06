package it.polito.tdp.bar.model;

public class Model {
	
	Simulator simulatore = new Simulator();

	public String Simula() {
		
		return this.simulatore.run();
	}

}
