package example;

import javax.jws.WebService;

@WebService
public interface WebServiceInterface {
	public String greet(String clientName);
	public double randTemp(String country, String city);
	public double getTemp(String country, String city) throws Exception;
	public double getWind(String country, String city) throws Exception;
}