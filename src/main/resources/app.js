//ARCHIVO CODIGO JS

//Promise
const promise = new Promise(function(resolve, reject) {
    resolve('hey!');
});


//Ejemplo
const cows = 9;


//Constructor
const countCows = new Promise(function(resolve, reject) {
    if (cows > 10) {
        resolve(`We have ${cows} cows on the farm`);
    } else {
        reject('There is no cows in the farm');
    }
});

var changeColor = function (){

    boton = document.getElementById("button");

    if (boton.style.color == "black"){

        boton.style.color = "yellow" ;

    }else{
        boton.style.color = "black";
    }

}