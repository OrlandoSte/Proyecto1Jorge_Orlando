import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

public class conexion {
	
	
	public void conexionyHtml() {
		
		try {
		
		File f = new File("Pagina.html");
		FileWriter writer = new FileWriter(f);
		
		//Aqui se monta el url completo del que sacaremos la informacion
		String api_key="e802f5714c9e4729848142736242504";
		String ciudad = "Alcaniz";
		String dias = "7";
		String url = "https://api.weatherapi.com/v1/forecast.xml?key="+ api_key +"&q=" + ciudad + "&days="+ dias +"&aqi=no&alerts=no\\r\\n";
		URL obj = new URL(url);
		
		// Aqui se crea un objeto conexion pasandole la conexion que hemos creado antes
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		
		//Aqui he sacado la fecha de hoy que luego le paso a la caja principal
		LocalDateTime hoy = LocalDateTime.now(); 
         
         int dia =hoy.getDayOfMonth();
         String mes = ""+hoy.getMonthValue();
         String anyo = ""+hoy.getYear();
		
		//Aqui saco el numero que me da la conexion para comprobar que esta bien
		// ya que si sale 400 es que no esta bien y 200 si esta todo correcto
		int responseCode = con.getResponseCode();
		System.out.println("Response COde : "+ responseCode);

		//Aqui se coge la informacion de la conexion HTTPS y se guarda en un StringBufer
		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
		
		//System.out.println(response.toString());
		

		//Aqui lo que se hace es que le pasas el StringBufer de antes al DocumentBuilder que es una libreria que se encarga de montar las 
		// inforamcion para poder exaertla y utilizarla
		Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder()
				.parse(new InputSource(new StringReader(response.toString())));
		
		/* Aqui cogemos la localizacion de la que se sacan los datos*/
		NodeList localizacion = doc.getElementsByTagName("location");
		String ciudadT ="";
		Element err = (Element) localizacion.item(0);
		ciudadT = err.getElementsByTagName("name").item(0).getTextContent();
		
		// Aqui se selecciona el nodo que se llama "current"
		NodeList actual = doc.getElementsByTagName("current");

		/* Aqui sacamos la temperatura actual que hay, los km/h del viento y la humedad del nodo "current"*/
		err = (Element) actual.item(0);		
		String tempAct = err.getElementsByTagName("temp_c").item(0).getTextContent();
		String viento = err.getElementsByTagName("wind_kph").item(0).getTextContent();
		String humedadD = err.getElementsByTagName("humidity").item(0).getTextContent();
		
		// Esto es solo un string en el que le escribo el html que luego escribire
        String cabeceraHtml = """
                 <html>
                  <head>
				    <meta charset="utf-8">
				    <title>Weather App Project JavaScript | CodingNepal</title>
				    <link rel="stylesheet" href="style.css">
				  </head>
                 <body id="body">
                 <h1 id="titulo">El Tiempo</h1>
				    <div class="container">
					    <div class="weather-input">        
             """ ;

		// Esto es otro string del cuerpo del HTML
         String cuerpoHtml = "";
        
         cuerpoHtml += "<button class=\"search-btn\" onclick=\"cambiarTema()\">Cambiar Tema</button>\n";
         cuerpoHtml += "</div>\n";
         cuerpoHtml += "<div class=\"weather-data\">\n";
         cuerpoHtml += "<div class=\"current-weather\">\n";
         cuerpoHtml += "<div class=\"details\">\n";
         cuerpoHtml += "<h2 id=\"ciud\" >"+ciudadT+"( "+dia+"-"+mes+"-"+anyo+" )"+"</h2>\n";
         cuerpoHtml += "<h6 class=\"textos\">Temperatura: "+tempAct+"°C</h6>\n";
         cuerpoHtml += "<h6 class=\"textos\">Viento: "+viento+" Km/h</h6>\n";
         cuerpoHtml += "<h6 class=\"textos\">Humedad: "+humedadD+"%</h6>\n";
         cuerpoHtml += "</div>\n";
         cuerpoHtml += "</div>\n";
         cuerpoHtml += "<div class=\"days-forecast\">\n";
         cuerpoHtml += "<h2 class=\"textos\">Pronostico de 7 dias</h2>\n";
         cuerpoHtml += "<ul class=\"weather-cards\">\n";
         
		// Esto es otro string del foteer del HTML
         String piesHtml = """
         		    		    	</ul>
         		    			</div>
							</div>
						</div>
					</body>
				</html>
				<script src="script.js"></script>
                 """;
         		
        // Aqui selecciono el nodo que se llama day y despues saco con "fore.getLength()" cuantos elementos tiene
		// y a continuacion lo meto en un if para comprobar que tiene elementos y despues voy recorriendo el XML con un for
		NodeList fore = doc.getElementsByTagName("day");
		int numDia = fore.getLength();
		if(numDia > 0) {
			

			for(int i=0; i<numDia;i++) {

				err = (Element) fore.item(i);
				
				String maxTemp = err.getElementsByTagName("maxtemp_c").item(0).getTextContent();
				String minTemp = err.getElementsByTagName("mintemp_c").item(0).getTextContent();
				String hum = err.getElementsByTagName("avghumidity").item(0).getTextContent();
				
				
				
				cuerpoHtml +="<li class=\"card\">\n";
				cuerpoHtml +="<h3 class=\"textos\">( "+dia+"-"+mes+"-"+anyo+" )</h3>\n";
				cuerpoHtml +="<h6 class=\"textos\">Temp max: "+maxTemp+"°C</h6>\n";
				cuerpoHtml +="<h6 class=\"textos\">Temp min: "+minTemp+"°C</h6>\n";
				cuerpoHtml +="<h6 class=\"textos\">Humedad: "+hum+"%</h6>\n";
				cuerpoHtml +="</li>\n";
				dia += 1;
			}
			

		//Aqui se monta el HTML completo y despues lo escribo 
		String htmlCompleto = cabeceraHtml + cuerpoHtml + piesHtml;
		

		writer.write(htmlCompleto);
		writer.close();
		
		url = "Pagina.html";
		File htmlFile = new File(url);
		Desktop.getDesktop().browse(htmlFile.toURI());
		
		System.out.println(htmlCompleto);

		}
		
		} catch (Exception e) {
			System.out.println("Algo salio mal");
		}
	}
}
