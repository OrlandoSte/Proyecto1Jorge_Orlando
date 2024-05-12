		var nocturno = false;

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

