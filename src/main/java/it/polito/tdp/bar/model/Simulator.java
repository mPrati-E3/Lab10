package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.PriorityQueue;

import it.polito.tdp.bar.model.Event.EventType;


public class Simulator {
	
		//coda eventi
		private PriorityQueue<Event> queue = new PriorityQueue<Event>();
	
		//paramentri di simulazione----------------------------------------------------
		private final int numeroEventi = 2000;
		private final int numeroTavoli = 15;
		private Duration T_IN;
		//tavoli?
		
		//variabili da calcolare--------------------------------------------------------
		private int clienti;
		private int insoddisfatti;
		private int soddisfatti;
		
		public void setT_IN(Duration t_IN) {
			T_IN = t_IN;
		}

		public int getClienti() {
			return clienti;
		}

		public int getInsoddisfatti() {
			return insoddisfatti;
		}

		public int getSoddisfatti() {
			return soddisfatti;
		}
		
		//metodo di simulazione vero e prorpio------------------------------------------
		public void run() {
			
			//simulazione iniziale (mondo+coda eventi)
			this.clienti=0;
			this.insoddisfatti=0;
			this.soddisfatti=0;
			
			
			this.queue.clear();
			

			
			do {
				
				Event e = new Event(
						Duration.of((long) (numTime*10), ChronoUnit.MINUTES), EventType.ARRIVO_GRUPPO_CLIENTI);
				
				queue.add(e);
				
				double numTime = Math.random();
				oraArrivoClienti=oraArrivoClienti.plus(Duration.of((long) (numTime*10), ChronoUnit.MINUTES));
				
			} while (oraArrivoClienti.isBefore(this.oraChiusura));
			
			//esecuzione del ciclo di simulazione
			
			while (!this.queue.isEmpty()) {
				
				Event e = this.queue.poll();
				System.out.println(e);
				
				processEvent(e);
			}
			
		}

		private void processEvent(Event e) {
			// TODO Auto-generated method stub
			
		}
		
		
		
		


}
