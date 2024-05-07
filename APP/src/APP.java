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

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.IOException;

	public class APP {
		
		public static void main(String[] args) {
			// TODO Auto-generated method stub

			APP oobj_test_http_xml = new APP();
			oobj_test_http_xml.get_response();

		}
		
		public void get_response() {
			
			try {
			
		
			String api_key="e802f5714c9e4729848142736242504";
			String ciudad = "Alcaniz";
			String dias = "7";
			String url = "https://api.weatherapi.com/v1/forecast.xml?key="+ api_key +"&q=" + ciudad + "&days="+ dias +"&aqi=no&alerts=no\\r\\n";
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
	         
	         String cabeceraHtml = """
	                 <html>
	                 <body>
	             """ ;
	         
	         String piesHtml = """
	         		</body>
	                 </html>
	                 """;
	         
	         String cuerpoHtml = "";
	         
			NodeList localizacion = doc.getElementsByTagName("location");
			System.out.println(localizacion.getLength());
			if(localizacion.getLength() > 0) {
				Element err = (Element) localizacion.item(0);
				System.out.println("Ciudad: "+err.getElementsByTagName("name").item(0).getTextContent());
				System.out.println("Region: "+err.getElementsByTagName("region").item(0).getTextContent());
			}
			
			NodeList fore = doc.getElementsByTagName("day");
			int numDay = fore.getLength();
			if(numDay > 0) {
				

				for(int i=0; i<numDay;i++) {
					cuerpoHtml += "<section id=\""+ i +"\">\n";
					Element err = (Element) fore.item(i);
					

					String maxTemp = err.getElementsByTagName("maxtemp_c").item(0).getTextContent();
					String minTemp = err.getElementsByTagName("mintemp_c").item(0).getTextContent();
					
					cuerpoHtml += "<h3> Día  " + (i+1) + " </h3>\n";
					cuerpoHtml += "<p>Temperatura máxima: <b>" + maxTemp + "</b></p>\n";
					cuerpoHtml += "<p>Temperatura mínima: <b>" + minTemp + "</b></p>\n";
					cuerpoHtml += "</section>\n";
				}
				
				
			String htmlCompleto = cabeceraHtml + cuerpoHtml + piesHtml;
			System.out.println(htmlCompleto);

			}
			
			} catch (Exception e) {
				System.out.println("Algo salio mal");
			}
		}
		
	}
