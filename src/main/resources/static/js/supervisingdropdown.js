//document.getElementById("selectprovincecontainer").addEventListener("load", removeElement());
var select = document.getElementById("selectrole");
if (select.value == 'MUP' || select.value == 'UNSELECTED') {
    removeElement()
} else {
    if (select.value == 'DISTRIKT') {
        document.getElementById("selectprovincecontainer").style.display = "none";
    }
    if (select.value == 'PROVINCE') {
        document.getElementById("selectdistriktcontainer").style.display = "none";
    }
}

//select.addEventListener("load", removeElement());


function removeElement() {
    document.getElementById("selectdistriktcontainer").style.display = "none";
    document.getElementById("selectprovincecontainer").style.display = "none";
}

function resetElement(selected) {
    if (selected == 'DISTRIKT') {
        document.getElementById("selectdistriktcontainer").style.display = "block";
        document.getElementById("selectprovincecontainer").style.display = "none";
    }

    if (selected == 'PROVINCE') {
        document.getElementById("selectprovincecontainer").style.display = "block";
        document.getElementById("selectdistriktcontainer").style.display = "none";
    }

    if (selected == 'MUP') {
        removeElement()
    }

    if (selected == 'UNSELECTED') {
        removeElement()
    }
}

;
	