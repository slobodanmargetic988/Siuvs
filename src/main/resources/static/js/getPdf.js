
function getPDF() {

    //first locate element 0 (the first and only) with the class type px-content which is located on every page. 
    //then set its width to 1200 in order to make a pdf and background color to white to make pdf look nicer
    var downloadwhat = "px-content";
    var content = document.getElementsByClassName(downloadwhat);
    if (typeof content[0] != "undefined") {
        content[0].style.width = "1200px";
        content[0].style.backgroundColor = "white";

    }
    var table = document.getElementsByTagName("tbody");
    if (typeof table[0] != "undefined") {
        table[0].style.backgroundColor = "white";
    }


    // second make a list of all obcets with "panel" and panel-body class and make them a little bit narower 
    // than px-content so they dont look bad inside the PDF
    var panel = document.getElementsByClassName("panel");
    var panelb = document.getElementsByClassName("panel-body");
    for (var i = 0; i < 6; i++) {
        if (typeof panel[i] != "undefined") {
            panel[i].style.width = "1150px";
            panel[i].style.backgroundColor = "white";
        }
        if (typeof panelb[i] != "undefined") {
            panelb[i].style.width = "1150px";
            panelb[i].style.backgroundColor = "white";
        }
    }




    //set Canvas_height to 14200 because pxcontent is now 1200 so it will definitly fit. 
    var Canvas_Width = 1200;// $("#"+downloadwhat).width();
    var Canvas_Height = $("." + downloadwhat).height();
    var margin = 15;

    //make PDF a little bigger than canvas so it all fits
    var PDF_Width = Canvas_Width + 30;
    var PDF_Height = PDF_Width * 1.2 + 30;
    var totalPDFPages = Math.ceil(Canvas_Height / 1400) - 1; //this is used if we want to set PDF height for making multiple page pdf instead of single page pdf

    console.log("pravi se canvas:");
    //capture everithing inside the div with class px-content and set it as canvas 
    html2canvas($("." + downloadwhat)[0], {allowTaint: true, imageTimeout: 0, width: Canvas_Width, height: Canvas_Height}).then(function (canvas) {

        var uri = canvas.toDataURL(allowtaint = true);

        var pdf = new jsPDF('p', 'pt', [PDF_Width, PDF_Height]);

        pdf.addImage(uri, margin, margin, Canvas_Width, Canvas_Height, 'NONE', 0);
        for (var i = 1; i <= totalPDFPages; i++) {
            pdf.addPage(PDF_Width, PDF_Height);
            pdf.addImage(uri, margin, -PDF_Height * i + margin, Canvas_Width, Canvas_Height, 'NONE', 0);
        }

        pdf.save("HTML-Document.pdf");


    });

}
;
	