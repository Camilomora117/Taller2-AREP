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

//Usamos la promesa con then
countCows.then(result => {
    console.log(result);
}).catch(error => {
    console.log(error);
}).finally(() => console.log('Finally'));