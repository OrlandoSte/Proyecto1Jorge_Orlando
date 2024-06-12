				
		
		var nocturno = false;
		let ciudBuscada ="Alcañiz";								//Declaracion de variables globales para poder usrse en cualquier lado
		let contenido = document.getElementById("insertarC");
				
		/** Creacion de la funcion buscar para poder cambiar la ciudad de la que se sacan los datos */
		function buscar(){
			ciudBuscada = document.getElementById("buscar").value
			console.log(ciudBuscada)
			contenido.innerHTML=""
			cargaJson()
		}


		/** Funcion que se encarga de la conexion con el json y la de añadir los elementos necesarios al HTML */
		async function cargaJson() {

			let url = "https://api.weatherapi.com/v1/forecast.json?key=e802f5714c9e4729848142736242504&q="+ciudBuscada+"&days=7&aqi=no&alerts=no";
			let response = await fetch(url);
			
			fetch(url)
        		.then(response => {
				if (!response.ok) {
					throw new Error('Error en el fetch' + response.status);
				}
           		return response.json();
        		})
			
			.then(data => {
			data.forecast.forecastday.forEach(dia => {
		
				let diaAc = dia.date;
				let tempMin = dia.day.mintemp_c;
				let tempMax = dia.day.maxtemp_c;
				let hume = dia.day.avghumidity
				
				
				
				let cajas = document.getElementById("days-forecast");

				let lineas = document.createElement("li")
				lineas.className = "card";

				let t3 = document.createElement("h3")
				t3.className = "textos";

				let t6 = document.createElement("h6")
				t6.className = "textos";

				let t62 = document.createElement("h6")
				t62.className = "textos";

				let t63 = document.createElement("h6")
				t63.className = "textos"

				t3.textContent = "( "+diaAc+" )"
				t6.textContent = "Temp min: "+tempMin+"ºC"
				t62.textContent = "Temp max: "+tempMax+"ºC"
				t63.textContent = "Humedad: "+hume+"%"

				lineas.appendChild(t3);
				lineas.appendChild(t62);
				lineas.appendChild(t6);
				lineas.appendChild(t63);

				contenido.appendChild(lineas);

			});

			let infoAct = document.getElementById("infor")
			infoAct.innerHTML=""

			let ciudadA = data.location.name
			let fechaA = data.current.last_updated
			let tempAct = data.current.temp_c
			let viento = data.current.wind_kph
			let humeA = data.current.humidity

			let t2 = document.createElement("h2");
			t2.textContent = ciudadA + " (" + fechaA + ")";
			t2.id = "ciud"
		
			let h61 = document.createElement("h6");
			h61.textContent = "Temperatura: "+tempAct + "°C";
			h61.className = "textos"
			
			let h62 = document.createElement("h6");
			h62.textContent = "Viento: "+viento + " Km/h";
			h62.className = "textos"

			let h63 = document.createElement("h6");
			h63.textContent ="Humedad: "+ humeA + "%";
			h63.className = "textos"
		
			
			infoAct.appendChild(t2);
			infoAct.appendChild(h61);
			infoAct.appendChild(h62);
			infoAct.appendChild(h63);

		});
	}

	/** Un evento que se encarga de ecuchar y llamar la funcion de cargaJson() al acabar la misma */

	document.addEventListener("DOMContentLoaded", function() {
		cargaJson();
	})


	/** Funcion encargada de ir cambiando los estilos al darle el boton a la que esta asignado con un "onclick="cambiarTema()" */
		function cambiarTema() {

		    const body = document.getElementById("body")

		    if (nocturno) {
		        nocturno = false;
		        body.style.backgroundColor = "#74eaff70";

		        const titulo = document.getElementById("titulo")
		        titulo.style.backgroundColor = "#5372F0"
		        titulo.style.color = "black"

	            const ciudad = document.getElementById("ciud")
		        ciudad.style.color = "black"

	            const diaAct = document.getElementsByClassName("current-weather");
	            for (let index = 0; index < diaAct.length; index++) {
	                const element = diaAct[index];
	                element.style.backgroundColor = "#5372F0"
	            }

		        const objetos = document.getElementsByClassName("card");
		        for (let index = 0; index < objetos.length; index++) {
		         const element = objetos[index];
		         element.style.backgroundColor = "#afb9c1"
		        }

	            const textos = document.getElementsByClassName("textos");
	            for (let index = 0; index < textos.length; index++) {
	                const element = textos[index];
	                element.style.color = "black"
	            }

		    } else {
		        nocturno = true;
		        body.style.backgroundColor = "#192229";

		        const titulo = document.getElementById("titulo")
		        titulo.style.backgroundColor = "#2A3B47"
		        titulo.style.color = "#fff"

	            const ciudad = document.getElementById("ciud")
		        ciudad.style.color = "white"

	            const diaAct = document.getElementsByClassName("current-weather");
	            for (let index = 0; index < diaAct.length; index++) {
	                const element = diaAct[index];
	                element.style.backgroundColor = "#2A3B47"
	            }

		        const objetos = document.getElementsByClassName("card");
		        for (let index = 0; index < objetos.length; index++) {
		         const element = objetos[index];
		         element.style.backgroundColor = "#2A3B47"
			    }

	            const textos = document.getElementsByClassName("textos");
	            for (let index = 0; index < textos.length; index++) {
	                const element = textos[index];
	                element.style.color = "white"
	            }
			}
		}