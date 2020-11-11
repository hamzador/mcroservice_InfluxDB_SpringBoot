package org.anl.Services;

import java.time.Instant;
import java.util.List;

import org.influxdb.dto.Point;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.anl.entities.Temperature;
import org.anl.entities.Temperatures;
import org.influxdb.dto.BoundParameterQuery.QueryBuilder;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;


@Service
public class TempService {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private InfluxDBTemplate<Point> influxDBTemplate;
	
	public List<Temperature> getListTemperatures() {
		Query query = QueryBuilder.newQuery("SELECT * FROM temperature")
		        .forDatabase("betainfllux")
		        .create();
		
		
		QueryResult queryResult = influxDBTemplate.query(query);
		
		
		return new InfluxDBResultMapper().toPOJO(queryResult, Temperature.class);
	}
	
	
	
	public Temperature getTem(@PathVariable("nameSensor") String nameSensor) {
		
		//Temperatures temp = restTemplate.getForObject("http://localhost:9999/"+nameSensor, Temperatures.class);
		Temperature temperature =restTemplate.getForObject("http://localhost:9999/"+nameSensor, Temperature.class);
		return new Temperature();
       
		
		//return temp.getUserTemperature();
       
    }
	
	

}
