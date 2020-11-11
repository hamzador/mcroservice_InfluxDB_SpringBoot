package org.anl.controllers;



import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.anl.entities.TempLevel;
import org.anl.entities.Temperature;
import org.influxdb.dto.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.influxdb.InfluxDBTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
@RequestMapping("/users")
public class TempController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private InfluxDBTemplate<Point> influxDBTemplate;
	
	
	
	private TempLevel level= new TempLevel();

	
	
	@RequestMapping("/{userId}")
	public List<Map<String,Object>> getTemperature(/*@PathVariable("userId")*/ String userId ){
	

			//For each movie ID, call movie info service and get details 
		List<Map<String,Object>> temp = restTemplate.getForObject("http://localhost:9999/source/"+ userId, List.class );	 
		
		
		for(Map<String,Object> tmpr : temp ) {
			
			System.out.println("111111111111111111111111");
				influxDBTemplate.createDatabase();
				System.out.println("22222222222");
				 Point p = Point.measurement("temperature")		 
						  .addFieldsFromPOJO(tmpr)
						  .build();
						influxDBTemplate.write(p);
			
						System.out.println("333333333333333333333");
		}
			
			
			return temp;  
	
		//return Collections.singletonList(
			//new CatalogIthem("transformes", "description filed", 4));
		 
	}
	
	@RequestMapping("/message/{userId}")
	public List<Map<String,Object>> levelTemp(@PathVariable("userId") String userId) {
		
		
		List<Map<String,Object>> temp =  getTemperature("userId");
		System.out.println(temp.toString());
		
		for(Map<String,Object> tmpr : temp ) {
			System.out.println(tmpr.toString());
			
			double valueT = (double) tmpr.get("valueTemperature");
			System.out.println("valueT"+valueT);
			
			if(   valueT >= level.getMeasure2() && valueT <= level.getMeasure1() ) {
				
				tmpr.put("level", level.getLevel2());
								
			}
			else
			{
				if(valueT > level.getMeasure1() && valueT <= level.getMeasure0() ) {
					tmpr.put("level", level.getLevel1());
					
				}
				else 
					if( valueT > level.getMeasure0()) {
						tmpr.put("level", level.getLevel0());
					}	
			}
		}
		
		
		
		return temp;
	}
	
	

}
