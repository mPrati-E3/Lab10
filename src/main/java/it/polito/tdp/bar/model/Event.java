package it.polito.tdp.bar.model;

import java.time.LocalTime;

public class Event implements Comparable<Event>{
	
	public enum EventType {
		ARRIVO_GRUPPO_CLIENTI, CLIENTI_LASCIANO_BAR
	}
	
	private LocalTime time ;
	private EventType type ;
	private int num_persone;
	private LocalTime durata;
	private float tolleranza;
	
	/**
	 * @param time
	 * @param type
	 */
	public Event(LocalTime time, EventType type, int np, LocalTime durata, float tolleranza) {
		super();
		this.time = time;
		this.type = type;
		this.num_persone=np;
		this.durata=durata;
		this.tolleranza=tolleranza;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public int getNum_persone() {
		return num_persone;
	}

	public void setNum_persone(int num_persone) {
		this.num_persone = num_persone;
	}

	public LocalTime getDurata() {
		return durata;
	}

	public void setDurata(LocalTime durata) {
		this.durata = durata;
	}

	public float getTolleranza() {
		return tolleranza;
	}

	public void setTolleranza(float tolleranza) {
		this.tolleranza = tolleranza;
	}

	@Override
	public String toString() {
		return "Event [time=" + time + ", type=" + type + ", num_persone=" + num_persone + ", durata=" + durata
				+ ", tolleranza=" + tolleranza + "]";
	}

	


	
	

	
	

}