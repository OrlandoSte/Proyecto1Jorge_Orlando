var nocturno = false;

function cambiarTema() {

    const body = document.getElementById("body")

    if (nocturno) {
        nocturno = false;
        body.style.backgroundColor = "#74eaff70";

        const titulo = document.getElementById("titulo")
        titulo.style.backgroundColor = "#5372F0"
        titulo.style.color = "black"

        const objetos = document.getElementsByClassName("card");
        for (let index = 0; index < objetos.length; index++) {
         const element = objetos[index];
         element.style.backgroundColor = "gray"
     
         }
    } else {
        nocturno = true;
        body.style.backgroundColor = "#192229";
        
        const titulo = document.getElementById("titulo")
        titulo.style.backgroundColor = "#2A3B47"
        titulo.style.color = "#fff"

        const objetos = document.getElementsByClassName("card");
        for (let index = 0; index < objetos.length; index++) {
         const element = objetos[index];
         element.style.backgroundColor = "#2A3B47"
     
         }
    } 
}