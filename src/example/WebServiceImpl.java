package example;

import javax.jws.WebService;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.Math;
import java.net.URL;
import org.json.JSONObject;

@WebService(
		endpointInterface = "example.WebServiceInterface",
		portName = "webservicePort",
		serviceName = "WebService")

public class WebServiceImpl implements WebServiceInterface {
	private String APIKey = "6c957e24d064a4170727a198c02a9691";
	private String URLHead = "https://api.openweathermap.org/data/2.5/weather?q=";
	private String URLTail = "&units=metric&appid=";
	
	/*
	 * Greets the client.
	 */
	@Override
	public String greet(String clientName) {
		return "Welcome, " + clientName + ", let's check the weather!";
	}

	/*
	 * Calculates the weather in the client's city.
	 */
	@Override
	public double randTemp(String country, String city) {
		//return a double between -10 to 30.
		double temp = (Math.random()*40) - 10; 
		//round to 2 dp
		temp = temp*100;
		temp = Math.round(temp);
		temp = temp/100;
		return temp;
	}

	/*
	 * Sends request to OpenWeather and returns the celcius temperature of the given location.
	 */
	@Override
	public double getTemp(String country, String city) throws Exception {
		try {
			String link = (URLHead + city + "," + country + URLTail + APIKey);
			String url = readURL(link);
			JSONObject obj = new JSONObject(url);
			if (obj.getInt("cod") != 200) {
				//error in finding data
				throw new Exception();
			}
			//get temperature
	        double temp = obj.getJSONObject("main").getDouble("temp");
	        return temp;
		}
		catch (Exception e) {
			throw new Exception();
		}
		
	}
	
	/*
	 * Sends request to OpenWeather and returns the wind speed of the given location.
	 */
	@Override
	public double getWind(String country, String city) throws Exception {
		try {
			String link = (URLHead + city + "," + country + URLTail + APIKey);
			String url = readURL(link);
			JSONObject obj = new JSONObject(url);
			if (obj.getInt("cod") != 200) {
				//error in finding data
				throw new Exception();
			}
			//get wind speed
	        double wind = obj.getJSONObject("wind").getDouble("speed");
	        return wind;
		}
		catch (Exception e) {
			throw new Exception();
		}
		
	}
	
	/*
	 * Parse the URL.
	 */
	public String readURL(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read); 

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }
	
	
}
