function prikazi(){

var element = document.getElementById("poruka1");
var ime = document.getElementById("ime");
var prezime = document.getElementById("prezimert");
element.innerHTML="Dobar dan "+ime.value+" "+prezime.value;
};

function suma() {
    
    var element = document.getElementById("poruka2");
    var inputA = document.getElementById("inputA");
    var inputB = document.getElementById("inputB");
    var suma = parseInt(inputA.value)+parseInt(inputB.value);
    element.innerHTML = "suma je "+suma;
};