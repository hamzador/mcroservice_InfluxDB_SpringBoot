package org.anl.entities;

import java.time.Instant;
import java.util.concurrent.TimeUnit;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "temperature", database = "betainflux", timeUnit = TimeUnit.MILLISECONDS)
public class Temperature {
	

    @Column(name = "nameSensor")
    private String nameSensor;
 
    @Column(name = "ValueT")
    private double valueTemperature;
    
    @Column(name = "time")
	private Instant time;
    
    @Column(name= "level")
    private int level;
	
    

    

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getNameSensor() {
		return nameSensor;
	}

	public void setNameSensor(String nameSensor) {
		this.nameSensor = nameSensor;
	}

	public double getValueTemperature() {
		return valueTemperature;
	}

	public void setValueTemperature(double valueTemperature) {
		valueTemperature = valueTemperature;
	}

	

	public void sendMessage(String userID) {
		
		if(this.valueTemperature>=10) {
			System.out.println("sendMessage");
		}
	}

	public Instant getTime() {
		return time;
	}

	public void setTime(Instant time) {
		this.time = time;
	}
	
	    

}
