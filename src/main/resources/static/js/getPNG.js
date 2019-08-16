
function getPNG() {

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

    //first locate element 0 (the first and only) with the class type px-content which is located on every page. then set its width to 1200 in order to make a pdf
    var downloadwhat = "px-content";
    var content = document.getElementsByClassName(downloadwhat);
    if (typeof content[0] != "undefined") {
        content[0].style.width = "1200px";
    }


    // second make a list of all obcets with "panel" class and make them a little bit narower than px-content so they dont look bad inside the PDF
    var panel = document.getElementsByClassName("panel");
    if (typeof panel[0] != "undefined") {
        panel[0].style.width = "1150px";
    }
    if (typeof panel[1] != "undefined") {
        panel[1].style.width = "1150px";
    }
    if (typeof panel[2] != "undefined") {
        panel[2].style.width = "1150px";
    }
    if (typeof panel[3] != "undefined") {
        panel[3].style.width = "1150px";
    }



    //set Canvas_height to 1400 because pxcontent is now 1200 so it will definitly fit. 
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
        /*
         var pdf = new jsPDF('p', 'pt',  [PDF_Width, PDF_Height]);        
         
         pdf.addImage(uri,  margin, margin, Canvas_Width, Canvas_Height,  'NONE', 0);
         for (var i = 1; i <= totalPDFPages; i++) { 
         pdf.addPage(PDF_Width,PDF_Height);
         pdf.addImage(uri,  margin, -PDF_Height*i+margin, Canvas_Width, Canvas_Height,  'NONE', 0);
         }
         
         pdf.save("HTML-Document.pdf");*/
        saveAs(uri, 'canvas.png');

    });

}
;
	