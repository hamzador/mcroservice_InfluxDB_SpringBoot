package org.anl.entities;

import java.util.List;

public class Message {
	
	private List<Temperature> temperature;
	private double level;
	
	
	
	
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public List<Temperature> getTemperature() {
		return temperature;
	}
	public void setTemperature(List<Temperature> temperature) {
		this.temperature = temperature;
	}
	public double getLevel() {
		return level;
	}
	public void setLevel(double level) {
		this.level = level;
	}
	
	

}
