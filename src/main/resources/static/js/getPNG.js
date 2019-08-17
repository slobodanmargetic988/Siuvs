
function getPNG() {
//html2canvas does not have a save file function like jsPDF so we have to make one
    function saveAs(uri, filename) {
        var link = document.createElement('a');
        if (typeof link.download === 'string') {
            link.href = uri;
            link.download = filename;

            //Firefox requires the link to be in the body
            document.body.appendChild(link);

            //simulate click
            link.click();

            //remove the link when done
            document.body.removeChild(link);
        } else {
            window.open(uri);
        }
    }

    //first locate element 0 (the first and only) with the class type px-content which is located on every page. 
    //then set its width to 1200 in order to make a pdf and background color to white to make pdf look nicer
    var downloadwhat = "px-content";
    var content = document.getElementsByClassName(downloadwhat);
    if (typeof content[0] != "undefined") {
        content[0].style.width = "1200px";
       content[0].style.backgroundColor = "white";}

    var table= document.getElementsByTagName("tbody");
    if (typeof table[0] != "undefined") {table[0].style.backgroundColor = "white";}

    // second make a list of all obcets with "panel" and panel-body class and make them a little bit narower 
    // than px-content so they dont look bad inside the PDF
    var panel = document.getElementsByClassName("panel");
    var panelb = document.getElementsByClassName("panel-body");
     for (var i = 0; i < 6; i++) {
    if (typeof panel[i] != "undefined") {panel[i].style.width = "1150px";
        panel[i].style.backgroundColor = "white";}
      if (typeof panelb[i] != "undefined") {panelb[i].style.width = "1150px";
        panelb[i].style.backgroundColor = "white";}  
     }



    //set Canvas_height to 1400 because pxcontent is now 1200 so it will definitly fit. 
    var Canvas_Width = 1200;// $("#"+downloadwhat).width();
    var Canvas_Height = $("." + downloadwhat).height()+20;


    //make PDF a little bigger than canvas so it all fits
    var PDF_Width = Canvas_Width + 30;

    console.log("pravi se canvas:");
    //capture everithing inside the div with class px-content and set it as canvas 
    html2canvas($("." + downloadwhat)[0], {allowTaint: true, imageTimeout: 0, width: Canvas_Width, height: Canvas_Height}).then(function (canvas) {

        var uri = canvas.toDataURL(allowtaint = true);

        saveAs(uri, 'canvas.png');

    });

}
;
	