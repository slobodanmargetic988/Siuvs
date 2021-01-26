
function poruka() {
    var i1 = document.getElementById("text1");
    var i2 = document.getElementById("text2");
    var output1 = document.getElementById("output1");
    output1.innerHTML = output1.innerHTML + " " + i1.value + " " + i2.value;
    i1.value = 3;
}
;

function suma() {
    var n1 = document.getElementById("number1");
    var n2 = document.getElementById("number2");
    var output2 = document.getElementById("output2");
    var suma = document.getElementById("number2").value + n1.value;
    var suma2 = parseInt(n1.value) + parseInt(n2.value);
    output2.innerHTML = "Suma je " + suma + " suma na drugi nacin " + suma2;
}
;

/*  function meni(){
 var dropdownContent = document.getElementById("meniid");
 if (dropdownContent.style.display == "block"){ 
 dropdownContent.style.display = "none";
 } else {
 dropdownContent.style.display = "block"
 }
 }; */
/*
function onama() {
    var dropdownContent = document.getElementById("onamaid");
    if (dropdownContent.style.display == "block") {
        dropdownContent.style.display = "none";
    } else {
        dropdownContent.style.display = "block"
    }
}
;
*/


function prikazi() {
    var dropdown3 = document.getElementById("meniid")
    dropdown3.style.display = "inline-block";
}
;

function hide() {
    var dropdown4 = document.getElementById("meniid")
    dropdown4.style.display = "none";
}

window.onload = function () {
    /*
     var dropdown = document.querySelector(".drop-content");*/

    document.getElementById("meniMainid").addEventListener("mouseover", prikazi);
    document.getElementById("meniMainid").addEventListener("mouseleave", hide);

};

function prvih5() {
document.getElementById("prvih5").style.display= "table"; 
document.getElementById("drugih5").style.display = "none"; 
}
;

function drugih5() {
document.getElementById("prvih5").style.display= "none"; 
document.getElementById("drugih5").style.display = "table"; 
}
;

function prikaziOba() {
document.getElementById("prvih5").style.display= "table"; 
document.getElementById("drugih5").style.display = "table"; 
}


/*
 var d = document.querySelector(".drop-meni");
 d.addEventListener("click", function () {
 if (d.style.display == "block") {
 d.style.display = "none";
 } else {
 d.style.display = "block";
 }
 });
 */