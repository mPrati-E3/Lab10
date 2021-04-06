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
		private int eventiFatti=0;
		private final int numeroEventiTot = 2000;
		private final int numeroTavoli = 15;
		private final float sogliaTol = (float) 0.9f;
		private final LocalTime oraArrivo = LocalTime.of(6, 00);
		private Tavolo[] tavoli = {
				new Tavolo(0,10,false),
				new Tavolo(1,10,false),
				new Tavolo(2,8,false),
				new Tavolo(3,8,false),
				new Tavolo(4,8,false),
				new Tavolo(5,8,false),
				new Tavolo(6,6,false),
				new Tavolo(7,6,false),
				new Tavolo(8,6,false),
				new Tavolo(9,6,false),
				new Tavolo(10,4,false),
				new Tavolo(11,4,false),
				new Tavolo(12,4,false),
				new Tavolo(13,4,false),
				new Tavolo(14,4,false)
		};
		
		//variabili da calcolare--------------------------------------------------------
		private int clienti;
		private int insoddisfatti;
		private int soddisfatti;
		

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
		public String run() {
			
			//simulazione iniziale (mondo+coda eventi)
			this.clienti=0;
			this.insoddisfatti=0;
			this.soddisfatti=0;
			this.eventiFatti=0;
			for (int i=0; i<numeroTavoli; i++) {
				tavoli[i].setOccupato(false);
			}
			
			this.queue.clear();
			
			do {
				
				double numTime = Math.random();
				this.oraArrivo.plus(Duration.of((long) (numTime*10), ChronoUnit.MINUTES));
				double numPers = Math.random();
				double numDur = Math.random();
				double numTol = Math.random();
				Event e = new Event(
						oraArrivo,
						EventType.ARRIVO_GRUPPO_CLIENTI,
						(int) (numPers*10),
						Duration.of((long) ((numDur*60)+60), ChronoUnit.MINUTES),
						(float) numTol,
						-1
						);
				
				queue.add(e);
				
				this.eventiFatti++;
				
				
			} while (this.eventiFatti<numeroEventiTot);
			
			//esecuzione del ciclo di simulazione
			
			while (!this.queue.isEmpty()) {
				
				Event e = this.queue.poll();
				System.out.println(e);
				
				processEvent(e);
			}
			
			
			return " Clienti arrivati: "+this.clienti+
					"\n Clienti soddisfatti: "+this.soddisfatti+
					"\n Clienti insoddisfatti: "+this.insoddisfatti;
			
		}

		private void processEvent(Event e) {
			
			switch (e.getType()) {
			
			case ARRIVO_GRUPPO_CLIENTI:
				
				int quantiSono = e.getNum_persone();
				boolean liHoPiazzati=false;
				
				clienti=clienti+quantiSono;
				
				for (int i=0; i<numeroTavoli; i++) {
					if ((!tavoli[i].isOccupato()) && tavoli[i].getPosti()>=quantiSono) {
						
						soddisfatti=soddisfatti+quantiSono;
						tavoli[i].setOccupato(true);
						liHoPiazzati=true;
						
						Event nuovo = new Event(
								e.getTime().plus(e.getDurata()),
								EventType.CLIENTI_LASCIANO_BAR,
								quantiSono,
								e.getDurata(),
								(float) e.getTolleranza(),
								tavoli[i].getId()
								);
						
						queue.add(nuovo);
						
						break;
					}	
				}
				
				if (!liHoPiazzati && e.getTolleranza()<sogliaTol) {
					
					liHoPiazzati=true;
					soddisfatti=soddisfatti+quantiSono;
					
					Event nuovo = new Event(
							e.getTime().plus(e.getDurata()),
							EventType.CLIENTI_LASCIANO_BAR,
							quantiSono,
							e.getDurata(),
							(float) e.getTolleranza(),
							-1
							);
					
					queue.add(nuovo);
					
					
				} else if (!liHoPiazzati){
					insoddisfatti=insoddisfatti+quantiSono;
				}
				
				
				break;
				
			case CLIENTI_LASCIANO_BAR:
				
				for (int i=0; i<numeroTavoli; i++) {
					if (tavoli[i].getId()==e.getTavoloAssociato()) {
						tavoli[i].setOccupato(false);
					}
				}

				
				break;
			}
			
		}
		
		
		
		


}
