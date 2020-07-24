package example;
import java.net.MalformedURLException;
import java.util.Scanner;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class WebServiceClient {
	
	public static void main(String[] args) throws MalformedURLException {
		Scanner sc = new Scanner(System.in);
		//connect to WebService and request greeting
		URL url = new URL("http://localhost:8080/MyWebService/WebServiceInterface?wsdl");
		QName qname = new QName("http://example/", "WebService");
		Service service = Service.create(url, qname);
		WebServiceInterface callWebService = service.getPort(WebServiceInterface.class);
		System.out.println("Service output: \n" + callWebService.greet("WebServiceClient") + "\n");
		//request and get user input
		System.out.println("Enter country: ");
		String country = sc.nextLine();
		System.out.println("Enter city: ");
		String city = sc.nextLine();
		sc.close();
		//request weather data
		try {
			double windSpeed = callWebService.getWind(country, city);
			double temp = callWebService.getTemp(country, city);
			System.out.println("It is " + temp + " degrees celcius in " + city + ", " + country + ", with a wind speed of "+ windSpeed + "m/s." + "\n");
		} catch (Exception e) {
			System.out.println("Error: invalid input/input could not be found. Please try again.");
		}
	}


}
