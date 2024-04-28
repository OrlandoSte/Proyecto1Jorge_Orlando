import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;


import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class APP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		APP oobj_test_http_xml = new APP();
		oobj_test_http_xml.get_response();
		
	}
	
	public void get_response() {
		
		try {
			
		String api_key="e802f5714c9e4729848142736242504";
		
		String url = "http://api.weatherapi.com/v1/forecast.xml?key="+api_key+"&q=Alcañiz&days=1&aqi=no&alerts=no";
		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		int responseCode = con.getResponseCode();
		System.out.println("Response COde : "+ responseCode);
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//System.out.println(response.toString());
		
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new InputSource(new StringReader(response.toString())));
		
		NodeList localizacion = doc.getElementsByTagName("location");
		if(localizacion.getLength() > 0) {
			Element err = (Element) localizacion.item(0);
			System.out.println("Ciudad: "+err.getElementsByTagName("name").item(0).getTextContent());
			System.out.println("Region: "+err.getElementsByTagName("region").item(0).getTextContent());
		}
		
		NodeList fore = doc.getElementsByTagName("day");
		if(fore.getLength() > 0) {
			Element err = (Element) fore.item(0);
			System.out.println("Temperatura max : "+err.getElementsByTagName("maxtemp_c").item(0).getTextContent());
			System.out.println("Temperatura min : "+err.getElementsByTagName("mintemp_c").item(0).getTextContent());
		}
		
		} catch (Exception e) {
			System.out.println("Algo salio mal");
		}
	}
	
}
